<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <jsp:include page="/WEB-INF/jsp/base/resource.jsp" />
</head>
<body>
<div id="app">
    <template>
        <i-Menu mode="horizontal" :theme="theme1" active-name="1">
            <Menu-Item name="1">
                <i-Icon type="ios-paper"/>
                内容管理
            </Menu-Item>
            <Menu-Item name="2">
                用户管理
            </Menu-Item>
            <Menu-Item name="3">
                统计分析
            </Menu-Item>
            <Submenu name="4">
                <template slot="title">
                    <Icon type="ios-cog" size="30" />
                </template>
                <Menu-Item name="3-1">设置1</Menu-Item>
                <Menu-Item name="3-2">设置2</Menu-Item>
                <Menu-Item name="3-3">设置3</Menu-Item>
            </Submenu>
        </i-Menu>
    </template>

</div>
<script>
    new Vue({
        el: '#app',
        data: {
            visible: false,
            theme1: 'light'
        },
        methods: {
            show: function () {
                this.visible = true;
            }
        }
    })
</script>
</body>
</html>
