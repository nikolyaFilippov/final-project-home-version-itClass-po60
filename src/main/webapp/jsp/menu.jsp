<%@ page import="by.mysite.constants.AppConstants" %>
<%@ page import="by.mysite.constants.JspConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="nav-ul">
  <c:choose>
    <c:when test="${empty user}">
      <li class="nav-li float-left"><a class="active" href='<c:url value="<%= JspConstants.INDEX_JSP %>"/>'>Home</a></li>
      <li class="nav-li"><a href='<c:url value="<%= JspConstants.LOGIN_JSP %>"/>'>Login</a></li>
      <li class="nav-li"><a href='<c:url value="<%= JspConstants.REGISTRATION_JSP %>"/>'>Registration</a></li>
    </c:when>
    <c:otherwise>
      <li class="nav-li float-left"><a class="active" href='<c:url value="<%= JspConstants.HOME_JSP %>"/>'>Home</a></li>
      <li class="nav-li"><a href='<c:url value="<%= AppConstants.LOGOUT_CONTROLLER %>"/>'>Logout</a></li>
    </c:otherwise>
  </c:choose>
</ul>