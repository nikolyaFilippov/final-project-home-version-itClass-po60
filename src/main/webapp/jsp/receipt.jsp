<%@ page import="by.mysite.constants.AppConstants" %>
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
      <c:if test="${not empty orderReceipt}">
         <div class="receipt-container">
            ${orderReceipt}
         </div>
      </c:if>
  </body>
</html>