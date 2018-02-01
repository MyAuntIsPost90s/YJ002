<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/video.css">
<title>视频介绍</title>
</head>
<body>
	<div class="header-tip-bar">
		<i onclick="javascript:window.history.go(-1);" class="icon icon-arrow"></i>
		<div>视频介绍</div>
	</div>
	<div class="wrapper uservideo" style="line-height:calc(100vh - 49px)">
		<video id="uservideo" src="${videourl}" width="100%" controls="controls" preload="auto" style="vertical-align: middle;"><%="您的浏览器不支持HTML5，无法播放" %></video>
	</div>
</body>
</html>