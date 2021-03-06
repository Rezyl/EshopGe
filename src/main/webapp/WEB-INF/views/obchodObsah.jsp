<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@include file="taglib_imports.jsp" %>
    <%--<sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')">--%>
    <%--<input type="button" value="Novy Zavodnik" onclick="javascript:go('saveCompetitor.do');" class="submit_btn float_l"/>--%>
    <%--</sec:authorize>--%>
<c:if test="${! empty SEARCH_ITEM_RESULTS_KEY.getContent()}">
    <div id="shoppingItems">
    <c:forEach var="item" items="${SEARCH_ITEM_RESULTS_KEY.getContent()}">
        <div id="ShoppingItem" style="float: left; width: 18%; align-content: center">
            <a href="showGallery?itemId=${item.itemId}" data-toggle="modal" data-target="#myModal">
                <img style="display: block; margin: 10%" id="image" src="${item.imageFilePath}" align="center"
                     alt="${item.name}"
                     width="87%" height="87%"></a>
            <span style="display: block; text-align: center" id="name"><c:out value="${item.name}"/></span>
            <span style="display: block; text-align: center" id="description"><c:out
                    value="${item.description}"/></span>

            <span style="display: block; text-align: center" id="price"><c:out value="${item.price} Kč s DPH"/></span>

            <div style="text-align: center">
                <form action="addItemsToOrder" method="get">
                    <select name="size" style="align-content: center; height:1.9em;">
                        <c:forEach var="s" items="${item.getAvailableSizesAsList()}">
                            <option value="${s}">${s}</option>
                        </c:forEach>
                    </select>
                    <input name="itemID" type="hidden" value="${item.itemId}"/>
                    <input type="submit" style="align-content: center;" value="Přidat do košíku"/>
                </form>
            </div>
        </div>
        <!-- Modal HTML -->
        <div id="myModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <!-- Content will be loaded here from "remote jsp" file -->
                </div>
            </div>
        </div>
    </c:forEach>
    </div>
    <jsp:include page="paginationShoppingItems.jsp"/>
</c:if>
<c:if test="${empty SEARCH_ITEM_RESULTS_KEY.getContent()}">
    <span>Nenalezeno žádné zboží.</span>
</c:if>
