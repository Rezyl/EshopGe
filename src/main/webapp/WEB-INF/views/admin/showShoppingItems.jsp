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

<div class="panel" id="socks">
    <h2>Sklad</h2>
    <strong>${message}</strong>

    <div class="col_all float_l">
        <div class="table_wrapper">
            <%--<sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')">--%>
                <%--<input type="button" value="Novy Zavodnik" onclick="javascript:go('saveCompetitor.do');" class="submit_btn float_l"/>--%>
            <%--</sec:authorize>--%>
                <c:if test="${! empty SEARCH_ITEM_RESULTS_KEY}">
            <table>
                <thead>
                <tr>
                    <th>Jmeno</th>
                    <th>Kategorie</th>
                    <th>Cena</th>
                    <th>ks/sklad</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="item" items="${SEARCH_ITEM_RESULTS_KEY}">
                        <tr>
                            <td width="200"><c:out value="${item.name}"></c:out></td>
                            <td width="200"><c:out value="${item.category}"></c:out></td>
                            <td width="200"><c:out value="${item.price}"></c:out></td>
                            <td width="200"><c:out value="${item.quantity}"></c:out></td>
                            <td>
                                &nbsp;<a href="updateShoppingItemForm?itemId=${item.itemId}">Upravit</a>
                                &nbsp;&nbsp;<a href="deleteItem?itemId=${item.itemId}">Smazat</a>
                            </td>
                        </tr>
                </tbody>
                </c:forEach>
            </table>
                </c:if>
                <c:if test="${empty SEARCH_ITEM_RESULTS_KEY}">
                    <span>Nenalezeno zboží.</span>
                </c:if>
        </div>
    </div>
</div>
</body>
</html>
