<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="head.jsp"></jsp:include>
<title>错误</title>
<style>
	.err{
		width:13rem;
		height: 13rem;
		float: left;
		margin:45% 3.5rem; 
	}
	.err img{
		width: 100%;
	}
	.err p{
		color:#888;
		padding:1rem;
		text-align: center;
	}
</style>
</head>
<body>
	<!-- 登陆失败 -->
	<div class="wrapper" style="padding-bottom: 0">
		<div class="err">
			<img src="/YuJian/Contents/images/err.png" />
			<p>${msg}</p>
		</div>
	</div>
</body>
</html>