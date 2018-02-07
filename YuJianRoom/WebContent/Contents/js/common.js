//用户展示模块
var UserInfoModel = {
	showImg : function(id, videourl) {
		// 加载视频
		if (videourl == '' || videourl == '') {
			$('#userimg-dialog-videourl').hide();
			$('#userimg-dialog-videotip').show();
		} else {
			$('#userimg-dialog-videourl').attr("src", videourl);
			$('#userimg-dialog-videourl').show();
			$('#userimg-dialog-videotip').hide();
		}
		// 加载图片
		$.post("/YuJianRoom/UserImgs/GetList?userid=" + id, function(data) {
			var html = '';
			for (var i = 0; i < data.length; i++) {
				html += '<img src="' + data[i].userimgurl + '" />'
			}
			$('#userimg-form-imgs').html(html);
			$('#userimg-dialog').dialog('open');
		}, 'json')
	},
	show : function(userid) {
		$('#userinfo-form').form({
			onLoadSuccess : function(data) {
				$('#userinfo-headimgurl').attr('src', data.headimgurl);
				$('#userinfo-dialog').dialog('open');

				$('#userinfo-dialog-imgs').click(function() {
					UserInfoModel.showImg(userid, data.videourl);
				});
			}
		})
		$('#userinfo-form').form('clear');
		$('#userinfo-form').form('load',
				'/YuJianRoom/Users/GetSingle?id=' + userid);
	},
	close : function() {
		$('#userinfo-dialog').dialog('close');
	},
	dialog : function(params) {
		$('#userinfo-dialog').dialog(params);
	}
}

//红娘展示模块
var MatchmackerModel = {
	show : function(userid) {
		$('#matchmaker-form').form({
			onLoadSuccess : function(data) {
				$('#matchmaker-headimgurl').attr('src', data.headimgurl);
				$('#matchmaker-dialog').dialog('open');
			}
		})
		$('#matchmaker-form').form('clear');
		$('#matchmaker-form').form('load',
				'/YuJianRoom/Users/GetSingle?id=' + userid);
	},
	close : function() {
		$('#matchmaker-dialog').dialog('close');
	},
	dialog : function(params) {
		$('#matchmaker-dialog').dialog(params);
	}
}

//用户快查模块
var UserListModel={
	show : function() {
		$('#userlist-dialog').dialog('open');
		if($('#userinfo-form').length>0){	//查看详情主键存在，添加双击查看详情
			$('#userlist-table').datagrid({
				onDblClickRow: function (index, row) {
          			$('#userlist-table').datagrid('unselectAll');
          			$('#userlist-table').datagrid('selectRow',index);
          			var rows = $('#userlist-table').datagrid('getSelections');
                    UserInfoModel.show(rows[0].userid);
            	}
			});
		}
		$('#userlist-table').datagrid({ url: '/YuJianRoom/Users/GetList?condition='+$('#userlist-condition').val() });
		$('#userlist-searchbtn').click(function(){
			$('#userlist-table').datagrid({ url: '/YuJianRoom/Users/GetList?condition='+$('#userlist-condition').val() });
		})
	},
	close : function() {
		$('#userlist-dialog').dialog('close');
	}
}