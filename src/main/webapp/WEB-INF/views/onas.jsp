<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="taglib_imports.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>O nás - Bisonsocks.cz</title>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1250">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="background-color: #F9F9F6">
<jsp:include page="menu.jsp"/>
<script src="http://code.jquery.com/jquery.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<p align="center"><font color="#07A9C3" face="Times New Roman" style="font-size: 13pt"><b>„NEJVĚTŠÍ CHYBA, KTEROU V
    ŽIVOTĚ MŮŽETE UDĚLAT, JE MÍT POŘÁD STRACH, ŽE NĚJAKOU UDĚLÁTE"
</b></font></p>

<p align="center"><b><span lang="en-us"><font color="#07A9C3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>- <span
        lang="en-us">Elbert Hubbard
1856–1915 </span></b></p>

<p align="center">
    Dejte nevýrazným ponožkám sbohem, mnozí mladí lidé se již naučili oblékat se barevně, výstředně a
    experimentovat.<br>
    To se ale vždy týkalo pouze hlavních částí outfitu, na ponožky se vždy tak nějak pozapomínalo. Až doposud.<br>
    Dosud nevídané, nápadité a svébytné vzory ponožek. Tak pestré ponožky jste na sobě ještě neměli!<br>
    Jedná se o ponožky pro každou příležitost, pro každé rozpoložení. Ať už jste veselí, smutní, naštvaní či v
    melancholické náladě, naše ponožky vás potěší.</p>

<p align="left">&nbsp;</p>
<p align="center"><img border="0" src="/resources/img/pruh.jpg" width="71.742%" height="31%"></p>
<br>
<br>
<p align="center">
    <font color="#07A9C3" face="Times New Roman" style="font-size: 13pt"><b>POZVAT PŘÍTELE</b></font></p>

<p align="center">
    <img border="0" src="/resources/img/pozvatpritele.png" width="9.5%" height="17.31%"></p>
<form action="sendInvitation">
    <p align="center"><input type="email" name="emailAddress" maxlength="41" size="41" placeholder="email">
        <span class="errorMessage"><c:out value="${validationError}"/></span></p>

    <p align="center"><button type="submit" class="btn btn-info " >Odeslat</button></p>
</form>
<p align="center">&nbsp;</p>

<p align="center">&nbsp;</p>

<p align="center"><font face="Arial">Hněvčeves 34, 50315 Nechanice |
    Provozovatel: Michal Gerstberger
    <BR>
    IČO: 03355543 | DIČ: CZ9112203870<br>
    Tel.: 777 279 448 | Email:

bisonsocks@email.cz<br>
    <a target="_blank" href="terms"> <font color="#000000">Obchodní podmínky</a>  </font></p>
<jsp:include page="socialnisite.jsp"/>
</body>

</html>
