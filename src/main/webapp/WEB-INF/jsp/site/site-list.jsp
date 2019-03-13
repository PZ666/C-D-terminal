<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../framework/ico.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>站点列表 | 农村电子商务信息管理平台</title>
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
					<li class="active"><strong>站点列表</strong></li>
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
			     <h2>站点列表</h2>
				</div>
			  	
			  	<!-- 搜索框区域 -->
			 	<div class="list-title-search">
				     <form class="form-inline">
				     	<div class="form-group">
							 <label>站点类别：</label>
							 <select style="width:120px;" id="siteType" class="form-control">
								 <option value="-1">-- 请选择 --</option>
								 <option value="1">县级服务中心</option>
								 <option value="2">乡镇级服务站</option>
								 <option value="3">村级服务站</option>
						     </select>
						 </div>&nbsp;&nbsp;&nbsp;
						 <div class="form-group">
							 <label>站点名称：</label>
							 <input type="text" id="siteName" class="form-control input-sm">
						 </div>&nbsp;&nbsp;&nbsp;
						 <div class="form-group">
							 <label>站点编码：</label>
							 <input type="text" id="siteCode" class="form-control input-sm">
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
					  		<th>站点名称</th>
					  		<th>站点编码</th>
					  		<th>站点类别</th>
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
				  		<button onclick="javascript:window.location.href='/site/toAddSite'" class="btn btn-sm button-common">新增</button>
				  		<button onclick="javascript:window.location.href='/site/downloadTemplate'" class="btn btn-sm button-common">下载导入模板</button>
						<button onclick="openFileWindow()" class="btn btn-sm button-common">批量导入</button>&nbsp;&nbsp;
						<input type="file" name="siteFile" accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" onchange="importSite(this)" style="display:none;">
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
			$("#delBtn").click(remove);
			init(1,pageSize);
		});

        function openFileWindow() {
            $("input[name='siteFile']").click();
        }

        function importSite(obj) {
            var file = obj.files[0];
            if(!obj.files) {
                return;
            }
            var fileName = file.name;
            fileSuffix = fileName.substring(fileName.lastIndexOf("."));
            if(fileSuffix != ".xls" && fileSuffix != ".xlsx"){
                toastr.warning("上传文件必修是必须是excel类型文件！", {time: 1000, icon: 1});
                return;
            }

            var formFile = new FormData();
            //加入文件对象
            formFile.append("siteFile", file);
            $("#loading").show();
            $.ajax({
                url: "/site/importSites",
                data: formFile,
                type: "POST",
                dataType: "json",
                cache: false,//上传文件无需缓存
                processData: false,////必须
                contentType: false, //必须
                success: function (response) {
                    if(response.code == 0){
                        //导入成功
                        toastr.success(response.message,"提示");
                        setTimeout(function(){
                            window.location.reload()
                        }, 1000 )
                        $("#loading").hide();
                    }else{
                        //导入失败
                        //toastr.error("批量导入失败,"+response.message,"提示");
                        var d = dialog({
                            title: '批量导入失败',
                            content: response.message,
                            okValue: '确定',
                            ok: function () {
                                window.location.reload()
                                return true;
                            }
                        });
                        d.show();
                        $("#loading").hide();
                    }
                },
                error:function(){
                    toastr.error("批量导入失败,请检查网络连接!","错误");
                    setTimeout(function(){
                        window.location.reload()
                    }, 1000 )
                    $("#loading").hide();
                }
            });

        }
		
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
	        			url:"/site/batchRemove?ids="+adIds,
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
			params.siteName = $("#siteName").val();
			params.siteCode = $("#siteCode").val();
			params.siteType = $("#siteType").val();
			$("#loading").css("display","block");
			var tbody = $("#tbody");
			var html = "";
			tbody.html(html);
			$.ajax({
				url:"/site/initSites",
				cache:false,
				type:"post",
				data:params,
				success:function(response){
					var data = response.data.sites.list;
					var startLine = response.data.sites.startRow;
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
							html += "<td>"+data[i].siteName+"</td>";
							html += "<td>"+data[i].siteCode+"</td>";
							if(data[i].siteType == "1"){
                                html += "<td>县级服务中心</td>";
							}else if (data[i].siteType == "2"){
                                html += "<td>乡镇级服务站</td>";
							}else if(data[i].siteType == "3"){
                                html += "<td>村级服务站</td>";
							}else{
                                html += "<td>未知</td>";
							}
							html += "<td>"+data[i].updateTime+"</td>";
							html += "<td>"+data[i].updateId+"</td>";
							html += "<td>";
								html += "<button onclick='toView(\""+data[i].id+"\");' class='btn btn-info btn-xs'>查看</button>&nbsp;";
							html += "</td>";
						html += "</tr>";
						startLine++;//行号递增
					}
					tbody.html(html);
					//构建分页选项卡
					var pageinfo = response.data.sites;
					buildPager(pageinfo,pageSize);
					
					$("#loading").css("display","none");
				},
				error:function(){
					
				}
			});
			
		}
		
		function toView(id){
			window.location.href="/site/toViewSite?id="+id;
		}
	</script>
	
	
	<i id="loading" class="fa fa-spinner fa-spin loading-image"></i>
</body>
</html>