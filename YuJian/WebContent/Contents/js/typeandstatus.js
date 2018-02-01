var UserType={
	RED:-1,
	NOMAL:0,
	VIP:1,
	ADMIN:2,
	
	getUserTypeName:function(val){
		if(val==-1){
			return "红人";
		}
		if(val==0){
			return "普通";
		}
		if(val==1){
			return "VIP";
		}
		if(val==2){
			return "管理员";
		}
		return "未知";
	}
}

var OtherFunction={
	NONE:0,
	MATCHMAKER :1
}

var SeekIntroductType={
	DOUBLE:0,
	SINGLE:1
}