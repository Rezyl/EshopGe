<%@include file="taglib_imports.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {

        $('.bxslider').bxSlider({
            mode: 'fade',
            responsive: false,
            touchEnabled: true,
            controls: true
        });
    });
</script>
    <ul class="bxslider">
        <c:forEach var="item" items="${images}">
            <li><img src=${item}/></li>
        </c:forEach>
    </ul>
