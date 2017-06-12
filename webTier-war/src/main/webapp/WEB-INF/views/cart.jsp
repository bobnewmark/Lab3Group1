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


<div class="single-product-area" ng-controller="CartController as ctrl" >
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="product-content-right">
                    <div class="woocommerce">
                        <form method="post" action="#">
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



                                    <tr class="cart_item" ng-repeat="p in ctrl.items">
                                        <td class="product-remove">
                                            <a title="Remove this item" class="remove" href="#">×</a>
                                        </td>

                                        <td class="product-thumbnail">
                                            <a href="${contextPath}/details/{{p.id}}"><img width="145" height="145"
                                                                                               alt="poster_1_up"
                                                                                               class="shop_thumbnail"
                                                                                           src="${contextPath}{{p.mapParameters.icon.value}}"></a>
                                        </td>

                                        <td class="product-name">
                                            <a href="${contextPath}/details/{{p.id}}" ng-bind="p.mapParameters.name.value"></a>
                                        </td>

                                        <td class="product-price">
                                            <input type="number" ng-model="item.cost" ng-init="item.cost=(p.mapParameters.price.value + 0)" class="amount" >
                                            <%--<span class="amount" ng-bind="p.mapParameters.price.value"></span>--%>
                                        </td>

                                        <td class="product-quantity">
                                            <div class="quantity buttons_added">
                                                <input type="number" ng-model="item.qty" ng-init="item.qty=1" size="4" class="input-text qty text" min="1">
                                                <%--<input type="button" class="minus" value="-">--%>
                                                <%--<input type="number" size="4" class="input-text qty text" title="Qty"--%>
                                                       <%--value="1" min="0" step="1">--%>
                                                <%--<input type="button" class="plus" value="+">--%>
                                            </div>
                                        </td>

                                        <td class="product-subtotal">
                                            <span class="amount">{{item.cost * item.qty}}</span>
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
                                        <span class="amount">total price</span>
                                    </td>
                                </tr>


                                <tr>
                                    <td class="actions" colspan="6">

                                        <input type="submit" value="Checkout" name="proceed"
                                               class="checkout-button button alt wc-forward">
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