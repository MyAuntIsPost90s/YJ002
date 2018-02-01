<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/mine.css?version=0.0.1">
<jsp:include page="../head.jsp"></jsp:include>
<title>个人中心</title>
</head>
<body>
	<div class="header-tip-bar">
		<div>个人中心</div>
	</div>
	<div class="wrapper minePage">
		<div class="mine">
			<div class="headimg">
				<i class="icon icon-crown vip" style="display: none" ></i>
				<img id="headimgurl" src="/YuJian/Contents/images/dfthead.png"/>
			</div>
			<div class="info">
				<label class="realname"><span id="realname"></span><i class="vip"  style="display: none">VIP</i></label>
				<label>交友ID:<span id="userid"></span></label>
				<label>魅力值:<span id="hotcount">0</span></label>
			</div>
			<div class="opt">
				<a href="/YuJian/MyInfo">修改资料</a>
			</div>
		</div>
		
		<div class="mine-menu">
			<a href="/YuJian/UserGifts?id=${id}">
				<i class="icon-menu icon-gift"></i>
				我的礼物
				<b class="icon icon-arrow"></b>
			</a>
			<a href="/YuJian/LeaveWords?id=${id}">
				<i class="icon-menu icon-word"></i>
				我的表情对话
				<b class="icon icon-arrow"></b>
			</a>
			<a href="/YuJian/UserSeekIntroduct">
				<i class="icon-menu icon-redheart"></i>
				我的求撮合
				<b class="icon icon-arrow"></b>
			</a>
			<a href="/YuJian/CollectUser">
				<i class="icon-menu icon-collect"></i>
				我的收藏
				<b class="icon icon-arrow"></b>
			</a>
			<a id="seekintroduct" href="/YuJian/MySeekIntroduct" style="display: none">
				<i class="icon-menu icon-likeme"></i>
				<span style="float: left;width: 14rem;">求撮合</span>
				<span class="point-cout" style="display: none">
					
				</span>
				<b class="icon icon-arrow"></b>
			</a>
			<a id='redpeople'>
				<i class="icon-menu icon-heard"></i>
				红人
				<b class="icon icon-arrow"></b>
			</a>
			<a href="/YuJian/UserIntroduces/MyMatchmaker">
				<i class="icon-menu icon-user-menu"></i>
				我的红娘
				<b class="icon icon-arrow"></b>
			</a>
			<a href="/YuJian/Recharge">
			<i class="icon-menu icon-doller"></i>
			<span style="float: left;width: 12rem;">金币充值</span>
			<span class="goldbalance">
				<i class="icon-menu icon-doller"></i>
				<span id="goldbalance">0</span>
			</span>
			<b class="icon icon-arrow"></b>
			</a>
			<a href="/YuJian/FeedBacks">
				<i class="icon-menu icon-connect"></i>
				联系
				<b class="icon icon-arrow"></b>
			</a>
		</div>
	</div>
	
	<jsp:include page="../foot.jsp"></jsp:include>
	
	<script>
		var user=null;
		$(function(){
			showSpin();
			loadMe();
		});
		
		$('#redpeople').click(function(){
			if(user==null)
				return;
			
			if(user.otherfunction!=OtherFunction.MATCHMAKER){
				phoneConfirm('该功能未开放，是否申请红娘功能？'
						,function(){
							closeAll();
							askBeMatchmaker();
						})
				return;
			}
			
			window.location.href='/YuJian/MyIntroduces';
		})
		
		//加载个人信息
		function loadMe(){
			$.post('/YuJian/Users/GetMyInformation'
					,function(data){
						closeSpin();
						user=data;
						//修改头像
						if(user.headimgurl!=null&&user.headimgurl!=''){
							$('#headimgurl').attr('src',user.headimgurl);
						}
						$('#realname').html(user.realname);
						$('#hotcount').html(user.hotcount);
						$('#goldbalance').html(user.goldbalance);
						$('#userid').html(idFormat(user.userid));
						if(user.usertype==UserType.VIP)
							setVip();
						if(user.otherfunction==OtherFunction.MATCHMAKER){
							$('#seekintroduct').show();
							getSeekIntroductCount();//获取未读数量
						}
			},'json');
		}
		
		function getSeekIntroductCount(){
			$.post('/YuJian/SeekIntroducts/GetMySeekIntroductCount'
					,{}
					,function(data){
						if(data>0&&data<100){
							$('.point-cout').html(data);
							$('.point-cout').show();
						}
						if(data>=100){
							$('.point-cout').html(99);
							$('.point-cout').show();
						}
					},'json')
		}
		
		function askBeMatchmaker(){
			$.post('/YuJian/FeedBacks/Add'
					,{feedbacktype:4,feedbackcontent:"请求开放红人功能，成为红娘用户"}
					,function(data){
						tipAlert(data.msg);
					},'json');
		}
		
		function setVip(){
			$('.vip').show();	
		}
	</script>
</body>
</html>