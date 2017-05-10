<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Online shop</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-resource.js"></script>
    <script src="<c:url value='/resources/js/app.js' />"></script>
    <script src="<c:url value='/resources/js/user_service.js' />"></script>
    <script src="<c:url value='/resources/js/user_controller.js' />"></script>
    <link href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/css/common.css' />" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <script src="https://code.jquery.com/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>
<body ng-app="myApp" class="ng-cloak" ng-controller="PhoneController as ctrl">
<nav class="navbar navbar-inverse" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Lab 3</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Главная</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Каталог<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li ng-repeat="u in ctrl.phones"><a href="#"><span ng-bind="u.brand"></span><span ng-bind="u.model"></span></a></li>
                    </ul>
                </li>
                <li><a href="#">Контакты</a></li>
            </ul>
            <ul class="nav navbar-nav pull-right">
                <li class="pull-right"><a href="#" data-toggle="modal" data-target="#myModal">Вход</a></li>
            </ul>
            <div class="col-sm-3 col-md-3  pull-right">
                <form class="navbar-form" role="search">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search" name="q">
                        <div class="input-group-btn">
                            <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                        </div>
                    </div>
                </form>
            </div>

        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header"><button class="close" type="button" data-dismiss="modal">×</button>
                <h4 class="modal-title">Заголовок окна</h4>
            </div>
            <div class="modal-body">
                    <form method="POST" action="${contextPath}/login" class="form-signin">
                        <h2 class="form-heading">Log in</h2>

                        <div class="form-group ${error != null ? 'has-error' : ''}">
                            <span>${message}</span>
                            <input name="username" type="text" class="form-control" placeholder="Username"
                                   autofocus="true"/>
                            <input name="password" type="password" class="form-control" placeholder="Password"/>
                            <span>${error}</span>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
                            <h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4>
                        </div>

                    </form>
            </div>
            <div class="modal-footer"><button class="btn btn-default" type="button" data-dismiss="modal">Закрыть</button></div>
        </div>
    </div>
</div>
<div id="testCarousel" class="carousel slide" data-ride="carousel">
    <!-- Индикаторы карусели -->
    <ol class="carousel-indicators">
        <li data-target="#testCarousel" data-slide-to="0" ></li>
        <li data-target="#testCarousel" data-slide-to="1" class="active"></li>
        <li data-target="#testCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Слайды карусели -->
    <div class="carousel-inner">
        <!-- Слайд 1 -->
        <div class="item ">
            <img style="width: 100%" src="${contextPath}/resources/images/1.jpg" alt="...">
        </div>
        <!-- Слайд 2 -->
        <div class="item active">
            <img style="width: 100%" src="${contextPath}/resources/images/2.jpg" alt="...">
        </div>
        <!-- Слайд 3 -->
        <div class="item">
            <img style="width: 100%" src="${contextPath}/resources/images/3.jpg" alt="...">
        </div>
    </div>

    <!-- Навигация карусели (следующий или предыдущий слайд) -->
    <a class="left carousel-control" href="#testCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left"></span>
    </a>
    <a class="right carousel-control" href="#testCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right"></span>
    </a>
</div>
<div class="generic-container">
    <div class="row">

        <!-- 1 Изображение -->
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="${contextPath}/resources/images/samsung.jpg" alt="...">
                <div class="caption">
                    <h3>Заголовок...</h3>
                    <p>Контент...</p>
                    <p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>
                </div>
            </div>
        </div>

        <!--...-->
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="${contextPath}/resources/images/huawei.jpg" alt="...">
                <div class="caption">
                    <h3>Заголовок...</h3>
                    <p>Контент...</p>
                    <p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="${contextPath}/resources/images/htc.jpg" alt="...">
                <div class="caption">
                    <h3>Заголовок...</h3>
                    <p>Контент...</p>
                    <p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>
                </div>
            </div>
        </div>

    </div>
   <%-- <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Phone Registration Form </span></div>
        <div class="formcontainer">
            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                <input type="hidden" ng-model="ctrl.phone.id"/>
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="brand">Name</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.phone.brand" id="brand"
                                   class="brand form-control input-sm" placeholder="Enter brand" required
                                   ng-minlength="3"/>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="model">Model</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.phone.model" id="model" class="form-control input-sm"
                                   placeholder="Enter Model"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="price">Price</label>
                        <div class="col-md-7">
                            <input type="number" ng-model="ctrl.phone.price" id="price"
                                   class="price form-control input-sm" placeholder="Enter Price"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-actions floatRight">
                        <input type="submit" value="{{!ctrl.phone.id ? 'Add' : 'Update'}}"
                               class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm"
                                ng-disabled="myForm.$pristine">Reset Form
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Phones </span></div>
        <div class="tablecontainer">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>ID.</th>
                    <th>Brand</th>
                    <th>Model</th>
                    <th>Price</th>
                    <th width="20%"></th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="u in ctrl.phones">
                    <td><span ng-bind="u.id"></span></td>
                    <td><span ng-bind="u.brand"></span></td>
                    <td><span ng-bind="u.model"></span></td>
                    <td><span ng-bind="u.price"></span></td>
                    <td>
                        <button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width">Edit
                        </button>
                        <button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remove
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>--%>
</div>

</body>
</html>