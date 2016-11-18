<%--
  Created by IntelliJ IDEA.
  User: jeremybrown
  Date: 2016-11-18
  Time: 11:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <title>Title</title>
    </head>

    <body>
        <header>
            <h1>Contract #${contractBean.id}</h1>
        </header>

        <content>
            <ul>
                <li>ID is: ${contractBean.id}</li>
                <li>Product is: ${contractBean.product}</li>
                <li>Revenue is: ${contractBean.revenue.getAmount()} ${contract.revenue.getCurrency().getCurrencyCode()}</li>
            </ul>
        </content>
    </body>

</html>
