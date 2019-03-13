<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript">
		function lightHight(args){
			$("#side-menu a").each(function(){
				var $a = $(this);
				$a.css("background-color","");
				$a.css("color","");
			});
			$(args).css("background-color","#f0f2fa");
			$(args).css("color","#373330");
		}
	</script>
</head>
<body>
	<!-- 页面左侧菜单区域 -->
	<jsp:include page="/WEB-INF/jsp/framework/baseframework-resource.jsp" ></jsp:include>
	<nav class="navbar-default" role="navigation" >
            <div class="sidebar-collapse" style="position:absolute; height:83%;width:100%;overflow-x: hidden;overflow-y: auto; ">
                <ul class="nav metismenu" id="side-menu" style="width:100%;">
                    <li class="nav-header" onclick="gotoHome()">
                        
                        <div class="logo-element">
                       　                                       
                        </div>
                    </li>
	               <c:forEach items="${sessionScope.menus}" var="ele">
	                   <c:if test="${empty ele.fid}">
                    		<li name="${ele.id}">
		                        <a href="${ele.url}" onclick="lightHight(this);" style="width:100%;" target="main_window"><i class="${ele.iconurl}"></i> <span class="nav-label">${ele.menuname}</span>
		                         <c:if test="${empty ele.url }">
		                        	<span class="fa arrow"></span>
		                         </c:if>
		                        
		                        </a>
		                        <c:if test="${empty ele.url}">
		                        	<ul class="nav nav-second-level collapse">
		                        </c:if>
		                        <c:forEach items="${sessionScope.menus}" var="ele1">
		                        	<c:if test="${not empty ele1.fid and ele1.fid == ele.id}">
		                        	
		                        		<li name="${ele1.id}">
					                        <a target="main_window" style="width:100%;" href="${ele1.url}" onclick="lightHight(this);"><i class=""></i> <span class="nav-label">${ele1.menuname}</span>
					                         <c:if test="${empty ele1.url || ele1.url == ''}">
					                        	<span class="fa arrow"></span>
					                         </c:if>
					                        </a>
					                        <c:if test="${empty ele1.url}">
					                        	<ul class="nav nav-second-level collapse">
					                        </c:if>
					                        <c:forEach items="${sessionScope.menus}" var="ele2">
					                        	<c:if test="${not empty ele2.fid and ele2.fid == ele1.id}">
					                        		<li name="${ele2.id}"><a href="${ele2.url}" onclick="lightHight(this);" style="width:100%;" target="main_window">${ele2.menuname}</a></li>
					                        	</c:if>	
					                        </c:forEach>
					                        <c:if test="${empty ele1.url}">
					                        	</ul>
					                        </c:if>
			                   			 </li>
			                        	</c:if>	
			                        </c:forEach>
			                        <c:if test="${empty ele.url}">
			                        	</ul>
			                        </c:if>
		                    </li>
                    	</c:if>
	               </c:forEach>
                </ul>
             </div>
        </nav>
</body>
</html>