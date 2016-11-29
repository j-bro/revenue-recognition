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
        <%@include file="hidden/header-imports.jsp"%>
    </head>

    <body>
    <%@include file="hidden/navbar.jsp"%>
        <div class="container">
            <div class="section">
                <h3>Contracts</h3>
            </div>
            <div class="section">
                <div class="row">
                    <div class="col s6">
                        <h5>Existing contracts</h5>
                        <jsp:useBean id="mapper" class="com.asdf.revenuerecognition.mappers.ContractMapper" scope="request" />
                        <ul class="collection">
                            <c:forEach var="contract" items="${mapper.findAll()}">
                                <li class="collection-item">
                                    <c:url var="contractUrl" value="contract">
                                        <c:param name="contractid" value="${contract.id}"></c:param>
                                    </c:url>
                                    <a href="${contractUrl}">
                                        [<c:out value="${contract.id}"/>] <c:out value="${contract.product.name}"/> (<c:out value="${contract.revenue.getAmount()} ${contract.revenue.getCurrency().getCurrencyCode()}"/>)
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>

                    <div class="col s6">
                        <h5>Create contract</h5>
                        <jsp:useBean id="productMapper" class="com.asdf.revenuerecognition.mappers.ProductMapper" scope="request" />
                        <form id="create-contact-form" action="contract" method="post">
                            <ul>
                                <li>
                                    <label>Product concerned</label>
                                    <select name="productid" id="productid" class="browser-default">
                                        <c:forEach var="product" items="${productMapper.findAll()}">
                                            <option value="${product.getId()}">${product.getName()}</option>
                                        </c:forEach>
                                    </select>
                                </li>
                                <li>
                                    <label>Total contact revenue (dollars)</label>
                                    <input type="number" name="contract-revenue" id="contract-revenue" />
                                </li>
                                <li>
                                    <label>Date signed</label>
                                    <input id="date-input" name="date-input" type="date" />
                                </li>
                                <li>
                                    <button class="btn waves-effect waves-light" type="submit" name="action">Create
                                        <i class="material-icons right">send</i>
                                    </button>
                                </li>
                            </ul>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </body>

</html>
