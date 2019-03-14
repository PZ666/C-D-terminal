<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/jsp/framework/baseframework-resource.jsp"></jsp:include>
<%@ page isELIgnored="false" %>

<style>
	
	.baseframe_title_li:hover{
		cursor: pointer; 
	}

</style>
       <div id="page-wrapper" class="gray-bg dashbard-1" style="width:100%;height:1000px;">
        <div class="row border-bottom">
        <nav class="navbar navbar-fixed-top" role="navigation" style="margin-bottom: 0;height:60px;">
	         <ul class="nav navbar-top-links navbar-right">
	         	<!-- <li style="font-size:20px;" class="baseframe_title_li" onclick="openTalkWindow();"><i class="fa fa-commenting"></i><span class="badge"></span></li> -->
	         	<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
	         	<li style="font-size:20px;" class="baseframe_title_li" ><label style="font-size:12px;">版本 V1.0.1<label></li>
	         	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <li>
                       <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <span class="text-muted text-xs block" style="padding-right:0px;">
                        	${sessionScope.session_user.userName }
                        	<b class="caret"></b>
                        	&nbsp;&nbsp;
                        	<img style="border-radius:50px;" src="${sessionScope.session_user.faceUrl}" width="30px" height="30px">
                        </span>
                       </a>
                       <ul class="dropdown-menu animated fadeInRight m-t-xs">
                           <%--<li ><a data-toggle="modal" href="/permission/user/toViewUser?id=${sessionScope.session_user.id }">个人信息</a></li>
                           <li class="divider"></li>--%>
                           <li><a href="/system/logout">退出</a></li>
                       </ul>
                </li>
	          </ul>
	        </nav>
	        </div>
	          
	          
   <script type="text/javascript">
	   //Logo图点击事件
	   function gotoHome (){
	   		
	   }
	   
		function overShow(obj, e){
			var tdId = $(obj).attr("id")
			$("#"+tdId).css("background-color","#e7eaec");
			$("#"+tdId).css("cursor","pointer");
		}
		
		function outHide(obj, e) {//鼠标移出
			var tdId = $(obj).attr("id")
			$("#"+tdId).css("background-color","#fff");
		}
		
		function shows(a) {
		    $('#pageSize').text(a);
		    var page = 	$("#pageSize").html();
			init(1,page);
		}	
		
		//列表全选和反选
		function selectAll(args){
			var checkbox = $(args);
		 	if(checkbox.is(":checked")){
				$("#tbody input[type=checkbox]").prop("checked",true);
			}else{
				$("#tbody input[type=checkbox]").prop("checked",false);
			}
		}
		
		//构建分页方法
		function buildPager(pageinfo,pageSize){
			var k = 0;
			//构建分页选项卡
			var pager = $("#pageUl");
			pager.empty();
			var pageHTML = "";
			if(pageinfo.pages <= 7){
				//判断是否能点击上一页
				if(pageinfo.hasPreviousPage == true){
					pageHTML += "<li onclick='doSearch(\""+(pageinfo.pageNum-1)+"\","+pageSize+");'>";
				}else{
					pageHTML += "<li class='disabled'>";
				}
				pageHTML += "<a href='javascript:void(0);' aria-label='Previous' style='border-top-left-radius: 30px;border-bottom-left-radius: 30px;'>";
				pageHTML += "<span aria-hidden='true'>&laquo;</span>";
				pageHTML += "</a>";
				pageHTML += "</li>";
				var next = 0;
				for(var i = 0; i<pageinfo.navigatepageNums.length; i++){
					if(pageinfo.pageNum == pageinfo.navigatepageNums[i]){
						pageHTML += "<li class='active' onclick='doSearch(\""+(pageinfo.navigatepageNums[i])+"\","+pageSize+");'><a href='#'>"+pageinfo.navigatepageNums[i]+"</a></li>";
					}else{
						if(pageinfo.navigatepageNums[i] <= pageinfo.pages){
							pageHTML += "<li onclick='doSearch(\""+pageinfo.navigatepageNums[i]+"\","+pageSize+");'><a href='#'>"+pageinfo.navigatepageNums[i]+"</a></li>";
						}
						if(pageinfo.navigatepageNums[i] = pageinfo.pages){
							next = pageinfo.pages;
						}
						
					}
				}
				//判断是否还能点击下一页
				if(pageinfo.hasNextPage == true){
						pageHTML += "<li onclick='doSearch(\""+(pageinfo.pageNum+1)+"\","+pageSize+");'>";
				}else{
					pageHTML += "<li class='disabled'>";
				}
				pageHTML += "<a href='javascript:void(0);' aria-label='Next' style='border-top-right-radius: 30px;border-bottom-right-radius: 30px;'>";
				pageHTML += "<span aria-hidden='true'>&raquo;</span>";
				pageHTML += "</a>";
				pageHTML += "</li>";
				pager.append(pageHTML);
			}else{
				var str = pageinfo.pageNum-1;
				var j = pageinfo.pages-2;
				var pageNo = pageinfo.pageNum;
				var lackPage = pageinfo.navigatepageNums.length -(pageinfo.pages-pageinfo.pageNum+1);
				var startPage = pageNo - lackPage;
				if(lackPage > 0){
					for(var i=0;i<lackPage;i++){
						if(startPage < pageNo){
							//需要补页数
							//判断是否能点击上一页
							if(i==0){
								if(pageinfo.hasPreviousPage == true){
									pageHTML += "<li onclick='doSearch(\""+(str)+"\","+pageSize+");'>";
								}else{
									pageHTML += "<li class='disabled'>";
								}
								pageHTML += "<a href='javascript:void(0);' aria-label='Previous' style='border-top-left-radius: 30px;border-bottom-left-radius: 30px;'>";
								pageHTML += "<span aria-hidden='true'>&laquo;</span>";
								pageHTML += "</a>";
								pageHTML += "</li>";
							}
							pageHTML += "<li onclick='doSearch(\""+startPage+"\","+pageSize+");'><a href='#'>"+startPage+"</a></li>";
						}
						startPage++;
					}
					
				}else if(lackPage == 0){
					//页数刚刚满足固定宽度
					if(pageinfo.hasPreviousPage == true){
						pageHTML += "<li onclick='doSearch(\""+(str)+"\","+pageSize+");'>";
					}else{
						pageHTML += "<li class='disabled'>";
					}
					pageHTML += "<a href='javascript:void(0);' aria-label='Previous' style='border-top-left-radius: 30px;border-bottom-left-radius: 30px;'>";
					pageHTML += "<span aria-hidden='true'>&laquo;</span>";
					pageHTML += "</a>";
					pageHTML += "</li>";
				}else{
					if(pageinfo.hasPreviousPage == true){
						pageHTML += "<li onclick='doSearch(\""+(pageNo-1)+"\","+pageSize+");'>";
					}else{
						pageHTML += "<li class='disabled'>";
					}
					pageHTML += "<a href='javascript:void(0);' aria-label='Previous' style='border-top-left-radius: 30px;border-bottom-left-radius: 30px;'>";
					pageHTML += "<span aria-hidden='true'>&laquo;</span>";
					pageHTML += "</a>";
					pageHTML += "</li>";
				}
				for(var i = 0; i<pageinfo.navigatepageNums.length; i++){
					var index = pageinfo.navigatepageNums.length-1
					var strValue =1; //parseInt(pageinfo.navigatepageNums[i])-i==1?(pageinfo.navigatepageNums[i]):parseInt(pageinfo.navigatepageNums[i])-(i+1);
				//	var pageNo = parseInt(strValue)+parseInt(str);
					var currentNo = strValue+str+i;
					if(pageinfo.pageNum == strValue+str+i){
						pageHTML += "<li class='active' onclick='doSearch(\""+currentNo+"\","+pageSize+");'><a href='#'>"+pageNo+"</a></li>";
					}else{
						if(parseInt(pageinfo.pages) > parseInt(pageinfo.navigatePages)){
							//总页数大于8
							if(k < 3){
								if(pageNo <= pageinfo.pages){
									pageHTML += "<li onclick='doSearch(\""+pageNo+"\","+pageSize+");'><a href='#'>"+pageNo+"</a></li>";
								}
							}else if(k == 3){
								if(lackPage < 0){
									if(pageNo == j-1){
										//此时不显示省略号
										pageHTML += "<li onclick='doSearch(\""+pageNo+"\","+pageSize+");'><a href='#'>"+pageNo+"</a></li>";
									}else{
										pageHTML += "<li onclick='doSearch(\""+pageNo+"\","+pageSize+");'><a href='#'>"+"...."+"</a></li>";
									}
								}else{
									if(pageNo <= pageinfo.pages){
										pageHTML += "<li onclick='doSearch(\""+pageNo+"\","+pageSize+");'><a href='#'>"+pageNo+"</a></li>";
									}
									
								}
								
							}else{
								if(lackPage < 0){
									if(j== parseInt(pageinfo.pages)){
										pageHTML += "<li  onclick='doSearch(\""+j+"\","+pageSize+");'><a href='#'>"+j+"</a></li>";
									}else{
										pageHTML += "<li  onclick='doSearch(\""+j+"\","+pageSize+");'><a href='#'>"+j+"</a></li>";
									}
									j++;	
								}else{
									if(pageNo <= pageinfo.pages){
										pageHTML += "<li onclick='doSearch(\""+pageNo+"\","+pageSize+");'><a href='#'>"+pageNo+"</a></li>";
									}
								}
								
							}
						}
						
					}
					pageNo++;
					k++;
				}
				//判断是否还能点击下一页
				if(pageinfo.hasNextPage == true){
						pageHTML += "<li onclick='doSearch(\""+(pageinfo.pageNum+1)+"\","+pageSize+");'>";
				}else{
					pageHTML += "<li class='disabled'>";
				}
				pageHTML += "<a href='javascript:void(0);' aria-label='Next' style='border-top-right-radius: 30px;border-bottom-right-radius: 30px;'>";
				pageHTML += "<span aria-hidden='true'>&raquo;</span>";
				pageHTML += "</a>";
				pageHTML += "</li>";
				pager.append(pageHTML);
			}
		
		
			$("#pageNum_now_").html(pageinfo.pageNum);
			$("#totalPageNum").html(pageinfo.total);
		}
		
		//查询按钮点击事件
		function queryButton(){
			var pageSize = $("#pageSize").text();
			init(1,pageSize);
		}
		
		function doSearch(pageIndex,pageSize){
			init(pageIndex,pageSize);
		}
		
		//Go按钮点击事件
		function go(){
			var to = $("#jumpTo");
			if(isNaN(to.val())){
				to.focus();
				to.select();
				return;
			}
			if(to.val() == null || to.val() == ""){
				return;
			}
			var page = 	$("#pageSize").html();
			doSearch(to.val(),page);
		}
   </script>
