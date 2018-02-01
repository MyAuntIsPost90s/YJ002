<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/meinfo.css?version=0.1.6">
<script type="text/javascript" src = "/YuJian/Contents/js/ajaxFileUpload.js"></script>
<title>红人</title>
</head>
<body>
	<div class="header-tip-bar">
		<i onclick="window.location.href='/YuJian/MyIntroduces'" class="icon icon-arrow"></i>
		<div id="realname">红人</div>
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
		
		<div class="me-multimedias">
			<a href="" class="me-video" style="display: none">
				<i class="icon icon-play"></i>
			</a>
			<div class="img-border camera">
				<i class="icon icon-camera"></i>
			</div>
		</div>
		
		<div class="me-content">
			<a href="/YuJian/UpdateRedBaseInfo?userid=${id}" class="header">基本资料 <i class="icon icon-arrow"></i></a>
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
			<a href="/YuJian/UpdateRedSignContent?userid=${id}" class="header">内心独白 <i class="icon icon-arrow"></i></a>
			<div id="signcontent">
				<!-- 内心独白 -->
				无
			</div>
		</div>
		<div class="me-content">
			<a href="/YuJian/UpdateRedSelectCondition?userid=${id}" class="header">择偶条件 <i class="icon icon-arrow"></i></a>
			<div id="selectcondition">
				<label>无</label>
			</div>
		</div>
		<div class="me-content">
			<a href="/YuJian/UpdateRedHobby?userid=${id}" class="header">兴趣爱好 <i class="icon icon-arrow"></i></a>
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
		<div class="me-content">
			<a href="/YuJian/GiftRank?id=${id}" class="header">礼物榜 <i class="icon icon-arrow"></i></a>
			<a href="/YuJian/UserGifts?id=${id}" class="header">TA的礼物 <i class="icon icon-arrow"></i></a>
		</div>
	</div>
	<input id="upl-headimg" type="file" name="headimg" hidden="true" onchange="readAvatar(this)" />
	<input id="upload" name="upload" onchange="upload(this)" type="file" hidden="true">
	
	<script type="text/javascript">
		var user=null;
		var uiuserid=${id};
		var sysconfig={photocount:8};
		$(function(){
			showSpin();
			loadUser();
			getUserImgs();
			loadSysConfig();
		});
		
		//上传头像
		$('.me-headimg').click(function(){
			$('#upl-headimg').click();
		})
		//上传视频或照片
		$('.me-multimedias .icon-camera').click(function(){
			$('#upload').click();
		});
		
		function upload(obj){
			if(user==null)
				return;
			
			var ext = obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();
			if (obj.files && obj.files[0]){
				$('#headimgurl').hide();
				$('#headimgurl').show();
				if(ext=='.mov'||ext=='.mp4'){
					var m=obj.files[0].size/(1024*1024);
		         	if(m>30){
		             	tipAlert("上传的文件大小不能大于30M哦~珍爱流量~");
		         		return;
		         	}
		         	uploadVideo();
				}
				else if(ext=='.jpg'||ext=='.jpeg'||ext=='.png'||ext=='.gif'){
					if($('.me-multimedias img').length>=sysconfig.photocount){
						tipAlert('最多只能上传'+sysconfig.photocount+'张图片');
						return;
					}
					uploadImgs(obj.files[0]);
				}
				else{
					tipAlert('只能上传图片或视频哦');
				}
			}
		}
		
		//上传视屏
		function uploadVideo(){
         	showSpin();	//转圈提示     	
         	//上传
         	$.ajaxFileUpload({
    			url: '/YuJianRoom/Common/UpdateVideo?userid='+user.uiuserid+'&pwd='+'',
                type: 'post',
                secureuri: false,
                fileElementId: "upload",
                dataType: 'json',
                success: function (data){  //服务器成功响应处理函数 
		         	if(data!=null){
	            		user=data;
		         	}
		         	if(user.videourl!=null&&user.videourl.trim()!=''){
						$('.me-video').show();
						$('.me-video').css('background-image','url('+user.headimgurl+')');
						$('.me-video').attr('href','/YuJian/UserVideo?videourl='+user.videourl);
					}
            		closeSpin();
            		tipAlert("上传成功");
                },
                error: function (data, status, e){//服务器响应失败处理函数
                	closeSpin();
                }
    		});
		}
		
		//上传头像
		function uploadHeadImg(file){
	    	showSpin();
          	//压缩
        	lrz(file, {width:500,height:500}).then(function(data) {
                   //上传
                   $.ajax({
                	   url: '/YuJianRoom/Common/UploadUserHeadImgs?userid='+user.uiuserid+'&pwd='+'',
                       async: true,
                       cache: false,
                       contentType: false,
                       processData: false,
                       method: "post",
                       data: data.formData,
                       success: function (data){  //服务器成功响应处理函数
                    	   	closeSpin();
	       					//重载
	       					user=data;
	       					//修改头像
	       					if(user.headimgurl!=null&&user.headimgurl!=''){
	       						$('#headimgurl').attr('src',user.headimgurl);
	       					}
                       },
                       error: function (data, status, e){//服务器响应失败处理函数
                       		closeSpin();
                       }
                   })
            });
		}
		
		//上传照片
		function uploadImgs(file){
			if(user==null)
	    		return;
	   		showSpin();
          	//压缩
	       	lrz(file,{quality:0.7}).then(function(data) {
	                  //上传
	                  $.ajax({
	                	  url: '/YuJianRoom/Common/UploadUserImgs?userid='+user.uiuserid+'&pwd='+'',
	                      async: true,
	                      cache: false,
	                      contentType: false,
	                      processData: false,
	                      method: "post",
	                      data: data.formData,
	                      success: function (data){  //服务器成功响应处理函数
	                    	closeSpin();
	                      	if(data==null){
	                      		tipAlert("上传失败，请稍后再试");
	                      		return;
	                      	}
	                      	var url = getMiniUrl(data.userimgurl);
	                      	var temp='<div class="img-border"><img data-imgid="{0}" src="{1}" onclick="window.location.href=\'/YuJian/UserIntroduceImgs?userid={2}\'" /></div>'
	      					temp=temp.format(data.userimgid,url,uiuserid);
	                      	$('.me-multimedias .camera').before(temp);
	                      },
	                      error: function (data, status, e){//服务器响应失败处理函数
	                      	closeSpin();
	                      }
	                  })
	           });
		}
		
		//加载红人的信息
		function loadUser(){
			$.post('/YuJian/UserIntroduces/GetIntroduce'
					,{uiuserid:uiuserid}
					,function(data){
				closeSpin();
				user=data;
				if(user==null)
					return;
				$('#userid').html(idFormat(user.userid));
				$('#sex').html(user.sex==0?"♀":"♂");
				$('#married').html(marriedFormat(user.married));
				//修改头像
				if(user.headimgurl!=null&&user.headimgurl!=''){
					$('#headimgurl').attr('src',user.headimgurl);
				}
				if(user.realname!=null&&user.realname.trim()!='')
					$('#realname').html(user.realname);
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
				
				if(user.otherfunction==OtherFunction.MATCHMAKER){
					$('#matchmaker').show();
				}
			},'json');
		}
		
		//获取红人的照片
		function getUserImgs(){
			$.post('/YuJian/UserImgs/GetUserImgs'
					,{userid:uiuserid}
					,function(data){
					if(data==null||data.length<1){
						return;
					}
					var html='';
					for(var i=0;i<data.length;i++){
						var url = getMiniUrl(data[i].userimgurl);
						var temp='<div class="img-border"><img data-imgid="{0}" src="{1}" onclick="window.location.href=\'/YuJian/UserIntroduceImgs?userid=${id}\'" /></div>'
						temp=temp.format(data[i].userimgid,url);
						html+=temp;
					}
					$('.me-multimedias .camera').before(html);
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
		
		function readAvatar(obj) {
	    	if(user==null)
		    	return;

			if (obj.files && obj.files[0]) {
	            uploadHeadImg(obj.files[0]);	//上传头像
	         }
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