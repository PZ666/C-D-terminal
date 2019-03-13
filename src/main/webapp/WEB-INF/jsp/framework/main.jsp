<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>农村电子商务信息管理平台</title>
<jsp:include page="/WEB-INF/jsp/framework/ico.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/framework/baseframework-resource.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		var ifm= document.getElementById("myiframe");
		ifm.height=document.documentElement.clientHeight;
	});
</script>

</head>
<body style="overflow-x: hidden; overflow-y: hidden;font-size:0;" class="pace-done fixed-nav fixed-nav-basic">
	<iframe style="margin-top:-8px;" align="left" height="100%" width="13%" scrolling="no" frameborder="0" marginheight="0" marginwidth="0" src="/system/initLeft"></iframe>
	<!-- <iframe id="myiframe" style="margin-top:-8px;" height="950" width="87%" scrolling="auto" src="/go/toIndex" name="main_window" align="right" src="/go/initRight" frameborder="0" marginheight="0" marginwidth="0"></iframe> -->
	<iframe id="myiframe" style="margin-top:-8px;" height="950" width="87%" scrolling="auto" src="/system/toIndex" name="main_window" align="right" frameborder="0" marginheight="0" marginwidth="0"></iframe>
</body>
</html>