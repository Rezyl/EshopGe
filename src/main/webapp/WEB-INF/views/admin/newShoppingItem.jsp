<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/views/taglib_imports.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>
</head>
<body>


<strong>${message}</strong>

<div class="panel">
    <h2>Nové zboží</h2>

    <div class="col_400 float_l">

        <form:form method="POST" modelAttribute="shoppingItem" action="saveShoppingItem" enctype='multipart/form-data'>
            <label for="file">Obrázek položky:</label>
            <input type="file" name="file" id="file"/>
            <br>
            <br>

            <label for="filesForGallery">Obrázky pro galerii:</label>
            <input type="file" name="filesForGallery" id="filesForGallery" multiple/>
            <br>
            <br>

            <label for="name">Název položky:</label>
            <form:input path="name" id="name" cssClass="input_field"/>
            <form:errors path="name" cssClass="errorMessage"/>
            <br>
            <br>
            <label for="description">Popisek položky:</label>
            <form:textarea path="description" id="description" cssClass="input_field"/>
            <form:errors path="description" cssClass="errorMessage"/>
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

            <div>
                <label for="sizes">Velikosti:</label>
                <form:checkboxes items="${sizesValues}" id="sizes" path="availableSizes"/>
            </div>
            <br>

            <div class="cleaner_h10"></div>

            <input type="submit" name="" value="Uložit" class="submit_btn float_l">
            <%--<input type="button" value="Cancel" onclick="javascript:go('viewAllCompetitors.do');" class="submit_btn float_r"/>--%>

        </form:form>

    </div>
</div>
</body>
</html>
