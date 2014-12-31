<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/views/taglib_imports.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <title>Sklad</title>
</head>
<body>

<div class="panel">
    <h2>Detail objednávky</h2>

    <div style="float: left" class="col_400 float_l">
        <form method="POST" action="showOrderDetail">

            <label>Kód obj.:</label>
            <c:out value="${orderDetail.orderID}"/>
            <input type="hidden" value="${orderDetail.orderID}" name="itemId"/>

            <label for="paid">Zaplaceno:</label>
            <c:choose>
                <c:when test="${orderDetail.paid == true}">
                    <input type="checkbox" name="paid" id="paid" checked/>
                </c:when>
                <c:otherwise>
                    <input type="checkbox" name="paid" id="paid"/>
                </c:otherwise>
            </c:choose>

            <label for="complete">Vyřízeno:</label>
            <c:choose>
                <c:when test="${orderDetail.complete == true}">
                    <input type="checkbox" name="complete" id="complete" checked/>
                </c:when>
                <c:otherwise>
                    <input type="checkbox" name="complete" id="complete"/>
                </c:otherwise>
            </c:choose>

            <br>
            <br>
            <label>Jméno:</label>
            <c:out value="${orderDetail.name}"/>

            <label>Přijmení:</label>
            <c:out value="${orderDetail.surname}"/>
            <br>
            <br>

            <label>Ulice:</label>
            <c:out value="${orderDetail.street}"/>
            <br>
            <br>

            <label>Město:</label>
            <c:out value="${orderDetail.city}"/>
            <br>
            <br>

            <label>PSČ:</label>
            <c:out value="${orderDetail.psc}"/>
            <br>
            <br>

            <label>E-mail:</label>
            <c:out value="${orderDetail.email}"/>
            <br>
            <br>

            <label>Mobilní číslo:</label>
            <c:out value="${orderDetail.mobile}"/>
            <br>
            <br>

            <label>Poznámky:</label>
            <c:out value="${orderDetail.notes}"/>
            <br>
            <br>

            <label>Typ platby:</label>
            <c:out value="${orderDetail.typeOfPayment}"/>
            <br>
            <br>

            <table>
                <thead>
                    <th>Položka</th>
                    <th>Počet</th>
                </thead>
                <tbody>
                <c:forEach items="${orderItems}" var="item">
                    <tr>
                        <td width="100"><c:out value="${item.key.name}"/></td>
                        <td width="100"><c:out value="${item.value}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br/>
            <br/>


            <label>Celková cena:</label>
            <c:out value="${orderDetail.totalPrice}"/>
            <br>
            <br>

            <label>Datum vytvoření:</label>
            <c:out value="${orderDetail.dateCreate}"/>
            <br>
            <br>

            <input type="submit" name="" value="Ulozit" class="submit_btn float_l">
            <%--<label type="button" value="Cancel" onclick="javascript:go('viewAllCompetitors.do');" class="submit_btn float_r"/>--%>

        </form>
    </div>

</div>
</body>
</html>
