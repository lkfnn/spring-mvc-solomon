<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<%@include file="/common/css-js.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>首页</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/default/css/main-style.css" />
    </head>

    <body>
        <!-- 上方导航条 -->
        <div class="navbar navbar-inverse navbar-fixed-top">
            <div>
                <div class="navbar-header">
                    <button type="button" data-toggle="collapse" data-target="#navbar-collapse-menu" class="navbar-toggle">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <div class="logo">
                        <i class="fa fa-rebel" style="font-size: 35px"></i>
                        <span class="sys-name">Solomon</span>
                    </div>
                </div>
                <!-- 上方导航条菜单区域 -->
                <div id="navbar-collapse-menu" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li class="active">
                            <a href="#"><i class="glyphicon glyphicon-home item"></i>首页</a>
                        </li>
                        <li>
                            <a href="#"><i class="glyphicon glyphicon-user item"></i>个人</a>
                        </li>
                        <li>
                            <a href="#"><i class="glyphicon glyphicon-th-large item"></i>单位</a>
                        </li>
                        <li>
                            <a href="#"><i class="glyphicon glyphicon-fire item"></i>总部</a>
                        </li>
                    </ul>

                    <!-- 上方导航条右侧显示 -->
                    <ul class="nav navbar-nav navbar-right">
                        <li class="user-profile">
                            <span>欢迎您：${userInfoDTO.deptName} &nbsp; ${userInfoDTO.personName}</span>
                        </li>
                        <li class="exit">
                            <a href="/loginOut.do">
                                <i class="glyphicon glyphicon-off"></i>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </body>

</html>