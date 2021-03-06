<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@include file="taglib_imports.jsp" %>
<html>

<head>
    <meta http-equiv="Content-Language" content="cs">
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1250">

    <script type="text/javascript">
        function changePrice() {
            document.getElementById('submitOrderNow').value = 'false';
            document.getElementById('submitForm').click();
        }
    </script>
</head>

<body style="background-color: #F9F9F6" style="overflow: scroll">
<jsp:include page="menu.jsp"/>
<p align="center"><font face="Times New Roman" style="font-size: 13pt"><b><font color="#07A9C3">&nbsp;
    NÁKUPNÍ KOŠÍK - DORUČOVACÍ A PLATEBNÍ ÚDAJE </font></b></font></p>
<br>

<div align="center">
    <form:form modelAttribute="OrderObj" action="completeOrder" method="post" id="submitOrder">
    <table>
            <tr><td width="160"><label for="name">Jméno:</label></td>

                <td width="220">
                    <form:input path="name" id="name" cssClass="input_field"/>
                    <form:errors path="name" cssClass="errorMessage"/></td>

                <td width="80"><label for="surname">Přijmení:</label></td>

                <td>        <form:input path="surname" id="surname" cssClass="input_field"/>
                <form:errors path="surname" cssClass="errorMessage"/></td>
            </tr>

            <tr><td><label for="street">Ulice a č.p.:</label></td>

                <td><form:input path="street" id="street" cssClass="input_field"/>
                <form:errors path="street" cssClass="errorMessage"/></td>

                <td><label for="city">Město:</label></td>

                <td>    <form:input path="city" id="city" cssClass="input_field"/>
                <form:errors path="city" cssClass="errorMessage"/></td>
            </tr>
            <tr>
                <td><label for="psc">PSČ:</label></td>
                <td>  <form:input path="psc" id="psc" cssClass="input_field"/>
                <form:errors path="psc" cssClass="errorMessage"/></td>
                <td>&nbsp;</td>
                <td>&nbsp;</td></tr>
            <tr><td><label for="email">Email:</label></td>
                <td> <form:input path="email" id="email" cssClass="input_field"/>
                <form:errors path="email" cssClass="errorMessage"/></td>
                <td><label for="mobile">Telefon:</label></td>
                <td> <form:input path="mobile" id="mobile" cssClass="input_field"/>
                <form:errors path="mobile" cssClass="errorMessage"/></td>
            </tr>
        <tr>
            <td><label for="typeOfPayment">Typ platby a dopravy:</label></td>
            <td>
                <form:select path="typeOfPayment" items="${typePaymentList}" id="typeOfPayment" cssClass="input_field"
                             onchange="changePrice()"/>
                    <%--this value tell if only change type of payment or submit order--%>
                <input type="hidden" id="submitOrderNow" name="submitOrderNow" value="true"/>
            </td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
        </tr>

        <tr><td><label for="notes">Poznámky:</label></td>
                <td>   <form:input path="notes" id="notes" cssClass="input_field"/></td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr><td>Souhlasím s <a target="_blank" href="terms">podmínkami</a></td>
                <td><form:checkbox path="acceptTerms"/>
                    <form:errors path="acceptTerms" cssClass="errorMessage"/></td>
                <td>&nbsp;</td>
                <td>&nbsp;</td></tr>
        <br>
        <div class="cleaner_h10"></div>
        </table>
        <br>
        <br>

        <p align="center"><b><font size="5">Cena celkem s dopravou: ${OrderObj.totalPrice} Kč&nbsp; </font></b></p>
        <br>

        <br>
        <button class="btn btn-info" type="submit" id="submitForm">Odeslat objednávku</button>
    </form:form>
    <br>

    <br>
    <img border="0" src="/resources/img/ponozky.png" width="7.6%" height="14.71%">
</div>
</body>

</html>
