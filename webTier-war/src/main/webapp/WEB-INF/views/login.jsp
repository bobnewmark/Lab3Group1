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
                              name="loginForm" class="login collapse in" method="post" ng-controller="loginController as ctrl">
                            <h2>login</h2>
                            <%--<span>${message}</span>--%>
                            <p class="form-row form-row-last">
                                <label for="email">e-mail<span class="required">*</span>
                                </label>
                            <div class="input-group" ng-class="{ 'has-error' : loginForm.email.$invalid && !loginForm.email.$pristine }">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                                <input id="email" type="text" name="j_username" class="input-text" ng-model="ctrl.item.parameters[0]" required>
                            </div>
                            <p ng-show="loginForm.email.$invalid && !loginForm.email.$pristine" class="help-block">введите логин</p>
                            </p>
                            <p class="form-row form-row-last">
                                <label for="password">Пароль<span class="required">*</span>
                                </label>
                            <div class="input-group" ng-class="{ 'has-error' : loginForm.password.$invalid && !loginForm.password.$pristine }">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                <input id="password" type="password" name="j_password" class="input-text" ng-model="ctrl.item.parameters[1]" required>
                            </div>
                            <p ng-show="loginForm.password.$invalid && !loginForm.password.$pristine" class="help-block">введите пароль</p>
                            </p>
                            <p ng-show="{{status==1}}" style="color: red">Вы ввели неправильный логин либо пароль</p>
                            <p class="form-row">
                                <button type="submit" class="btn btn-primary" ng-disabled="loginForm.$invalid">Логин</button>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </p>

                            <div class="clear"></div>
                        </form>

                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="product-content-right">
                    <div class="woocommerce" ng-controller="mainController as ctrl">
                        <form ng-submit="ctrl.submit()" name="userForm" class="form-horizontal" novalidate>
                            <h2>Registration</h2>
                            <span>${message}</span>
                            <p class="form-row form-row-last">
                                <label for="email">e-mail<span class="required">*</span>
                                </label>
                            <div class="input-group" ng-class="{ 'has-error' : userForm.email.$invalid && !userForm.email.$pristine }">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                            <input type="email" name="email" class="input-text" ng-model="ctrl.item.parameters[0].value" required>
                            </div>
                            <p ng-show="userForm.email.$invalid && !userForm.email.$pristine" class="help-block">вы ввели не правильный email</p>
                            </p>
                            <p class="form-row form-row-last">
                                <label for="password">Пароль<span class="required">*</span>
                                </label>
                            <div class="input-group" ng-class="{ 'has-error' : userForm.password.$invalid && !userForm.password.$pristine }">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                <input type="password" name="password" class="input-text" ng-model="ctrl.item.parameters[1].value" ng-minlength="6" ng-maxlength="12" required>
                            </div>
                            <p ng-show="userForm.password.$error.minlength" class="help-block">Ваш пароль короткий</p>
                            <p ng-show="userForm.password.$error.maxlength" class="help-block">Ваш пароль длинный</p>
                            </p>
                            <p class="form-row form-row-last">
                                <label for="password">Подтвердите пароль<span class="required">*</span>
                                </label>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                <input type="password" id="confirmpass" name="confirmpassword" class="input-text" ng-model="confirmpassword" data-password-verify="ctrl.item.parameters[1].value" required>
                            </div>
                            <p ng-show="userForm.confirmpassword.$error.passwordVerify" class="help-block">Пароли не совпадают</p>
                            </p>
                            <p ng-show="{{statusreg==1}}" style="color: red">Пользователь с таким логином уже существует</p>
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
