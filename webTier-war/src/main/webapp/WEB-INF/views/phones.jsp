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
<body ng-app="myApp" class="ng-cloak">


<div class="tablecontainer">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>ID.</th>
            <th>Brand</th>

            <th width="20%"></th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="u in ctrl.phones">
            <td><span ng-bind="u.id"></span></td>

        </tr>
        </tbody>
    </table>
</div>



</body>
</html>
