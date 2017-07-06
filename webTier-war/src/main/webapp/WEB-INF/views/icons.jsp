<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="form-group col-md-12">
    <div class="col-md-3" ng-repeat="i in ctrl.icons">
        <a href="#" ng-click="ctrl.setIcon(i.name)" >
            <i  style="font-size: 24px" class="{{i.name}}"></i>
        </a>
    </div>
</div>