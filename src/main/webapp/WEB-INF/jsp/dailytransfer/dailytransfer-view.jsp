<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <li class="active"><strong>查看数据传输</strong></li>
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
                    <h2>查看数据传输</h2>
                </div>
                <hr/>

                <form class="form-inline">
                    <div class="form-group">
                        <label>报表时间：</label>
                        <input type="text" readonly="readonly" value="<fmt:formatDate value="${requestScope.dailytransfer.reportTime}" pattern="yyyy/MM/dd" />" id="reportDate" class="form-control input-sm">
                    </div>
                    &nbsp;&nbsp;
                    <div class="form-group">
                        <label>状态：</label>
                        <!-- 传输状态,0:待传输，1:传输成功, 2:传输失败 -->
                        <c:choose>
                            <c:when test="${requestScope.dailytransfer.status == 0}">
                                <span class="label label-default">待传输</span>
                            </c:when>
                            <c:when test="${requestScope.dailytransfer.status == 1}">
                                <span class="label label-success">传输成功</span>
                            </c:when>
                            <c:when test="${requestScope.dailytransfer.status == 2}">
                                <span class="label label-danger">传输失败</span>
                            </c:when>
                            <c:otherwise>
                                <span class="label label-default">未知</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <br/><br/>
                </form>

                <div style="width:100%;">

                    <table class="table table-hover table-bordered">
                        <thead>
                            <tr>
                                <th>序号</th>
                                <th>站点编码（ID）</th>
                                <th>站点名称</th>
                                <th>站点类型</th>
                                <th>代买/代卖订单类型</th>
                                <th>代买/代卖商品类别</th>
                                <th>代买/代卖总金额（元）</th>
                                <th>代买总订单数（笔）</th>
                                <th>代卖总订单数（笔）</th>
                            </tr>
                        </thead>
                        <tbody id="dataBody">
                            <c:forEach var="site" items="${requestScope.sites}" varStatus="idx">
                                <tr id="${idx.count}" site-id="${site.id}">
                                    <td>站点${idx.count}</td>
                                    <td>${site.siteCode}</td>
                                    <td>${site.siteName}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${site.siteType == 1}">
                                                县级服务中心
                                            </c:when>
                                            <c:when test="${site.siteType == 2}">
                                                乡镇级服务站
                                            </c:when>
                                            <c:when test="${site.siteType == 3}">
                                                村级服务站
                                            </c:when>
                                            <c:otherwise>
                                                未知
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <%--<select class="form-control" id="currentBuySellType${idx.count}">
                                            <option value="-1">-- 请选择 --</option>
                                            <option value="代卖">代卖</option>
                                            <option value="代买">代买</option>
                                        </select>--%>
                                        <div id="buySellDiv${idx.count}">
                                            <c:forEach var="item" items="${requestScope.dailytransfer.orders}">
                                                <c:if test="${item.siteId == site.id}">
                                                    <input class="form-control" readonly="readonly" value="${item.buySellType}">
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </td>
                                    <td>
                                        <%--<select class="form-control" id="currentProductType${idx.count}">
                                            <option value="-1">-- 请选择 --</option>
                                            <c:forEach var="type" items="${requestScope.productTypes}">
                                                <option value="${type.id}">${type.productType}</option>
                                            </c:forEach>
                                        </select>--%>
                                        <div id="productTypeDiv${idx.count}">
                                            <c:forEach var="item" items="${requestScope.dailytransfer.orders}">
                                                <c:if test="${item.siteId == site.id}">
                                                    <input class="form-control" readonly="readonly" value="${item.res1}">
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </td>
                                    <td>
                                        <%--<input class="form-control" id="currentSumMoney${idx.count}"/>--%>
                                        <div id="sumMoneyDiv${idx.count}">
                                            <c:forEach var="item" items="${requestScope.dailytransfer.orders}">
                                                <c:if test="${item.siteId == site.id}">
                                                    <input class="form-control" readonly="readonly" value="${item.money}">
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </td>
                                    <td>
                                        <%-- <input class="form-control" value="0"/>--%><!-- 代买总订单数 -->
                                        <c:set var="buyCountLoopExit" value="0" scope="page" />
                                        <c:forEach var="item" items="${requestScope.dailytransfer.orders}">
                                            <c:if test="${item.siteId == site.id and buyCountLoopExit != 1}">
                                                <input class="form-control" readonly="readonly" value="${item.buyCount}"><!-- 代买总订单数 -->
                                                <c:set var="buyCountLoopExit" value="1" scope="page"  />
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <%--<input class="form-control" value="0"/>--%><!-- 代卖总订单数 -->
                                        <c:set var="sellCountLoopExit" value="0" scope="page" />
                                        <c:forEach var="item" items="${requestScope.dailytransfer.orders}">
                                            <c:if test="${item.siteId == site.id and sellCountLoopExit != 1}">
                                                <input class="form-control" readonly="readonly" value="${item.sellCount}"><!-- 代卖总订单数 -->
                                                <c:set var="sellCountLoopExit" value="1" scope="page"  />
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <c:if test="${requestScope.dailytransfer.status == 1}">
                        <div>
                            <a class="btn btn-primary" role="button" data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                                查阅XML数据源
                            </a>
                            <div class="collapse" id="collapseExample">
                                <div class="well">
                                    <c:out value="${requestScope.dailytransfer.xmlData }"  escapeXml="true" />
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <table id="hiddenValueTable">
                        <%--<tr>
                            <td>站点编号</td>
                            <td>订单类型</td>
                            <td>商品类别</td>
                            <td>总金额</td>
                        </tr>--%>
                    </table>

                    <br>
                    <button type="button" onclick="javascript:window.history.back();" class="btn btn-sm btn-default">返&nbsp;&nbsp;回</button>&nbsp;&nbsp;
                    <button type="button" onclick="toEdit('${requestScope.dailytransfer.id}');" class="btn btn-sm button-common">编&nbsp;&nbsp;辑</button>


                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    function toEdit(dailytransferId){
        window.location.href="/dailytransfer/toEdit?id="+dailytransferId;
    }

</script>

<i id="loading" class="fa fa-spinner fa-spin loading-image"></i>
</body>
</html>