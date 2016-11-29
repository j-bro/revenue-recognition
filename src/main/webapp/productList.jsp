<%--
  Created by IntelliJ IDEA.
  User: jeremybrown
  Date: 2016-11-18
  Time: 5:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Products</title>
    <%@include file="hidden/header-imports.jsp"%>
</head>

<body>
<%@include file="hidden/navbar.jsp"%>
    <div class="container">
        <div class="section">
            <h3>Products</h3>
        </div>
        <div class="section">
            <div class="row">
                <div class="col s6">
                    <jsp:useBean id="mapper" class="com.asdf.revenuerecognition.mappers.ProductMapper" scope="request" />
                    <ul class="collection">
                        <c:if test="${empty mapper.findAll()}">
                            <li class="collection-item">No products found</li>
                        </c:if>

                        <c:if test="${not empty mapper.findAll()}">
                            <c:forEach var="product" items="${mapper.findAll()}">
                                <li class="collection-item">
                                    <c:url var="contractUrl" value="product">
                                        <c:param name="productid" value="${product.id}"></c:param>
                                    </c:url>
                                    <a href="${contractUrl}">
                                        [<c:out value="${product.id}"/>] <c:out value="${product.name}"/>
                                    </a>
                                </li>
                            </c:forEach>
                        </c:if>

                    </ul>
                </div>
        </div>

    </div>
</body>

</html>
