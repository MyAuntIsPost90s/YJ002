<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/meinfo.css?version=0.1.2">
<script type="text/javascript" src = "/YuJian/Contents/js/ajaxFileUpload.js"></script>
<title>我</title>
</head>
<body>
	<div class="header-tip-bar">
		<i onclick="window.location.href='/YuJian/MyInfo'" class="icon icon-arrow"></i>
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
			<a href="/YuJian/UpdateMatchmakerIntroduct" class="header introduce-header"><i class="icon-introduct"></i>红娘简介 <i class="icon icon-arrow"></i></a>
			<div id="matchmakerintroduct">
				<!-- 红娘简介 -->
				无
			</div>
		</div>

		<div class="me-content">
			<a href="/YuJian/UpdateMatchmakerGoodAt" class="header"><i class="icon icon-goodat">擅</i>擅长领域 <i class="icon icon-arrow"></i></a>
			<div class="goodat" id="matchmakergoodat">
				<label>无</label>
			</div>
		</div>
		<div class="me-content">
			<a href="/YuJian/UpdateMatchmakerAdvise" class="header"><i class="icon icon-advice">建</i>恋爱建议 <i class="icon icon-arrow"></i></a>
			<div id="matchmakeradvise">
				无
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		var user=null;
		$(function(){
			showSpin();
			loadMe();
			loadSysConfig();
		});
		
		//加载自己的信息
		function loadMe(){
			$.post('/YuJian/Users/GetMyInformation'
					,function(data){
				closeSpin();
				user=data;
				if(user==null)
					return;
				$('#sex').html(user.sex==0?"♀":"♂");
				//修改头像
				if(user.headimgurl!=null&&user.headimgurl!=''){
					$('#headimgurl').attr('src',user.headimgurl);
				}
				if(user.realname!=null&&user.realname.trim()!=''){
					$('#realname').html('红娘-'+user.realname);
					document.title='红娘-'+user.realname;
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
	</script>
</body>
</html>