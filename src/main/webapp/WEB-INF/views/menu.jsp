<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@include file="taglib_imports.jsp" %>
<p align="center">
    <a href="admin/adminPartShoppingItems"><img border="0" src="/resources/img/logovlevo.png" width="29%"></a>
    <img border="0" src="/resources/img/logovpravo.png" width="42%" height="18.62%"></p>

<p align="center"><img border="0" src="/resources/img/oddelovac.bmp" width="73%" height="4"></p>

<p align="center">
    <a target="_top" href="showByCategory/pages/1?category=ALTERNATIVE"><img border="0" src="/resources/img/obchod.png"
                                                                             width="11%" height="4.036%"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

    <a target="_top" href="/top"><img border="0" src="/resources/img/top.png" width="11%" height="4.036%"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

    <a target="_top" href="/onas"><img border="0" src="/resources/img/onas.png" width="11%" height="4.036%"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

    <a target="_top" href="/kontakt"><img border="0" src="/resources/img/kontakt.png" width="11%" height="4.036%"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

    <a target="_top" href="/orderForm"><img border="0" src="/resources/img/kosik.png" width="11%" height="4.036%"></a>
    <c:choose>
        <c:when test="${OrderObj.emptyItems eq false}">
            <a href="/orderForm"><img border="0" src="/resources/img/plny.png" width="2.4%" height="3.9%"></a>
        </c:when>
        <c:otherwise>
            <a href="/orderForm"><img border="0" src="/resources/img/prazdny.png" width="2.4%" height="3.9%"></a>
        </c:otherwise>
    </c:choose>
</p>

<p align="center"><img border="0" src="/resources/img/oddelovac.bmp" width="73%" height="1"></p>
