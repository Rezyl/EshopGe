<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@include file="taglib_imports.jsp" %>
<body bgcolor="#F9F9F6" style="background-color: #F9F9F6">
<script src="http://code.jquery.com/jquery.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<p align="center"><font face="Times New Roman" style="font-size: 13pt"><b><font color="#07A9C3"><span class="dot">&#9679;</span>&nbsp;1.
    NÁKUPNÍ KOŠÍK <span class="dot">&#9679;</span></font></b></font></p>

<div align="center">
    <c:if test="${! empty SEARCH_ITEM_RESULTS_KEY}">
        <table>
            <thead>
            <tr>
                <th>Produkt</th>
                <th>Velikost</th>
                <th>Počet kusů</th>
                <th>Cena</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${SEARCH_ITEM_RESULTS_KEY}">
                <tr>
                    <td width="200"><img src="${item.imageFilePath}" alt="${item.name}" width="214" height="233"/></td>
                    <td width="200"><c:out value="${item.size}"></c:out></td>
                    <td width="200"><c:out value="${item.quantity}"></c:out></td>
                    <td width="200"><c:out value="${item.price}"></c:out></td>
                    <td>
                        &nbsp;<a href="deleteItemFromOrder?itemId=${item.itemId}">Smazat</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <p align="center"><b><font size="5">Cena celkem: 119Kč&nbsp; </font></b></p>

        <p align="center"><font face="Arial Unicode MS" color="#FFFFFF" size="3">
            <button class="btn btn-info " onclick="location.href='continueToUserData'">Další</button>
        </font></p>
    </c:if>
    <c:if test="${empty SEARCH_ITEM_RESULTS_KEY}">
        <span>Košík je prázdný</span>
    </c:if>


</div>