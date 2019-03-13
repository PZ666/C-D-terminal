<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../framework/ico.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>全局资源配置 | 农村电子商务信息管理平台</title>
</head>
<body  class="pace-done fixed-nav fixed-nav-basic">
	<div id="wrapper">
		<%@ include file="../framework/baseframework.jsp"%>


		<!-- 位置导航 -->
		<div style="background-color: #f0f2fa;"
			class="row wrapper border-bottom white-bg page-heading">
			<div style="width: 100%; background-color: #f0f2fa;">
				<ol class="breadcrumb"
					style="background-color: #f0f2fa; margin-buttom: 0px; padding-buttom: 1px; font-size: 14px;">
					<li class="active"><strong>系统资源配置</strong></li>
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
			     <h2>全局资源配置</h2>
				</div>
				<div class="alert alert-warning alert-dismissible" role="alert">
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				  <strong>警告！</strong> 修改以下数据将可能导致系统部分功能不可用。因此当不确定其具体配置用途时，请勿擅自修改！
				</div>
			  	<hr/>
			  	
			  	<div  style="width:100%;">
			  		<form class="form-horizontal">
					  <div class="form-group">
					    <label for="company_id" class="col-sm-2 control-label">商务部.企业编号：</label>
					    <div class="col-sm-10">
					      <input id="company_id" value="${requestScope.companyId }" type="text" style="width:500px;" class="form-control" >
					    </div>
					  </div>
					</form>
			  	</div>
			  	
			  	<br>
			  	<div style="float:left;" class="foot-buttons">
					<button id="update_btn" type="button" onclick="update();" class="btn btn-sm button-common">更&nbsp;&nbsp;新</button>
				</div>
			  </div>
			 </div>
		</div>
	</div>
	
	<script type="text/javascript">
		function update(){
			var params = {};
			params.companyId = $("#company_id").val();
			$("#update_btn").prop("disabled",true);
			$.ajax({
				url:"/config/doSave",
				cache:false,
				type:"post",
				data:params,
				success:function(response){
					toastr.success("更新成功!","提示");
					$("#update_btn").prop("disabled",false);
				},
				error:function(){
					toastr.error("操作失败,请检查网络连接!","错误");
					$("#update_btn").prop("disabled",false);
				}
			});
			
		}
	</script>
	
	<i id="loading" class="fa fa-spinner fa-spin loading-image"></i>
</body>
</html>