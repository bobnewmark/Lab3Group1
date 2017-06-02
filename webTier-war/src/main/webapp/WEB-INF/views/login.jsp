<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="product-big-title-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="product-bit-title text-center">
                    <h2>Login</h2>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="single-product-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">


            <div class="col-md-12">
                <div class="product-content-right">
                    <div class="woocommerce">


                        <form id="login-form-wrap" action="${contextPath}/j_spring_security_check" class="login collapse in" method="post">


                            <p>If you have shopped with us before, please enter your details in the boxes below. If you are a new customer please proceed to the Billing &amp; Shipping section.</p>
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
                                <label class="inline" for="rememberme"><input type="checkbox" value="forever" id="rememberme" name="rememberme"> Remember me </label>
                            </p>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <p class="lost_password">
                                <a href="#">Lost your password?</a>
                            </p>

                            <div class="clear"></div>
                        </form>



                        <form id="coupon-collapse-wrap2" method="post" class="checkout_coupon collapse">

                            <p class="form-row form-row-first">
                                <input type="text" value="" id="coupon_code2" placeholder="Coupon code" class="input-text" name="coupon_code">
                            </p>

                            <p class="form-row form-row-last">
                                <input type="submit" value="Apply Coupon" name="apply_coupon" class="button">
                            </p>

                            <div class="clear"></div>
                        </form>



                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

