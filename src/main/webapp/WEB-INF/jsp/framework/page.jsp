<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<div class="foor-pages">
	<span style="float: right; padding-top: 5px;"> &nbsp;&nbsp;跳转到
		<button style="float: right;" onclick="go();"
			class="btn btn-primary btn-xs">Go</button> <input type="text"
		id="jumpTo" class="form-control input-sm"
		style="margin: 0 0 0 0; top: 0px; width: 38px; float: right; height: 22px; font-size: 10px;">&nbsp;
	</span>
	<ul class="pagination pagination-sm"
		style="float: right; margin-top: 4px;margin-right:85px;position:relative;" id="pageUl">
	</ul>
	
	<%--<ul class="pagination pagination-sm"
		style=" margin-top: 4px;">
		<!-- <div class="pull-left pagination-detail" style="margin-left:1050px;margin-top:-3px;position:absolute;left:44px;"> -->
		<div class="" style="margin-top:-3px;right:12%;float:right;position:absolute;">
		<!-- <span>当前第</span> <span class="pagination-info" id="pageNum_now_"></span><span>页</span> -->
		<span class="page-list"><span class="btn-group dropup">
					<button type="button" class="btn btn-default"
						data-toggle="dropdown">
						<span class="page-size" id="pageSize">10</span> <span class="caret"></span>
					</button>
				<ul class="dropdown-menu" role="menu" id="pageSelect">
					<li>
						<table class="table table-bordered" id="table">
							<tr>
								<td onmouseover="overShow(this,event)"
									onmouseout="outHide(this,event)" onclick="shows($(this).text())" color: gray; id="pageTd1">5</td>
								<td onmouseover="overShow(this,event)"
									onmouseout="outHide(this,event)" onclick="shows($(this).text())" color: gray; id="pageTd2">10</td>
							</tr>
							<tr>
								<td onmouseover="overShow(this,event)"
									onmouseout="outHide(this,event)" onclick="shows($(this).text())" color: gray; id="pageTd3">15</td>
								<td onmouseover="overShow(this,event)"
									onmouseout="outHide(this,event)" onclick="shows($(this).text())" color: gray; id="pageTd4">20</td>
							</tr>
							<tr>
								<td onmouseover="overShow(this,event)"
									onmouseout="outHide(this,event)" onclick="shows($(this).text())" color: gray; id="pageTd5">30</td>
								<td onmouseover="overShow(this,event)"
									onmouseout="outHide(this,event)" onclick="shows($(this).text())" color: gray; id="pageTd6">40</td>
							</tr>
							<tr>
								<td onmouseover="overShow(this,event)"
									onmouseout="outHide(this,event)" onclick="shows($(this).text())" color: gray; id="pageTd7">45</td>
								<td onmouseover="overShow(this,event)"
									onmouseout="outHide(this,event)" onclick="shows($(this).text())" color: gray; id="pageTd8">50</td>
							</tr>
							<tr>
								<td onmouseover="overShow(this,event)"
									onmouseout="outHide(this,event)" onclick="shows($(this).text())" color: gray; id="pageTd9">100</td>
								<td onmouseover="overShow(this,event)"
									onmouseout="outHide(this,event)" onclick="shows($(this).text())" color: gray; id="pageTd10">200</td>
							</tr>
							<tr>
								<td onmouseover="overShow(this,event)"
									onmouseout="outHide(this,event)" onclick="shows($(this).text())" color: gray; id="pageTd11">500</td>
								<td onmouseover="overShow(this,event)"
									onmouseout="outHide(this,event)" onclick="shows($(this).text())" color: gray; id="pageTd12">1000</td>
							</tr>
							<tr>
								<td onmouseover="overShow(this,event)"
									onmouseout="outHide(this,event)" onclick="shows($(this).text())" color: gray; id="pageTd13">1500</td>
								<td onmouseover="overShow(this,event)"
									onmouseout="outHide(this,event)" onclick="shows($(this).text())" color: gray; id="pageTd14">2000</td>
							</tr>
						</table>
					</li>
				</ul>
				
				<br> </span>
	</div>
	</ul>--%>
	<ul class="pagination pagination-sm" style="margin:0 0 0 0;padding:0 0 0 0;float:right;margin-right:20px;margin-top:8px;position:relative;">
		<span style="padding-left: 10px;">共</span><span id="totalPageNum"></span>条记录</span> 
	</ul>
</div>

