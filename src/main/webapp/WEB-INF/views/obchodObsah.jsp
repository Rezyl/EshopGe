<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@include file="taglib_imports.jsp" %>
<body>
<div style="float: left">
    <%--<sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')">--%>
    <%--<input type="button" value="Novy Zavodnik" onclick="javascript:go('saveCompetitor.do');" class="submit_btn float_l"/>--%>
    <%--</sec:authorize>--%>

    <c:if test="${! empty SEARCH_ITEM_RESULTS_KEY}">
        <c:forEach var="item" items="${SEARCH_ITEM_RESULTS_KEY}">
            <div id="ShoppingItem" style="float: left; width: 30%; align-content: center">
                <img style="display: block" id="image" src="${item.imageFilePath}" align="center" alt="${item.name}"
                     width="214" height="233">
                <span style="display: block" id="name"><c:out value="${item.name}"/></span>
                <div>Ahoj, já jsem rampampam ponožka, která má 90% vlny a  10% ledu</div>
                <span style="display: block" id="price"><c:out value="${item.price} Kč"/></span>

                <div>
                    <form action="addItemsToOrder" method="get">
                        <select name="size">
                            <c:forEach var="s" items="${sizeList}">
                                <option value="${s}">${s}</option>
                            </c:forEach>
                        </select>
                        <input name="itemID" type="hidden" value="${item.itemId}"/>
                        <input type="submit" value="Přidat do košíku"/>
                    </form>
                </div>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${empty SEARCH_ITEM_RESULTS_KEY}">
        <span>Nenalezeno žádné zboží.</span>
    </c:if>
</div>

</body>
