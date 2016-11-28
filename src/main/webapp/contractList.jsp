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
    <header>
        <a href="index.jsp">
            <h1>Revenue Recognition</h1>
        </a>
        <h2>Contracts</h2>
    </header>

        <content>
            <section>
                <jsp:useBean id="mapper" class="com.asdf.revenuerecognition.mappers.ContractMapper" scope="request" />
                <ul>

                    <c:forEach var="contract" items="${mapper.findAll()}">
                    <li>
                        <c:url var="contractUrl" value="contract">
                            <c:param name="contractid" value="${contract.id}"></c:param>
                        </c:url>
                        <a href="${contractUrl}">
                            [<c:out value="${contract.id}"/>] <c:out value="${contract.product.name}"/> (<c:out value="${contract.revenue.getAmount()} ${contract.revenue.getCurrency().getCurrencyCode()}"/>)
                        </a>
                    </li>
                    </c:forEach>

                </ul>
            </section>

            <section>
                <h3>Create contract</h3>
                <jsp:useBean id="productMapper" class="com.asdf.revenuerecognition.mappers.ProductMapper" scope="request" />
                <form id="create-contact-form" action="contract" method="post">
                    <ul>
                        <li>
                            <label>Product concerned</label>
                            <select name="productid">
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
                            <button type="submit">Create</button>
                        </li>
                    </ul>
                </form>
            </section>
        </content>
    </body>

</html>
