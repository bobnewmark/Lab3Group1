<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="product-big-title-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="product-bit-title text-center">
                    <h2>Login/Registration</h2>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="single-product-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="product-content-right">
                    <div class="woocommerce">


                        <form id="login-form-wrap" action="${contextPath}/j_spring_security_check"
                              class="login collapse in" method="post">


                            <h2>login</h2>
                            <span>${message}</span>
                            <p class="form-row form-row-first">
                                <label for="username">Username or email <span class="required">*</span>
                                </label>
                                <input type="text" id="username" name="username" class="input-text">
                            </p>
                            <p class="form-row form-row-last">
                                <label for="password">Password <span class="required">*</span>
                                </label>
                                <input type="password" id="password" name="password" class="input-text">
                            </p>
                            <div class="clear"><span>${error}</span></div>


                            <p class="form-row">
                                <input type="submit" value="Login" name="login" class="button">
                                <label class="inline" for="rememberme"><input type="checkbox" value="forever"
                                                                              id="rememberme" name="rememberme">
                                    Remember me </label>
                            </p>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <p class="lost_password">
                                <a href="#">Lost your password?</a>
                            </p>

                            <div class="clear"></div>
                        </form>


                        <form id="coupon-collapse-wrap2" method="post" class="checkout_coupon collapse">

                            <p class="form-row form-row-first">
                                <input type="text" value="" id="coupon_code2" placeholder="Coupon code"
                                       class="input-text" name="coupon_code">
                            </p>

                            <p class="form-row form-row-last">
                                <input type="submit" value="Apply Coupon" name="apply_coupon" class="button">
                            </p>

                            <div class="clear"></div>
                        </form>


                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="product-content-right">
                    <div class="woocommerce" ng-controller="mainController">


                        <form id="registration-form-wrap" action="${contextPath}/registration"
                             method="post" name="userForm" ng-submit="submitForm()" novalidate>


                            <h2>Registration</h2>
                            <span>${message}</span>
                            <p class="form-row form-row-last">
                                <label for="password">e-mail<span class="required">*</span>
                                </label>
                            <div class="input-group" ng-class="{ 'has-error' : userForm.email.$invalid && !userForm.email.$pristine }">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                            <input type="email" name="email" class="input-text" ng-model="user.email" required>
                            </div>
                            <p ng-show="userForm.email.$invalid && !userForm.email.$pristine" class="help-block">вы ввели не правильный email</p>
                            </p>
                            <p class="form-row form-row-last">
                                <label for="password">Пароль<span class="required">*</span>
                                </label>
                            <div class="input-group" ng-class="{ 'has-error' : userForm.password.$invalid && !userForm.password.$pristine }">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                <input type="password" name="password" class="input-text" ng-model="user.password" ng-minlength="6" ng-maxlength="12" required>
                            </div>
                            <p ng-show="userForm.password.$error.minlength" class="help-block">Ваш пароль короткий</p>
                            <p ng-show="userForm.password.$error.maxlength" class="help-block">Ваш пароль длинный</p>
                            </p>
                            <p class="form-row form-row-last">
                                <label for="password">Подтвердите пароль<span class="required">*</span>
                                </label>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                <input type="password" id="confirmpass" name="confirmpassword" class="input-text" ng-model="user.confirmpassword" data-password-verify="user.password" required>
                            </div>
                            <p ng-show="userForm.confirmpassword.$error.passwordVerify" class="help-block">Пароли не совпадают</p>
                            </p>
                            <div class="clear"><span>${error}</span></div>
                            <button type="submit" class="btn btn-primary" ng-disabled="userForm.$invalid">Регистрация</button>
                            <input type="hidden"  class="btn btn-primary" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <div class="clear"></div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <title>AngularJS Form Validation</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

    <link rel='stylesheet prefetch' href='http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css'>

    <link rel="stylesheet" href="${contextPath}/resources/css/style.css">


</head>

<body>--%>
<%--<div ng-app="validationApp" ng-controller="mainController">
    <div class="container">
        <div class="row">

            <div class="col-sm-6">
                <!-- FORM ============ -->

                <form name="userForm" ng-submit="submitForm()" novalidate>

                    <!-- NAME -->
                    <div class="form-group" ng-class="{ 'has-error' : userForm.name.$invalid && !userForm.name.$pristine }">
                        <label>Name</label>
                        <input type="text" name="name" class="form-control" ng-model="user.name" required>
                        <p ng-show="userForm.name.$invalid && !userForm.name.$pristine" class="help-block">You name is required.</p>
                    </div>

                    <!-- USERNAME -->
                    <div class="form-group" ng-class="{ 'has-error' : userForm.username.$invalid && !userForm.username.$pristine }">
                        <label>Username</label>
                        <input type="text" name="username" class="form-control" ng-model="user.username" ng-minlength="3" ng-maxlength="8">
                        <p ng-show="userForm.username.$error.minlength" class="help-block">Username is too short.</p>
                        <p ng-show="userForm.username.$error.maxlength" class="help-block">Username is too long.</p>
                    </div>

                    <!-- EMAIL -->
                    <div class="form-group" ng-class="{ 'has-error' : userForm.email.$invalid && !userForm.email.$pristine }">
                        <label>Email</label>
                        <input type="email" name="email" class="form-control" ng-model="user.email">
                        <p ng-show="userForm.email.$invalid && !userForm.email.$pristine" class="help-block">Enter a valid email.</p>
                    </div>

                    <button type="submit" class="btn btn-primary" ng-disabled="userForm.$invalid">Submit</button>

                </form>
            </div>
        </div>
    </div>
</div>--%>
<%--    <script src='http://cdnjs.cloudflare.com/ajax/libs/angular.js/1.3.14/angular.min.js'></script>

    <script src="${contextPath}/resources/js/index.js"></script>

</body>
</html>--%>
