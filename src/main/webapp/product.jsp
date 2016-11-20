<%--
  Created by IntelliJ IDEA.
  User: jeremybrown
  Date: 2016-11-18
  Time: 5:59 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
        <title>Product #${productBean.id}</title>
    </head>

    <body>
    <header>
        <h1>Product #${productBean.id}</h1>
    </header>

    <content>
        <ul>
            <li>ID is: ${productBean.id}</li>
            <li>Name is: ${productBean.name}</li>
        </ul>
    </content>
    </body>

</html>
