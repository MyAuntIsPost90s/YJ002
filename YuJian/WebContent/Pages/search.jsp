<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/search.css?version=0.1.0">
<title>搜索</title>
</head>
<body>
	<div class="header-tip-bar" style="background-color: #E97EAF">
		<a class="icon-ltriangle" onclick="javascript:window.history.go(-1);"></a>
		<div class="search">
			<input id="condition" type="text" />
		</div>
		<button onclick="search()">搜索</button>
	</div>
	<div class="wrapper searchbody" style="padding-top: 49px;padding-bottom: 0">

	</div>

	<script type="text/javascript">
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
			ListAnimation.show();
			loadData();
		})
		function search(){
			$('.searchbody').html('');
			ListAnimation.show();
			page=1;
			loadData();
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
		
		function loadData(){
			var condition=$('#condition').val();
			if(page<0)
				return;
			$.post("/YuJian/Users/SearchUsers"
					,{
						condition:condition,
						page:page,
						pagesize:10
					},function(data){
						ListAnimation.close();
						if(data==null||data.length<1){
							return;
						}
						var html='';
						for(var i=0;i<data.length;i++){
							html+=beUserItem(data[i]);
						}
						$('.searchbody').append(html);
					},'json');
		}
		
		function beUserItem(user){
			var html='<div class="search-item">'
					+'<a href="/YuJian/UserDetail?id={5}">'
					+'<img src="{0}"/>'
					+'<div class="item-info">'
					+'<label>{1}</label>'
					+'<label>性别:{2}</label>'
					+'<label>年龄:{3}</label>'
					+'<label>{4}</label></div></a></div>'
			return html.format(user.headimgurl,user.realname
					,user.sex==0?"女":"男"
					,getAge(user.birthday)
					,user.signcontent==''?'该用户太懒了，什么都没留下':user.signcontent
					,user.userid);
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