function tipAlert(txt){
	$('.tip-background').remove();
	var html='<div style="width:100%;height:'+document.body.clientHeight+'px;" class="tip-background" onclick="tipAlertClick(this)">'
	+'<div class="tip-board" style="margin-top:'+(document.body.clientHeight/2-100)+'px">'+txt+'</div></div>';
	$('body').append(html);
}
function tipAlert(txt,width){
	$('.tip-background').remove();
	var html='<div style="width:100%;height:'+document.body.clientHeight+'px;" class="tip-background" onclick="tipAlertClick(this)">'
	+'<div class="tip-board" style="width:'+width+';margin-top:'+(document.body.clientHeight/2-100)+'px">'+txt+'</div></div>';
	$('body').append(html);
}
function loadTip(txt){
	$('.tip-background').remove();
	var html='<div style="width:100%;height:'+document.body.clientHeight+'px;" class="tip-background" >'
	+'<div class="tip-board" style="margin-top:'+(document.body.clientHeight/2-100)+'px">'+txt+'</div></div>';
	$('body').append(html);
}
function tipShareClick(){
	$('.tip-background').remove();
	var html='<div style="width:100%;height: 100vh;" class="tip-background share-bord" onclick="tipAlertClick(this)">'
			+'<div style="color:#fff;font-size: 1rem;margin-top: 40vh">点击右上角<br/>分享给你的小伙伴吧</div></div>'
	$('body').append(html);
}
function tipAlertClick(obj) {
	$(obj).remove();
}
//确定框
function phoneConfirm(tip,dook){
	$('.tip-background').remove();
	var html='<div class="tip-background" style="height: 100vh;width: 100%;">'
			+'<div class="tip-board" style="margin-top: 40vh;height:auto;zoom:1;overflow: hidden;width:12rem">'
			+'<div style="min-height: 1.5rem;width: 100%;height: auto;padding:0.3rem">{0}</div>'
			+'<div style="border-top: 1px solid #e8e8e8;height:auto;zoom:1;overflow: hidden;margin-bottom: -0.5rem;padding: 0.5rem 0">'
			+'<div id="btn-ok" style="float:left;width: 50%;">确定</div>'
			+'<div id="btn-cancel" style="float:left;width: 50%;">取消</div>'
			+'</div></div></div>'
	
	$('body').append(html.format(tip));
	$('#btn-ok').click(function(){
		if(dook!=null)
			dook();
	});
	$('#btn-cancel').click(function(){
		$('.tip-background').remove();
	});
}
function closeAll(){
	$('.tip-background').remove();
}

var spinner=null;
function showSpin(){
	$('.tip-background').remove();
	var html='<div style="width:100%;background-color: rgba(50,50,50,0.2);height:'+(document.body.clientHeight)+'px;" class="tip-background">'
	+'</div>';
	$('body').append(html);
    var spinnerOpts = {
            lines: 11 // 共有几条线组成
            , length: 13 // 每条线的长度
            , width: 8 // 每条线的长度
            , radius: 19 // 内圈的大小
            , scale: 0.5 // Scales overall size of the spinner
            , corners: 0.1 // 圆角的程度
            , color: '#000' // #rgb or #rrggbb or array of colors
            , opacity: 0.1 // Opacity of the lines
            , rotate: 18 // 整体的角度（因为是个环形的，所以角度变不变其实都差不多）
            , direction: 1 // 1: clockwise, -1: counterclockwise
            , speed: 0.8 // 速度：每秒的圈数
            , trail: 55 //  高亮尾巴的长度
            , fps: 20 // Frames per second when using setTimeout() as a fallback for CSS
            , zIndex: 2e9 // z-index的值 2e9（默认为2000000000
            , className: 'spinner' // The CSS class to assign to the spinner
            , top: '50%' // Top position relative to parent
            , left: '50%' // Left position relative to parent
            , shadow: false // 是否要阴影
            , hwaccel: true // 是否用硬件加速
            , position: 'absolute' // Element positioning
    };
    spinner=new Spinner(spinnerOpts);
    spinner.spin($('.tip-background')[0]);
}
function closeSpin(){
	spinner.spin();
	$('.tip-background').remove();
}