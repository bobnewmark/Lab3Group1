<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="product-big-title-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="product-bit-title text-center">
                    <h2>Contacts</h2>
                </div>
            </div>
        </div>
    </div>
</div>
<sec:authorize access="hasAnyRole('ADMIN')">
    <div ng-controller="ItemController as ctrl">
        <jsp:include page="/WEB-INF/views/modal.jsp" flush="true"></jsp:include>
    </div>
</sec:authorize>
<div class="single-product-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="product-content-right">
                    <div class="col-sm-12">
                        <div class="product-inner">
                            <h2 class="product-name">Lab3Group1</h2>
                            <div role="tabpanel">
                                <div class="tab-content">
                                    <div role="tabpanel" class="tab-pane fade in active" id="home">
                                        <p>&nbsp;</p>
                                        <p>Hello world!</p>
                                        <p>You can find source code for this project <b><a
                                                href="https://github.com/bobnewmark/Lab3Group1">here</a></b>.</p>
                                        <p><b><i>#SpringData&nbsp;&nbsp;#SpringSecurity&nbsp;&nbsp;#JavaEE</i></b></p>
                                        <p>&nbsp;</p>
                                        <p>&nbsp;</p>
                                        <p>&nbsp;</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
