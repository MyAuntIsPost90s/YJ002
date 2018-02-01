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
<title>择偶条件</title>
</head>
<body>
	<div class="header-tip-bar">
		<i onclick="window.location.href='/YuJian/MyIntroducesEdit?id=${id}'" class="icon icon-arrow"></i>
		<div>择偶条件</div>
	</div>
	<div class="wrapper updatebaseinfo">
		<form id="data-form" action="/YuJian/Users/UpdateMe" method="post">
			<div class="register-item">
				<label>婚姻状况</label>
				<select name="married" style="direction: rtl;">
					<option value="">无</option>
					<option value="未婚">未婚</option>
					<option value="已婚">已婚</option>
				</select>
			</div>
			<div class="register-item">
				<label>身高范围</label>
				<select name="height" style="direction: rtl;">
					<option value="">无</option>
					<option value="140-150">140-150</option>
					<option value="150-160">150-160</option>
					<option value="160-170">160-170</option>
					<option value="170-180">170-180</option>
					<option value="180-190">180-190</option>
					<option value="190-200">190-200</option>
				</select>
			</div>
			<div class="register-item">
				<label>血型</label>
				<select name="bloodtype" style="direction: rtl;">
					<option value="">无</option>
					<option value="O">O</option>
					<option value="A">A</option>
					<option value="B">B</option>
					<option value="AB">AB</option>
				</select>
			</div>
			<div class="register-item">
				<label>学历</label>
				<select id="record" name="record" style="direction: rtl;">
					<option value="">无</option>
			 		<option value="小学">小学</option>
                    <option value="初中">初中</option>
                    <option value="高中">高中</option>
                    <option value="大专">大专</option>
                    <option value="本科">本科</option>
                    <option value="硕士">硕士</option>
                    <option value="博士">博士</option>
				</select>
			</div>
			<div class="register-item">
				<label>职业</label>
				<select name="occupation" style="direction: rtl;">
					<option value="">无</option>
			 		<option value="工人">工人</option>
                    <option value="会计">会计</option>
                    <option value="IT">IT</option>
                    <option value="医生">医生</option>
                    <option value="护士">护士</option>
                    <option value="服务员">服务员</option>
                    <option value="公务员">公务员</option>
                    <option value="教师">教师</option>
                    <option value="自由职业">自由职业</option>
				</select>
			</div>
			<div class="register-item">
				<label>薪资</label>
				<select name="wage" style="direction: rtl;">
					<option value="">无</option>
					<option value="0-3000">0-3000</option>
					<option value="3000-5000">3000-5000</option>
					<option value="5000-8000">5000-8000</option>
					<option value="8000-10000">8000-10000</option>
					<option value="10000-20000">10000-20000</option>
					<option value="20000+">20000+</option>
				</select>
			</div>
		</form>
		<button class="register-btn-next">确定</button>
	</div>
	
	<script src="/YuJian/Contents/js/LArea.js?version=0.1"></script>
	<script src="/YuJian/Contents/js/LAreaData.js"></script>
	<script type="text/javascript">
		var user=null;
		var uiuserid=${id};
		$(function(){
			loadUser();
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
			$.post("/YuJian/UserIntroduces/UpdateSelectCondtion"
	        		,{selectcondition:str,uiuserid:uiuserid}
	        		,function(data){
						closeSpin();
	        			if(data==null||data=='null'){
	        				tipAlert('修改失败T^T,请稍后后重试。')
	        				return;
	        			}
	        			//重新加载
	        			window.location.href='/YuJian/MyIntroducesEdit?id=${id}';
	        		},'json');
		})
		
		//加载自己的信息
		function loadUser(){
			$.post('/YuJian/UserIntroduces/GetIntroduce'
					,{uiuserid:uiuserid}
					,function(data){
						user=data;
						if(user!=null){
							var selectcondition=user.selectcondition;
							if(selectcondition!=null&&selectcondition!=''){
								var objs=$('#data-form select');
								var strs=user.selectcondition.split(',');
								
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