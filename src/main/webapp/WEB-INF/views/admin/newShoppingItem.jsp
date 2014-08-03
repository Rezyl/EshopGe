<%--
  Created by IntelliJ IDEA.
  User: lukas
  Date: 3.8.14
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<%@include file="/WEB-INF/views/taglib_imports.jsp" %>
<strong>${message}</strong>

<div class="panel">
    <h2>Novy zboží</h2>

    <div class="col_400 float_l">


        <form:form method="POST" modelAttribute="shoppingItem" action="saveShoppingItem">
            <label for="name">Jmeno:</label>
            <form:input path="name" id="name" cssClass="input_field"/>
            <form:errors path="name" cssClass="errorMessage"/>


            <label for="price">Cena za kus:</label>
            <form:input path="price" id="price" cssClass="input_field"/>

            <label for="category">Kategorie:</label>
            <form:select path="category" items="${categoryValues}" id="category" cssClass="input_field"/>

            <label for="quantity">Počet:</label>
            <form:input path="quantity" id="quantity" cssClass="input_field"/>

            <div class="cleaner_h10"></div>

            <input type="submit" name="" value="Ulozit" class="submit_btn float_l">
            <%--<input type="button" value="Cancel" onclick="javascript:go('viewAllCompetitors.do');" class="submit_btn float_r"/>--%>

        </form:form>
    </div>
</div>
</body>
</html>
