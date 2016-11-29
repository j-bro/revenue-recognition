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
        <%@include file="hidden/header-imports.jsp"%>
    </head>

    <body>
    <%@include file="hidden/navbar.jsp"%>
        <div class="container">

            <div class="section">
                <h3>Product #${productBean.id}</h3>
            </div>

            <div class="section">
                <div class="row">
                    <div class="col s12">
                        <h5>Name: ${productBean.name}</h5>
                        <h5>Recognition strategy: ${productBean.recognitionStrategy.toString()}</h5>
                    </div>
                </div>
            </div>

        </div>
    </body>

</html>
