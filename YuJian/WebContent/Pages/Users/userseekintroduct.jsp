<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/search.css?version=0.0.5">
<title>我的求撮合</title>
</head>
<body>
	<div class="header-tip-bar">
		<i onclick="window.location.href='/YuJian/Mine'" class="icon icon-arrow"></i>
		<div>我的求撮合</div>
	</div>
	<!-- 和search公用一套界面 -->
	<div class="wrapper searchbody" style="padding-top: 49px;padding-bottom: 0">
		<div id="eplist-tip">暂无数据哦~</div>
	</div>

	<script type="text/javascript">
		var userid=${id};
		var page=-1;
		var ListAnimation={
			show:function(){
				var html='<div class="listspinner"><div class="rect1"></div><div class="rect2"></div><div class="rect3"></div>'
				  	+'<div class="rect4"></div><div class="rect5"></div>'
				  	+'<p>正在加载</p></div>'
				$('.searchbody').html(html);
			},
			close:function(){
				$('.listspinner').remove();
			}
		}
		$(function(){
			page=1;
			//ListAnimation.show();
			loadData();
		})
		
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
		
		function loadData(){
			if(page<0)
				return;
			$.post("/YuJian/UserSeekIntroducts/GetList"
					,{
						userid:userid,
						page:page,
						pagesize:10
					},function(data){
						//ListAnimation.close();
						if(data==null||data.length<1){
							return;
						}
						var html='';
						for(var i=0;i<data.length;i++){
							html+=beUserItem(data[i]);
						}
						$('#eplist-tip').hide();
						$('.searchbody').append(html);
					},'json');
		}
		
		function beUserItem(user){
			var html='<div class="search-item">'
					+'<div class="btn-del" onclick="del(this,\'{6}\')">×</div>'
					+'<a href="/YuJian/UserDetail?id={5}">'
					+'<img src="{0}"/>'
					+'<div class="item-info">'
					+'<label>{1}</label>'
					+'<label>性别:{2}</label>'
					+'<label>年龄:{3}</label>'
					+'<label>{4}</label></div></a></div>'
			return html.format(user.toheadimgurl,user.torealname
					,user.tosex==0?"女":"男"
					,getAge(user.tobirthday)
					,user.tosigncontent==''?'该用户太懒了，什么都没留下':user.tosigncontent
					,user.touserid
					,user.userseekintroductid);
		}
		
		function del(obj,userseekintroductid){
			if(userseekintroductid==null)
				return;
			$(obj).parent().remove();
			$.post('/YuJian/UserSeekIntroducts/Delete',{userseekintroductid:userseekintroductid});
			if($('.searchbody .search-item').length<1)
				$('#eplist-tip').show();
		}
		
		function getAge(str){
			var d1=new Date(str);
			var y1 = d1.getFullYear();
			var y2=new Date().getFullYear();
			
			return y2-y1;
		}
	</script>
</body>
</html>