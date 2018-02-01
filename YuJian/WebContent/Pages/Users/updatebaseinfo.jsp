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
<title>基本信息</title>
</head>
<body>
	<div class="header-tip-bar">
		<i onclick="javascript:window.history.go(-1);" class="icon icon-arrow"></i>
		<div>基本信息</div>
	</div>
	<div class="wrapper updatebaseinfo">
		<form id="data-form" action="/YuJian/Users/UpdateMe" method="post">
			<div class="register-item">
				<label>姓名</label>
				<input type="text" name="realname"/>
			</div>
			<div class="register-item">
				<label>出生日期</label>
				<input id="txt-date" type="text" name="birthday" placeholder="请选择" readonly="readonly" />
			</div>
			<div class="register-item">
				<label>性别</label>
				<select name="sex">
					<option value="1">男</option>
					<option value="0">女</option>
				</select>
			</div>
			<div class="register-item">
				<label>婚姻状况</label>
				<select name="married">
					<option value="0">未婚</option>
					<option value="1">已婚</option>
					<option value="2">保密</option>
				</select>
			</div>
			<div class="register-item">
				<label>所在地区</label>
				<label><input id="addr" name="address" type="text" readonly="readonly" placeholder="请选择"></label>
			</div>
			<div class="register-item">
				<label>身高范围</label>
				<select name="height">
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
					<option value="">保密</option>
					<option value="O">O</option>
					<option value="A">A</option>
					<option value="B">B</option>
					<option value="AB">AB</option>
				</select>
			</div>
			<div class="register-item">
				<label>学历</label>
				<select id="record" name="record">
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
					<option value="">保密</option>
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
					<option value="">保密</option>
					<option value="0-3000">0-3000</option>
					<option value="3000-5000">3000-5000</option>
					<option value="5000-8000">5000-8000</option>
					<option value="8000-10000">8000-10000</option>
					<option value="10000-20000">10000-20000</option>
					<option value="20000+">20000+</option>
				</select>
			</div>
			<div class="register-item">
				<label>联系方式</label>
				<input type="tel" name="phone" placeholder="手机号" />
			</div>
		</form>
		<button class="register-btn-next enable">确定</button>
	</div>
	
	<script src="/YuJian/Contents/js/LArea.js?version=0.1"></script>
	<script src="/YuJian/Contents/js/LAreaData.js"></script>
	<script type="text/javascript">
		var user=null;
		$(function(){
			loadDateCtl();
			loadAreaCtl();
			loadMe();
		});
		
		$('input[name=phone]').keyup(function(){
			check();
		})
		$('input[name=realname]').keyup(function(){
			check();
		})
		$('input[name=birthday]').change(function(){
			check();
		})
		function check(){
			var enable=false;
			$('.register-item input').each(function(){
				var val = $(this).val();
				if(val==null||val=='')
					enable=true;
				if($(this).attr('name')=='phone'&&!isPhone(val)){
					enable=true;
					//tipAlert('手机号格式不正确');
				}
			});
			if(!enable)
				$('.register-btn-next').removeClass('enable');
			else
				$('.register-btn-next').addClass('enable');
		}
		$('.register-btn-next').click(function(){
			$('#ctl-date').click();
			if($(this).hasClass('enable')){
				return;
			}
			showSpin();
			//提交
			$.post("/YuJian/Users/UpdateMe"
	        		,$('#data-form').serialize()
	        		,function(data){
						closeSpin();
	        			if(data==null||data=='null'){
	        				tipAlert('修改失败T^T,请稍后后重试。')
	        				return;
	        			}
	        			//重新加载
	        			window.location.href='/YuJian/MyInfo';
	        		},'json');
		})
		
		//加载自己的信息
		function loadMe(){
			$.post('/YuJian/Users/GetMyInformation'
					,function(data){
				user=data;
				if(user!=null){
					$('input[name=realname]').val(user.realname);
					$('input[name=birthday]').val(user.birthday);
					$("#txt-date").mobiscroll('setDate', new Date(user.birthday), true);

					$('select[name=sex]').val(user.sex);
					$('input[name=married]').val(user.married);
					$('input[name=address]').val(user.address);
					if(user.height!=null&&user.height!='')
						$('select[name=height]').val(user.height);
					$('input[name=record]').val(user.record);
					$('input[name="phone"]').val(user.phone);
					$('select[name=wage]').val(user.wage);
					$('select[name=bloodtype]').val(user.bloodtype);
					$('select[name=occupation]').val(user.occupation);
					
					 check();
				}
			},'json');
		}

	    function loadDateCtl(){
	    	var opt = {  
			        theme: "ios", 
			        display: 'bottom', //显示方式  
			        mode: 'scroller',
			        height:50,
			        lang: "zh",  
			        setText: '确定', //确认按钮名称
			        cancelText: "取消",  
			        dateFormat: 'yyyy-mm-dd', //返回结果格式化为年月格式  
			        dateOrder: 'yyyymmdd', //面板中日期排列格式
			        headerText: function (valueText) { //自定义弹出框头部格式  
			            array = valueText.split('-');  
			            return array[0] + "年" + array[1] + "月" + array[2] + "日";  
			        }
			    };

	    		$("#txt-date").mobiscroll().date(opt);
				$("#txt-date").mobiscroll('setDate', new Date((new Date().getFullYear()-22)+"-01"+"-01"), true);
	    } 
	    function loadAreaCtl(){
	    	 var area = new LArea();
	         area.init({
	             'trigger': '#addr', //触发选择控件的文本框，同时选择完毕后name属性输出到该位置
	             'valueTo': '#value1', //选择完毕后id属性输出到该位置
	             'keys': {
	                 id: 'id',
	                 name: 'name'
	             }, //绑定数据源相关字段 id对应valueTo的value属性输出 name对应trigger的value属性输出
	             'type': 1, //数据源类型
	             'data': LAreaData //数据源
	         });
	         area.value=[1,13,3];//控制初始位置，注意：该方法并不会影响到input的value
	    }
	</script>
</body>
</html>