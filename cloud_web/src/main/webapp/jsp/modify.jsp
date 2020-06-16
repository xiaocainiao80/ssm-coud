<%--
  Created by IntelliJ IDEA.
  User: HelloZeiKan
  Date: 2020/6/16
  Time: 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>密码修改</title>
    <link rel="stylesheet" type="text/css" href="../css/register.css">
    <script type="text/javascript" src="../js/register.js"></script>
</head>
<body>
<div id="register">
    <div class="registerTop">
        <div class="topContent">
            <div class="contentLeft">
                <span class="logoBg"></span> <span
                    style="border-left: 1px solid #C9C9C9; margin-left: 10px; padding-left: 10px">云端网盘修改密码</span>
            </div>
            <div class="contentRight">
                <span>已有账号</span> <span class="login"> <a href="${pageContext.request.contextPath}/User/tologin">
							<input type="button" value="登录" />
					</a>
					</span>
            </div>
            <div style="clear: both;"></div>
        </div>
    </div>
    <div class="content">
        <div class="reContent">
            <div class="reContentLeft">
                <form action="${pageContext.request.contextPath}/User/modify" method="post"
                      onsubmit="return checkLoad()">
                    <input type="hidden" id="username" name="username" value="${user}">
                    <div>
                        用户密码：<input type="password" id="password" name="password"
                                    placeholder="请输入新密码">
                    </div>
                    <div>
                        确认密码：<input type="password" id="repass" name="repass"
                                    placeholder="请输入密码以进行确认">
                    </div>
                    <div class="check">
                        <input type="checkbox" id="agreement" name="agreement" />接受并阅读《<span>QST用户协议</span>》
                    </div>
                    <div class="regis">
                        <input type="submit" value="注册">
                    </div>
                </form>
            </div>
            <div style="clear: both;"></div>
        </div>
    </div>
    <div class="reBottom">
        <img src="../images/foot_pic.jpg">
    </div>
</div>
</body>
</html>