<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../framework/ico.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增商品类型 | 农村电子商务信息管理平台</title>
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
                <li class="active"><strong>新增商品类型</strong></li>
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
                    <h2>新增商品类型</h2>
                </div>
                <hr/>

                <div style="width:50%;">

                    <form class="form-horizontal">
                        <div class="form-group">
                            <label for="broadType" class="col-sm-2 control-label"><span class="required-span">*</span> 商品大类：</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="broadType">
                                	<option value="-1">-- 请选择 --</option>
                                	<option value="农产品">农产品</option>
                                	<option value="工业消费品">工业消费品</option>
                                	<option value="生产资料">生产资料</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="productType" class="col-sm-2 control-label"><span class="required-span">*</span> 具体类别：</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="productType" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="generateBuyNo" class="col-sm-2 control-label"><span class="required-span">*</span> 代买编码：</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="generateBuyNo" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="generateSellNo" class="col-sm-2 control-label"><span class="required-span">*</span> 代卖编码：</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="generateSellNo" />
                            </div>
                        </div>
                        
                        <br>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="button" onclick="javascript:window.history.back();" class="btn btn-sm btn-default">返&nbsp;&nbsp;回</button>&nbsp;&nbsp;
	                            <button type="button" onclick="save();" class="btn btn-sm button-common">保&nbsp;&nbsp;存</button>
                            </div>
                        </div>
                    </form>


                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function save() {
        var broadType = $("#broadType");
        var productType = $("#productType");
        var generateBuyNo = $("#generateBuyNo");
        var generateSellNo = $("#generateSellNo");
        
        if (isBlank(broadType.val()) || broadType.val() == "-1") {
            toastr.warning("请选择商品大类!", "提示");
            broadType.focus();
            broadType.select();
            return;
        }
        if (isBlank(productType.val())) {
            toastr.warning("商品类别信息为必填项!", "提示");
            productType.focus();
            productType.select();
            return;
        }
        if (productType.val().length > 50) {
            toastr.warning("商品类别信息不允许超过50个字符!", "提示");
            productType.focus();
            productType.select();
            return;
        }
        if (isBlank(generateBuyNo.val()) || generateBuyNo.val().length > 10) {
            toastr.warning("代买编码为必须参数且不允许超过10个长度!", "提示");
            generateBuyNo.focus();
            generateBuyNo.select();
            return;
        }
        if (isNaN(generateBuyNo.val())) {
            toastr.warning("代买编码为数值类型!", "提示");
            generateBuyNo.focus();
            generateBuyNo.select();
            return;
        }
        if (isBlank(generateSellNo.val()) || generateSellNo.val().length > 10) {
            toastr.warning("代卖编码为必须参数且不允许超过10个长度!", "提示");
            generateSellNo.focus();
            generateSellNo.select();
            return;
        }
        if (isNaN(generateSellNo.val())) {
            toastr.warning("代卖编码为数值类型!", "提示");
            generateSellNo.focus();
            generateSellNo.select();
            return;
        }
        
        var params = {};
        params.broadType = broadType.val();
        params.productType = productType.val();
        params.generateBuyNo = generateBuyNo.val();
        params.generateSellNo = generateSellNo.val();
        $("#loading").show();
        $.ajax({
            url: "/product-type/save",
            cache: false,
            type: "POST",
            data: params,
            success: function (response) {
                if (response.code == 0) {
                	window.location.href = "/product-type/toProductTypes";
                } else {
                    toastr.error("保存失败!", "提示");
                    $("#loading").hide();
                }
            },
            error: function () {
                toastr.error("保存失败,请检查网络连接!", "错误");
            }
        });
    }
</script>

<i id="loading" class="fa fa-spinner fa-spin loading-image"></i>
</body>
</html>