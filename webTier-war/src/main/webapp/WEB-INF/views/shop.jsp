<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div ng-controller="ItemController as ctrl">
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
    <script>
        $(function () {

            $('#slide-submenu').on('click', function () {
                $(this).closest('.list-group').fadeOut('slide', function () {
                    $('.mini-submenu').fadeIn();
                });

            });

            $('.mini-submenu').on('click', function () {
                $(this).next('.list-group').toggle('slide');
                $('.mini-submenu').hide();
            })
        })
    </script>

    <div class="col-sm-1 col-md-1 sidebar">
        <div class="mini-submenu">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </div>
        <div class="list-group">
        <span href="#" class="list-group-item active">
            Products
            <span class="pull-right" id="slide-submenu">
                <i class="fa fa-times"></i>
            </span>
        </span>
                    <a href="#" class="list-group-item" ng-repeat="t in ctrl.types" ng-if="t.objectType.product==true" ng-click="ctrl.getProducts(t.objectType.name)" >
                        <i class="{{t.objectType.icon}}"></i> {{t.objectType.name}} <%--<span class="badge">{{$index+1}}</span>--%>
                    </a>
            <%--<a href="#" class="list-group-item">
                <i class="fa fa-search"></i> Lorem ipsum
            </a>
            <a href="#" class="list-group-item">
                <i class="fa fa-user"></i> Lorem ipsum
            </a>
            <a href="#" class="list-group-item">
                <i class="fa fa-folder-open-o"></i> Lorem ipsum <span class="badge">14</span>
            </a>
            <a href="#" class="list-group-item">
                <i class="fa fa-bar-chart-o"></i> Lorem ipsumr <span class="badge">14</span>
            </a>
            <a href="#" class="list-group-item">
                <i class="fa fa-envelope"></i> Lorem ipsum
            </a>--%>
        </div>
    </div>
    <div class="single-product-area">
        <%-- <div class="container-fluid">
             <div class="row">
                 <div class="col-sm-3 col-lg-2">
                     <nav class="navbar navbar-default navbar-fixed-side">
                         <h4>Продукты</h4>
                     </nav>
                 </div>
                 <div class="col-sm-9 col-lg-10" ng-repeat="t in ctrl.types">
                         <div ng-if="t.objectType.product==true">
                             <span ng-model="t.objectType.name">{{t.objectType.name}}</span>
                         </div>
                 </div>
             </div>
         </div>--%>
        <div class="zigzag-bottom"></div>
        <div class="container">
            <div class="row">

                <div class="col-md-3 col-sm-6" ng-repeat="p in ctrl.items.content">
                    <div class="single-shop-product">
                        <div class="product-upper">
                            <img ng-src="${contextPath}{{p.mapParameters.icon.value}}" alt="">
                        </div>
                        <h2><a href="${contextPath}/details/{{p.id}}" target="_self">{{p.mapParameters.name.value}}</a>
                        </h2>
                        <div class="product-carousel-price">
                            <ins ng-bind="p.mapParameters.price.attribute.name+': '+p.mapParameters.price.value"></ins>
                            <del></del>
                        </div>

                        <div class="product-option-shop">
                            <a href="#" class="add_to_cart_button"
                               ng-click="(p.mapParameters.quantity.value == '0')||ctrl.buy(p.id)">{{p.mapParameters.quantity.value
                                == '0' ? 'SOLD OUT' : 'Add to cart'}}</a>
                        </div>
                        <div class="admin" data-toggle="modal" data-target="#editModal" ng-click="ctrl.editPage(p.id)">
                            <i
                                    class="fa fa-pencil-square-o admin-edit"></i> edit
                        </div>
                        <div class="admin" ng-click="ctrl.remove(p.id)"><i class="fa fa-times admin-del"></i> delete
                        </div>
                        <div class="admin" data-toggle="modal" data-target="#addModal" ng-click="ctrl.resetAdd()"><i
                                class="fa fa-plus admin-edit"></i> add
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="/WEB-INF/views/modal.jsp" flush="true"></jsp:include>
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
                                <li ng-if="ctrl.items.first == false"><a style="cursor: pointer"
                                                                         ng-click="ctrl.goToPage(ctrl.items.number)">{{ctrl.items.number}}</a>
                                </li>
                                <li><a style="cursor: pointer">{{ctrl.items.number+1}}</a></li>
                                <li ng-if="ctrl.items.last == false"><a style="cursor: pointer"
                                                                        ng-click="ctrl.goToPage(ctrl.items.number+2)">{{ctrl.items.number
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
</div>