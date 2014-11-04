<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="/resources/css/mainCss.css" rel="stylesheet"/>

<c:url var="firstUrl" value="/showByCategory/pages/1">
    <c:param name="category" value="${actualCategory}"/>
</c:url>

<c:url var="lastUrl" value="/showByCategory/pages/${SEARCH_ITEM_RESULTS_KEY.totalPages}">
    <c:param name="category" value="${actualCategory}"/>
</c:url>

<c:url var="prevUrl" value="/showByCategory/pages/${currentIndex - 1}">
    <c:param name="category" value="${actualCategory}"/>
</c:url>

<c:url var="nextUrl" value="/showByCategory/pages/${currentIndex + 1}">
    <c:param name="category" value="${actualCategory}"/>
</c:url>

<div class="pagination">
    <ul>
        <c:choose>
            <c:when test="${currentIndex == 1}">
                <li class="disabled"><a href="#">&lt;&lt;</a></li>
                <li class="disabled"><a href="#">&lt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${firstUrl}">&lt;&lt;</a></li>
                <li><a href="${prevUrl}">&lt;</a></li>
            </c:otherwise>
        </c:choose>
        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
            <c:url var="pageUrl" value="/showByCategory/pages/${i}">
                <c:param name="category" value="${actualCategory}"/>
            </c:url>
            <c:choose>
                <c:when test="${i == currentIndex}">
                    <li class="active"><a href="${pageUrl}"><c:out value="${i}"/></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageUrl}"><c:out value="${i}"/></a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${currentIndex == SEARCH_ITEM_RESULTS_KEY.totalPages}">
                <li class="disabled"><a href="#">&gt;</a></li>
                <li class="disabled"><a href="#">&gt;&gt;</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${nextUrl}">&gt;</a></li>
                <li><a href="${lastUrl}">&gt;&gt;</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
