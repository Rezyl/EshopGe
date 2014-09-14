<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@include file="taglib_imports.jsp" %>
<p align="center">
    <a href="admin/adminPartShoppingItems"><img border="0" src="/resources/img/logovlevo.png"></a>
    <img border="0" src="/resources/img/logovpravo.png" width="570" height="143"></p>

<p align="center"><img border="0" src="/resources/img/oddelovac.bmp" width="980" height="4"></p>

<p align="center">
    <a target="_top" href="showByCategory?category=LONG_SOCKS"><img border="0" src="/resources/img/obchod.png"
                                                                    width="144" height="31"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

    <a target="_top" href="top"><img border="0" src="/resources/img/top.png" width="144" height="31"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

    <a target="_top" href="onas"><img border="0" src="/resources/img/onas.png" width="144" height="31"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

    <a target="_top" href="kontakt"><img border="0" src="/resources/img/kontakt.png" width="144" height="31"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

    <a target="_top" href="orderForm"><img border="0" src="/resources/img/kosik.png" width="144" height="31"></a>
    <c:choose>
        <c:when test="${OrderObj.emptyItems eq false}">
            <a href="orderForm"><img border="0" src="/resources/img/plny.png" width="34" height="30"></a>
        </c:when>
        <c:otherwise>
            <a href="orderForm"><img border="0" src="/resources/img/prazdny.png" width="34" height="30"></a>
        </c:otherwise>
    </c:choose>
</p>

<p align="center"><img border="0" src="/resources/img/oddelovac.bmp" width="980" height="1"></p>
