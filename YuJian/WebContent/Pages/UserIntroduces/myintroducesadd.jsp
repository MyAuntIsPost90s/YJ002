<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../head.jsp"></jsp:include>
<script type="text/javascript" src = "/YuJian/Contents/js/ajaxFileUpload.js"></script>
<script type="text/javascript" src = "/YuJian/Contents/js/mobiscroll.custom-3.0.0-beta2.min.js"></script>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/register.css">
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/mobiscroll.custom-3.0.0-beta2.min.css">
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/LArea.css">
<title>基本资料</title>
</head>
<body>
	<div class="header-tip-bar">
		<i onclick="window.location.href='/YuJian/MyIntroduces'" class="icon icon-arrow"></i>
		<div>基本资料</div>
	</div>
	<div class="wrapper registerbase">
		<div class="registerheadimg">
			<div class="uploadheadimg-btn" style="margin-top: 0.5rem;">
				<img src="/YuJian/Contents/images/camera-gray.png" />
				<p>点击上传头像</p>
			</div>
			<div class="headimg" hidden="true" style="margin-top: 0rem;">
				<img />
			</div>
		</div>
		<form id="data-form" action="/YuJian/Users/UpdateMe" method="post" enctype="multipart/form-data">
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
				<label>联系方式</label>
				<input type="text" name="phone" placeholder="请选择" />
			</div>
			<input id="upl-headimg" type="file" name="headimg" hidden="true" onchange="readAvatar(this)" />
			<input name="myid" type="hidden">
			<input name="pwd" type="hidden">
		</form>
		<button class="register-btn-next enable">下一步</button>
	</div>
	
	<script src="/YuJian/Contents/js/LArea.js?version=0.1"></script>
	<script src="/YuJian/Contents/js/LAreaData.js"></script>
	<script type="text/javascript">
		$(function(){
			showSpin();
			loadDateCtl();
			loadAreaCtl();
			loadMe();
		});
		$('.headimg').click(function(){
			$('#upl-headimg').click();
		})
		$('.uploadheadimg-btn').click(function(){
			$('#upl-headimg').click();
		})
		$('.register-item input').change(function(){
			var enable=false;
			$('.register-item input').each(function(){
				var val = $(this).val();
				if(val==null||val==''||$('.headimg').is(':hidden'))
					enable=true;
			});
			if(!enable)
				$('.register-btn-next').removeClass('enable');
			else
				$('.register-btn-next').addClass('enable');
		})
		
		//加载自己的信息
		function loadMe(){
			$.post('/YuJian/Users/GetMyInformation'
					,function(data){
					closeSpin();
					user=data;
					if(user!=null){
						$('input[name=pwd]').val(user.password);
						$('input[name=myid]').val(user.userid);
					}
			},'json');
		}
		$('.register-btn-next').click(function(){
			$('#ctl-date').click();
			if($(this).hasClass('enable')){
				return;
			}
			showSpin();
			var formData = new FormData($("#data-form")[0]);
			//提交
			$.ajax({
	            type:'POST',
	            url:"/YuJianRoom/Common/AddIntroduce",
	            data:formData,
	            dataType:'json',
	            processData:false,
	            contentType: false,
	            success:function(data){
	            	closeSpin();
	            	if(data.status!=1){
	            		tipAlert(data.msg);
	            		return;
	            	}
	            	window.location.href="/YuJian/MyIntroducesEdit?id="+data.jsonData.userid;
	            }
	        });
		})
		
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
	    
	    function readAvatar(obj) {
			if (obj.files && obj.files[0]) {
				var m=obj.files[0].size/(1024*1024);
	         	if(m>5){
	             	tipAlert("上传的文件大小不能大于5M哦~珍爱流量~");
	         		return;
	         	}
	             var url = getObjectURL(obj.files[0]);
	             $('.headimg img').attr('src',url);
	             
	             $('.uploadheadimg-btn').hide();
	             $('.uploadheadimg-label').hide();
	             $('.headimg').show();
	             
	             $('.register-item input').change();
	         }
	    }
	</script>
</body>
</html>