
/*所有页面全局事件*/
$(function(){
	//注册ajax全局过滤，拦截后台传入的未登录响应并且跳转到登录页
	initAjaxSetup();
});

function initAjaxSetup(){
	$.ajaxSetup({
	    type : 'POST',
	    dataFilter : function(data, type){
	    	var json = JSON.parse(data);
	    	if(json.code == 99){
                // ajax请求，发现session过期，重新刷新页面，跳转到登录页面
                var top = getTopWinow();
                top.location.href = '/login.jsp';
			}else{
			    return data;
			}
		}
	});
}


function getTopWinow() {
	var p = window;
	while (p != p.parent) {
		p = p.parent;
	}
	return p;
}

