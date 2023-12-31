<%@ page import="by.mysite.constants.AppConstants" %>
<%@ page import="by.mysite.constants.JspConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Registration page</title>
    <link rel="stylesheet" href="<%=JspConstants.PATH_TO_STYLES %>">
</head>
<body>
    <jsp:include page="<%=JspConstants.MENU_JSP %>"/>
    <img class="default-image" src="<%=JspConstants.BACKGROUND_IMAGE %>" alt="pizza">
    <div class="form-box">
        <h2>Registration</h2>
        <form method="post" action="<c:url value="<%=AppConstants.REGISTRATION_CONTROLLER%>"/>">
            <input name="<%= JspConstants.LOGIN_PARAM%>" placeholder="Login">
            <input name="<%= JspConstants.NAME_PARAM%>" placeholder="Name">
            <input type="password" name="<%= JspConstants.PASSWORD_PARAM%>" placeholder="Password">
            <input name="<%= JspConstants.EMAIL_PARAM%>" placeholder="eMail">
            <input type="submit" value="Register">
            <c:if test="${not empty message}">
                <h2 class="error">${message}</h2>
            </c:if>
        </form>
    </div>
</body>
</html>