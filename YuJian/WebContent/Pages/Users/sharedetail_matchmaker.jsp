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
		<i id="btn-return" class="icon icon-arrow"></i>
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
	</div>
	<script type="text/javascript">
		var userid=${id};
		var user=null;
		var sysconfig=null;
		$.ajaxSetup({async : false });
		$(function(){
			showSpin();	
			loadUser();
			loadSysConfig();
			closeSpin();
		});
		$('#btn-return').click(function(){
			if(sysconfig==null||sysconfig.sharebackurl==''||sysconfig.sharebackurl==null)
				return;
			window.location.href=sysconfig.sharebackurl;
		})
		$('#headimgurl').click(function(){
			var url=$(this).attr('src');
			if(url==''||url==null)
				return;
			headImgBoradShow(url);
		});
		
		function loadSysConfig(){
        	$.get('/YuJianRoom/SysConfigs/GetSingle'
        			,function(data){
        				sysconfig=data;
        				$('.me-header').css("background-image","url("+sysconfig.userbgurl+")");
        	},'json')
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