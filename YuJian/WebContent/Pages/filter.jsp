<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/register.css?version=0.0.1">
<title>筛选</title>
</head>
<body>
	<div class="header-tip-bar">
		<i onclick="javascript:window.history.go(-1);" class="icon icon-arrow"></i>
		<div>筛选</div>
	</div>
	<div class="wrapper registerbase" style="padding-top: 49px;">
		<form id="data-form" action="/YuJian/Users/UpdateMe" method="post">
			<div class="register-item-search">
				<input name="condition" type="text" placeholder="请输入姓名"/>
			</div>
			<div class="register-item">
				<label>性别</label>
				<select name="sex" style="direction: rtl;">
					<option value="">不限</option>
					<option value="1">男</option>
					<option value="0">女</option>
				</select>
			</div>
			<div class="register-item">
				<label>年龄</label>
				<select name="age" style="direction: rtl;">
					<option value="">不限</option>
					<option value="20-30">20-30</option>
					<option value="30-40">30-40</option>
					<option value="40-50">40-50</option>
					<option value="50-60">50-60</option>
					<option value="60-70">60-70</option>
				</select>
			</div>
			<div class="register-item">
				<label>血型</label>
				<select name="bloodtype" style="direction: rtl;">
					<option value="">不限</option>
					<option value="O">O</option>
					<option value="A">A</option>
					<option value="B">B</option>
					<option value="AB">AB</option>
				</select>
			</div>
			<div class="register-item">
				<label>身高范围</label>
				<select name="height" style="direction: rtl;">
					<option value="">不限</option>
					<option value="140-150">140-150</option>
					<option value="150-160">150-160</option>
					<option value="160-170">160-170</option>
					<option value="170-180">170-180</option>
					<option value="180-190">180-190</option>
					<option value="190-200">190-200</option>
				</select>
			</div>
			<div class="register-item">
				<label>学历</label>
				<select id="record" name="record" style="direction: rtl;">
					<option value="">不限</option>
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
				<label>薪资</label>
				<select name="wage" style="direction: rtl;">
					<option value="">不限</option>
					<option value="0-3000">0-3000</option>
					<option value="3000-5000">3000-5000</option>
					<option value="5000-8000">5000-8000</option>
					<option value="8000-10000">8000-10000</option>
					<option value="10000-20000">10000-20000</option>
					<option value="20000+">20000+</option>
				</select>
			</div>
		</form>
		<button onclick="save()" class="register-btn-next">确定</button>
	</div>
	
	<script type="text/javascript">
		$(function(){
			$('.register-item select').each(function(){
				var name=$(this).attr('name');
				var val=getQueryString(name);
				if(name=='age')
					val=beAge(getQueryString('btime'),getQueryString('etime'));
				if(val!=null&&val!='')
					$(this).val(val)
			});
			$('input[name=condition]').val(getQueryString("condition"));
		})
	
		function save(){
			var params='';
			$('.register-item select').each(function(){
				var name=$(this).attr('name');
				var val=$(this).val();
				if(name=='age'&&val!=''&&val!=null){
					val=resolveAge(val);
					params+=val+'&';
					return;
				}
				
				if(val!=''&&val!=null)
					params+=name+'='+val+'&';
			});
			params=params.substring(0,params.length-1);
			var val=$('input[name=condition]').val();
			if(val!=''&&val!=null){
				params+="&condition="+val;
			}
			window.location.href='/YuJian/CommonUsers?'+params;
			//window.location.href='/YuJian/Filter?'+params;
		}
		
		function resolveAge(str){
			var arrs=str.split('-');
			var date=new Date();
			var etime = (date.getFullYear()-parseInt(arrs[0]))+"-01-01";
			var btime = (date.getFullYear()-parseInt(arrs[1]))+"-01-01";
			return 'btime='+btime+'&etime='+etime;
		}
		
		function beAge(btime,etime){
			if(btime==''||etime==''||etime==null||btime==null)
				return '';
			
			var date=new Date();
			var eage =date.getFullYear()-parseInt(btime.split('-')[0]);
			var bage =date.getFullYear()-parseInt(etime.split('-')[0]);
			
			return bage+'-'+eage;
		}
	</script>
</body>
</html>