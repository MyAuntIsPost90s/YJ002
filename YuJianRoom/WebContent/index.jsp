<%@page import="yujianroom.common.UserType"%>
<%@page import="yujian.models.Users"%>
<%@page import="yujianroom.common.Skin"%>
<%@ page language="java" contentType="text/html; charset=Utf-8"
    pageEncoding="Utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=Utf-8">
	<title>主页-芋圆后台管理</title>
	<script src="/YuJianRoom/Contents/js/jquery.min.js" type="text/javascript" ></script>

    <link href="/YuJianRoom/Contents/lib/easyui-theme/themes/insdep/easyui.css" rel="stylesheet" type="text/css">
    <link href="/YuJianRoom/Contents/lib/easyui-theme/themes/insdep/insdep_theme_default.css" rel="stylesheet" type="text/css">
    <link href="/YuJianRoom/Contents/lib/easyui-theme/themes/insdep/icon.css" rel="stylesheet" type="text/css">
    <link href="/YuJianRoom/Contents/lib/easyui-theme/plugin/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="/YuJianRoom/Contents/lib/easyui-theme/themes/insdep/easyui_animation.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/YuJianRoom/Contents/lib/easyui-theme/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/YuJianRoom/Contents/lib/easyui-theme/themes/insdep/jquery.insdep-extend.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="/YuJianRoom/Contents/css/chart.css" />
	<script src="/YuJianRoom/Contents/js/jquery.flot.js" type="text/javascript"></script>
	<script src="/YuJianRoom/Contents/js/md5.js" type="text/javascript" ></script>
	<script src="/YuJianRoom/Contents/js/LAreaData.js" type="text/javascript"></script>
	<script src="/YuJianRoom/Contents/js/common.js?version=0.0.1" type="text/javascript"></script>
	<script src="/YuJianRoom/Contents/js/ajaxFileUpload.js?a=1.5"></script>
	<style>
    .theme-header-layout{
        height: 46px !important;
        background: #1d1d1d;
    }
    .theme-left-layout{
        background: #232323;
    }
    .theme-foot-layout{
        height:30px;
        background: #1d1d1d;
        text-align:center;
        line-height:30px;
        color:#d1cfcf;
    }
    .theme-header-layout .logo{
    	float:left;
        height:100%;
        min-width:100px;
        font-size:20px;
        text-align:left;
        line-height:46px;
        padding:0 10px;
        color:#e8e8e8;
    }
    
	.upload-img-board img{
		padding:3px;
		width:80px;
		height: 80px;
		border:2px solid #fff;
	}
	.upload-img-board .active{
		border:2px solid #4A6A99;
	}
	.theme-header-layout .right {
		width:160px;
		height:46px;
		line-height:46px;
		float:right;
	}
	.right a{
		color:#e8e8e8;
		padding: 0 8px
	}
	.right a{
		border-right: 1px solid #e8e8e8;
	}
	.right a:last-child {
		border-right:none;
	}
	.badgecount{
		float: right;
		margin-right: 25px;
	}
</style>
</head>
<%
	Users user = (Users)request.getSession().getAttribute(Skin.USER);
