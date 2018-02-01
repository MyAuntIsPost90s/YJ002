<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../head.jsp"></jsp:include>
<script type="text/javascript" src = "/YuJian/Contents/js/ajaxFileUpload.js"></script>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/register.css?version=0.2">
<title>内心独白</title>
</head>
<body>
	<div class="header-tip-bar">
		<i onclick="window.location.href='/YuJian/MyInfo'" class="icon icon-arrow"></i>
		<div>内心独白</div>
	</div>
	<div class="wrapper updatesigncontent">
		<textarea id="signcontent"></textarea>
		<button class="register-btn-next">提交</button>
		<label class="uploadheadimg-tip"><i>提示：</i>内心独白请控制在<i>200</i>字以内哦</label>
	</div>
	
	<script type="text/javascript">
		var user=null;
		$(function(){
			loadMe();
		});
		
        $('#signcontent').keydown(function(){
        	  var val = $(this).val();
        	  if(val==null)
        		  return;
        	  if(val.length>40)
        		  $(this).val(val.substring(0,200));
        });
		
		$('.register-btn-next').click(function(){
			var signcontent=$('#signcontent').val();
			showSpin();
			//提交
			$.post("/YuJian/Users/UpdateSignContent"
	        		,{signcontent:signcontent}
	        		,function(data){
						closeSpin();
	        			if(data==null||data=='null'){
	        				tipAlert('修改失败T^T,请稍后后重试。')
	        				return;
	        			}
	        			//重新加载
	        			$.get("/YuJian/Users/ReloadUser");	//重载user
	        			window.location.href='/YuJian/MyInfo';
	        		},'json');
		});
		
		//加载自己的信息
		function loadMe(){
			$.post('/YuJian/Users/GetMyInformation'
					,function(data){
				user=data;
				if(user!=null)
					$('#signcontent').val(user.signcontent);
			},'json');
		}
		
	</script>
</body>
</html>