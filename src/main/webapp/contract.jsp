<%--
  Created by IntelliJ IDEA.
  User: jeremybrown
  Date: 2016-11-18
  Time: 11:41 AM
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.asdf.revenuerecognition.util.DateCalculator" %>
<html>

    <head>
        <title>Contract #${contractBean.id}</title>
    </head>

    <body>
        <header>
            <h1>Contract #${contractBean.id}</h1>
        </header>

        <content>
            <ul>
                <li>ID is: ${contractBean.id}</li>
                <li>Product is: ${contractBean.product.name}</li>
                <li>Revenue is: ${contractBean.revenue.getAmount()} ${contract.revenue.getCurrency().getCurrencyCode()}</li>
                <li>Revenue Recognitions:
                    <ul>
                    <c:forEach var="recognition" items="${contractBean.getRecognitions()}">
                        <li>${recognition.getAmount().amount()} on ${recognition.getDateString()}</li>
                    </c:forEach>
                    </ul>
                </li>
            </ul>
        </content>
    </body>

</html>
