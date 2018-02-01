<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/commonuser.css?version=0.0.3">
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/swiper-3.4.2.min.css?version=0.1">
<script type="text/javascript" src = "/YuJian/Contents/js/swiper-3.4.2.jquery.min.js"></script>
<title>芋圆</title>
</head>
<body id="main">
 	<div class="nav-index">
 		<!-- <a href="/YuJian/Search" class="icon icon-search"></a> -->
		<div onclick="showAddrBoard()" class="addr-btn">
			<span class="now-addr">全国</span>
		</div>
     </div>
	 <div class="index-page wrapper">
        <!-- 轮播图区域 -->
        <div class="swiper-container canhide" style="width: 20rem;height: 8rem;display: none">
	        <div class="swiper-wrapper">
	          
	        </div>
	        <!-- 如果需要分页器 -->
	        <div class="swiper-pagination"></div>
    	</div>
        
        <!-- <div class="now-addr canhide">全国</div> -->
        <div class="filter-bar canhide">
        	<div class="nav-btn user-type active" data-id="0" data-otherfunction="-1">普通</div>
        	<div class="nav-btn user-type" data-id="1" data-otherfunction="-1" >VIP</div>
        	<div class="nav-btn user-type" data-id="99" data-otherfunction="1" >红娘</div>
        	<!-- <div onclick="showAgeBoard()" class="nav-btn">筛选</div> -->
        	<div onclick="window.location.href='/YuJian/Filter'+window.location.search" class="nav-btn">筛选</div>
        </div>
        <ul class="user-list canhide">
		<!-- 用户数据 -->
			
        </ul>
        
        <!-- 弹窗部分 -->
	    <div id="address-board" class="nav-btn-borad">
	    	<div class="addr-header">
	    		<div id='selected-addr' class="selected-addr">
	    			全国
	    		</div>
	    		<div class="loaction-addr" onclick="getMyLoaction()">
	    			<img id="img-loaction" src="/YuJian/Contents/images/reload.png"/>
	    			定位
	    		</div>
	    	</div>
	    	<div class="addr-select">
	    		<h1>选择</h1>
	    		<div class="addrs-list">
		    		
	    		</div>
	    	</div>
	    </div>
    </div>
	<jsp:include page="../foot.jsp"></jsp:include>
	<script src="/YuJian/Contents/js/LAreaData.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=2GXsaGKtG0aKdOBpHlXWiiMLdyaDthuj"></script>
	<script>
		var myusertype=${usertype};
		var viptip='${viptip}';
		var page=-1;
		var beginage=18;
		var endage=99;
		var islocation=0;
		var index=0;
		var usertype=0;
		var otherfunction=-1;
		var postData={condition:null,sex:null,height:null,btime:null,etime:null,bloodtype:null,record:null,wage:null,page:-1,pagesize:12,usertype:0,otherfunction:0};
		
		var ListAnimation={
			show:function(){
				var html='<div class="listspinner"><div class="rect1"></div><div class="rect2"></div><div class="rect3"></div>'
				  	+'<div class="rect4"></div><div class="rect5"></div>'
				  	+'<p>正在加载</p></div>'
				$('.user-list').html(html);
			},
			close:function(){
				$('.listspinner').remove();
			}
		}
		
		$(function(){
			loadPostData();	//加载塞选参数
			loadSelectAddr(-1);	//加载地址
			loadBanner();	//加载轮播图
			//getMyLoaction();	//获取我的定位
			reLoad();//加载用户
			setUserListHeight();
		});
		
		function setUserListHeight(){
			$('.user-list').css("min-height",(document.body.clientHeight-$('.swiper-container').height()-49-$('.nav-index').height())+"px")
		}
		$('.user-type').click(function(){	
			if(!$(this).hasClass('active')){
				$('.nav-btn').removeClass('active');
				$(this).addClass('active');
				
				usertype=$(this).attr('data-id');
				otherfunction=$(this).attr('data-otherfunction');
				reLoad();//加载
				
				if(usertype==UserType.VIP){
					vipTip(myusertype,viptip);
				}
			}
		});
		$('.nav-index .addr-btn').click(function(){	
			if(!$(this).hasClass('active')){
				$(this).addClass('active');
			}else{
				$(this).removeClass('active');
			}
		});
		function showAddrBoard(){
			if($('#address-board').css('display')=='none'){
				$('.nav-btn-borad').hide();
				$('.canhide').hide();
				
				$('#address-board').show(300);
			}else{
				$('.canhide').show();
				$('#address-board').hide();
			}
		}
		$('#usertype').change(function(){
			reLoad();
			page++;
		})
		function addrChangeLoad() { 
			//当定位改变时重载
			$('.addr-btn').removeClass('active');
			reLoad();
		}
		function reLoad(){
			stop=false;
			end=false;
			index=0;
			page=1;
			ListAnimation.show();
			loadData();	//加载数据
			loadBanner();	//加载轮播图
		}
		
		//滚动加载事件
		var stop=false;
		var end=false;
		$(window).scroll(function() {
			if(stop==true||end==true||page<0)
				return;
			var totalheight = parseFloat($(window).scrollTop()+$(window).height()/2); 
		    if($(document).height()*0.5 <= totalheight){
		        if(stop==false){ 
		        	stop=true;
		        	page++;
		        	loadData();
		        }
		    }
		});
		
		function collect(obj){
			if($(obj).attr("src")=="/YuJian/Contents/images/icon-collect.png"){
				$.post("/YuJian/UserCollections/UnCollect",{usercollectionid:$(obj).attr('data-id')});	
				$(obj).attr("src","/YuJian/Contents/images/icon-nocollect.png");
			}
			else{
				$.post("/YuJian/UserCollections/Collect",{usercollectionid:$(obj).attr('data-id')});
				$(obj).attr("src","/YuJian/Contents/images/icon-collect.png");
			}
		}
		
		var loadKey='';//防止请求错乱
		function loadData(){
			loadKey=Math.random();
			var address=$('.selected-addr').html().replace('全国','').trim();
			usertype=usertype==UserType.RED?null:usertype;
			if(address=='')
				address==null;
			if(page<0)
				return;
			
			postData.address=address;
			postData.usertype=usertype;
			postData.page=page;
			postData.otherfunction=otherfunction;
			
			$.post("/YuJian/Users/GetUsers?loadKey="+loadKey
					,postData
					,function(data){
						if(data.key!=loadKey)
							return;
						ListAnimation.close();
						if(data!=null&&data.list!=null&&data.list.length<1){
							return;
						}
						stop=false;
						var html='';
						for(var i=0;i<data.list.length;i++){
							if(data.list[i].usertype!=(UserType.ADMIN+99))
			            		html+=getUserItemHtml(data.list[i]);
							else
								html+=getAdvertItemHtml(data.list[i]);
						}
						$('.user-list').append(html);
					},'json')
		}
		
		//获取单项UserItem html
		function getUserItemHtml(data){
			var style='';
			
			if((index+2)%3==0)
				style='style="margin-left:0.5rem;margin-right:0.5rem"';
			else
				style='style="margin-right:0rem"';
			index++;
			
			var temp='<li '+style+'>'
				+'{0}'
				+'<a href="{4}"><img src="{1}" class="picture"></a>'
				+'<p>{5}<span>{2}</span><i>{3} 岁</i></p>'
				+'<label>{6}</label>'
    			+'</li>';
	    	var colloct='';
	    	if(data.iscollect>0){
	    		colloct='<img onclick="collect(this)" src="/YuJian/Contents/images/icon-collect.png" data-id="'+data.userid+'" class="icon-collect" >'
	    	}
	    	else{
	    		colloct='<img onclick="collect(this)" src="/YuJian/Contents/images/icon-nocollect.png" data-id="'+data.userid+'" class="icon-collect" >'
	    	}
	    	var imgHtml='';
	    	if(data.usertype==1)
	    		imgHtml='<img src="/YuJian/Contents/images/huangguan.png" width="22" style="float:left;margin-top:-2px" />';
	    	temp = temp.format(colloct
	    			,data.headimgurl==''?"/YuJian/Contents/images/dfthead.png":data.headimgurl
	    			,data.realname,getAge(data.birthday)
	    			,'/YuJian/UserDetail?id='+data.userid+(otherfunction==OtherFunction.MATCHMAKER?'&type=matchmaker':'')
	    			,imgHtml
	    			,data.signcontent==''?"该用户太懒了，什么都没留下~":data.signcontent);
	    	
	    	return temp;
		}
		
		//获取广告项html
		function getAdvertItemHtml(data){
			var temp='<li class="advert"><div class="advert-head">'
					+'<img class="advert-logo" src="{0}"/>'
					+'<label class="advert-title">{1}</label>'
					+'<label class="advert-tip">广告</label></div>'
					+'<div class="advert-content">{2}</div>'
					+'<a href="{3}"><img class="advert-img" src="{4}"/></a></li>';
			temp = temp.format(data.headimgurl,data.realname
					,data.signcontent,data.phone,data.videourl);
			return temp;	
		}
		
		//加载轮播图
		function loadBanner(){
			var address=$('.selected-addr').html().replace('全国','').trim();
			$.post('/YuJian/Banners/GetList?bannertype=0&&address='+address
					 ,function(data){
				 if(data==null||data.length<1){
					 return;
				 }
				 var html='';
				 for(var i=0;i<data.length;i++){
					 var temp='<div class="swiper-slide"><a href="'+data[i].bannerlink+'"><img src="'+data[i].bannerimgurl+'"/></a></div>';
					 html+=temp;
				 }
				 $('.swiper-wrapper').html(html);
				 
				 $('.swiper-container').show();
				 var swiper = new Swiper ('.swiper-container', {
				        // 轮播图的方向，也可以是vertical方向
				        direction:'horizontal',        
				        //循环播放
				        loop: true,
				        // 自动播放时间
				        autoplay:5000,
				        // 播放的速度
				        speed:1000,
				        // 如果需要分页器，即下面的小圆点
				        pagination: '.swiper-pagination', 
				        //前进后退按钮
				        //nextButton: '.swiper-button-next',
				        //prevButton: '.swiper-button-prev',
				　　　　// 滑动之后， 定时器不会被清除
				　　　　autoplayDisableOnInteraction : false,
				 });
			 },'json');
			 
		}
		
		//加载地址
		var addrs=[];
		var addrval='';
		var level=0;
		function loadSelectAddr(id){
			var html='';
			level++;
			if(id!=null&&id<0){
				addrs=[];
				addrval='';
				level=1;
				html+='<div onclick="loadSelectAddr()">全国</div>';
				for(var i=0;i<LAreaData.length;i++){
					html+='<div onclick="loadSelectAddr('+LAreaData[i].id+')">'
						+LAreaData[i].name+'</div>';
					addrs.push(LAreaData[i]);
				}
			}
			else{
				var item=null;
				for(var i=0;i<addrs.length;i++){
					if(id==addrs[i].id){
						item=addrs[i];
						break;
					}
				}
				if(item==null){
					addrval+='全国';
				}else{
					addrval+=item.name+" ";
				}
				
				if(item==null||item.child==null||level>2){
					islocation=1;
					$("#img-loaction").removeClass('rotate');
					$('.selected-addr').html(addrval.trim());
					$('.now-addr').html(addrval.trim());
					
					addrChangeLoad();
					loadSelectAddr(-1);//重载
					
					showAddrBoard();	//关闭窗口
					return;
				}
				
				addrs=[];
				html+='<div onclick="loadSelectAddr(-1)" style="color:#fff;background-color:red">清除</div>'
				for(var i=0;i<item.child.length;i++){
					html+='<div onclick="loadSelectAddr('+item.child[i].id+')">'
					+item.child[i].name+'</div>';
					addrs.push(item.child[i]);
				}
			}
	    	$('.addrs-list').html(html);
		}
		
		//获取定位
	    function getMyLoaction(){
	    	islocation=0;
	    	$("#img-loaction").addClass('rotate'); 
	    	var geolocation = new BMap.Geolocation();
	    	geolocation.getCurrentPosition(function(r){
	    		$("#img-loaction").removeClass('rotate');
	    		if(islocation!=0)
	    			return;
	    		if(this.getStatus() == BMAP_STATUS_SUCCESS){
	    			$('.selected-addr').html(r.address.province+" "+r.address.city);
	    			$('.now-addr').html(r.address.province+" "+r.address.city);
	    			showAddrBoard();	//关闭
	    			
	    			addrChangeLoad();//加载用户
	    		}
	    		else {
	    			$('.selected-addr').html('全国')
	    			alert('failed'+this.getStatus());
	    		}        
	    	},{enableHighAccuracy: true})
	    }
		
		function loadPostData(){
			postData.sex= getQueryString('sex');
			postData.height= getQueryString('height');
			postData.btime= getQueryString('btime');
			postData.etime= getQueryString('etime');
			postData.record= getQueryString('record');
			postData.bloodtype= getQueryString('bloodtype');
			postData.wage= getQueryString('wage');
			postData.condition=getQueryString('condition');
		}
	</script>
</body>
</html>