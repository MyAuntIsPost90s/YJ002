<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>留言</title>
<jsp:include page="../head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/YuJian/Contents/css/leaveword.css?version=0.0.42">
</head>
<body>
	<div class="header-tip-bar">
		<i class="icon icon-arrow" onclick="javascript:window.history.go(-1);"></i>
		<div id="title">留言</div>
	</div>
	<div class="wrapper leavewordPage">
		<div class="userleavewords-board">
			<!-- 留言 -->
		</div>
	</div>
	
	<div class="tabbar">
		<div style="width: 100%;">
			<button class="words-btn">给Ta留言</button>
		</div>
		<div class="expression-board">
			<div class="expressions-content">
				<!-- 表情 -->
			</div>
			<div class="expressionbags-nav">
				<div class="ex-add"><a href="/YuJian/MinExpressionShop">+</a></div>
				<div class="expressionbags-borad">
					<div class="expressionbags">
						<!-- 表情包 -->
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script>
		var userid='${id}';
		var page=-1;
		var toleaveworduserid=-1;
		
		$(function(){
			page=1;
			showSpin();
			loadLeaveWords();	//加载留言
			
			setTitle();	//设置标题
			loadUserExpressionBags();	//加载用户表情包
			$('.leavewordPage').css('min-height',(document.body.clientHeight-49)+"px");	//设置高度
		});
		
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
		        	loadLeaveWords();
		        }
		    }
		});
		
		$('.words-btn').click(function(){
			$(".expression-board").animate({height:'12rem'},300);
		});
		$('.wrapper').click(function(){
			$(".expression-board").animate({height:'0rem'},300);
			
			toleaveworduserid=-1;
			$('.words-btn').html('给TA留言');
		})
		function leavewordtoClick(obj,event){
			toleaveworduserid=parseInt($(obj).attr('data-id'));
			$('.words-btn').html('回复'+$(obj).attr('data-name')+'的留言');
			$('.words-btn').click();
		}
		
		//表情包点击事件
		function expressionbagClick(id,obj){
			$('.expressions').hide();	
			$('div[data-ebid='+id+']').show();
			
			$('.expressionbags img').removeClass('active');
			$(obj).addClass('active');
		}
		
		//表情点击事件
		function expressionClick(obj){
			$('.expressions img').removeClass('active');
			$(obj).addClass('active');
			
			showSpin();
			$.get('/YuJian/LeaveWords/AddLeaveWord?touserid='+userid
					+'&expressionurl='+$(obj).attr('src')
					+'&toleaveworduserid='+toleaveworduserid
					,function(){						
						page=1;
						loadLeaveWords(function(){
							$('.userleavewords-board').html('');
						});	//加载留言
			});
		}
		
		//获取留言项html
		function getLeaveWordsItem(item){
			var temp='<div class="leavewords-item "><div class="header">'
				+'<a href="/YuJian/UserDetail?id={0}"><img src="{1}" /></a>'
				+'<div class="leaveword-user">'
				+'<label class="realname">{2}</label>'
				+'<label class="leavedate">{3}</label>'
				+'</div><a href="/YuJian/LeaveWordsDetail?leavewordid={5}&userid='+userid+'" class="leavewordto" data-name="{2}" data-id="{0}">回复</a></div>'
				+'<img class="leaveword-body" src="{4}" /></div>';
				
			temp=temp.format(item.fromuserid
					,item.headimgurl==''?"/YuJian/Contents/images/dfthead.png":item.headimgurl
					,item.realname+(item.toleaveworduserid==-1?'':' 回复 '+item.torealname)
					,isToday(item.leavewordtime)
					,item.leavewordurl
					,item.leavewordid);
			
			if(item.leavewordsanduser==null){
				return temp;
			}
			
			var son='<div class="leavewords-item right-item"><div class="header">'
				+'<a href="/YuJian/UserDetail?id={0}"><img src="{1}" /></a>'
				+'<div class="leaveword-user">'
				+'<label class="realname">{2}</label>'
				+'<label class="leavedate">{3}</label>'
				+'</div></div>'
				+'<img class="leaveword-body" src="{4}" />'
				+'<div class="leavewords-foot">'
				+'<a href="/YuJian/LeaveWordsDetail?leavewordid={5}&userid='+userid+'">回复详情>></a></div></div>';
				
			son=son.format(item.leavewordsanduser.fromuserid
					,item.leavewordsanduser.headimgurl==''?"/YuJian/Contents/images/dfthead.png":item.leavewordsanduser.headimgurl
							,item.leavewordsanduser.realname+(item.leavewordsanduser.toleaveworduserid==-1?'':' 回复 '+item.leavewordsanduser.torealname)
							,isToday(item.leavewordsanduser.leavewordtime)
							,item.leavewordsanduser.leavewordurl
							,item.leavewordid);
			return temp+son;
		}
		
		//加载用户留言
		function loadLeaveWords(callback){
			$.post('/YuJian/LeaveWords/GetUserLeaveWords'
					,{userid :userid,page:page,pageSize:10}
					,function(data){
						closeSpin();
						if(callback!=null)
							callback();
						if(data==null||data.length<1)
							return;
						stop=false;
						
						var html='';
						for(var i=0;i<data.length;i++){
							html+=getLeaveWordsItem(data[i]);
						}
						$('.userleavewords-board').append(html);
					},'json');
		}
		
		//加载用户表情包
		function loadUserExpressionBags(){
			$.post("/YuJian/UserExpressionBags/GetMyExpressionBags"
					,{}
					,function(data){
						if(data==null||data.length<1){
							//当没有表情包时
							$('.expressions-content').html('<div class="ex-tip">没有任何表情包哦~请先去购买^_^</div>')
							return;
						}
						
						var html='';
						var ids='';
						for(var i=0;i<data.length;i++){
							var temp='<img data-id="{1}" onclick="expressionbagClick(\'{1}\',this)" src="{0}"/>';
			            	
			            	temp = temp.format(data[i].expressionbagurl,data[i].expressionbagid);
			            	html+=temp;
			            	
			            	ids+=','+data[i].expressionbagid;
						}
						$('.expressionbags').append(html);
						setExpressionBagWidth();//设置宽度
						
						if(ids!='')
							loadExpressions(ids.substring(1,ids.length));	//加载表情
					},'json')
		}
		
		//加载表情
		function loadExpressions(ids){
			$.post('/YuJian/Expressions/GetExpressionMap'
					,{ids:ids}
					,function(data){
						if(data==null){
							$('.expressions-content').html('');
							return;
						}
						var html='';
						for(var key in data){
							var ebhtml="<div data-ebid='"+key+"' class='expressions'>{0}</div>"
							var ebtemp='';
							for(var i=0;i<data[key].length;i++){
								var temp='<img onclick="expressionClick(this)" src="{0}" alt="">';
								temp = temp.format(data[key][i].expressionurl);
								ebtemp+=temp;
							}
							ebhtml=ebhtml.format(ebtemp);
							html+=ebhtml;
						}
						
						$('.expressions-content').html(html);
						
						//点击第一个
						var strs = ids.split(',');
						if(strs!=null&&strs.length>0)
							$('img[data-id='+strs[0]+']').click();
					},'json');
		}
		
		function setExpressionBagWidth(){
			var width=0;
			$('.expressionbags img').each(function(){
				width+=parseInt($(this).css('width').replace('px',''));
			});
			$('.expressionbags').css('width',(width+10)+"px");
		}
		
		//设置标题
		function setTitle(){
			$.post("/YuJian/Users/GetOtherUserInfo"
					,{userid:userid}
					,function(data){
				if(data==null)
					return;
				$('#title').text(data.realname+'的留言');
			},'json');
		}
		
		//判断是否为今天
		function isToday(str) {
			var date=new Date(Date.parse(str.replace(/-/g,  "/")));
		    if (date.toDateString() === new Date().toDateString()) {
		    	var hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
		    	var minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
		    	var second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
		        return "今天 "+hour+":"+minute+":"+second;
		    } else {
		        return str;
		    }
		}
	</script>
</body>
</html>