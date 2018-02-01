<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/meinfo.css?version=0.1.6">
<script type="text/javascript" src = "/YuJian/Contents/js/ajaxFileUpload.js"></script>
<title>TA</title>
</head>
<body>
	<div class="header-tip-bar">
		<i id="btn-return" class="icon icon-arrow"></i>
		<div id="realname">TA</div>
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
		
		<div class="me-multimedias" style="display: none">
			<a href="" class="me-video" style="display: none">
				<i class="icon icon-play"></i>
			</a>
		</div>
		
		<div class="me-content">
			<a class="header">基本资料</a>
			<div class="myinfo-base">
				<label>编号：<span id="userid">#0000</span></label>
				<label>身高：<span id="height">未透露</span></label>
				<label>职业：<span id="occupation">未透露</span></label>
				<label>生肖：<span id="bornin">未透露</span></label>
				<label>月薪：<span id="wage">未透露</span></label>
				<label>学历：<span id="record">未透露</span></label>
				<label>婚姻：<span id="married">未透露</span></label>
				<label>血型：<span id="bloodtype">未透露</span></label>
				<label class="label-block">现居：<span id="address">未透露</span></label>
			</div>
		</div>
		<div class="me-content">
			<a class="header">内心独白</a>
			<div id="signcontent">
				<!-- 内心独白 -->
				无
			</div>
		</div>
		<div class="me-content">
			<a class="header">择偶条件</a>
			<div id="selectcondition">
				<label>无</label>
			</div>
		</div>
		<div class="me-content">
			<a class="header">兴趣爱好</a>
			<div id="hobby">
				<i class="icon-hobby icon-sport-active" data-val="运动"></i>
				<i class="icon-hobby icon-music-active" data-val="音乐"></i>
				<i class="icon-hobby icon-movie-active" data-val="电影"></i>
				<i class="icon-hobby icon-food-active" data-val="美食"></i>
				<i class="icon-hobby icon-game-active" data-val="游戏"></i>
				<i class="icon-hobby icon-out-active" data-val="户外"></i>
				<i class="icon-hobby icon-read-active" data-val="文学"></i>
				<i class="icon-hobby icon-dance-active" data-val="艺术"></i>
				<i class="icon-hobby icon-animation-active" data-val="动漫"></i>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var userid=${id};
		var user=null;
		var sysconfig=null;
		
		$(function(){
			showSpin();
			
			$.ajaxSetup({async : false });
			loadUser();
			getUserImgs();
			loadSysConfig();
			$.ajaxSetup({async : true });
			
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
						$('#userid').html(idFormat(user.userid));
						$('#sex').html(user.sex==0?"♀":"♂");
						$('#married').html(marriedFormat(user.married));
						//修改头像
						if(user.headimgurl!=null&&user.headimgurl!=''){
							$('#headimgurl').attr('src',user.headimgurl);
							
							//添加默认照
							var html='<div class="img-border"><img src="{0}" onclick="window.location.href=\'/YuJian/ShareImgs?userid={1}\'" /></div>'
							html=html.format(data.headimgurl,data.userid);
							$('.me-multimedias').append(html);
							$('.me-multimedias').show();
						}
						if(user.realname!=null&&user.realname.trim()!=''){
							$('#realname').html(user.realname);
							document.title=user.realname;
						}
						if(user.height!=null&&user.height.trim()!='')
							$('#height').html(user.height);
						if(user.address!=null&&user.address.trim()!='')
							$('#address').html(user.address);
						if(user.birthday!=null&&user.birthday.trim()!='')
							$('#astro').html(getAstro(user.birthday));
						if(user.birthday!=null&&user.birthday.trim()!='')
							$('#age').html(getAge(user.birthday));
						if(user.birthday!=null&&user.birthday.trim()!='')
							$('#bornin').html(getBornIn(user.birthday));
						if(user.wage!=null&&user.wage.trim()!='')
							$('#wage').html(user.wage);
						if(user.record!=null&&user.record.trim()!='')
							$('#record').html(user.record);
						if(user.bloodtype!=null&&user.bloodtype.trim()!='')
							$('#bloodtype').html(user.bloodtype);
						if(user.occupation!=null&&user.occupation.trim()!='')
							$('#occupation').html(user.occupation);
		
						if(user.signcontent!=null&&user.signcontent.trim()!='')
							$('#signcontent').html(user.signcontent);
						if(user.videourl!=null&&user.videourl.trim()!=''){
							$('.me-multimedias').show();
							$('.me-video').show();
							$('.me-video').css('background-image','url('+user.headimgurl+')');
							$('.me-video').attr('href','/YuJian/ShareVideo?videourl='+user.videourl);
						}
						if(selectcondition!=null&&selectcondition!=''){
							var objs=$('#data-form select');
							var strs=user.selectcondition.split(',');
							
							var html='';
							for(var i=0;i<strs.length;i++){
								if(strs[i]!='')
									html+="<label>"+strs[i]+"</label>";
							}
							if(strs.length>1)
								$('#selectcondition').html(html);
						}
						if(user.hobby!=null&&user.hobby!=""){
							var arr=user.hobby.split(' ');
							for(var i=0;i<arr.length;i++){
								$('i[data-val='+arr[i]+']').show();
							}
						}
			},'json');
		}
		
		//获取用户照片
		function getUserImgs(){
			$.post('/YuJian/UserImgs/GetUserImgs'
					,{userid:userid}
					,function(data){
						if(data==null||data.length<1){
							return;
						}
						var html='';
						for(var i=0;i<data.length;i++){
							var url = getMiniUrl(data[i].userimgurl);
							var temp='<div class="img-border"><img data-imgid="{0}" src="{1}" onclick="window.location.href=\'/YuJian/ShareImgs?userid={2}\'" /></div>'
							temp=temp.format(data[i].userimgid,url,userid);
							html+=temp;
						}
						$('.me-multimedias').show();
						$('.me-multimedias').append(html);
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