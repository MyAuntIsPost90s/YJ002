<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/meinfo.css?version=0.1.2">
<script type="text/javascript" src = "/YuJian/Contents/js/ajaxFileUpload.js"></script>
<title>红娘</title>
</head>
<body>
	<div class="header-tip-bar">
		<i onclick="javascript:window.history.go(-1);" class="icon icon-arrow"></i>
		<div id="realname">红娘</div>
	</div>
	<div class="wrapper myinfo">
		<div class="me-header">
			<div class="me-headimg">
				<img id="headimgurl" src="/YuJian/Contents/images/dfthead.png"/>
				<label>
					<span id="sex">♂</span>
					<span id="age">23</span>
					<span id="astro">双鱼座</span>
				</label>
			</div>
		</div>
		<div class="me-content">
			<a class="header introduce-header"><i class="icon-introduct"></i>红娘简介</a>
			<div id="matchmakerintroduct">
				<!-- 红娘简介 -->
				无
			</div>
		</div>

		<div class="me-content">
			<a class="header"><i class="icon icon-goodat">擅</i>擅长领域</a>
			<div class="goodat" id="matchmakergoodat">
				<label>无</label>
			</div>
		</div>
		<div class="me-content">
			<a class="header"><i class="icon icon-advice">建</i>恋爱建议</a>
			<div id="matchmakeradvise">
				无
			</div>
		</div>
		
		<div class="me-content">
			<a href="/YuJian/GiftRank?id=${id}" class="header">礼物榜 <i class="icon icon-arrow"></i></a>
			<a href="/YuJian/UserGifts?id=${id}" class="header">TA的礼物 <i class="icon icon-arrow"></i></a>
		</div>
	</div>
	<div class="tabbar">
		<a href="/YuJian/LeaveWords?id=${id}" class="item">
			<i class="icon icon-word-base"></i>
			表情对话
		</a>
		<a href="/YuJian/GiftShop?id=${id}" class="item">
			<i class="icon icon-gift-base"></i>
			送礼
		</a>
		<a class="item" id="share-btn">
			<i class="icon icon-share"></i>
			分享
		</a>
	</div>
	<a class="floatright-btn">
		<i class="icon icon-heart-white"></i>
		求脱单
	</a>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript">
		var userid=${id};
		var myid=${myid};
		var macthtip='${macthtip}';
		var user=null;

		$(function(){
			showSpin();
			
			loadSysConfig();
			$.ajaxSetup({async : false });
			loadUser();
			loadWXConfig();
			$.ajaxSetup({async : true });
			
			closeSpin();
		});
		
		$('#headimgurl').click(function(){
			var url=$(this).attr('src');
			if(url==''||url==null)
				return;
			headImgBoradShow(url);
		});
		
		$('.floatright-btn').click(function(){
			tipAlert(macthtip);
			$.post('/YuJian/SeekIntroducts/Add'
					,{touserid:userid,userid:myid,type:SeekIntroductType.SINGLE});
		});
		
		$('#share-btn').click(function(){
			if(user==null)
				return;
			tipShareClick();
		})
		
		function loadWXConfig(){
			//alert(window.location.href.replace('&','#amp'))
			$.get('/YuJian/Share/GetShareConfig?url='+window.location.href.replace('&','amp;')
					,function(data){
						wx.config({
						    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
						    appId: data.appId, // 必填，公众号的唯一标识
						    timestamp: data.timestamp, // 必填，生成签名的时间戳
						    nonceStr: data.noncestr, // 必填，生成签名的随机串
						    signature: data.signature,// 必填，签名，见附录1
						    jsApiList: [  
					            'onMenuShareTimeline',  
					            'onMenuShareAppMessage',  
					            'onMenuShareQQ',  
					            'onMenuShareWeibo',  
					        ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
						});
						wx.ready(function(){							
							wxshareconfig={
								title: '推荐：'+user.realname, // 分享标题
						 		desc: "我向你推荐了红娘"+user.realname+"，快来看看吧", // 分享描述
						 		link: "http://"+window.location.host+'/YuJian/ShareDetail?id='+user.userid,
						 		imgUrl:"http://"+window.location.host+user.headimgurl, // 分享图标
						        success: function (res) {
						        
						        },
						        cancel: function (res) {
						        }
							}
							
						 	wx.onMenuShareTimeline(wxshareconfig);
							wx.onMenuShareAppMessage(wxshareconfig);
							wx.onMenuShareQQ(wxshareconfig);
							wx.onMenuShareWeibo(wxshareconfig);
						});
			},'json');
		}
		//加载用户的信息
		function loadUser(){
			$.post('/YuJian/Users/GetOtherUserInfo'
					,{userid:userid}
					,function(data){
						user=data;
						if(user==null)
							return;
						$('#sex').html(user.sex==0?"♀":"♂");
						//修改头像
						if(user.headimgurl!=null&&user.headimgurl!=''){
							$('#headimgurl').attr('src',user.headimgurl);
						}
						if(user.realname!=null&&user.realname.trim()!=''){
							$('#realname').html(user.realname);
							document.title=user.realname;
						}
						if(user.birthday!=null&&user.birthday.trim()!='')
							$('#astro').html(getAstro(user.birthday));
						if(user.birthday!=null&&user.birthday.trim()!='')
							$('#age').html(getAge(user.birthday));
						if(user.matchmakerintroduct!=null&&user.matchmakerintroduct!=''){
							$('#matchmakerintroduct').html(user.matchmakerintroduct);
						}
						if(user.matchmakeradvise!=null&&user.matchmakeradvise!=''){
							$('#matchmakeradvise').html(user.matchmakeradvise);
						}
						if(user.matchmakergoodat!=null&&user.matchmakergoodat!=''){
							var strs = user.matchmakergoodat.split(',');
							if(strs.length>1){
								var html='';
								for(var i=0;i<strs.length;i++){
									if(strs[i]!=''){
										html+='<label>'+strs[i]+'</label>';
									}
								}
								$('#matchmakergoodat').html(html);
							}
						}
			},'json');
		}
		
		function loadSysConfig(){
        	$.get('/YuJianRoom/SysConfigs/GetSingle'
        			,function(data){
        				if(data==null){
        					sysconfig={photocount:8}
        				}
        				sysconfig=data;
        				$('.me-header').css("background-image","url("+sysconfig.userbgurl+")");
        	},'json')
	   }
		
		function marriedFormat(val){
			if(val==0)
				return '未婚';
			if(val==1)
				return '已婚';
			if(val==2)
				return '保密';
		}
	</script>
</body>
</html>