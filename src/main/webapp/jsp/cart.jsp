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
          <c:when test="${ not empty orderItems}">
            <h2>Yours order items: </h2>
                <c:forEach var="item" items="${orderItems}">
                    <div class="cart-item-container">
                        <img class="cart-img" src="/img/${item.item.name}.jpg">
                        <h3 class="cart-text">You ordered  ${item.quantity}  ${item.item.name} by ${item.item.price} byn. Amount is ${item.quantity * item.item.price}</h3>
                        <form method="post" action="<c:url value="<%=AppConstants.CART_CONTROLLER%>"/>">
                            <input type="hidden" name="<%= JspConstants.CARD_ACTION_PARAM %>" value="removeFromCart">
                            <input type="hidden" name="<%= JspConstants.FOOD_ID_PARAM %>" value="${item.item.id}">
                            <input type="hidden" name="<%= JspConstants.FOOD_TYPE_PARAM %>" value="${item.item.type}">
                            <input type="hidden" name="<%= JspConstants.FOOD_NAME_PARAM %>" value="${item.item.name}">
                            <input type="hidden" name="<%= JspConstants.FOOD_PRICE_PARAM %>" value="${item.item.price}">
                            <input type="hidden" name="<%= JspConstants.FOOD_QUANTITY_PARAM %>" value="${item.quantity}">
                            <input type="submit" value="Remove from Cart">
                        </form>
                    </div>
                </c:forEach>
            <div class="order-container">
                <form method="post" action="<c:url value="<%=AppConstants.ORDER_CONTROLLER%>"/>">
                    <input type="text" name="<%= JspConstants.ADDRESS_PARAM %>" placeholder="Delivery address" required>
                    <input type="submit" value="Submit order">
                </form>
            </div>
          </c:when>
          <c:otherwise>
            <p>You have no items in the order</p>
          </c:otherwise>
        </c:choose>
    </body>
</html>