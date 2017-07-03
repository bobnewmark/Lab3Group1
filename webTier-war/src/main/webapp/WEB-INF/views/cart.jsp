<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="product-big-title-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="product-bit-title text-center">
                    <h2>Shopping Cart</h2>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End Page title area -->


<div class="single-product-area" ng-controller="CartController as ctrl">

    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="product-content-right">
                    <div class="woocommerce">
                        <form method="post" ng-submit="ctrl.checkout()">
                            <table cellspacing="0" id="cartTable" class="shop_table cart">
                                <thead>
                                <tr>
                                    <th class="product-remove">&nbsp;</th>
                                    <th class="product-thumbnail">&nbsp;</th>
                                    <th class="product-name">Product</th>
                                    <th class="product-price">Price</th>
                                    <th class="product-quantity">Quantity</th>
                                    <th class="product-subtotal">Total</th>
                                </tr>
                                </thead>
                                <tbody>

                                <tr class="cart_item" ng-repeat="item in ctrl.items.references | unique: 'name'">
                                    <td class="product-remove">
                                        <a title="Remove this item" ng-click="ctrl.remove(item.refObject.id)"
                                           class="remove" href="#">×</a>
                                    </td>

                                    <td class="product-thumbnail">
                                        <a href="${contextPath}/details/{{item.refObject.id}}" target="_self"><img width="145"
                                                                                                    height="145"
                                                                                                    alt="poster_1_up"
                                                                                                    class="shop_thumbnail"
                                                                                                    src="${contextPath}{{item.refObject.mapParameters.icon.value}}"></a>
                                    </td>

                                    <td class="product-name" ng-model="item.num" ng-init="item.num=item.refObject.id">
                                        <a href="${contextPath}/details/{{item.refObject.id}}"
                                           ng-bind="item.refObject.mapParameters.name.value" target="_self"></a>
                                    </td>

                                    <td class="product-price">
                                        <input type="number" align="middle" readonly="true" ng-model="item.cost"
                                               ng-init="item.cost=((item.refObject.mapParameters.price.value + 0) / 10)"
                                               value="{{item.refObject.mapParameters.price.value}}" size="4"
                                               class="input-text qty text">
                                    </td>

                                    <td class="product-quantity">
                                        <div class="quantity buttons_added">
                                            <input type="number" ng-model="item.qty"
                                                   ng-init="item.qty = ctrl.getCount(item.refObject.id)" size="4"
                                                   class="input-text qty text" min="1"
                                                   max="{{item.refObject.mapParameters.quantity.value}}">
                                        </div>
                                    </td>

                                    <td class="product-subtotal">
                                        <span class="amount">{{item.cost * item.qty}} </span>
                                    </td>
                                </tr>

                                <tr class="cart_item">
                                    <td class="product-remove">
                                        <a title="Remove this item" class="remove" href="#"></a>
                                    </td>

                                    <td class="product-thumbnail"></td>

                                    <td class="product-name">
                                        TOTAL FOR ALL ITEMS:
                                    </td>

                                    <td class="product-price">
                                        <span class="amount"></span>
                                    </td>

                                    <td class="product-quantity">

                                    </td>

                                    <td class="product-subtotal">
                                        <span class="amount">{{ctrl.total()}}</span>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="actions" colspan="6">

                                        <input type="submit" value="Checkout" name="proceed"
                                               class="checkout-button button alt wc-forward">
                                        <p ng-show="tooMuch==1" style="color: red"><br />Oops, not enough items in the shop. Please, try again...</p>
                                    </td>
                                </tr>

                                </tbody>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>