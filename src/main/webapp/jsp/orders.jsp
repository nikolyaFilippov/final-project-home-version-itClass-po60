<%@ page import="by.mysite.constants.AppConstants" %>
<%@ page import="by.mysite.constants.JspConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
   <head>
      <title>Cart Page</title>
      <link rel="stylesheet" href="<%=JspConstants.PATH_TO_STYLES %>">
   </head>
   <body>
      <jsp:include page="<%=JspConstants.MENU_JSP %>"/>
      <h2>Hello ${user.name}</h2>
      <c:choose>
         <c:when test="${not empty ordersList}">
            <h2>Yours orders:</h2>
            <c:forEach var="order" items="${ordersList}">
               <div class = "order-list-container">
                  <h3>${order.date} you ordered delivery to address ${order.address}, Id of order is ${order.id}</h3>
                     <form method="post" action="<c:url value="<%=AppConstants.PRINT_ORDER_CONTROLLER%>"/>">
                        <input type="hidden" name="<%= JspConstants.ORDER_ID_ATTR %>" value="${order.id}">
                        <input type="submit" value="Print receipt">
                     </form>
               </div>
            </c:forEach>
         </c:when>
         <c:otherwise>
            <p>You have no orders at present</p>
         </c:otherwise>
      </c:choose>
   </body>
</html>