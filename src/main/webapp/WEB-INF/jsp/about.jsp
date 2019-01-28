<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <jsp:include page="/WEB-INF/jsp/base/resource.jsp" />
    <style>

    </style>
</head>
<body>
<div id="app">

    <!-- include header java server page code -->
    <jsp:include page="/WEB-INF/jsp/base/header.jsp" />



    <div class="master-container">

        <template>
        <div>
            <Tag type="dot" color="primary" class="title-tag">关于 About </Tag>
        </div>
        </template>

        <br>



        <template>
            <div style="width:50%;margin-left:20%;">
                <Card style="width:100%;">
                    <p slot="title">
                        <Icon type="ios-contacts" size="22"></Icon>
                         关于
                    </p>
                    <a href="#" slot="extra" @click.prevent="changeLimit">
                        <Icon type="ios-contacts"></Icon>
                        预留栏
                    </a>
                    <ul>
                        <li v-for="item in list">
                            {{ item.name }}
                            <a :href="item.url" target="_blank"> <span>{{ item.label }}</span></a>
                        </li>
                    </ul>
                </Card>
            </div>
        </template>



    </div>







    <!-- include footer java server page code -->
    <jsp:include page="/WEB-INF/jsp/base/footer.jsp" />
</div>
<script>
    new Vue({
        el: '#app',
        data: {
            list:[
                {name:'系统名称：',url:'#',label:'C-D Terminal'},
                {name:'版本：',url:'#',label:'V1.0.0'},
                {name:'作者邮箱：',url:'mailto:icefrog.su@qq.com',label:'icefrog.su@qq.com'},
                {name:'托管地址：',url:'https://baidu.com',label:'Github'}
            ]
        },
        methods: {

        }
    })
</script>
</body>
</html>
