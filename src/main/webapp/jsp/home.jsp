<%@ page import="by.mysite.constants.JspConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Home Page</title>
        <link rel="stylesheet" href="<%=JspConstants.PATH_TO_STYLES %>">
    </head>
    <body>
        <jsp:include page="<%=JspConstants.MENU_JSP %>"/>
        <h2>User Info</h2>
        <p>login ${user.login}</p>
        <p>name ${user.name}</p>
        <p>email ${user.email}</p>
    </body>
</html>