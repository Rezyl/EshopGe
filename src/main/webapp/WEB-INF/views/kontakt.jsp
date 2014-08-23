<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1250">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
</head>

<body bgcolor="#F9F9F6" style="background-color: #F9F9F6">
<jsp:include page="menu.jsp"/>
<script src="http://code.jquery.com/jquery.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<p align="center"><font face="Times New Roman" style="font-size: 13pt"><b><font color="#07A9C3"></span>&nbsp;
    OFICIÁLNÍ KONTAKT&nbsp; </span></font></b></font></p>

<p>
    &nbsp;</p>

<div align="center">

    <table class="tabform" cellpadding="4" cellspacing="0" width="760" height="519">
        <tr>
            <td class="paramname" width="213" height="21" align="justify">
                <p align="left">
                    <font face="Times New Roman">Jméno:</font></td>
            <td class="paramvalue" width="532" height="21" align="justify">

                <p align="left">

	<span>
		<font color="#FFFFFF" size="3" face="Arial Unicode MS">
            <input class="input_text" name="conlname" size="26"></font></span></td>
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
		<font color="#FFFFFF" size="3" face="Arial Unicode MS">
            <input class="input_text" name="conlname3" size="26"></font></span></td>
            <td class="paramvalue" width="532" height="21" align="justify">

                &nbsp;</td>
        </tr>
        <tr>
            <td class="paramname" width="213" align="justify">
                <font face="Times New Roman">Kategorie:</font></td>
            <td class="paramvalue" width="532" align="justify">

                <form method="POST" action="--WEBBOT-SELF--">
                    <!--webbot bot="SaveResults" U-File="fpweb:///_private/form_results.csv" S-Format="TEXT/CSV" S-Label-Fields="TRUE" -->
                    <p><font face="Times New Roman" color="#FFFFFF">
                        <input type="radio" value="V" checked name="R1" tabindex="1"></font><font
                            face="Times New Roman">
                        Hodnocení</font></p>
                </form>
                <form method="POST" action="--WEBBOT-SELF--">
                    <!--webbot bot="SaveResults" U-File="fpweb:///_private/form_results.csv" S-Format="TEXT/CSV" S-Label-Fields="TRUE" -->
                    <p><font face="Times New Roman" color="#FFFFFF">
                        <input type="radio" value="V" name="R1" tabindex="1" checked></font><font
                            face="Times New Roman">
                        Moje objednávka</font></p>
                </form>
                <form method="POST" action="--WEBBOT-SELF--">
                    <!--webbot bot="SaveResults" U-File="fpweb:///_private/form_results.csv" S-Format="TEXT/CSV" S-Label-Fields="TRUE" -->
                    <p><font face="Times New Roman" color="#FFFFFF">
                        <input type="radio" value="V" name="R1" tabindex="1" checked></font><font
                            face="Times New Roman">
                        Barva/model/velikost</font></p>
                </form>
                <form method="POST" action="--WEBBOT-SELF--">
                    <!--webbot bot="SaveResults" U-File="fpweb:///_private/form_results.csv" S-Format="TEXT/CSV" S-Label-Fields="TRUE" -->
                    <p><font face="Times New Roman" color="#FFFFFF">
                        <input type="radio" value="V" name="R1" tabindex="1" checked></font><font
                            face="Times New Roman">
                        Ostatní</font></p>
                </form>
            </td>
            <td class="paramvalue" width="532" align="justify">

                <img border="0" src="/resources/img/kontaktkladivo.png" width="150" height="114" align="right"></td>
        </tr>
        <tr>
            <td class="paramname" width="213" align="justify" height="246">
                <p align="left"><font face="Times New Roman">Zpráva:</font></td>
            <td class="paramvalue" width="532" align="justify" height="246">

                <p align="left"><textarea rows="10" name="S1" cols="47"></textarea></td>
            <td class="paramvalue" width="532" align="justify" height="246">

                &nbsp;</td>
        </tr>
    </table>

</div>

<p align="center"><font face="Arial Unicode MS" color="#FFFFFF" size="3">
    <button class="btn btn-info ">Odeslat</button>
</font></p>
<jsp:include page="socialnisite.jsp"/>
</body>

</html>
