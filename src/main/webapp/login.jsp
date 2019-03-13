<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon"
          href="favicon.ico"
          type="image/x-icon"/>
    <link href="css/bootstrap.min.css"
          rel="stylesheet">
    <link href="css/font-awesome.css"
          rel="stylesheet">
    <link href="css/style.css"
          rel="stylesheet">
    <link
            href="css/plugins/layx/layx.min.css"
            rel="stylesheet">
    <!-- Toastr style -->
    <link
            href="css/plugins/toastr/toastr.min.css"
            rel="stylesheet">
    <!-- Sweet Alert -->
    <link
            href="css/plugins/sweetalert/sweetalert.css"
            rel="stylesheet">
    <script src="js/jquery-2.1.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <!-- Sweet alert -->
    <script
            src="js/plugins/sweetalert/sweetalert.min.js"></script>
    <!-- Toastr -->
    <script
            src="js/plugins/toastr/toastr.min.js"></script>
    <script
            src="js/plugins/layx/layx.min.js"></script>
    <title>农村电子商务信息管理平台 | 欢迎回来</title>
    <script type="text/javascript">
        $(function () {
            if (window != top) {
                top.location.href = location.href;
            }
        });

        var cookie = {
            set: function (key, val, time) {//设置cookie方法
                var date = new Date(); //获取当前时间
                var expiresDays = time;  //将date设置为n天以后的时间
                date.setTime(date.getTime() + expiresDays * 24 * 3600 * 1000); //格式化为cookie识别的时间
                document.cookie = key + "=" + val + ";expires=" + date.toGMTString();  //设置cookie
            },
            get: function (key) {//获取cookie方法
                /*获取cookie参数*/
                var getCookie = document.cookie.replace(/[ ]/g, "");  //获取cookie，并且将获得的cookie格式化，去掉空格字符
                var arrCookie = getCookie.split(";")  //将获得的cookie以"分号"为标识 将cookie保存到arrCookie的数组中
                var tips;  //声明变量tips
                for (var i = 0; i < arrCookie.length; i++) {   //使用for循环查找cookie中的tips变量
                    var arr = arrCookie[i].split("=");   //将单条cookie用"等号"为标识，将单条cookie保存为arr数组
                    if (key == arr[0]) {  //匹配变量名称，其中arr[0]是指的cookie名称，如果该条变量为tips则执行判断语句中的赋值操作
                        tips = arr[1];   //将cookie的值赋给变量tips
                        break;   //终止for循环遍历
                    }
                }
                return tips;
            },
            delete: function (key) {
                var date = new Date(); //获取当前时间
                date.setTime(date.getTime() - 10000); //将date设置为过去的时间
                document.cookie = key + "=v; expires =" + date.toGMTString();//设置cookie
            }
        };
    </script>
    <style type="text/css">
        .input-remix {
            border-left: none;
            border-right: none;
            border-top: none;
        }

        .non-border {
            border-left: none;
            border-right: none;
            border-top: none;
        }
    </style>
    <style>
        .input-username-noncheck-group {
            border: none;
            width: 300px;
            height: 40px;
            background: url(image/account-noncheck.png) no-repeat 0 center;
        }

        .input-username-check-group {
            border: none;
            width: 300px;
            height: 40px;
            background: url(image/account-checked.png) no-repeat 0 center;
        }

        .input-username-check-group input {
            border-left: none;
            border-right: none;
            border-top: none;
            background: none;
            height: 40px;
            line-height: 30px;
            width: 100%;
            text-indent: 32px;
        }

        .input-username-noncheck-group input {
            border-left: none;
            border-right: none;
            border-top: none;
            background: none;
            height: 40px;
            line-height: 30px;
            width: 100%;
            text-indent: 32px;
        }

        .input-password-noncheck-group {
            border: none;
            width: 300px;
            height: 40px;
            background: url(image/password-noncheck.png) no-repeat 0 center;
        }

        .input-password-checked-group {
            border: none;
            width: 300px;
            height: 40px;
            background: url(image/password-checked.png) no-repeat 0 center;
        }

        .input-password-checked-group input {
            border-left: none;
            border-right: none;
            border-top: none;
            background: none;
            height: 40px;
            line-height: 30px;
            width: 100%;
            text-indent: 32px;
        }

        .input-password-noncheck-group input {
            border-left: none;
            border-right: none;
            border-top: none;
            background: none;
            height: 40px;
            line-height: 30px;
            width: 100%;
            text-indent: 32px;
        }

        .input-identitycode-noncheck-group {
            border: none;
            width: 300px;
            height: 40px;
            background: url(image/identitycode-noncheck.png) no-repeat 0 center;
        }

        .input-identitycode-checked-group {
            border: none;
            width: 300px;
            height: 40px;
            background: url(image/identitycode-checked.png) no-repeat 0 center;
        }

        .input-identitycode-checked-group input {
            border-left: none;
            border-right: none;
            border-top: none;
            background: none;
            height: 40px;
            line-height: 30px;
            width: 100%;
            text-indent: 32px;
        }

        .input-identitycode-noncheck-group input {
            border-left: none;
            border-right: none;
            border-top: none;
            background: none;
            height: 40px;
            line-height: 30px;
            width: 100%;
            text-indent: 32px;
        }

        .focus-color {
            color: #0c8ae2;
        }
    </style>
