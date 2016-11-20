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
</head>

<body>
<header>
    <h1>Products</h1>
</header>

<content>
    <jsp:useBean id="mapper" class="com.asdf.revenuerecognition.mappers.ProductMapper" scope="request" />
    <ul>

        <c:forEach var="product" items="${mapper.findAll()}">
            <li>
                <c:url var="contractUrl" value="product">
                    <c:param name="productid" value="${product.id}"></c:param>
                </c:url>
                <a href="${contractUrl}">
                    [<c:out value="${product.id}"/>] <c:out value="${product.name}"/>
                </a>
            </li>
        </c:forEach>

    </ul>
</content>
</body>

</html>
