<%@ page language="java" contentType="text/html; charset=Utf-8"
    pageEncoding="Utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Utf-8">
<title>登陆-芋圆后台管理</title>
<link rel="icon" href="/YuJianRoom/Contents/images/logo.png" type="image/x-icon" />
<link href="/YuJianRoom/Contents/lib/login-theme/style_log.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/YuJianRoom/Contents/lib/login-theme/style.css">
<link rel="stylesheet" type="text/css" href="/YuJianRoom/Contents/lib/login-theme/userpanel.css">
<link rel="stylesheet" type="text/css" href="/YuJianRoom/Contents/lib/login-theme/jquery.ui.all.css">
<script type="text/javascript" src="/YuJianRoom/Contents/js/jquery.min.js"></script>
<script type="text/javascript" src="/YuJianRoom/Contents/js/md5.js"></script>
</head>
<body class="login">
	<div class="login_m">
		<div class="login_logo">
			<img src="/YuJianRoom/Contents/lib/login-theme/logo.png" width="196" height="46">
		</div>
		<div class="login_boder">
			<div class="login_padding" id="login_model">
				<h2>用户名</h2>
  				<label>
    				<input type="text" name="phone" id="phone" class="txt_input txt_input2" placeholder="用户名" value="${model.userName}">
  				</label>
  				<h2>密码</h2>
  				<label>
    				<input type="password" name="password" id="userpwd" class="txt_input" placeholder="密码">
  				</label>
  				<div class="alert alert-warning" style="width:100%;">
				    <strong>提示 ：</strong><span id='tip'>您的网络连接有问题</span>。
				    <a class="close" data-dismiss="alert" style="cursor: pointer;float:right;font-size: 18px">
				        &times;
				    </a>
				</div>
  				<div class="rem_sub">
  					<div class="rem_sub_l">
  						<input type="checkbox" name="checkbox" id="save_me">
   						<label for="checkbox">记住我</label>
   					</div>
				    <label>
				      <input type="button" onclick="login()" class="sub_button" name="button" id="button" value="SIGN-IN" style="opacity: 0.7;">
				    </label>
  				</div>
			</div>
  		</div>
	</div>
<!--login_padding  Sign up end-->
<br/>
<br/>
<p align="center">福州智慧恒星</p>
</body>

<script type="text/javascript">

	$('.alert').hide();
	$('.close').click(function(){
		$('.alert').hide();	//隐藏
	});
	
	function login(){
		var phone=$('input[name=phone]').val();
		var password =$('input[name=password]').val();
		var isSave=$('#save_me').val();
		
		if(phone==''){
			$('#tip').html('请输入用户名');
			$('.alert').show();
			return;
		}
		if(password==''){
			$('#tip').html('请输入密码');
			$('.alert').show();
			return;
		}
	
		var postData={
			phone:phone,
			password:hex_md5(password).toUpperCase(),
			isSave:isSave
		};
		$.post("/YuJianRoom/DoLogin"
				,postData
				,function(data){
					if(data.status==1){
						window.location.href='/YuJianRoom/Index';
						return;
					}
					else{
						$('#tip').html(data.msg);
						$('.alert').show();
					}
		},'json');
	}
</script>
</html>