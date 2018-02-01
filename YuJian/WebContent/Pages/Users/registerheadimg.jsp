<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../head.jsp"></jsp:include>
<script type="text/javascript" src = "/YuJian/Contents/js/ajaxFileUpload.js"></script>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/register.css">
<title>上传头像</title>
<style>
	.wellcome-borad{
		position: absolute;
		margin: auto;
		width: 20rem;
		height: 100vh;
		background: url(/YuJian/Contents/images/wellcome.png);
		background-size: 100% 100%;
		top:0;
		z-index: 99;
	}
</style>
</head>
<body>
	<div class="header-tip-bar">
		<div>上传头像</div>
	</div>
	<div class="wrapper registerheadimg">
		<div class="uploadheadimg-btn">
			<img src="/YuJian/Contents/images/camera-gray.png" />
			<p>点击上传头像</p>
		</div>
		<div class="headimg" hidden="true">
			<img />
		</div>
		<label class="uploadheadimg-label">上传头像，人气立即提升 <i>76.15%</i></label>
		<button class="register-btn-next enable">下一步</button>
		<label class="uploadheadimg-tip"><i>提示：</i>支持<i>JPG，JPGE，PNG</i>格式，图片小于<i>5M</i></label>
	</div>
	<input id="upl-headimg" type="file" name="headimg" hidden="true" onchange="readAvatar(this)" />
	
	<script type="text/javascript">
		var user=null;
		$(function(){
			loadMe();
		});
		
		$('.headimg').click(function(){
			$('#upl-headimg').click();
		})
		$('.uploadheadimg-btn').click(function(){
			$('#upl-headimg').click();
		})
		$('.register-btn-next').click(function(){
			if($(this).hasClass('enable')){
				return;
			}
			window.location.href="/YuJian/RegisterBase";
		})
		
		//加载自己的信息
		function loadMe(){
			$.post('/YuJian/Users/GetMyInformation'
					,function(data){
				user=data;
				
				if(user.headimgurl==null||user.headimgurl==''){
					$('body').append('<div class="wellcome-borad" onclick="$(this).animate({\'top\':\'-100vh\'},500)"></div>');
				}
			},'json');
		}
		//上传头像
		function uploadHeadImg(file){
         	//压缩
         	lrz(file,  {width:500,height:500}).then(function(data) {
                    //上传
                    $.ajax({
                        url: '/YuJianRoom/Common/UploadUserHeadImgs?userid='+user.userid+'&pwd='+user.password,
                        async: true,
                        cache: false,
                        contentType: false,
                        processData: false,
                        method: "post",
                        data: data.formData,
                        success: function (data){  //服务器成功响应处理函数
                        	closeSpin();
                        	$('.register-btn-next').removeClass('enable');	
                        	$('.headimg img').attr('src',data.headimgurl);
        					//重载
        					user=data;
        					$.get("/YuJian/Users/ReloadUser");	//重载user
                        },
                        error: function (data, status, e){//服务器响应失败处理函数
                        	closeSpin();
                        }
                    })
             });
		}

	    function readAvatar(obj) {
	    	if(user==null)
		    	return;
	    	
			if (obj.files && obj.files[0]) {
				if(obj.files[0].size<1){
					return;
				}

	             var url = getObjectURL(obj.files[0]);
	             //$('.headimg img').attr('src',url);
	             
	             $('.uploadheadimg-btn').hide();
	             $('.uploadheadimg-label').hide();
	             $('.headimg').show();
	             
	             showSpin();
	             uploadHeadImg(obj.files[0]);	//上传头像
	         }
	    }
	    
	</script>
</body>
</html>