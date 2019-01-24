<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<template>
    <i-Menu mode="horizontal" theme="light" active-name="1" style="width:100%;">
        <div style="margin-right:100px;float:left;">
            <img src="/static/images/logo.png" style="float:left;">
            <label style="float:left;font-size:22px;color:#515a6e;margin-left:20px;">C-D Terminal</label>
        </div>
        <Menu-Item name="1" to="/system/toSites">
            主页
        </Menu-Item>
        <Menu-Item name="2">
            站点列表
        </Menu-Item>
        <Menu-Item name="3">
            用户列表
        </Menu-Item>
        <Menu-Item name="4">
            操作记录
        </Menu-Item>
        <Menu-Item name="5">
            商务部
        </Menu-Item>
        <Submenu name="6">
            <template slot="title">
                <Icon type="ios-cog" size="30" />
            </template>
            <Menu-Item name="6-1">商品类别设置</Menu-Item>
            <Menu-Item name="6-2">系统资源配置</Menu-Item>
            <Menu-Item name="6-3">关于</Menu-Item>
            <Menu-Item name="6-4">退出登录</Menu-Item>
        </Submenu>
    </i-Menu>
</template>
