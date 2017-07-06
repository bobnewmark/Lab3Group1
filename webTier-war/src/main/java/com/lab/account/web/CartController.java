package com.lab.account.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.database.entities.*;
import com.shop.database.entities.Object;
import com.shop.database.services.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <code>CartController</code> is a controller to handle cart-related operations.
 */
@Controller
public class CartController {
    @Autowired
    private ObjectService objectService;
    @Autowired
    private ObjectTypeService objectTypeService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private ParameterService parameterService;
    @Autowired
    private AttributeService attributeService;
    @Autowired
    private ReferenceService referenceService;

    private final static Logger LOGGER = Logger.getLogger(CartController.class);

    /**
     * Method for adding new items to user shopping cart
     * @param itemId object_id of a product
     * @return number of items in the cart
     */
    @RequestMapping(value = "/addToCart", method = RequestMethod.GET)
    public @ResponseBody
    String addToCart(@RequestParam int itemId) {
        Object cart = securityService.getCart();
        if (cart == null) {
            return "0";
        }
        ObjectType cartOT = objectTypeService.findByName("cart");
        Reference item = new Reference(cart, objectService.findById(itemId), "item", attributeService.findByNameAndObjectType("item", cartOT));
        int amountOfItemInCart = 0;
        for (Map.Entry<String, Parameter> entry : item.getRefObject().getMapParameters().entrySet()) {
            if (entry.getKey().equals("quantity")) {
                amountOfItemInCart = Integer.parseInt(entry.getValue().getValue());
            }
        }
        if (referenceService.findByObjectAndRefObject(cart, item.getRefObject()).size() < amountOfItemInCart) {
            cart.getReferences().add(item);
            referenceService.save(item);
        }
        return String.valueOf(cart.getReferences().size());
    }

    /**
     * Method to take user to the shopping cart page
     * @param model carries cart as inner jsp
     * @return outer page with it
     */
    @RequestMapping(value = {"/cart"})
    public String cart(Model model) {
        model.addAttribute("current", "/WEB-INF/views/cart.jsp");
        return "index";
    }

    /**
     * Method for displaying the content of the shopping cart
     * @return shopping cart of current user
     */
    @RequestMapping(value = {"/showCart"}, method = RequestMethod.GET)
    public ResponseEntity<Object> cartContent() {
        Object cart = securityService.getCart();
        if (cart == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        if (cart.getReferences().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Reference ref: cart.getReferences()) {
            int availableInShop = Integer.parseInt(ref.getRefObject().getMapParameters().get("quantity").getValue());
            if (referenceService.findByObjectAndRefObject(cart, ref.getRefObject()).size() > availableInShop) {
                for (int i = 0; i < referenceService.findByObjectAndRefObject(cart, ref.getRefObject()).size() - availableInShop ; i++) {
                    referenceService.delete(referenceService.findByObjectAndRefObject(cart, ref.getRefObject()).get(0));
                }
            }
        }
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    /**
     * Method to remove items from shopping cart
     * @param id is object_id of an item user wants to remove
     * @return no content
     */
    @RequestMapping(value = "/showCart/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> removeFromCart(@PathVariable("id") int id) {
        Object cart = securityService.getCart();
        List<Reference> refToDelete = referenceService.findByObjectAndRefObject(cart, objectService.findById(id));
        for (Reference ref : refToDelete) {
            referenceService.delete(ref);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Method to get current number of items in the shopping cart
     * @return number of items in cart
     */
    @RequestMapping(value = {"/cartIndex"}, method = RequestMethod.GET)
    public ResponseEntity<String> cartIndex() {
        Object cart = securityService.getCart();
        String result = String.valueOf(cart == null ? 0 : cart.getReferences().size());
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    /**
     * Performing a checkout. Decreasing number of items user has bought. This method
     * also controls the actual amount of every item to buy. User cannot buy more
     * than is available at the moment.
     * @param checkoutMap object_ids and quantities of items to buy
     * @return no content
     */
    @RequestMapping(value = {"/checkout"}, method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> checkout(@RequestBody String checkoutMap) {
        Object cart = securityService.getCart();
        Map<Integer, Integer> itemsToBuy = new HashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            itemsToBuy = mapper.readValue(checkoutMap, new TypeReference<HashMap<Integer, Integer>>() {
            });
        } catch (IOException e) {
            LOGGER.error("Cannot proceed with checkout, ", e);
        }

        // Checking if customer is trying to buy too much
        boolean buyingTooMuch = false;
        for (Map.Entry<Integer, Integer> entry : itemsToBuy.entrySet()) {
            Object item = objectService.findById(entry.getKey());
            Attribute itemQuantity = attributeService.findByNameAndObjectType("quantity", item.getObjectType());
            Parameter quantityToChange = parameterService.findByObjectAndAttribute(item.getId(), itemQuantity.getId());
            int wasInShop = Integer.parseInt(quantityToChange.getValue());
            if (entry.getValue() > wasInShop) {
                buyingTooMuch = true;
                for (Reference r : cart.getReferences()) {
                    if (entry.getKey() == r.getRefObject().getId()) {
                        if (referenceService.findByObjectAndRefObject(cart, r.getRefObject()).size() > wasInShop) {
                            for (int i = 0; i < referenceService.findByObjectAndRefObject(cart, r.getRefObject()).size() - wasInShop; i++) {
                                referenceService.delete(referenceService.findByObjectAndRefObject(cart, r.getRefObject()).get(0));
                            }
                        }
                    }
                }
            }
        }
        // If shop doesn't have enough items customer gets a notification
        if (buyingTooMuch) {
            Map<String, String> map = new HashMap<>();
            map.put("message", "Trying to buy too much!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        // Otherwise customer buys items - quantity decreases
        else {
            for (Map.Entry<Integer, Integer> entry : itemsToBuy.entrySet()) {
                Object item = objectService.findById(entry.getKey());
                Attribute itemQuantity = attributeService.findByNameAndObjectType("quantity", item.getObjectType());
                Parameter quantityToChange = parameterService.findByObjectAndAttribute(item.getId(), itemQuantity.getId());
                int wasInShop = Integer.parseInt(quantityToChange.getValue());
                quantityToChange.setValue(String.valueOf(wasInShop - entry.getValue()));
                parameterService.save(quantityToChange);
            }
        }
        // All references are removed
        List<Reference> allInCart = cart.getReferences();
        for (Reference r : allInCart) {
            referenceService.delete(r);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
