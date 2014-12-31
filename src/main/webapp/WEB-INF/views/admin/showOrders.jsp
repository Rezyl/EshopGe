<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="../taglib_imports.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <title>Sklad</title>
</head>
<body>
<div class="table_wrapper">
    <%--<sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')">--%>
    <%--<input type="button" value="Novy Zavodnik" onclick="javascript:go('saveCompetitor.do');" class="submit_btn float_l"/>--%>
    <%--</sec:authorize>--%>
    <div id="filter">
        <form action="filterOrders" method="get" target="_blank">
            <label><input type="checkbox" name="complete"/>Vyřízené</label>
            <label><input type="checkbox" name="paid"/>Zaplacené</label>
            <input type="submit" value="Zobrazit"/>
        </form>

    </div>
    <c:if test="${! empty SEARCH_ITEM_RESULTS_KEY}">
        <table>
            <thead>
            <tr>
                <th>Kód obj.</th>
                <th>Jmeno</th>
                <th>Prijmeni</th>
                <th>Celková cena</th>
                <th>Datum vytvoření</th>
                <th>Zaplaceno?</th>
                <th>Vyřízeno?</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${SEARCH_ITEM_RESULTS_KEY}">
            <c:url var="orderDetail" value="/admin/showOrderDetailForm">
                <c:param name="itemId" value="${item.orderID}"/>
            </c:url>
            <tr>
                <td width="200"><c:out value="${item.orderID}"></c:out></td>
                <td width="200"><c:out value="${item.name}"></c:out></td>
                <td width="200"><c:out value="${item.surname}"></c:out></td>
                <td width="200"><c:out value="${item.totalPrice}"></c:out></td>
                <td width="200"><c:out value="${item.dateCreate}"></c:out></td>
                <td width="200"><c:out value="${item.paid}"></c:out></td>
                <td width="200"><c:out value="${item.complete}"></c:out></td>
                <td width="200"><a href="${orderDetail}">DETAIL</a></td>
            </tr>
            </tbody>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty SEARCH_ITEM_RESULTS_KEY}">
        <span>Nejsou vytvořeny žádné objednávky.</span>
    </c:if>
</div>

</body>

</html>
