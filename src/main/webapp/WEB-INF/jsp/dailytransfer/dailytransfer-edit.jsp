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
<title>编辑数据传输 | 农村电子商务信息管理平台</title>
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
                <li class="active"><strong>编辑数据传输</strong></li>
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
                    <h2>编辑数据传输</h2>
                </div>
                <hr/>

                <form class="form-inline">
                    <div class="form-group">
                        <label>报表时间：</label>
                        <input type="text" value="<fmt:formatDate value="${requestScope.dailytransfer.reportTime}" pattern="yyyy/MM/dd" />" id="reportDate" class="form-control input-sm">
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
                                                    <input class="form-control" order-id="${item.id}" value="${item.money}">
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </td>
                                    <td>
                                        <%-- <input class="form-control" value="0"/>--%><!-- 代买总订单数 -->
                                        <c:set var="buyCountLoopExit" value="0" scope="page" />
                                        <c:forEach var="item" items="${requestScope.dailytransfer.orders}">
                                            <c:if test="${item.siteId == site.id and buyCountLoopExit != 1}">
                                                <input class="form-control" value="${item.buyCount}"><!-- 代买总订单数 -->
                                                <!-- 设置循环退出条件 -->
                                                <c:set var="buyCountLoopExit" value="1" scope="page"  />
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <%--<input class="form-control" value="0"/>--%><!-- 代卖总订单数 -->
                                        <c:set var="sellCountLoopExit" value="0" scope="page" />
                                        <c:forEach var="item" items="${requestScope.dailytransfer.orders}">
                                            <c:if test="${item.siteId == site.id and sellCountLoopExit != 1}">
                                                <input class="form-control" value="${item.sellCount}"><!-- 代卖总订单数 -->
                                                <!-- 设置循环退出条件 -->
                                                <c:set var="sellCountLoopExit" value="1" scope="page"  />
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>


                    <table id="hiddenValueTable" style="display: none;">
                        <%--<tr>
                            <td>站点编号</td>
                            <td>订单编号</td>
                            <td>总金额</td>
                            <td>代买总订单数</td>
                            <td>代卖总订单数</td>
                        </tr>--%>
                    </table>

                    <br>
                    <button type="button" onclick="javascript:window.history.back();" class="btn btn-sm btn-default">返&nbsp;&nbsp;回</button>&nbsp;&nbsp;
                    <button type="button" onclick="doEdit('${requestScope.dailytransfer.id}');" class="btn btn-sm button-common">更&nbsp;&nbsp;新</button>


                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    $(function(){
        $("#reportDate").datepicker({
            autoclose:true
        });
    });

    function doEdit(dailytransferId){

        var rp =$("#reportDate");
        if(rp.val() == ""){
            rp.focus();
            layx.msg("请选择需要修改的报表时间!", {
                dialogIcon: 'error'
            });
            return;
        }

        var hiddenValueTable = $("#hiddenValueTable");
        hiddenValueTable.html("");

        var dataBody = $("#dataBody");
        var trs = dataBody.find("tr");

        var jsonBody = {};
        var array = [];

        for(var i = 0; i < trs.length; i++){
            var tr = trs[i];
            var inputs = $(tr).find("td").eq(6).find("input");
            var siteId = $(tr).attr("site-id");
            var orderType = $(tr).find("td").eq(4).children(":first").children(":first").val();
            var goodsType = $(tr).find("td").eq(5).children(":first").children(":first").val();

            if(!isBlank(orderType) && !isBlank(goodsType)) {
                for(var k = 0; k<inputs.length; k++){
                    var input = $(inputs[k]);
                    if(isBlank(input.val()) || isNaN(input.val())){
                        layx.msg( "代买、代卖订单总金额不合法!", {
                            dialogIcon: 'error'
                        });
                        input.focus();
                        return;
                    }
                }
            }

            // 查询该行最后代买代卖订单数
            var buyCount = $(tr).find("td").eq(7).children(":first");// 代买订单数;
            var sellCount = $(tr).find("td").eq(8).children(":first");// 代卖订单数;

            if(isBlank(buyCount.val()) || isNaN(buyCount.val())){
                if(!isBlank(orderType) && !isBlank(goodsType)) {
                    var siteName = $(tr).find("td").eq(2).text();
                    layx.msg(siteName + "代买订单数不合法!", {
                        dialogIcon: 'error'
                    });
                    buyCount.focus();
                    return;
                }
            }
            if(isBlank(sellCount.val()) || isNaN(sellCount.val())){
                if(!isBlank(orderType) && !isBlank(goodsType)) {
                    var siteName = $(tr).find("td").eq(2).text();
                    layx.msg(siteName + "代卖订单数不合法!", {
                        dialogIcon: 'error'
                    });
                    sellCount.focus();
                    return;
                }
            }

            for(var j = 0; j < inputs.length; j++){
                var input = $(inputs[j]);
                var appendTrHtml = "";
                appendTrHtml += "<tr>";
                appendTrHtml += "<td>"+siteId+"</td>";
                appendTrHtml += "<td>"+input.attr("order-id")+"</td>";
                appendTrHtml += "<td>"+input.val()+"</td>";
                appendTrHtml += "<td>"+buyCount.val()+"</td>";
                appendTrHtml += "<td>"+sellCount.val()+"</td>";
                appendTrHtml += "</tr>";
                hiddenValueTable.append(appendTrHtml);

                var item = {};
                item.siteId = siteId;
                item.orderId = input.attr("order-id");
                item.sumMoney = input.val();
                item.buyCount = buyCount.val();
                item.sellCount = sellCount.val();
                array.push(item);
            }
        }
        // 将array变成json字符串提交后台
        jsonBody.orders = array;
        jsonBody.reportDate = $("#reportDate").val();
        $.ajax({
            url:"/dailytransfer/update",
            cache:false,
            type:"post",
            data:{
                jsonStr:JSON.stringify(jsonBody),
                dailytransferId:dailytransferId
            },
            success:function (response) {
                if(response.code == 0){
                    window.location.href="/dailytransfer/list";
                }else{
                    layx.msg(response.message, {
                        dialogIcon: 'error'
                    });
                }
            },
            error:function () {
                layx.msg("更新失败,请假检查网络连接!", {
                    dialogIcon: 'error'
                });
            }
        });
    }

    function isBlank(str){
        if(str == null || typeof str == "undefined" ||
            str == "" || trim(str) == ""){
            return true;
        }
        return false;
    };

</script>

<i id="loading" class="fa fa-spinner fa-spin loading-image"></i>
</body>
</html>