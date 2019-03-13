<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../framework/ico.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看站点 | 农村电子商务信息管理平台</title>
</head>
<body class="pace-done fixed-nav fixed-nav-basic">
<div id="wrapper">
    <%@ include file="../framework/baseframework.jsp" %>


    <!-- 位置导航 -->
    <div style="background-color: #f0f2fa;"
         class="row wrapper border-bottom white-bg page-heading">
        <div style="width: 100%; background-color: #f0f2fa;">
            <ol class="breadcrumb"
                style="background-color: #f0f2fa; margin-buttom: 0px; padding-buttom: 1px; font-size: 14px;">
                <li class="active"><strong>查看站点</strong></li>
            </ol>
        </div>
    </div>

    <!-- 渐入Div -->
    <div class="wrapper wrapper-content animated fadeInRight"
         style="margin-top: -15px; margin-left: -15px; margin-right: -15px;">

        <div class="panel panel-default">
            <div class="panel-body">

                <!-- 标题区域 -->
                <div class="list-title-h2">
                    <h2>查看站点</h2>
                </div>
                <hr/>

                <div style="width:50%;">

                    <form class="form-horizontal">
                        <div class="form-group">
                            <label for="siteType" class="col-sm-2 control-label"><span class="required-span">*</span> 站点类型：</label>
                            <div class="col-sm-10">
                                <select disabled class="form-control" id="siteType">
                                	<option value="-1" selected="selected">-- 请选择 --</option>
                                	<option value="1" ${requestScope.site.siteType == 1 ? 'selected=selected' : ''}>县级服务中心</option>
                                	<option value="2" ${requestScope.site.siteType == 2 ? 'selected=selected' : ''}>乡镇级服务站</option>
                                	<option value="3" ${requestScope.site.siteType == 3 ? 'selected=selected' : ''}>村级服务站</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="siteName" class="col-sm-2 control-label"><span class="required-span">*</span> 站点名称：</label>
                            <div class="col-sm-10">
                                <input disabled type="text" class="form-control" id="siteName" value="${requestScope.site.siteName}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="siteCode" class="col-sm-2 control-label"><span class="required-span">*</span> 站点编码：</label>
                            <div class="col-sm-10">
                                <input disabled type="text" class="form-control" value="${requestScope.site.siteCode}" id="siteCode" />
                            </div>
                        </div>
                        
                        <br>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="button" onclick="javascript:window.history.back();" class="btn btn-sm btn-default">返&nbsp;&nbsp;回</button>&nbsp;&nbsp;
	                            <button type="button" onclick="toEdit();" class="btn btn-sm button-common">编&nbsp;&nbsp;辑</button>
                            </div>
                        </div>
                    </form>


                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function toEdit() {
       window.location.href="/site/toEditSite?id=${requestScope.site.id}";
    }
</script>

<i id="loading" class="fa fa-spinner fa-spin loading-image"></i>
</body>
</html>