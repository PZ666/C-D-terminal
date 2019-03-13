<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../framework/ico.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看商品类型 | 农村电子商务信息管理平台</title>
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
                <li class="active"><strong>查看商品类型</strong></li>
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
                    <h2>查看商品类型</h2>
                </div>
                <hr/>

                <div style="width:50%;">

                    <form class="form-horizontal">
                        <div class="form-group">
                            <label for="broadType" class="col-sm-2 control-label"><span class="required-span">*</span> 商品大类：</label>
                            <div class="col-sm-10">
                                <select disabled="disabled" class="form-control" id="broadType">
                                	<option value="-1">-- 请选择 --</option>
                                	<option ${requestScope.productType.broadType == '农产品' ? 'selected=selected' : '' } value="农产品">农产品</option>
                                	<option ${requestScope.productType.broadType == '工业消费品' ? 'selected=selected' : '' } value="工业消费品">工业消费品</option>
                                	<option ${requestScope.productType.broadType == '生产资料' ? 'selected=selected' : '' } value="生产资料">生产资料</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="productType" class="col-sm-2 control-label"><span class="required-span">*</span> 具体类别：</label>
                            <div class="col-sm-10">
                                <input disabled="disabled" value="${requestScope.productType.productType }" type="text" class="form-control" id="productType" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="generateBuyNo" class="col-sm-2 control-label"><span class="required-span">*</span> 代买编码：</label>
                            <div class="col-sm-10">
                                <input disabled="disabled" value="${requestScope.productType.generateBuyNo }" type="text" class="form-control" id="generateBuyNo" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="generateSellNo" class="col-sm-2 control-label"><span class="required-span">*</span> 代卖编码：</label>
                            <div class="col-sm-10">
                                <input disabled="disabled" value="${requestScope.productType.generateSellNo }" type="text" class="form-control" id="generateSellNo" />
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
    function toEdit(){
    	window.location.href="/product-type/toEditProductType?id=${requestScope.productType.id}";
    }
</script>

<i id="loading" class="fa fa-spinner fa-spin loading-image"></i>
</body>
</html>