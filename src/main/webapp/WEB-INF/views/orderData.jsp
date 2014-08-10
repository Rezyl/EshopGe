<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@include file="taglib_imports.jsp" %>
<html>

<head>
    <meta http-equiv="Content-Language" content="cs">
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1250">
</head>
<body bgcolor="#F9F9F6">
<jsp:include page="menu.jsp"/>
<div align="center">
    <form:form modelAttribute="order" action="completeOrder">

        <label for="name">Jmeno:</label>
        <form:input path="name" id="name" cssClass="input_field"/>
        <form:errors path="name" cssClass="errorMessage"/>

        <label for="surname">Přijmení:</label>
        <form:input path="surname" id="surname" cssClass="input_field"/>
        <form:errors path="surname" cssClass="errorMessage"/>
        <br/>
        <label for="street">Ulice a č.p.:</label>
        <form:input path="street" id="street" cssClass="input_field"/>
        <form:errors path="street" cssClass="errorMessage"/>

        <label for="city">Město:</label>
        <form:input path="city" id="city" cssClass="input_field"/>
        <form:errors path="city" cssClass="errorMessage"/>

        <label for="psc">PSČ:</label>
        <form:input path="psc" id="psc" cssClass="input_field"/>
        <form:errors path="psc" cssClass="errorMessage"/>
        <br/>
        <label for="email">Email:</label>
        <form:input path="email" id="email" cssClass="input_field"/>
        <form:errors path="email" cssClass="errorMessage"/>

        <label for="mobile">Telefon:</label>
        <form:input path="mobile" id="mobile" cssClass="input_field"/>
        <form:errors path="mobile" cssClass="errorMessage"/>
        <br/>
        <label for="typeOfPayment">Typ platby:</label>
        <form:select path="typeOfPayment" items="${typePaymentList}" id="typeOfPayment" cssClass="input_field"/>

        <label for="notes">Poznámky:</label>
        <form:input path="notes" id="notes" cssClass="input_field"/>

        <div class="cleaner_h10"></div>

        <input type="submit" name="" value="Dokončit objednávku" class="submit_btn float_l">
    </form:form>
</div>
</body>

</html>