</head>
<body style="overflow: hidden; background: url('image/login.png'); background-position: center center; background-repeat: no-repeat; background-attachment: fixed; background-size: cover;">
<center>
    <div style="margin-top: 15%;margin-left:35%; width: 30%; height: 400px; padding: 1% 1% 1% 1%; background-color: white; border-radius: 10px; box-shadow: -1px 0px 32px 0px rgba(116, 114, 114, 0.27);">
        <div style="width: 100%;">
            <div style="width: 80%; margin-top: 4%;">
                <span style="font-size: 19px; margin-left: 20px;">登录页面</span><br>
                <br>

                <div class="input-username-noncheck-group">
                    <input id="username" onfocus="focusUsername(this);" onblur="blurUsername(this);" type="text"
                           class="form-control" placeholder="账号"/>
                </div>

                <div class="input-password-noncheck-group" style="margin-top: 25px;">
                    <input id="password" onfocus="focusPassword(this);" onblur="blurPassword(this);" type="password"
                           class="form-control" placeholder="密码"/>
                </div>

                <div>
                    <div class="input-identitycode-noncheck-group" style="margin-top: 25px;">
                        <input maxlength="4" id="code" onfocus="focusCode(this);" onblur="blurCode(this);" type="text"
                               class="form-control" placeholder="验证码" style="float:left;"/>
                        <div style="border:0px solid blue;width:120px;height:60px;position: relative;left:100px;top:-14px">
                            <img alt="刷新获取验证码"
                                 style="width: 100px; height: 35px;margin-top:-15px;position: absolute;top:20px;left:10px;"
                                 id="identity_code_img" onclick="changeIdentityCode();">
                            <label style="width: 100px;float:right; position: absolute;bottom:0;left:10px;margin-top:-15px;font-size:10px;font-weight: normal;color: #b1b3b9;"
                                   onclick="changeIdentityCode();">看不清？ 换一张</label>
                        </div>
                    </div>
                </div>

                <div class="checkbox" style="margin-top: 25px;width:300px;text-align:left;">
                    <label style="color: #b1b3b9;">
                        <input type="checkbox" id="savePasswordCbx"> 保存密码
                    </label>
                </div>

                <button onclick="doLogin();"
                        style="width:100%;margin-top:25px;background-color: #0cb8e2;box-shadow: -2px 4px 13px 0px rgba(9, 195, 240, 0.36);border-radius: 25px;"
                        type="button" class="btn btn-primary">登录
                </button>

            </div>
        </div>
    </div>


    <div style="position: fixed; bottom: 3.5%; width: 100%; z-index: 100;">
        Copyright 2016-2018 All rights reserved
    </div>
    <div style="position: fixed; bottom: 1%; width: 100%; z-index: 100;">
        <%--湖南众高电子商务有限公司 版权所有 湘ICP备18018471号-1--%>
    </div>
