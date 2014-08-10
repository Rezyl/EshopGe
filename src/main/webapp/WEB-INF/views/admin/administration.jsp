<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<%@include file="../taglib_imports.jsp" %>

<head>
    <meta http-equiv="Content-Language" content="cs">
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1250">
    <title>Administrační část</title>
</head>

<body>
<h1>Administrační část</h1>
<a href="adminPartShoppingItems">Sklad</a>
<a href="saveShoppingItemForm">Vložit nové zboží</a>
<a href="adminPartOrders">Objednávky</a>
<hr>
<div align="center">
    <c:if test="${typeOfPage == 'item'}">
        <jsp:include page="showShoppingItems.jsp"/>
    </c:if>
    <c:if test="${typeOfPage == 'order'}">
        <jsp:include page="showOrders.jsp"/>
    </c:if>
</div>
</body>

</html>
