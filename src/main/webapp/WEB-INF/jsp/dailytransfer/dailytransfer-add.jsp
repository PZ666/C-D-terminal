<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
                <li class="active"><strong>新建数据传输</strong></li>
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
                    <h2>新建数据传输</h2>
                </div>
                <hr/>

                <form class="form-inline">
                    <div class="form-group">
                        <label>报表时间：</label>
                        <input type="text" id="reportDate" class="form-control input-sm">
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
                                <th style="width:150px;">代买/代卖总金额（元）</th>
                                <th style="width:150px;">代买总订单数（笔）</th>
                                <th style="width:150px;">代卖总订单数（笔）</th>
                                <th>操作</th>
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
                                        <select class="form-control" id="currentBuySellType${idx.count}">
                                            <option value="-1">-- 请选择 --</option>
                                            <option value="代卖">代卖</option>
                                            <option value="代买">代买</option>
                                        </select>
                                        <div id="buySellDiv${idx.count}"></div>
                                    </td>
                                    <td>
                                        <select class="form-control" id="currentProductType${idx.count}">
                                            <option value="-1">-- 请选择 --</option>
                                            <c:forEach var="type" items="${requestScope.productTypes}">
                                                <option value="${type.id}">${type.productType}</option>
                                            </c:forEach>
                                        </select>
                                        <div id="productTypeDiv${idx.count}"></div>
                                    </td>
                                    <td>
                                        <input class="form-control" id="currentSumMoney${idx.count}"/>
                                        <div id="sumMoneyDiv${idx.count}"></div>
                                    </td>
                                    <td>
                                        <input class="form-control" value="0"/><!-- 代买总订单数 -->
                                    </td>
                                    <td>
                                        <input class="form-control" value="0"/><!-- 代卖总订单数 -->
                                    </td>
                                    <td>
                                        <button onclick="tempAdd('${idx.count}','${site.id}');" class="btn btn-xs btn-primary">添加</button>&nbsp;
                                        <button onclick="tempClear('${idx.count}','${site.id}');" class="btn btn-xs btn-danger">清空</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>


                    <table id="hiddenValueTable" style="display: none;">
                        <%--<tr>
                            <td>站点编号</td>
                            <td>订单类型</td>
                            <td>商品类别</td>
                            <td>总金额</td>
                        </tr>--%>
                    </table>

                    <br>
                    <button type="button" onclick="javascript:window.history.back();" class="btn btn-sm btn-default">返&nbsp;&nbsp;回</button>&nbsp;&nbsp;
                    <button type="button" onclick="save();" class="btn btn-sm button-common">保&nbsp;&nbsp;存</button>


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

    function save(){

        var reportDate = $("#reportDate");
        if(reportDate.val() == ""){
            layx.msg('请选择报表时间！', {
                dialogIcon: 'error'
            });
            reportDate.focus();
            return;
        }
        var hiddenTable = $("#hiddenValueTable");
        var trs = hiddenTable.find("tr");
        if(trs.length == 0){
            layx.msg('请至少保存一条数据！', {
                dialogIcon: 'error'
            });
            return;
        }


        // 检查页面上代买和代卖订单数
        var dataBody = $("#dataBody");
        var dataBodyTrs = dataBody.find("tr");

        var siteIdOrderCount = [];


        for(var i = 0; i<dataBodyTrs.length; i++){
            var tr = dataBodyTrs[i];
            var buyCount = $(tr).find("td").eq(7).children(":first");// 代买订单数
            var sellCount = $(tr).find("td").eq(8).children(":first");// 代卖订单数


            var _orderType = $(tr).find("td").eq(4).children(":last");
            var _goodsType = $(tr).find("td").eq(5).children(":last");


            if(isBlank(buyCount.val()) || isNaN(buyCount.val())){
                if(!isBlank(_orderType.html()) && !isBlank(_goodsType.html())){
                    layx.msg('请输入合法的代买订单数！', {
                        dialogIcon: 'error'
                    });
                    buyCount.focus();
                    return;
                }
            }
            if(isBlank(sellCount.val()) || isNaN(sellCount.val())){
                if(!isBlank(_orderType.html()) && !isBlank(_goodsType.html())) {
                    layx.msg('请输入合法的代卖订单数！', {
                        dialogIcon: 'error'
                    });
                    sellCount.focus();
                    return;
                }
            }

            var siteId = $(tr).attr("site-id");// 该行对应的隐藏站点ID
            var orderItem = {};
            orderItem.buyCount = buyCount.val();
            orderItem.sellCount = sellCount.val();
            orderItem.siteId = siteId;
            siteIdOrderCount.push(orderItem);
        }

        var orderInfo = [];
        for(var i = 0; i < trs.length; i++){
            var tr = trs[i];
            var siteCode = $(tr).find("td").eq(0).text();
            var buySellType = $(tr).find("td").eq(1).text();
            var productType = $(tr).find("td").eq(2).text();
            var sumMoney = $(tr).find("td").eq(3).text();
            var item = {};
            item.siteCode = siteCode;
            item.buySellType = buySellType;
            item.productType = productType;
            item.sumMoney = sumMoney;
            orderInfo.push(item);
        }

        var pageInfo = {};
        pageInfo.orderInfo = orderInfo;
        pageInfo.siteIdOrderCount = siteIdOrderCount;
        pageInfo.reportDate = reportDate.val();

        var jsonStr = JSON.stringify(pageInfo);

        $.ajax({
            url:"/dailytransfer/save",
            cache:false,
            type:"post",
            data:{
                jsonStr:jsonStr
            },
            success:function(response){
                if(response.code == 0){
                    window.location.href="/dailytransfer/list";
                }else{
                    layx.msg(response.message, {
                        dialogIcon: 'error'
                    });
                }
            },
            error:function () {
                layx.msg('保存失败,请检查网络连接！', {
                    dialogIcon: 'error'
                });
            }
        });
    }

    function tempClear(lineId,siteId) {
        var hiddenValueTable = $("#hiddenValueTable");
        var trs = hiddenValueTable.find("tr");
        for(var i = trs.length; i >= 0; i--){
            var lineSiteId = $(trs[i]).find("td").eq(0).text().trim();
            if(lineSiteId == siteId){
                $(trs[i]).remove();
            }
        }
        $("#buySellDiv" + lineId).html("");
        $("#productTypeDiv" + lineId).html("");
        $("#sumMoneyDiv" + lineId).html("");
    }

    function tempAdd(lineId,siteId){

        var buySellDiv = $("#buySellDiv" + lineId);
        var productTypeDiv = $("#productTypeDiv"+lineId);
        var sumMoneyDiv = $("#sumMoneyDiv"+lineId);

        var currentBuySellType = $("#currentBuySellType" + lineId);
        var currentProductType = $("#currentProductType" + lineId);
        var currentSumMoney = $("#currentSumMoney" + lineId);

        if(currentBuySellType.val() == "-1"){
            layx.msg('请选择订单类型！', {
                dialogIcon: 'error'
            });
            currentBuySellType.focus();
            return;
        }
        if(currentProductType.val() == "-1"){
            layx.msg('请选择商品类型！', {
                dialogIcon: 'error'
            });
            currentProductType.focus();
            return;
        }
        if(isBlank(currentSumMoney.val()) || isNaN(currentSumMoney.val())){
            layx.msg('订单金额为必填项且必须为数值类型！', {
                dialogIcon: 'error'
            });
            currentSumMoney.focus();
            return;
        }

        var buySellHtmlStr = "<input type='text' readonly class='form-control' value='"+currentBuySellType.val()+"'/>";
        var productTypeHtmlStr = "<input type='text' readonly class='form-control' value='"+currentProductType.find("option:selected").text()+"'/>";
        var sumMoneyHtmlStr = "<input type='text' readonly class='form-control' value='"+currentSumMoney.val()+"'/>";

        buySellDiv.append(buySellHtmlStr);
        productTypeDiv.append(productTypeHtmlStr);
        sumMoneyDiv.append(sumMoneyHtmlStr);

        // 添加到临时表
        var hiddenTable = $("#hiddenValueTable");
        var hiddenTr = "";
        hiddenTr += "<tr>";
        hiddenTr += "<td>"+siteId+"</td>";
        hiddenTr += "<td>"+currentBuySellType.val()+"</td>";
        hiddenTr += "<td>"+currentProductType.val()+"</td>";
        hiddenTr += "<td>"+currentSumMoney.val()+"</td>";
        hiddenTr += "</tr>";
        hiddenTable.append(hiddenTr);

        // 清除当前选择数据
        currentBuySellType.val("-1");
        currentProductType.val("-1");
        currentSumMoney.val("");
    }
</script>

<i id="loading" class="fa fa-spinner fa-spin loading-image"></i>
</body>
</html>