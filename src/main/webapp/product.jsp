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
        <a href="index.jsp">
            <h1>Revenue Recognition</h1>
        </a>
        <h2>Product #${productBean.id}</h2>
    </header>

    <content>
        <ul>
            <li>ID: ${productBean.id}</li>
            <li>Name: ${productBean.name}</li>
            <li>Recognition strategy: ${productBean.recognitionStrategy.toString()}</li>
        </ul>
    </content>
    </body>

</html>
