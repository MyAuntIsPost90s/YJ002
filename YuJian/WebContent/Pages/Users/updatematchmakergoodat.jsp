<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../head.jsp"></jsp:include>
<script type="text/javascript" src = "/YuJian/Contents/js/ajaxFileUpload.js"></script>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/register.css?version=0.1">
<script type="text/javascript" src = "/YuJian/Contents/js/mobiscroll.custom-3.0.0-beta2.min.js"></script>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/register.css">
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/mobiscroll.custom-3.0.0-beta2.min.css">
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/LArea.css">
<title>擅长领域</title>
</head>
<body>
	<div class="header-tip-bar">
		<i onclick="window.location.href='/YuJian/MyInfoMatchmaker'" class="icon icon-arrow"></i>
		<div>擅长领域</div>
	</div>
	<div class="wrapper updatebaseinfo">
		<form id="data-form" action="" method="post">
			<div class="register-item">
				<label>客户群体</label>
				<select style="direction: rtl;">
					<option value="">无</option>
					<option value="海归人士">海归人士</option>
					<option value="商业人士">商业人士</option>
					<option value="事业单位">事业单位</option>
				</select>
			</div>
			<div class="register-item">
				<label>年龄类别</label>
				<select style="direction: rtl;">
					<option value="">无</option>
					<option value="大龄">大龄</option>
					<option value="适龄">适龄</option>
					<option value="青年">青年</option>
				</select>
			</div>
			<div class="register-item">
				<label>情况类型</label>
				<select style="direction: rtl;">
					<option value="">无</option>
					<option value="终年单身">终年单身</option>
					<option value="恋爱求婚">恋爱求婚</option>
					<option value="离异">离异</option>
					<option value="情感复合">情感复合</option>
				</select>
			</div>
			<div class="register-item">
				<label>处理类型</label>
				<select style="direction: rtl;">
					<option value="">无</option>
					<option value="亲密关系处理">亲密关系处理</option>
					<option value="情感矛盾处理">情感矛盾处理</option>
					<option value="两性心理处理">两性心理处理</option>
				</select>
			</div>
		</form>
		<button class="register-btn-next">确定</button>
	</div>
	
	<script src="/YuJian/Contents/js/LArea.js?version=0.1"></script>
	<script src="/YuJian/Contents/js/LAreaData.js"></script>
	<script type="text/javascript">
		var user=null;
		$(function(){
			loadMe();
		});
		
		$('.register-btn-next').click(function(){
			showSpin();
			var objs=$('#data-form select');
			var str='';
			
			for(var i=0;i<objs.length;i++){	
				if(i>=objs.length-1)
					str+=objs.get(i).value;
				else
					str+=objs.get(i).value+",";
			}
			//提交
			$.post("/YuJian/Users/UpdateMatchmakerGoodAt"
	        		,{matchmakergoodat:str}
	        		,function(data){
						closeSpin();
	        			if(data==null||data=='null'){
	        				tipAlert('修改失败T^T,请稍后后重试。')
	        				return;
	        			}
	        			//重新加载
	        			window.location.href='/YuJian/MyInfoMatchmaker';
	        		},'json');
		})
		
		//加载自己的信息
		function loadMe(){
			$.post('/YuJian/Users/GetMyInformation'
					,function(data){
						user=data;
						if(user!=null){
							var matchmakergoodat=user.matchmakergoodat;
							if(matchmakergoodat!=null&&matchmakergoodat!=''){
								var objs=$('#data-form select');
								var strs=user.matchmakergoodat.split(',');
								
								for(var i=0;i<strs.length;i++){
									if(objs.length>i)
										objs.get(i).value=strs[i];
								}
							}
						}
			},'json');
		}
	</script>
</body>
</html>