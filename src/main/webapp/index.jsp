<%--
  Created by IntelliJ IDEA.
  User: jeremybrown
  Date: 2016-11-13
  Time: 10:05 PM
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Contracts</title>
    </head>
    <body>
    <jsp:useBean id="mapper" class="com.asdf.revenuerecognition.mappers.ContractMapper" scope="request" />
        <ul>
            <c:forEach var="contract" items="${mapper.findAll()}">
            <li>
                <c:url var="contracturl" value="contract.jsp">
                    <c:param name="contractid" value="${contract.id}"></c:param>
                </c:url>
                <a href="${contracturl}">
                    [<c:out value="${contract.id}"/>] <c:out value="${contract.product}"/> (<c:out value="${contract.revenue.getAmount()}"/>)
                </a>
            </li>
            </c:forEach>
        </ul>
    </body>
</html>