%>
<body>
    <div id="master-layout">
        <div data-options="region:'north',border:false,bodyCls:'theme-header-layout'">
            <div class="logo">芋圆</div>
             <div class="right">
             	<a id='load-index'>首页</a>
             	<a id='update-pwd'>修改密码</a>
             	<a id="login-out">退出</a>
             </div>
        </div>
        <div data-options="region:'west',border:false,bodyCls:'theme-left-layout'" style="width:200px;">
            <div class="easyui-layout" data-options="border:false,fit:true">
                <div data-options="region:'north',border:false" style="height:100px;">
                    <div class="theme-left-user-panel">
                        <dl>
                            <dt>
                                <img id="menu-userheadimg" src="<%=user.getHeadimgurl()%>" width="43" height="43" onclick="$('#menu-img-file').click()" />
                                <input type="file" name="file" id="menu-img-file" style="display: none;" onchange="MenuUserImgUpload()" />
                            </dt>
                            <dd>
                                <b class="badge-prompt"><%=user.getRealname() %></b>
                                <span><%=user.getPhone() %></span>
                                <p>权限等级：<i class="text-success"><%=user.getUsertype()==4?"超级管理员":"管理员" %></i></p>
                            </dd>

                        </dl>
                    </div>
                </div>   
                
                <div data-options="region:'center',border:false">
                    <div class="easyui-accordion leftmenu" data-options="border:false,fit:true">
                        <div title="<span id='user-item'>用户设置</span>">
                            <ul class="easyui-datalist" data-options="border:false,fit:true">
                                <li><span data-href="/YuJianRoom/Pages/Users/List.html">用户管理</span></li>
                                <li><span data-href="/YuJianRoom/Pages/Users/MatchmakerList.html">红娘管理</span></li>
                                <li><span id="item-red" data-href="/YuJianRoom/Pages/UserIntroduces/List.html">红人管理</span></li>
                                <li><span data-href="/YuJianRoom/Pages/UserLovers/List.html">牵手成功管理</span></li>
                                 <li><span data-href="/YuJianRoom/Pages/SeekIntroductLogs/List.html">撮合安排记录</span></li>
                            </ul>
                        </div>
                        
                        <div title="广告设置">
                            <ul class="easyui-datalist" data-options="border:false,fit:true">
                                <%if(user.getUsertype()==UserType.ROOT){%><li><span data-href="/YuJianRoom/Pages/Adverts/List.html" >插入广告</span></li><%} %>
                                <li><span data-href="/YuJianRoom/Pages/Banners/IndexList.html">首页轮播</span></li>
                                <%if(user.getUsertype()==UserType.ROOT){%><li><span data-href="/YuJianRoom/Pages/Banners/ExpressionShopList.html">表情轮播</span></li><%} %>
                            </ul>
                        </div>
                        <%if(user.getUsertype()==UserType.ROOT){%>
                        <div title="商城管理">
                         	<ul class="easyui-datalist" data-options="border:false,fit:true">
                                <li><span data-href="/YuJianRoom/Pages/Gifts/List.html" >礼物管理</span></li>
                                <li><span data-href="/YuJianRoom/Pages/ExpressionBags/List.html">表情管理</span></li>
                            </ul>
                        </div>
                        <%} %>
                        <%if(user.getUsertype()==UserType.ROOT){%>
                        <div title="财务管理">
                         	<ul class="easyui-datalist" data-options="border:false,fit:true">
                                <li><span data-href="/YuJianRoom/Pages/Orders/ExpressionInCome.html" >表情收入</span></li>
                                <li><span data-href="/YuJianRoom/Pages/Orders/GiftInCome.html">礼物收入</span></li>
                                <li><span data-href="/YuJianRoom/Pages/Orders/VipInCome.html" >VIP收入</span></li>
                                <li><span data-href="/YuJianRoom/Pages/Orders/LoverInCome.html">基金收入</span></li>
                                <li><span data-href="/YuJianRoom/Pages/Orders/RechargeInCome.html">充值收入</span></li>
                            </ul>
                        </div>
                        <%} %>
                        <div title="<span id='feedback-item'>反馈管理</span>">
                         	<ul class="easyui-datalist" data-options="border:false,fit:true">
                                <li><span id="item-confession" data-href="/YuJianRoom/Pages/FeedBacks/CourtList.html" >告白求助</span></li>
                                <li><span id="item-propose" data-href="/YuJianRoom/Pages/FeedBacks/ProposeList.html">求婚求助</span></li>
                                <li><span id="item-other" data-href="/YuJianRoom/Pages/FeedBacks/OtherList.html" >其他求助</span></li>
                                <li><span id="item-feedback" data-href="/YuJianRoom/Pages/FeedBacks/FeedBackList.html">用户反馈</span></li>
                                <li><span id="item-askmatchmaker" data-href="/YuJianRoom/Pages/FeedBacks/AskMatchmakerList.html">申请红娘</span></li>
                            </ul>
                        </div>
                        
                        <div title="系统管理">
                        	<ul class="easyui-datalist" data-options="border:false,fit:true">
                        		<li><span data-href="/YuJianRoom/Pages/Mine.html" >个人中心</span></li>
                                <%if(user.getUsertype()==UserType.ROOT){%><li><span data-href="/YuJianRoom/Pages/SysSet.html" >系统设置</span></li><%} %>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="control" data-options="region:'center',border:false,href:'/YuJianRoom/Pages/Wellcome.html'">
        
        </div>
        <div data-options="region:'south',border:false" class="theme-foot-layout">
			@福州智汇恒星
        </div>
    </div>
    <!--修改密码窗口-->
    <div id="UpdatePwdDialog" class="easyui-window" style="width:230px;height:150px"
         data-options="title:'添加/修改',maximizable:false,minimizable:false,collapsible:false,modal:true,closed:true">
        <div style="padding:10px">
            <form id="ff-pwd" action="/YuJianRoom/Users/UpdatePwd" method="post">
            <div>
                <table data-options='cellpadding=3'>
                    <tr>
                        <td>新密码：</td>
                        <td>
                            <input name="pwd" class="easyui-textbox" type="password" data-options="required:true,validType:'length[1,50]'" />
                            <input type="text" name="01" style="display:none" />
                        </td>
                    </tr>
                </table>
            </div>
            </form>
            <div id="bb" style="text-align: right;padding:5px 10px">
		        <button type="button" onclick="UpdatePwd()" data-options="iconCls:'icon-ok'" class="easyui-linkbutton">
		        	保存
		        </button>
		        <a onclick="$('#UpdatePwdDialog').dialog('close')" data-options="iconCls:'icon-cancel'"  class="easyui-linkbutton">
					关闭
		        </a>
		    </div>
        </div>
    </div>
