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
        <div style="background:#515a6e;width:100%;height:100%;padding-top:8%;">
            <Card :bordered="false" style="width:30%;margin-left:35%;">
                <p slot="title" style="color:#19be6b;font-size:20px;">C-D Terminal</p>
                <i-Form ref="formInline" :model="formInline" :rules="ruleInline" inline>
                    <i-FormItem prop="user">
                        <i-Input type="text" v-model="formInline.user" placeholder="Username">
                            <Icon type="ios-person-outline" slot="prepend"></Icon>
                        </i-Input>
                    </i-FormItem>
                    <br>
                    <i-FormItem prop="password">
                        <i-Input type="password" v-model="formInline.password" placeholder="Password">
                            <Icon type="ios-lock-outline" slot="prepend"></Icon>
                        </i-Input>
                    </i-FormItem>
                    <br>
                    <i-FormItem prop="single">
                        <Checkbox v-model="formInline.single">Remember password</Checkbox>
                    </i-FormItem>
                    <br><br>
                    <i-Button type="success" @click="handleSubmit('formInline')" long>SUBMIT</i-Button>
                </i-Form>
                <%--<template>
                <div style="text-align:right;">
                    <Icon type="logo-github" @click="github" style="cursor: pointer;" size="30" />
                </div>
                </template>--%>
            </Card>

            <div style="position:fixed; left:0px; bottom:0px; width:100%; height:45px; z-index:9999;text-align:center;">
                <Icon type="logo-github" @click="github" style="cursor: pointer;color:white;" size="40" />
            </div>
        </div>
    </template>
</div>
<script>

    var App = new Vue({
        el: '#app',
        data:{
            formInline: {
                user: '',
                password: '',
                single:false
            },
            ruleInline: {
                user: [
                    { required: true, message: 'Please fill in the user name', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: 'Please fill in the password.', trigger: 'blur' },
                    { type: 'string', min: 6, message: 'The password length cannot be less than 6 bits', trigger: 'blur' }
                ]
            }
        },
        methods:{
            show:function () {

            },
            github:function () {
              window.location.href="https://github.com/icefrog-su";
            },
            handleSubmit:function(name) {
                /*this.$refs[name].validate(function(valid){
                    alert(valid);
                    if (valid) {
                        this.$Message.success('Success!');
                    } else {
                        this.$Message.error('Fail!');
                    }
                });*/
                window.location.href="/system/toIndex";
            }
        }
    });


</script>
</body>
</html>
