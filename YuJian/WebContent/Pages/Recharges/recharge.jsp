<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/charge.css">
<title>充值</title>
</head>
<body>
	<div class="header-tip-bar">
		<i class="icon icon-arrow" onclick="javascript:window.history.go(-1);"></i>
		<div>充值</div>
	</div>
	<div class="wrapper rechargePage" style="padding-top:49px;">
		<div class="header">
			<label>充值</label>
		</div>
		<label class="balance-tip">
		金币余额:<span id="balance">20</span>
		金币  (10金币=1元 vip=1000元)
		</label>
		<div class="charges">
			<div class="active">10 金币</div>
			<div>50 金币</div>
			<div>100 金币</div>
			<div>1000 金币</div>
			<div>5000 金币</div>
			<div id="vip">VIP</div>
		</div>
		<label class="balance-tip">
		售价:<span id="cost" style="color:red"> 1</span>元<span id="sendtip"></span>
		</label>
		<div class="charges-btn">
			<button onclick="pay()">充值</button>
		</div>
	</div>
</body>

<script type="text/javascript" src = "http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script>
	var selecttype='${selecttype}';
	var sysconfig=null;
	var user=null;
	$(function(){
		showSpin();
		if(selecttype=='vip')
			$('#vip').click();
		//loadWXConfig();
		loadUser();
		loadSysConfig();
		closeSpin();
	})
	$('.charges div').click(function(){
		if(user!=null&&user.usertype==1&&$(this).attr('id')=='vip')
			return;
		
		$('#sendtip').hide();
		$('.charges div').removeClass('active');
		$(this).addClass('active');
		var val=$(this).html();
		if(val=='VIP'){
			if(sysconfig!=null&&sysconfig.sendgold>0){
				$('#sendtip').html('（当前成为vip，立即赠送'+sysconfig.sendgold+'金币哦）');
				$('#sendtip').show();
			}
			$('#cost').html('1000');
			return;
		}
		$('#cost').html(parseInt(val.replace("金币",''))/10);
	})
	
	
	function loadSysConfig(){
		$.get('/YuJianRoom/SysConfigs/GetSingle'
	 			,function(data){
	 				if(data==null){
	 					sysconfig={photocount:8}
	 				}
	 				sysconfig=data;
		},'json')
	 }
	
	function loadUser(){
		$.post('/YuJian/Users/GetMyInformation'
				,function(data){
			user=data;
			$('#balance').html(user.goldbalance);
			if(user.usertype==1){
				$('#vip').css('background-color','#888');
				$('#vip').css('color','#fff');
			}
		},'json');
	}
	
	function pay(){
		if(user==null)
			return;
		
		showSpin();
		var type=$('#cost').html();
		$.post("/YuJian/Pay/DoPay"
				,{type:type}
				,function(data){
					closeSpin();
					if(data.status!=1){
						tipAlert(data.msg);
						return;
					}
					loadTip(data.msg);
					
					var map=data.jsonData;
				  	WeixinJSBridge.invoke('getBrandWCPayRequest',{  
		                "appId" : map.appId,                  //公众号名称，由商户传入  
		                "timeStamp": map.timeStamp,          //时间戳，自 1970 年以来的秒数  
		                "nonceStr" : map.nonceStr,         //随机串  
		                "package" : map.packageValue,      //<span style="font-family:微软雅黑;">商品包信息</span>  
		                "signType" : map.signType,        //微信签名方式:  
		                "paySign" : map.paySign           //微信签名  
		                },function(res){
			                if(res.err_msg == 'get_brand_wcpay_request:ok' ){
			                    window.location.href="/YuJian/PayTip";  
			                }else{  
			                	tipAlert("支付失败");  
			                }  
		            });
				},'json');
	}
</script>
</html>