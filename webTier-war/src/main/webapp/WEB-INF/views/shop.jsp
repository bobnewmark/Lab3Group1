<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="product-big-title-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="product-bit-title text-center">
                    <h2>Shop</h2>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="single-product-area"  ng-controller="ItemController as ctrl">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">

            <div class="col-md-3 col-sm-6" ng-repeat="p in ctrl.items">
                    <div class="single-shop-product">
                        <div class="product-upper">
                            <img src="${contextPath}{{p.mapParameters.icon.value}}" alt="">
                        </div>
                        <h2><a href="${contextPath}/details/{{p.id}}" target="_self">{{p.mapParameters.name.value}}</a></h2>
                        <div class="product-carousel-price">
                            <ins>Price: {{p.mapParameters.price.value}}</ins>
                            <del></del>
                        </div>

                        <div class="product-option-shop">
                            <a href="#" class="add_to_cart_button" ng-click="ctrl.buy(p.id)">Add to cart</a>
                        </div>
                    </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <div class="product-pagination text-center">
                    <nav>
                        <ul class="pagination">
                            <li>
                                <a href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li>
                                <a href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>
