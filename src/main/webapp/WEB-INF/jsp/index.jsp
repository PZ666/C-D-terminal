<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <jsp:include page="/WEB-INF/jsp/base/resource.jsp" />
</head>
<body>
<div id="app">

    <!-- include header java server page code -->
    <jsp:include page="/WEB-INF/jsp/base/header.jsp" />



    <!-- include footer java server page code -->
    <jsp:include page="/WEB-INF/jsp/base/footer.jsp" />
</div>
<script>
    new Vue({
        el: '#app',
        data: {

        },
        methods: {

        }
    })
</script>
</body>
</html>
