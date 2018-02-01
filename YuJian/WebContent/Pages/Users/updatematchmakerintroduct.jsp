<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../head.jsp"></jsp:include>
<script type="text/javascript" src = "/YuJian/Contents/js/ajaxFileUpload.js"></script>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/register.css">
<title>红娘简介</title>
</head>
<body>
	<div class="header-tip-bar">
		<i onclick="window.location.href='/YuJian/MyInfoMatchmaker'" class="icon icon-arrow"></i>
		<div>红娘简介</div>
	</div>
	<div class="wrapper updatesigncontent">
		<textarea id="matchmakerintroduct"></textarea>
		<button class="register-btn-next">提交</button>
		<label class="uploadheadimg-tip"><i>提示：</i>简介请控制在<i>500</i>字以内哦</label>
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
        		  $(this).val(val.substring(0,500));
        });
		
		$('.register-btn-next').click(function(){
			var matchmakerintroduct=$('#matchmakerintroduct').val();
			showSpin();
			//提交
			$.post("/YuJian/Users/UpdateMatchmakerIntroduct"
	        		,{matchmakerintroduct:matchmakerintroduct}
	        		,function(data){
						closeSpin();
	        			if(data==null||data=='null'){
	        				tipAlert('修改失败T^T,请稍后后重试。')
	        				return;
	        			}
	        			//重新加载
	        			$.get("/YuJian/Users/ReloadUser");	//重载user
	        			window.location.href='/YuJian/MyInfoMatchmaker';
	        		},'json');
		});
		
		//加载自己的信息
		function loadMe(){
			$.post('/YuJian/Users/GetMyInformation'
					,function(data){
				user=data;
				if(user!=null)
					$('#matchmakerintroduct').val(user.matchmakerintroduct);
			},'json');
		}
		
	</script>
</body>
</html>