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
    <c:if test="${! empty SEARCH_ITEM_RESULTS_KEY}">
        <table>
            <thead>
            <tr>
                <th>Kód obj.</th>
                <th>Jmeno</th>
                <th>Prijmeni</th>
                <th>Ulice</th>
                <th>Mesto</th>
                <th>PSČ</th>
                <th>E-mail</th>
                <th>Mobil</th>
                <th>Poznámky</th>
                <th>Druh platby</th>
                <th>Položky</th>
                <th>Celková cena</th>
                <th>Datum vytvoření</th>
                <th>Zaplaceno?</th>
                <th>Vyřízeno?</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${SEARCH_ITEM_RESULTS_KEY}">
            <tr>
                <td width="200"><c:out value="${item.orderID}"></c:out></td>
                <td width="200"><c:out value="${item.name}"></c:out></td>
                <td width="200"><c:out value="${item.surname}"></c:out></td>
                <td width="200"><c:out value="${item.street}"></c:out></td>
                <td width="200"><c:out value="${item.city}"></c:out></td>
                <td width="200"><c:out value="${item.psc}"></c:out></td>
                <td width="200"><c:out value="${item.email}"></c:out></td>
                <td width="200"><c:out value="${item.mobile}"></c:out></td>
                <td width="200"><c:out value="${item.notes}"></c:out></td>
                <td width="200"><c:out value="${item.typeOfPayment}"></c:out></td>
                    <%--todo etems--%>
                <td width="200"><c:out value="${item.totalPrice}"></c:out></td>
                <td width="200"><c:out value="${item.dateCreate}"></c:out></td>
                    <%--todo radio buttons--%>
            </tr>
            </tbody>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty SEARCH_ITEM_RESULTS_KEY}">
        <span>Nenalezeno zboží.</span>
    </c:if>
</div>

</body>

</html>
