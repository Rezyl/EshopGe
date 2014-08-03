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
        <h1>Košík</h1>

         <table>
             <thead>
             <tr>
                 <th>Jmeno</th>
                 <th>Pocet kusu</th>
             </tr>
             </thead>
             <tbody>
             <c:forEach var="item" items="${OrderObj.shoppingItems}">
                 <tr>
                     <td><c:out value="${item.name}"></c:out></td>
                     <td><c:out value="${item.quantity}"></c:out></td>
                 </tr>
                 
             </c:forEach>
             </tbody>
         </table>
         <a href="continueToUserData">Pokracuj</a>
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="email1" class="col-sm-2 control-label">Email</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control" id="email1" placeholder="Email">
                </div>
            </div>
            <div class="form-group">
                <label for="heslo1" class="col-sm-2 control-label">Heslo</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="heslo1" placeholder="Heslo">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Potvrdit</button>
                </div>
            </div>
        </form>

    </body>
</html>
