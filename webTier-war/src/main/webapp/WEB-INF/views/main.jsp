<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="slider-area">
    <!-- Slider -->
    <div class="block-slider block-slider4">
        <ul class="" id="bxslider-home4">
            <li>
                <img src="${contextPath}/resources/img/h4-slide.png" alt="Slide">
                <div class="caption-group">
                    <h2 class="caption title">
                        iPhone <span class="primary">6 <strong>Plus</strong></span>
                    </h2>
                    <h4 class="caption subtitle">Dual SIM</h4>
                    <a class="caption button-radius" href="#"><span class="icon"></span>Shop now</a>
                </div>
            </li>
            <li><img src="${contextPath}/resources/img/h4-slide2.png" alt="Slide">
                <div class="caption-group">
                    <h2 class="caption title">
                        by one, get one <span class="primary">50% <strong>off</strong></span>
                    </h2>
                    <h4 class="caption subtitle">school supplies & backpacks.*</h4>
                    <a class="caption button-radius" href="#"><span class="icon"></span>Shop now</a>
                </div>
            </li>
            <li><img src="${contextPath}/resources/img/h4-slide3.png" alt="Slide">
                <div class="caption-group">
                    <h2 class="caption title">
                        Apple <span class="primary">Store <strong>Ipod</strong></span>
                    </h2>
                    <h4 class="caption subtitle">Select Item</h4>
                    <a class="caption button-radius" href="#"><span class="icon"></span>Shop now</a>
                </div>
            </li>
            <li><img src="${contextPath}/resources/img/h4-slide4.png" alt="Slide">
                <div class="caption-group">
                    <h2 class="caption title">
                        Apple <span class="primary">Store <strong>Ipod</strong></span>
                    </h2>
                    <h4 class="caption subtitle">& Phone</h4>
                    <a class="caption button-radius" href="#"><span class="icon"></span>Shop now</a>
                </div>
            </li>
        </ul>
    </div>
    <!-- ./Slider -->
</div>

<!-- End slider area -->


<div class="maincontent-area" ng-controller="ItemController as ctrl">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="latest-product">
                    <h2 class="section-title">Latest Products</h2>
                   <%-- <div class="product-carousel" ng-cloak>
                        <div class="owl-stage-outer">
                            <div class="owl-stage">--%>

                                <%--<div class="single-product">--%>
                                <%--<div class="product-f-image">--%>
                                <%--<img src="${contextPath}/resources/img/product-1.jpg" alt="">--%>
                                <%--<div class="product-hover">--%>
                                <%--<a href="#" class="add-to-cart-link"><i class="fa fa-shopping-cart"></i> Add to cart</a>--%>
                                <%--<a href="single-product.html" class="view-details-link"><i class="fa fa-link"></i> See details</a>--%>
                                <%--</div>--%>
                                <%--</div>--%>

                                <%--<h2><a href="single-product.html">Samsung Galaxy s5- 2015</a></h2>--%>

                                <%--<div class="product-carousel-price">--%>
                                <%--<ins>$700.00</ins> <del>$100.00</del>--%>
                                <%--</div>--%>
                                <%--</div>--%>

                                    <data-owl-carousel class="owl-carousel" data-options="{navigation: true, pagination: false, rewindNav : false}">
                                        <div owl-carousel-item="" ng-repeat="p in ctrl.items" class="item">
                                            <div class="product-f-image">
                                                <img src="${contextPath}{{p.icon}}" alt="">
                                                <div class="product-hover">
                                                    <a href="#" class="add-to-cart-link"><i class="fa fa-shopping-cart"></i>
                                                        Add to
                                                        cart</a>
                                                    <a href="${contextPath}/details/{{p.id}}" class="view-details-link"><i
                                                            class="fa fa-link"></i> See details</a>
                                                </div>
                                            </div>

                                            <h2 ng-bind="p.name"></h2>
                                            <div class="product-carousel-price">
                                                <ins ng-bind="p.price"></ins>
                                                <del></del>
                                            </div>
                                        </div>
                                    </data-owl-carousel>




                          <%--  </div>
                        </div>

                    </div>--%>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End main content area -->

<div class="brands-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="brand-wrapper">
                    <div class="brand-list">
                        <img src="${contextPath}/resources/img/brand1.png" alt="">
                        <img src="${contextPath}/resources/img/brand2.png" alt="">
                        <img src="${contextPath}/resources/img/brand3.png" alt="">
                        <img src="${contextPath}/resources/img/brand4.png" alt="">
                        <img src="${contextPath}/resources/img/brand5.png" alt="">
                        <img src="${contextPath}/resources/img/brand6.png" alt="">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End brands area -->
