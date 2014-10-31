<%@include file="taglib_imports.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<div align="left" style="float: left; height: 100%; width: 12.5%; margin-bottom: 100%">
<span style="letter-spacing: 9px"><font color="#CE2F45"><b>KATEGORIE</b></font><font color="#07A9C3"><b><br>
    &nbsp;</b></font></span>

    <c:forEach var="cat" items="${allCategories}">
        <c:url var="showByCategoryUrl" value="/showByCategory">
            <c:param name="category" value="${cat}"/>
        </c:url>
        <p>
            <font color="#07A9C3">
                <b>
                    <a href="${showByCategoryUrl}" style="text-decoration: none">
                        <font color="#07A9C3"><c:out value="${cat.displayName}"/></font>
                    </a>
                </b>
            </font>
        </p>
    </c:forEach>
</div>
