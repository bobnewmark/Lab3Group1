<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="product-big-title-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="product-bit-title text-center">
                    <h2>Search results</h2>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="single-product-area" ng-controller="ItemController as ctrl" ng-cloak>
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">
            <h2 style="text-align: center" ng-if="ctrl.items.totalElements==0">Ничего не найдено</h2>
            <div class="col-md-3 col-sm-6" ng-repeat="p in ctrl.items.content">
                <div class="single-shop-product">
                    <div class="product-upper">
                        <img ng-src="${contextPath}{{p.mapParameters.icon.value}}" alt="">
                    </div>
                    <h2><a href="${contextPath}/details/{{p.id}}" target="_self">{{p.mapParameters.name.value}}</a></h2>
                    <div class="product-carousel-price" style="text-align: center; vertical-align: middle;">
                        <ins>₴ {{p.mapParameters.price.value}}</ins>
                    </div>

                    <div class="product-option-shop">
                        <a href="#" class="add_to_cart_button"
                           ng-click="(p.mapParameters.quantity.value == '0')||ctrl.buy(p.id)">{{p.mapParameters.quantity.value
                            == '0' ? 'SOLD OUT' : 'Add to cart'}}</a>
                    </div>
                    <sec:authorize access="hasAnyRole('ADMIN')">
                        <div class="admin" data-toggle="modal" data-target="#editModal" ng-click="ctrl.editPage(p.id)">
                            <i
                                    class="fa fa-pencil-square-o admin-edit"></i> edit
                        </div>
                        <div class="admin" ng-click="ctrl.remove(p.id)"><i class="fa fa-times admin-del"></i> delete
                        </div>
                        <div class="admin" data-toggle="modal" data-target="#addModal" ng-click="ctrl.resetAdd()"><i
                                class="fa fa-plus admin-edit"></i> add
                        </div>
                    </sec:authorize>
                </div>
            </div>

        </div>
        <sec:authorize access="hasAnyRole('ADMIN')">
            <jsp:include page="/WEB-INF/views/modal.jsp" flush="true"></jsp:include>
        </sec:authorize>
        <div class="row">
            <div class="col-md-12">
                <div class="product-pagination text-center">
                    <nav>
                        <ul class="pagination">
                            <li>
                                <a href="#" ng-click="ctrl.goToPage(1)" aria-label="First">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li ng-if="ctrl.items.first == false">
                                <a style="cursor: pointer" ng-click="ctrl.goToPage(ctrl.items.number)">{{ctrl.items.number}}</a>
                            </li>
                            <li><a style="cursor: pointer">{{ctrl.items.number+1}}</a></li>
                            <li ng-if="ctrl.items.last == false">
                                <a style="cursor: pointer" ng-click="ctrl.goToPage(ctrl.items.number+2)">{{ctrl.items.number
                                    + 2}}</a></li>
                            <li>
                                <a style="cursor: pointer" ng-click="ctrl.goToPage(ctrl.items.totalPages)"
                                   aria-label="Last">
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