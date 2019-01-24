<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <jsp:include page="/WEB-INF/jsp/base/resource.jsp" />
    <style>

    </style>
</head>
<body>
<div id="app">

    <!-- include header java server page code -->
    <jsp:include page="/WEB-INF/jsp/base/header.jsp" />



    <div class="master-container">

        <template>
        <div>
            <Tag type="dot" color="primary" class="title-tag">传输记录 Upload records </Tag>
        </div>
        </template>

        <br>
        <template>

            <div>
                <i-Table height="450" :columns="table.columns" :data="table.tableData" :loading="table.isLoading" size="small" border="true" stripe="true"></i-Table>
            </div>

            <div style="margin: 10px;overflow: hidden">
                <div style="float:left;">
                    <i-ButtonGroup>
                        <i-Button shape="circle" size="small" icon="ios-trash" type="error">删除</i-Button>
                        <i-Button shape="circle" size="small" icon="md-share-alt" type="primary">导出</i-Button>
                    </i-ButtonGroup>
                </div>
                <div style="float: right;">
                    <Page :total="40" size="small" @on-change="changePage" show-elevator show-sizer />
                </div>
            </div>
        </template>

    </div>







    <!-- include footer java server page code -->
    <jsp:include page="/WEB-INF/jsp/base/footer.jsp" />
</div>
<script>
    new Vue({
        el: '#app',
        data: {
            searchForm :{
                user:'',
                password:''
            },
            table:{
                isLoading:false,
                columns: [
                    {
                        key: 'name',
                        type:'selection',
                        width:'50'
                    },
                    {
                        title: 'Name',
                        key: 'name',
                        ellipsis:true,
                        tooltip:true
                    },
                    {
                        title: 'Age',
                        key: 'age',
                        ellipsis:true,
                        tooltip:true
                    },
                    {
                        title: 'Address',
                        key: 'address',
                        ellipsis:true,
                        tooltip:true
                    },
                    {
                        title: 'Date',
                        key: 'date',
                        ellipsis:true,
                        tooltip:true
                    },
                    {
                        title: 'Action',
                        key: 'action',
                        width: 200,
                        align: 'center',
                        render: function (h, params) {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'info',
                                        size: 'small',
                                        icon:'ios-alert-outline'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: function () {
                                            this.show(params.index)
                                        }
                                    }
                                }, '查看详情'),
                                h('Button', {
                                    props: {
                                        type: 'warning',
                                        size: 'small',
                                        icon:'md-refresh'
                                    },
                                    on: {
                                        click: function () {
                                            this.remove(params.index)
                                        }
                                    }
                                }, '重传')
                            ]);
                        }
                    }
                ],
                tableData: [
                    {
                        name: 'John Brown',
                        age: 18,
                        address: 'New York No. 1 Lake Park',
                        date: '2016-10-03'
                    },
                    {
                        name: 'Jim Green',
                        age: 24,
                        address: 'London No. 1 Lake Park',
                        date: '2016-10-01'
                    },
                    {
                        name: 'Joe Black',
                        age: 30,
                        address: 'Sydney No. 1 Lake Park',
                        date: '2016-10-02'
                    },
                    {
                        name: 'Jon Snow',
                        age: 26,
                        address: 'Ottawa No. 2 Lake Park',
                        date: '2016-10-04'
                    },
                    {
                        name: 'Jon Snow',
                        age: 26,
                        address: 'Ottawa No. 2 Lake Park',
                        date: '2016-10-04'
                    },
                    {
                        name: 'Jon Snow',
                        age: 26,
                        address: 'Ottawa No. 2 Lake Park',
                        date: '2016-10-04'
                    },
                    {
                        name: 'Jon Snow',
                        age: 26,
                        address: 'Ottawa No. 2 Lake Park',
                        date: '2016-10-04'
                    },
                    {
                        name: 'Jon Snow',
                        age: 26,
                        address: 'Ottawa No. 2 Lake Park',
                        date: '2016-10-04'
                    },
                    {
                        name: 'Jon Snow',
                        age: 26,
                        address: 'Ottawa No. 2 Lake Park',
                        date: '2016-10-04'
                    },
                    {
                        name: 'Jon Snow',
                        age: 26,
                        address: 'Ottawa No. 2 Lake Park',
                        date: '2016-10-04'
                    },
                    {
                        name: 'Jon Snow',
                        age: 26,
                        address: 'Ottawa No. 2 Lake Park',
                        date: '2016-10-04'
                    },
                    {
                        name: 'Jon Snow',
                        age: 26,
                        address: 'Ottawa No. 2 Lake Park',
                        date: '2016-10-04'
                    },
                    {
                        name: 'Jon Snow',
                        age: 26,
                        address: 'Ottawa No. 2 Lake Park',
                        date: '2016-10-04'
                    },
                    {
                        name: 'Jon Snow',
                        age: 26,
                        address: 'Ottawa No. 2 Lake Park',
                        date: '2016-10-04'
                    },
                    {
                        name: 'Jon Snow',
                        age: 26,
                        address: 'Ottawa No. 2 Lake Park',
                        date: '2016-10-04'
                    },
                    {
                        name: 'Jon Snow',
                        age: 26,
                        address: 'Ottawa No. 2 Lake Park',
                        date: '2016-10-04'
                    },
                    {
                        name: 'Jon Snow',
                        age: 26,
                        address: 'Ottawa No. 2 Lake Park',
                        date: '2016-10-04'
                    },
                    {
                        name: 'Jon Snow',
                        age: 26,
                        address: 'Ottawa No. 2 Lake Park',
                        date: '2016-10-04'
                    }
                ]
            }

        },
        methods: {
            handleSelectAll:function(status) {
                this.$refs.selection.selectAll(status);
            },
            changePage:function () {
                // The simulated data is changed directly here, and the actual usage scenario should fetch the data from the server
                this.table.tableData = this.table.tableData;
            }
        }
    })
</script>
</body>
</html>
