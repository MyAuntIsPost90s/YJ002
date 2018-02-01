//转换url
function getMiniUrl(url){
	var p=url.lastIndexOf('/')+1;
	var filename='mini_'+url.substring(p,url.length);
	var path=url.substring(0,p);
	return path+filename;
}

//获取参数
function getQueryString(name){
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  decodeURI(r[2]); return null;
}

//计算星座
function getAstro(str){
	str=str.replace('-','/');
	var date = new Date(str);
	var m=date.getMonth()+1;
	var d=date.getDate();
    return "魔羯水瓶双鱼牡羊金牛双子巨蟹狮子处女天秤天蝎射手魔羯".substr(m*2-(d<"102223444433".charAt(m-1)- -19)*2,2)
    +"座";
}

//获取十二生肖
function getBornIn(str){
	var date = new Date(str);
	var year=date.getFullYear();
	var num=year%12-4;//设定2008为初始年份
	if(num<0)
		num=12+num;
	
	var arr=new Array("鼠","牛","虎","兔","龙","蛇","马","羊","猴","鸡","狗","猪"); 
	return arr[num];
}

function getAge(str){
	var d1=new Date(str);
	var y1 = d1.getFullYear();
	var y2=new Date().getFullYear();
	
	return y2-y1;
}

function getObjectURL(file) {
     var url = null;
     if (window.createObjectURL != undefined) { // basic
         url = window.createObjectURL(file);
     } else if (window.URL != undefined) { // mozilla(firefox)
         url = window.URL.createObjectURL(file);
     } else if (window.webkitURL != undefined) { // webkit or chrome
         url = window.webkitURL.createObjectURL(file);
     }
     return url;
 }
 
 function idFormat(id){
	if(id==null||id=='')
		return '0000';
	id=id+"";
	if(id.length==1)
		return '000'+id;
	if(id.length==2)
		return '00'+id;
	if(id.length==3)
		return '0'+id;
	return id;
}

//是否是手机号
function isPhone(str) {  
	var myreg=/^[1][3,4,5,6,7,8][0-9]{9}$/;  
	if (!myreg.test(str)) {  
		return false;  
	} else {  
		return true;  
	}
}