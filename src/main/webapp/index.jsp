<%@ page import="by.mysite.constants.JspConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Pizza Hot</title>
        <link rel="stylesheet" href="<%=JspConstants.PATH_TO_STYLES %>">
    </head>
    <body>
        <jsp:include page="<%=JspConstants.MENU_JSP %>"/>
        <img class="default-image" src="<%=JspConstants.BACKGROUND_IMAGE %>" alt="pizza">
        <h1 style="position: absolute; top: 50%; width: 100%; text-align: center"> The best and fast pizza </h1>
    </body>
</html>