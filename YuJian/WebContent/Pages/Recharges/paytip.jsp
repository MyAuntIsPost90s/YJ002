<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/charge.css?version=0.0.1">
<title>支付成功</title>
</head>
<body>
	<!-- 支付成功 -->
	<div class="wrapper" style="padding-bottom: 0">
		<div class="pay-tip">
			<img src="/YuJian/Contents/images/paysuccess.png" />
			<p>支付成功，点此<a href="/YuJian/Mine">返回</a></p>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			$.get("/YuJian/Users/ReloadUser");	//重载user
		})
	</script>
</body>
</html>