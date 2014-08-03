<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/taglib_imports.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <title>Home</title>
    </head>
    <body>
         <script src="http://code.jquery.com/jquery.js"></script>
        <script src="/js/bootstrap.min.js"></script>
        <h1>Polozky</h1>
            <div class="form-group">
                <label for="quantity" class="col-sm-2 control-label">Polozka 1</label>
                <div class="col-sm-1">
                    <form:form action="addToOrder" commandName="shoppingItem">
                        <form:input path="quantity" id="quantity" cssClass="form-control"/>
                        <input type="submit" onclick="" class="form-control" id="btn" value="Pridej">
                    </form:form>
                </div>
            </div>
    </body>
</html>
