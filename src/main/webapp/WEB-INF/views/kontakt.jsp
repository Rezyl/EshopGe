<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>Kontakt - Bisonsocks.cz</title>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1250">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<%@include file="taglib_imports.jsp" %>
<body bgcolor="#F9F9F6" style="background-color: #F9F9F6">
<jsp:include page="menu.jsp"/>
<script src="http://code.jquery.com/jquery.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<p align="center"><font face="Times New Roman" style="font-size: 13pt"><b><font color="#07A9C3"></span>&nbsp;
    OFICIÁLNÍ KONTAKT&nbsp; </span></font></b></font></p>

<p>
    &nbsp;</p>

<div align="center">
    <form:form action="sendContact" modelAttribute="contactMessage">

    <table class="tabform" cellpadding="4" cellspacing="0" width="760" height="519">
        <tr>
            <td class="paramname" width="213" height="21" align="justify">
                <p align="left">
                    <font face="Times New Roman">Jméno:</font></td>
            <td class="paramvalue" width="532" height="21" align="justify">

                <p align="left">

	            <span>
                    <input type="text" class="form-control" name="to" id="to" placeholder="Jméno">
                    <form:errors path="to" cssClass="errorMessage"/>
                </span>
            </td>
            <td class="paramvalue" width="532" height="21" align="justify">

                &nbsp;</td>
        </tr>
        <tr>
            <td class="paramvalue" width="213" height="21" align="justify">
                <p align="left">
                    <font face="Times New Roman">Email:</font></td>
            <td class="paramvalue" width="532" height="21" align="justify">
                <p align="left">
            	<span>
                    <input type="email" class="form-control" name="from" id="from" placeholder="Email">
                <form:errors path="from" cssClass="errorMessage"/>
                </span>
            </td>
            <td class="paramvalue" width="532" height="21" align="justify">

                &nbsp;</td>
        </tr>
        <tr>
            <td class="paramname" width="213" align="justify">
                <font face="Times New Roman">Kategorie:</font></td>
            <td class="paramvalue" width="532" align="justify">
                <div class="btn-group" data-toggle="buttons">
                        <label class="btn btn-default active" >
                        <form:radiobutton path="subject" value="Hodnocení"/>Hodnoceni
                        </label >
                        <label class="btn btn-default" >
                            <form:radiobutton path="subject" value="Moje objednávka"/>Moje objednávka
                        </label >
                        <label class="btn btn-default" >
                            <form:radiobutton path="subject" value="Barva/model/velikost"/>Barva/model/velikost
                        </label >
                        <label class="btn btn-default" >
                            <form:radiobutton path="subject" value="Ostatní"/>Ostatní
                        </label >
                </div>
            </td>
            <td class="paramvalue" width="532" align="justify">

                <img border="0" src="/resources/img/kontaktkladivo.png" width="150" height="114" align="right"></td>
        </tr>
        <tr>
            <td class="paramname" width="213" align="justify" height="246">
                <p align="left"><font face="Times New Roman">Zpráva:</font></td>
            <td class="paramvalue" width="532" align="justify" height="246">

                <p align="left"><textarea id="message" rows="10" name="message" cols="47"></textarea></td>
            <td class="paramvalue" width="532" align="justify" height="246">

                &nbsp;</td>
        </tr>
    </table>

</div>

<p align="center"><font face="Arial Unicode MS" color="#FFFFFF" size="3">
    <button type="submit" class="btn btn-info ">Odeslat</button>
    </form:form>
</font></p>
<jsp:include page="socialnisite.jsp"/>
</body>

</html>
