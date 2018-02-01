<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的红人</title>
<jsp:include page="../head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/userintroduces.css">
</head>
<body>
	<div class="header-tip-bar">
		<i onclick="window.location.href='/YuJian/Mine'" class="icon icon-arrow"></i>
		<div>红人</div>
	</div>
	<div class="wrapper webpersonPage" >
		<div class="person-list" style="display: none">			
			<!-- 红人列表 -->
		</div>

		<div class="add-btn"></div>
	</div>
	
	<script>
		var page=-1;
		$(function(){
			page=1;
			loadData();
		});
		
		$('.add-btn').click(function(){
			window.location.href="/YuJian/MyIntroducesAdd";
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
		
		//加载数据
		function loadData(){
			$.post('/YuJian/UserIntroduces/GetMyIntroduces'
					,{page:page,pageSize:8}
					,function(data){
						if(data==null||data.length<1)
							return;
						
						stop=false;
						var html='';
						for(var i=0;i<data.length;i++){
							var temp='<div class="item"><div class="avatar">'
									+'<a href="/YuJian/MyIntroducesEdit?id={7}"><img src="{0}"></a><span><img src="{0}">'
									+'<i>1/{1}</i></span></div>'
									+'<div class="intro">'
									+'<p>{2}</p>'
									+'<p>年龄: <span>{3}</span></p>'
									+'<p>居住地: <span>{4}</span></p>'
									+'<p>学历: <span>{5}</span></p>'
									+'<p>审核状态: <span>{6}</span></p></div></div>';
							temp=temp.format(data[i].headimgurl
									,data[i].userimgs==null?1:data[i].userimgs.length+1
									,data[i].realname,getAge(data[i].birthday),data[i].address,data[i].record
									,data[i].userintroducestatus==0?"未通过":"已通过"
									,data[i].uiuserid);
							
							html+=temp;
						}
						if(data.length>0)
							$('.person-list').show();
						$('.person-list').append(html);	
					},'json');
		}
	</script>
</body>
</html>