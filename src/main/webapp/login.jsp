<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<%@include file="/common/css-js.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>系统登录</title>
        
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/default/css/login-style.css" />

        <script type="text/javascript">
            $(document).ready(function() {
                //默认焦点为用户名，并点亮用户名图标。
                $("#userName").focus();
                $("#userName").parent().find(".glyphicon").css("color", "#2e6da4");

                //获得焦点点亮图标，失去焦点熄灭图标。
                $("input").on("focus blur", function() {
                    if($(this).is(":focus")) {
                        $(this).parent().find(".glyphicon").css("color", "#2e6da4");
                    } else {
                        $(this).parent().find(".glyphicon").css("color", "");
                    }
                });

            });
        </script>
    </head>

    <body>
        <section class="container">
            <section class="login-form">
                <form method="post" action="login.do" role="login">
                    <section>
                        <div class="logo"><i class="fa fa-rebel" style="font-size: 35px"></i>
                            <span class="sys-name">Solomon</span>
                        </div>
                        <div>
                            <c:if test="${error != null}">
                                <h5 class="alert alert-danger small" role="alert"><b>${error}</b></h5>
                            </c:if>
                            <div class="form-group">
                                <div class="input-group input-group-lg">
                                    <div class="input-group-addon">
                                        <span class="glyphicon glyphicon-user"></span>
                                    </div>
                                    <input type="text" id="userName" name="userName" placeholder="请输入用户名" required value="admin" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group input-group-lg">
                                    <div class="input-group-addon">
                                        <span class="glyphicon glyphicon-lock"></span>
                                    </div>
                                    <input type="password" id="password" name="password" placeholder="请输入密码" required value="123456" class="form-control" />
                                </div>
                            </div>
                            <button type="submit" class="btn btn-block btn-primary">登录</button>
                            <div>
                    </section>
                    <div style="text-align:center;">
                        <p>Copyright © 2017 Solomon All Rights Reserved</p>
                    </div>
                </form>
            </section>
        </section>
    </body>

</html>