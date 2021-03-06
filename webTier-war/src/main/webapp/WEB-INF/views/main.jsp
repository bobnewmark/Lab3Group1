<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="slider-area">
    <!-- Slider -->
    <div class="block-slider block-slider4">
        <ul class="" id="bxslider-home4">
            <li>
                <img src="${contextPath}/resources/img/h4-slide.png" alt="Slide">
                <div class="caption-group">
                    <h2 class="caption title">
                        iPhone <span class="primary">6 Plus<strong> Soon!</strong></span>
                    </h2>
                    <h4 class="caption subtitle">Dual SIM</h4>
                    <a class="caption button-radius" href="${contextPath}/shop" target="_self"><span
                            class="icon"></span>Shop now</a>
                </div>
            </li>
            <li><img src="${contextPath}/resources/img/h4-slide2.png" alt="Slide">
                <div class="caption-group">
                    <h2 class="caption title">
                        <span class="primary"><strong>New items soon!</strong></span>
                    </h2>
                    <h4 class="caption subtitle">school supplies & backpacks.*</h4>
                    <a class="caption button-radius" href="${contextPath}/shop" target="_self"><span
                            class="icon"></span>Shop now</a>
                </div>
            </li>
            <li><img src="${contextPath}/resources/img/h4-slide3.png" alt="Slide">
                <div class="caption-group">
                    <h2 class="caption title">
                        Apple <span class="primary">Store Ipod<strong> Coming soon!</strong></span>
                    </h2>
                    <h4 class="caption subtitle"></h4>
                    <a class="caption button-radius" href="${contextPath}/shop" target="_self"><span
                            class="icon"></span>Shop now</a>
                </div>
            </li>
            <li><img src="${contextPath}/resources/img/h4-slide4.png" alt="Slide">
                <div class="caption-group">
                    <h2 class="caption title">
                        Apple <span class="primary">Store Ipod<strong> Next week!</strong></span>
                    </h2>
                    <h4 class="caption subtitle">& Phone</h4>
                    <a class="caption button-radius" href="${contextPath}/shop" target="_self"><span
                            class="icon"></span>Shop now</a>
                </div>
            </li>
        </ul>
    </div>
</div>
<!-- End slider area -->

<div class="maincontent-area" ng-controller="ItemController as ctrl">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="latest-product">
                    <h2 class="section-title">Most popular</h2>
                    <data-owl-carousel class="owl-carousel"
                                       data-options="{navigation: true, pagination: true, rewindNav : true}">
                        <div owl-carousel-item="" ng-repeat="p in ctrl.items" id="items" class="item single-product"
                             style="padding: 10px">
                            <div class="product-f-image">
                                <img ng-src="${contextPath}{{p.mapParameters.icon.value}}" alt="">
                                <div class="product-hover">
                                    <a href="#" class="add-to-cart-link"
                                       ng-click="(p.mapParameters.quantity.value == '0')||ctrl.buy(p.id)"><i
                                            class="fa fa-shopping-cart"></i>
                                        {{p.mapParameters.quantity.value == '0' ? 'SOLD OUT' : 'Add to cart'}}</a>
                                    <a href="${contextPath}/details/{{p.id}}" class="view-details-link"
                                       target="_self"><i
                                            class="fa fa-link"></i> See details</a>
                                </div>
                            </div>
                            <h2 ng-bind="p.mapParameters.name.value"></h2>
                            <div class="product-carousel-price">
                                <div style="text-align: center; vertical-align: middle;">
                                    <ins ng-bind="'₴ '+p.mapParameters.price.value"></ins>
                                </div>
                                <sec:authorize access="hasAnyRole('ADMIN')">
                                    <div class="admin" data-toggle="modal" data-target="#editModal"
                                         ng-click="ctrl.edit(p.id)"><i class="fa fa-pencil-square-o admin-edit"></i>
                                        edit
                                    </div>
                                    <div class="admin" ng-click="ctrl.remove(p.id)"><i
                                            class="fa fa-times admin-del"></i>
                                        delete
                                    </div>
                                    <div class="admin" data-toggle="modal" data-target="#addModal"
                                         ng-click="ctrl.resetAdd()"><i class="fa fa-plus admin-edit"></i> add
                                    </div>
                                </sec:authorize>
                            </div>
                        </div>
                    </data-owl-carousel>
                    <sec:authorize access="hasAnyRole('ADMIN')">
                        <jsp:include page="/WEB-INF/views/modal.jsp" flush="true"></jsp:include>
                    </sec:authorize>
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