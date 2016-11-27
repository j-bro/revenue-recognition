<%--
  Created by IntelliJ IDEA.
  User: jeremybrown
  Date: 2016-11-18
  Time: 11:41 AM
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <c:if test="${invalidParameters ne true}">
        <head>
            <title>Contract #${contractBean.id}</title>
        </head>

        <body>
        <header>
            <h1>Contract #${contractBean.id}</h1>
        </header>

        <content>
            <section>
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
            </section>

            <section>
                <h3>Get revenue before a date</h3>
                <form id="recognized-revenue-form" method="get">
                    <label>Date (yyyy-mm-dd): </label>
                    <input id="date-input" name="date-input" type="date" />
                    <input type="hidden" name="contractid" value="${param.contractid}">
                    <button type="submit">Submit</button>
                </form>

                <c:if test="${not empty dateString}">
                    <h4>Revenue before date ${dateString}</h4>
                    <h4>${recognizedRevenueBeforeDate.getAmount()} ${contract.revenue.getCurrency().getCurrencyCode()}</h4>
                </c:if>
            </section>
        </content>
        </body>
    </c:if>

    <c:if test="${invalidParameters eq true}">
        <h1>Invalid parameters</h1>
    </c:if>


</html>
