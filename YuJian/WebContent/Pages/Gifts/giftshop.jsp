<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/gift.css?version=0.0.2">
<title>礼物商城</title>
</head>
<body>
	<div class="header-tip-bar">
		<i class="icon icon-arrow" onclick="javascript:window.history.go(-1);"></i>
		<div>礼物商店</div>
	</div>
	<div class="wrapper sendgiftPage">
		<div class="sendgiftList">
			<!-- 礼物列表 -->
			
		</div>
		<div class="shopcar">
			<div class="header">
				<div class="title">已选商品</div>
				<div class="btn-clear" onclick="clearShopCar()">清空</div>
			</div>
			<div class="body">
				<div id="carnone-tip" style="text-align: center;height:8rem;line-height: 8rem;">暂无</div>
			</div>
		</div>
		<div class="sendgiftFooter">
			<div class="shopcar-icon">
				<i class="icon icon-shopcar"></i>
				<i class="point">0</i>
			</div>
			<div class="cost-item">
				<span>合计:<i id="allcost">0</i></span>
				<label onclick="send()">赠送</label>
			</div>
		</div>
	</div>
	<script>
		var isvip=false;
		var discount=1;
		var userid='${id}';
		var page=-1;
		var pointcount=0;
		
		$(function(){
			//加载vip后回调加载礼物
			
			getIsVip(function(){
				page=1;
				getGifts();
			})
		});
		
		var flag=1;
		$('.shopcar-icon').click(function(){
			if(flag==1){
				$('.shopcar').animate({'bottom':'2.5rem'},300);
				flag=0;
			}
			else{
				$('.shopcar').animate({'bottom':'-12.5rem'},300);
				flag=1;
			}
		});
		
		//赠送
		function send(){
			//计算显示价格
			var allcost=0;
			var giftids='';
			var counts='';
			$('.opt input').each(function(){
				if(isvip)
					allcost+=parseFloat($(this).attr('data-cost'))*parseInt($(this).val())*discount;
				else
					allcost+=parseFloat($(this).attr('data-cost'))*parseInt($(this).val());
				
				giftids+=$(this).attr('id')+',';
				counts+=$(this).val()+',';
			});
			
			if(counts!='')
				counts=counts.substring(0,counts.length-1);
			if(giftids!='')
				giftids=giftids.substring(0,giftids.length-1);
			
			if(allcost<=0){
				tipAlert('请挑选心爱的礼物，赠送心爱的人哦~~~');
				return;
			}
			
			$.post('/YuJian/UserGifts/SendGift'
					,{giftids:giftids,counts:counts,touserid:userid}
					,function(data){
						if(data.status==1){
							window.location.href='/YuJian/UserGifts?id='+userid;
						}
						tipAlert(data.msg);
					},'json');
		}
		
		//数量+1
		function addCount(id){
			var rid='#'+id;
		 	pointshow(1);
		 	addInShopCar($('dl[data-id='+id+']'),id,1);
		 	$(rid).change();	//触发监听
		}
		
		//数量-1
		function minusCount(id){
			var rid='#'+id;
			var cost=parseFloat($(rid).attr('data-cost'));
			if(pointcount-1>-1){	
		 		pointshow(-1);
		 		addInShopCar($('dl[data-id='+id+']'),id,-1);
			}
			
			$(rid).change();	//触发监听
		}
		
		//添加物品进入
		function addInShopCar($obj,id,count){
			var $item=$('.shopcar .body').children('div[data-id='+id+']');
			var val=1;
			if($item.length<1){
				var temp='<div data-id="{0}" class="shopcar-item"><div class="item-head">'
						+'<img src="{1}"/>{2}</div>'
						+'<div class="item-opt">'
						+'<span class="opt-cost">￥{3}</span> '
						+'<a onclick="minusCount(\'{0}\')">-</a> '
						+'<input readonly="readonly" class="C{0}" type="text" value="{4}"/> '
						+'<a onclick="addCount(\'{0}\')">+</a> '
						+'</div></div>'
				temp=temp.format(id,$obj.find('dt img').attr('src')
						,$obj.find('.title').html()
						,$('#'+id).attr('data-cost')
						,count);
				$('.shopcar .body').append(temp);
			}else{
				val=parseInt($('#'+id).val());
				val+=count;
				if(val<1){
					$item.remove();
				}
			}
			if($('.shopcar-item').length<1){
				$('#carnone-tip').show();
			}else{
				$('#carnone-tip').hide();
			}
			
			var cost=parseFloat($('#'+id).attr('data-cost'))*val;
			if(isvip){
				cost=cost*discount;
			}
			$item.find('.opt-cost').text('￥'+cost.toFixed(1));
			$('.C'+id).val(val);
		}
		
		//清空
		function clearShopCar(){
			$('.opt input').each(function(){ $(this).val(0); });
			$('#allcost').html(0);
			$('.shopcar-item').remove();
			$('#carnone-tip').show();
			
			pointcount=0;
			$('.point').html(0);
		}
		
		//小圆点提示
		function pointshow(val){
			pointcount+=val;
			if(pointcount>0){
				$('.point').html(pointcount);
			}
			else{
				pointcount=0;
				$('.point').html(0);
			}
		}
		
		//数量监听
		function numListen(obj){
			var val=$(obj).val();
			//非数字
			if(val==null||val==''||isNaN(val)){
				$(obj).val(0);
				return;
			}
			
			
			//计算显示价格
			var allcost=0;
			$('.opt input').each(function(){
				if(isvip)
					allcost+=parseFloat($(this).attr('data-cost'))*parseInt($(this).val())*discount;
				else
					allcost+=parseFloat($(this).attr('data-cost'))*parseInt($(this).val());
			});
			
			$('#allcost').html(allcost.toFixed(1));
		}
		
		//是否是vip
		function getIsVip(callback){
			$.post('/YuJian/Users/GetMyInformation'
					,function(data){
				isvip=data.usertype==1;
				
				getDisCount(callback);
			},'json');
		}
		
		//加载折扣
		function getDisCount(callback){
			$.get('/YuJian/SysConfigs/GetSysConfig'
					,function(data){
				discount=data.vipdiscount;
				callback();
			},'json');
		}
		
		//加载礼物
		function getGifts(){
			$.post('/YuJian/Gifts/GetGifts'
					,{page:page,pageSize:12}
					,function(data){
				if(data==null||data.length<1){
					return;
				}
				stop=false;
				
				var html='';
				for(var i=0;i<data.length;i++){
					var temp='<dl class="gift-item" data-id="{4}"><dt>'
							+'<img src="{0}" /><span class="vip"><img src="/YuJian/Contents/images/vip.png">'
							+'{1}折</span></dt>'
							+'<dd class="content">{5}</dd><dd class="cost-info">'
							+'<span class="title">{2}</span>'
							+'<span class="cost"><i class="icon-sort icon-rich"></i>'
							+'{3}币</span></dd><dd class="opt">'
							+'<a onclick="minusCount(\'{4}\')">-</a>'
							+'<input readonly="readonly" onchange="numListen(this)" id="{4}" class="C{4}" data-cost="{3}" type="text" value="0" />'
							+'<a onclick="addCount(\'{4}\')">+</a></dd></dl>'
					temp=temp.format(data[i].gifturl,discount*10
							,data[i].gifttitle,data[i].giftcost
							,data[i].giftid,data[i].giftcontent);
					
					html+=temp;
				}
				
				$('.sendgiftList').append(html);
			},'json')
		}
		
		//滚动加载事件
		var stop=false;
		var end=false;
		$(window).scroll(function() {
			if(stop==true||end==true||page<0)
				return;
			var totalheight = parseFloat($(window).scrollTop()+$(window).height()/2); 
		    if($(document).height() <= totalheight){
		        if(stop==false){ 
		        	stop=true;
		        	page++;
		        	getGifts();
		        }
		    }
		});
	</script>
</body>
</html>