</center>
<i id="loading" class="fa fa-spinner fa-spin loading-image"
   style="z-index: 9999;"></i>
</body>
<script type="text/javascript">
    //监听Enter按键登录
    document.onkeydown = function (event) {
        e = event ? event : (window.event ? window.event : null);
        //Enter  keyCode 为13
        if (e.keyCode == 13) {
            doLogin();
        }
    }

    $(function () {
        changeIdentityCode();

        if ((cookie.get("uname") != undefined && cookie.get("uname") != "undefined") && (cookie.get("upass") != undefined && cookie.get("upass") != "undefined")) {
            $("#username").val(cookie.get("uname"));
            $("#password").val(cookie.get("upass"));
            $("#savePasswordCbx").prop("checked", true);
        } else {
            $("#username").val("");
            $("#password").val("");
            $("#savePasswordCbx").prop("checked", false);
        }
    });


    function focusUsername(args) {
        $(args).parent().attr("class", "input-username-check-group");
        $(args).addClass("focus-color");
    }

    function blurUsername(args) {
        $(args).parent().attr("class", "input-username-noncheck-group");
        $(args).removeClass("focus-color");
    }

    function focusPassword(args) {
        $(args).parent().attr("class", "input-password-checked-group");
        $(args).addClass("focus-color");
    }

    function blurPassword(args) {
        $(args).parent().attr("class", "input-password-noncheck-group");
        $(args).removeClass("focus-color");
    }

    function focusCode(args) {
        $(args).parent().attr("class", "input-identitycode-checked-group");
        $(args).addClass("focus-color");
    }

    function blurCode(args) {
        $(args).parent().attr("class", "input-identitycode-noncheck-group");
        $(args).removeClass("focus-color");
    }

    function changeIdentityCode() {
        var img = $("#identity_code_img");
        img.attr("src", "/system/authcode?r=" + Math.random());
    }

    function doLogin() {
        var username = $("#username");
        var password = $("#password");
        var code = $("#code");
        if (username.val() == "") {
            layx.msg('请输入用户名！', {
                dialogIcon: 'error'
            });
            username.focus();
            return;
        }
        if (password.val() == "") {
            layx.msg('请输入密码！', {
                dialogIcon: 'error'
            });
            password.focus();
            return;
        }
        if (code.val() == "") {
            layx.msg('请输入验证码！', {
                dialogIcon: 'error'
            });
            code.focus();
            return;
        }
        var param = {};
        param.username = username.val();
        param.password = password.val();
        param.code = code.val();
        $("#loading").show();
        $.ajax({
            url: "/system/login",
            type: "POST",
            cache: false,
            data: param,
            success: function (response) {
                if (response.code == 1) {
                    toastr.error(response.message, "提示");
                    password.val("");
                    code.val("");
                    username.focus();
                    username.select();
                    $("#loading").hide();
                    return;
                } else if (response.code == 2) {
                    toastr.error(response.message, "提示");
                    code.val("");
                    code.focus();
                    code.select();
                    $("#loading").hide();
                }
                $("#loading").hide();


                // 登录成功,写入cookies
                var savePasswordCbx = $("#savePasswordCbx");
                if (savePasswordCbx.is(":checked")) {
                    // 写cookies
                    cookie.set("uname", username.val(), 7);//设置为7天过期
                    cookie.set("upass", password.val(), 7);//设置为7天过期
                }

                window.location.href = response.data.callback;
            },
            error: function () {
                toastr.error("登录失败！", "错误");
                $("#loading").hide();
            }
        });
    }
</script>
</html>