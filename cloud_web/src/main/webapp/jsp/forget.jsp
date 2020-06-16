<%--
  Created by IntelliJ IDEA.
  User: HelloZeiKan
  Date: 2020/6/16
  Time: 9:24
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
                    style="border-left: 1px solid #C9C9C9; margin-left: 10px; padding-left: 10px">云端网盘账号忘记</span>
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
                <form action="${pageContext.request.contextPath}/User/forget" method="post"
                      onsubmit="return checkLoad()">
                    <div>
                        用户名称：<input type="text" id="username" name="username"
                                    placeholder="请输入用户名">
                    </div>
                    <div>
                        手机号码：<input type="text" id="phone" name="phone"
                                    placeholder="请输入注册时的手机号码">
                    </div>
                    <div>
                        电子邮箱：<input type="text" id="email" name="email"
                                    placeholder="请输入注册时的邮箱">
                    </div>
                    <div class="regis">
                        <input type="submit" value="下一步">
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