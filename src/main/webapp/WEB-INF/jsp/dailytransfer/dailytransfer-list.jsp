<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../framework/ico.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品类型列表 | 农村电子商务信息管理平台</title>
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
					<li class="active"><strong>上传列表</strong></li>
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
			     <h2>数据传输列表</h2>
				</div>
			  	
			  	<!-- 搜索框区域 -->
			 	<div class="list-title-search">
				     <form class="form-inline">
				     	<div class="form-group">
							 <label>传输状态：</label>
							 <select style="width:120px;" id="status" class="form-control">
								<option value="-1">-- 请选择 --</option>
                               	<option value="0">待传输</option>
                               	<option value="1">传输成功</option>
                               	<option value="2">传输失败</option>
						     </select>
						 </div>&nbsp;&nbsp;&nbsp;
						 <div class="form-group">
							 <label>开始时间：</label>
							 <input type="text" id="startTime" class="form-control input-sm">
						 </div>&nbsp;&nbsp;&nbsp;
						 <div class="form-group">
							 <label>结束时间：</label>
							 <input type="text" id="endTime" class="form-control input-sm">
						 </div>&nbsp;&nbsp;&nbsp;
					 	 <button type="button" onclick="queryButton();" class="btn btn-default btn-sm">查&nbsp;找</button>&nbsp;&nbsp;
					</form>
				</div>
			  	
			  	<div class="list-table-header">
			 		<!-- table table-hover;table table-striped -->
			 		<table class="table table-striped">
					  <thead>
					  	<tr>
					  		<th style="width:1%;">
					  			<div id="checkboxDiv" class="checkbox" style="margin-top:0px;margin-bottom:0px;">
				                     <input onchange="selectAll(this);" class="styled" type="checkbox"><label></label>
				                </div>
					  		</th>
					  		<th>序号</th>
							<th>创建时间</th>
							<th>创建人</th>
					  		<th>报表时间</th>
					  		<th>状态</th>
							<th>最后操作时间</th>
					  		<th>最后操作人</th>
					  		<th>操作</th>
					  	</tr>
					  </thead>
					  <tbody id="tbody">
					  	
					  </tbody>
					 </table>
			 	 </div>
			  
			  	<div>
				  	<div class="foot-buttons">
				  		<button id="delBtn" class="btn btn-danger btn-sm button-common">删除</button>&nbsp;&nbsp;
				  		<button onclick="javascript:window.location.href='/dailytransfer/toAddDailytransfer'" class="btn btn-sm button-common">新增</button>
				  	</div>
			 		<!-- 分页公共jsp引入  --> 
		  		    <jsp:include   page="../framework/page.jsp" flush="true"/>
			  	</div>
			  </div>
			 </div>
		</div>
	</div>
	<script type="text/javascript">
	
		//每一页显示的数量
		var pageSize = 10;
	
		$(function(){

            $("#startTime").datepicker({
                autoclose:true
            });
            $("#endTime").datepicker({
                autoclose:true
            });

			$("#delBtn").click(remove);
			init(1,pageSize);
		});
		
		//删除按钮点击事件
		function remove(){
			var adIds = "";  
	        $("#tbody input[type=checkbox]:checked").each(function(i){  
	            if(0==i){  
	                adIds = $(this).val();  
	            }else{  
	                adIds += (","+$(this).val());  
	            }  
	        });
	        if(adIds == ""){
	        	toastr.error("请至少选择一条需要删除的记录!"); 
	        	return;
	        }
	        var d = dialog({
	        	title: '提示',
	        	content: '确认删除吗？',
	        	okValue: '确定',
	        	ok: function () {
	        		$("#loading").css("display","block");
	        		$.ajax({
	        			url:"/dailytransfer/batchRemove?ids="+adIds,
	        			cache:false,
	        			type:"GET",
	        			success:function(response){
	        				if(response.code == 0){
	        					//删除成功
	        					doSearch(1,pageSize);
	        					toastr.success(response.message,"提示"); 
				        		$("#loading").css("display","none");
	        				} else {
	        					//删除失败
	        					toastr.error(response.message,"提示"); 
				        		$("#loading").css("display","none");
	        				}
	        			},
	        			error:function(){
	        				toastr.error("删除失败,请检查网络连接!","错误"); 
			        		$("#loading").css("display","none");
	        			}
	        		});
	        	},
	        	cancelValue: '取消',
	        	cancel: function () {}
	        });
	        d.show();
		}
		
		function init(pageIndex,pageSize){
			var params = {};
			params.pageIndex = pageIndex;
			params.pageSize = pageSize;
			params.status = $("#status").val();
			params.startTime = $("#startTime").val();
			params.endTime = $("#endTime").val();
			$("#loading").css("display","block");
			var tbody = $("#tbody");
			var html = "";
			tbody.html(html);
			$.ajax({
				url:"/dailytransfer/initFers",
				cache:false,
				type:"POST",
				data:params,
				success:function(response){
					var data = response.data.list.list;
					var startLine = response.data.list.startRow;
					for(var i = 0; i<data.length; i++){
						html += "<tr>";
							html += "<td>";
								html += "<div class='checkbox' style='margin-top:0px;margin-bottom:0px;'>";
								html += "<input class='styled' value='"+data[i].id+"' type='checkbox'><label></label>";
								html += "</div>";
							html += "</td>";
							html += "<td>"+(startLine)+"</td>";
                        	html += "<td>"+data[i].createTime+"</td>";
                        	html += "<td>"+data[i].createId+"</td>";
							html += "<td>"+data[i].reportTime.substring(0,10)+"</td>";
							if(data[i].status == 0){
                                html += "<td>待传输</td>";
							}else if(data[i].status == 1){
                                html += "<td>传输成功</td>";
							}else if(data[i].status == 2){
                                html += "<td>传输失败</td>";
                            }else{
                                html += "<td>未知</td>";
							}
							html += "<td>"+data[i].updateTime+"</td>";
							html += "<td>"+data[i].updateId+"</td>";
							html += "<td>";
								if(data[i].status != 1){
                                    html += "<button onclick='doUpstream(\""+data[i].id+"\");' class='btn btn-danger btn-xs'>上传</button>&nbsp;";
								}
                        		html += "<button onclick='toView(\""+data[i].id+"\");' class='btn btn-info btn-xs'>查看</button>&nbsp;";
							html += "</td>";
						html += "</tr>";
						startLine++;//行号递增
					}
					tbody.html(html);
					//构建分页选项卡
					var pageinfo = response.data.list;
					buildPager(pageinfo,pageSize);
					
					$("#loading").css("display","none");
				},
				error:function(){
					
				}
			});
			
		}

		function doUpstream(id){
		    $.ajax({
				url:"/dailytransfer/uploadToEc",
				cache:false,
				type:"post",
				data:{
				    id:id
				},
				success:function (response) {
					if(response.code == 0){
                        toastr.success(response.message,"上传成功");
					}else{
                        toastr.warning(response.message,"上传失败");
                    }
                },
				error:function () {
                    toastr.error("操作失败,请检查网络连接!","错误");
                }
			});
		}

		function toView(id){
			window.location.href="/dailytransfer/toView?id="+id;
		}
	</script>
	
	
	<i id="loading" class="fa fa-spinner fa-spin loading-image"></i>
</body>
</html>