</body>
<script>
    $(function(){
        /*布局部分*/
        $('#master-layout').layout({
            fit:true/*布局框架全屏*/
        });

        $('.leftmenu .datagrid-cell').click(function () {
            var src = $(this).find('span').attr('data-href');
            $('.easyui-dialog').dialog('destroy');
            $.insdep.control(src);
        })
        
        $.messager.show({
                title:'我的消息',
                msg:'欢迎<%=user.getRealname()%>用户，权限为管理员',
                showType:'show',
            });
        
        loadFeedbackCount();
        loadRedCount();
    })
    
    $('#ff-pwd').form({
        onSubmit: function () {
            return $(this).form('validate');
        },
        success: function (data) {
            $('#UpdatePwdDialog').dialog('close')
            $('#ff-pwd').form('clear');
            
            $.messager.show({
                title:'我的消息',
                msg:data,
                showType:'show',
            })
        }
    });
    
    function UpdatePwd() {
    	if($('input[name=pwd]').val()!='')
    		$('input[name=pwd]').val(hex_md5($('input[name=pwd]').val()).toUpperCase());
        $('#ff-pwd').submit();
    }
    
    $('#load-index').click(function(){
    	$.insdep.control('/YuJianRoom/Pages/Wellcome.html');
    });
    $('#update-pwd').click(function(){
    	$('#UpdatePwdDialog').dialog('open');
    });
    $('#login-out').click(function () {
        $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function (r) {
            if (r) {
                location.href = '/YuJianRoom/LoginOut';
            }
        });
    });
    
    //加载未处理红人数量
    function loadRedCount(){
    	$.post('/YuJianRoom/UserIntroduces/GetCount'
    			,{userintroducestatus:0,address:'<%=user.getUsertype()==UserType.ROOT?"":user.getAddress()%>'}
    			,function(data){
    				if(data>0){
    					data=data>99?99:data;
    					$('#item-red').after('<i class="badgecount badge color-important">'+data+'</i>');
    					$('#user-item').after('<i class="badgecount badge color-important">'+data+'</i>');
    				}
    			},'json');
    }
    //加载未处理数量
    function loadFeedbackCount(){
    	$.post('/YuJianRoom/FeedBacks/GetUnDoCount'
    			,{feedbackstatus:0}
    			,function(data){
    				$('.feedbackcount').remove();
    				if(data.sum>0){
    					data.sum=data.sum>99?99:data.sum;
    					$('#feedback-item').after('<i class="badgecount feedbackcount badge color-important">'+data.sum+'</i>');
    				}
    				if(data.confession>0){
    					data.confession=data.confession>99?99:data.confession;
    					$('#item-confession').after('<i class="badgecount feedbackcount badge color-important">'+data.confession+'</i>');
    				}
    				if(data.propose>0){
    					data.propose=data.propose>99?99:data.propose;
    					$('#item-propose').after('<i class="badgecount feedbackcount badge color-important">'+data.propose+'</i>');
    				}
    				if(data.other>0){
    					data.other=data.other>99?99:data.other;
    					$('#item-other').after('<i class="badgecount feedbackcount badge color-important">'+data.other+'</i>');
    				}
    				if(data.feedback>0){
    					data.feedback=data.feedback>99?99:data.feedback;
    					$('#item-feedback').after('<i class="badgecount feedbackcount badge color-important">'+data.feedback+'</i>');
    				}
    				if(data.askmatchmaker>0){
    					data.askmatchmaker=data.askmatchmaker>99?99:data.askmatchmaker;
    					$('#item-askmatchmaker').after('<i class="badgecount feedbackcount badge color-important">'+data.askmatchmaker+'</i>');
    				}
    			},'json');
    }
    
    //上传头像
    function MenuUserImgUpload(){
    	$.ajaxFileUpload({
			url: '/YuJianRoom/Common/UploadUserHeadImgs?userid=<%=user.getUserid() %>&pwd=<%=user.getPassword() %>',
            type: 'post',
            secureuri: false,
            fileElementId: "menu-img-file",
            dataType: 'json',
            success: function (data){  //服务器成功响应处理函数  
				if(data!=null){
					$('#menu-userheadimg').attr('src',data.headimgurl);
					$.get('/YuJianRoom/Users/GetNowUser'); //刷新session
				}
            },
            error: function (data, status, e){//服务器响应失败处理函数
            	if(data.status==1){
					$('.upload-img-board').append('<img onclick="ImgClick(this)" data-id="'+data.jsonData.userimgid+'" src="'+data.jsonData.userimgurl+'" />');
				}
				$.messager.alert("提示",data.msg);
            }
		});
    }
</script>
</html>