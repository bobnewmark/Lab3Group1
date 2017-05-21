<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Phones</title>
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
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
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
                        <li ng-repeat="u in ctrl.phones"><a href="#"><span ng-bind="u.brand"></span><span
                                ng-bind="u.model"></span></a></li>
                    </ul>
                </li>
                <li><a href="/laba/phones">Phones</a></li>
            </ul>
            <ul class="nav navbar-nav pull-right">
                <li class="pull-right"><a href="#" data-toggle="modal" data-target="#myModal">Вход</a></li>
            </ul>
            <div class="col-sm-3 col-md-3  pull-right">
                <form class="navbar-form" role="search">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search" name="q">
                        <div class="input-group-btn">
                            <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i>
                            </button>
                        </div>
                    </div>
                </form>
            </div>

        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<table>
    <tr>
        <th>Details</th>
        <th>Picture</th>
    </tr>

    <c:forEach items="${allPhones}" var="phone">
        <tr>


            <td>
                    ${phone.name}<br>
                    ${phone.objectType.name}<br>
            </td>
            <c:forEach items="${phone.parameters}" var="paramm">
                <td>
                    <c:if test="${paramm.attribute.name=='icon'}">
                        icon!!!
                        <div>
                            <img src="${paramm.value}" alt="...oops, cannot find picture...">
                        </div>
                    </c:if>
                </td>
            </c:forEach>
        </tr>
    </c:forEach>

</table>


</body>
</html>