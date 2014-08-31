<%--
  Created by IntelliJ IDEA.
  User: lukas
  Date: 3.8.14
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<%@include file="/WEB-INF/views/taglib_imports.jsp" %>
<strong>${message}</strong>

<div class="panel">
    <h2>Nové zboží</h2>

    <div class="col_400 float_l">

        <form:form method="POST" modelAttribute="shoppingItem" action="saveShoppingItem" enctype='multipart/form-data'>
            <label for="file">Obrázek položky</label>
            <input type="file" name="file" id="file"/>
            <br>
            <br>

            <label for="filesForGallery">Obrázky pro galerii</label>
            <input type="file" name="filesForGallery" id="filesForGallery" multiple/>
            <br>
            <br>

            <label for="name">Název položky</label>
            <form:input path="name" id="name" cssClass="input_field"/>
            <form:errors path="name" cssClass="errorMessage"/>
            <br>
            <br>
            <label for="info">Popisek položky</label>
            form:input path="info" id="info" cssClass="input_field"/>
            NEFUNGUJE :(
            <br>
            <br>

            <label for="price">Cena za kus:</label>
            <form:input path="price" id="price" cssClass="input_field"/>
            <br>
            <br>

            <label for="category">Kategorie:</label>
            <form:select path="category" items="${categoryValues}" id="category" cssClass="input_field"/>
            <br>
            <br>

            <label for="quantity">Počet:</label>
            <form:input path="quantity" id="quantity" cssClass="input_field"/>
            <br>
            <br>
            Velikost:
            <br>
            35-40<input type="checkbox" checked name="35-40" value="ano">
            <br>
            35-41<input type="checkbox" checked name="35-41" value="ano">
            <br>

            36-41<input type="checkbox" checked name="36-41" value="ano">
            <br>
            39-45<input type="checkbox" checked name="39-45" value="ano">
            <br>
            40-45<input type="checkbox" checked name="40-45" value="ano">
            <br>
            <br>

            <div class="cleaner_h10"></div>

            <input type="submit" name="" value="Uložit" class="submit_btn float_l">
            <%--<input type="button" value="Cancel" onclick="javascript:go('viewAllCompetitors.do');" class="submit_btn float_r"/>--%>

        </form:form>

    </div>
</div>
</body>
</html>
