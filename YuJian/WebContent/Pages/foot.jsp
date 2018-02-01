<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="Utf-8"%>
 <div class="tabbar">
	<a href="/YuJian/CommonUsers" class="tabbar-item">
	    <i class="icon-tab icon-home"></i>
	    <span>首页</span>
	</a>
	<a href="/YuJian/ExpressionShop" class="tabbar-item">
	   	<i class="icon-tab icon-shop"></i>
	    <span>商城</span>
	</a>
	<a href="/YuJian/SortUsers" class="tabbar-item">
	    <i class="icon-tab icon-rank"></i>
	    <span>排行榜</span>
	</a>
	<a href="/YuJian/Mine" class="tabbar-item">
	    <i class="icon-tab icon-user"></i>
	    <span>我的</span>
	</a>
</div>
<script>
	$(".tabbar a").each(function(){
		if($(this).attr('href')==window.location.pathname){
			$(this).addClass("active");
			var obj=$(this).find('.icon-tab');
			var classname = obj.attr('class').replace('icon-tab ','');
			obj.removeClass(classname);
			obj.addClass(classname+'-active');
		}
	});
</script>