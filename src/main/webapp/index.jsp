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
    </head>

    <body>
        <header>
            <h1>Revenue Recognition</h1>
        </header>

        <content>
            <ul>
                <li>
                    <c:url var="contractsUrl" value="contractList.jsp" />
                    <a href="${contractsUrl}">Contracts</a>
                </li>
                <li>
                    <c:url var="productsUrl" value="productList.jsp" />
                    <a href="${productsUrl}">Products</a>
                </li>
            </ul>
        </content>
    </body>
</html>
