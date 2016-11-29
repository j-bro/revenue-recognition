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
            <%@include file="hidden/header-imports.jsp"%>
        </head>

        <body>
            <%@include file="hidden/navbar.jsp"%>
            <div class="container">
                <div class="section">
                    <h3>Contract #${contractBean.id}</h3>
                </div>

                <div class="section">
                    <div class="row">
                        <div class="col s6">
                            <h5>Product: <a href="product?productid=${contractBean.product.id}">${contractBean.product.name}</a></h5>
                            <h5>Total Revenue: ${contractBean.revenue.getAmount()} ${contract.revenue.getCurrency().getCurrencyCode()}</h5>

                            <div class="divider"></div>

                            <h6>Get revenue before a date</h6>
                            <form id="recognized-revenue-form" method="get">
                                <label>Date (yyyy-mm-dd): </label>
                                <input id="date-input" name="date-input" type="date" />
                                <input type="hidden" name="contractid" value="${param.contractid}">
                                <button class="btn waves-effect waves-light" type="submit" name="action">Submit
                                    <i class="material-icons right">send</i>
                                </button>
                            </form>

                            <c:if test="${not empty dateString}">
                                <h5>Revenue before: ${dateString}</h5>
                                <h6>${recognizedRevenueBeforeDate.getAmount()} ${contract.revenue.getCurrency().getCurrencyCode()}</h6>
                            </c:if>
                        </div>
                        <div class="col s6">
                            <h4>Revenue Recognitions</h4>
                            <ul class="collection">
                                <c:forEach var="recognition" items="${contractBean.getRecognitions()}">
                                    <li class="collection-item">${recognition.getAmount().amount()} on ${recognition.getDateString()}</li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </body>
    </c:if>

    <c:if test="${invalidParameters eq true}">
        <h3>Invalid parameters</h3>
    </c:if>


</html>
