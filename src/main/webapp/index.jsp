<%--
  Created by IntelliJ IDEA.
  User: jeremybrown
  Date: 2016-11-18
  Time: 5:55 PM
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Revenue Recognition</title>
        <%@include file="hidden/header-imports.jsp"%>
    </head>

    <body>
    <%@include file="hidden/navbar.jsp"%>
        <div class="container">
            <div class="section">
                <div class="row">
                    <div class="col s6">
                        <c:url var="contractsUrl" value="contractList.jsp" />
                        <h3 class="center"><a href="${contractsUrl}">Contracts</a></h3>
                    </div>
                    <div class="col s6">
                        <c:url var="productsUrl" value="productList.jsp" />
                        <h3 class="center"><a href="${productsUrl}">Products</a></h3>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
