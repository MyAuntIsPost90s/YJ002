var UserInfoModel={
	show:function(userid){
		$('#userinfo-form').form({
    		onLoadSuccess:function(data){
            	$('#userinfo-headimgurl').attr('src',data.headimgurl);
            	$('#userinfo-dialog').dialog('open');
            }
    	})
    	$('#userinfo-form').form('clear');
    	$('#userinfo-form').form('load','/YuJianRoom/Users/GetSingle?id='+userid);
	},
	close:function(){
		$('#userinfo-dialog').dialog('close');
	},
	dialog:function(params){
		$('#userinfo-dialog').dialog(params);
	}
}