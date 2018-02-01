// rem
(function(win) {
    var doc = win.document;
    var docEl = doc.documentElement;


    function refreshRem() {
        var width = docEl.getBoundingClientRect().width;
        console.log(width);
        if (width > 540) { // 最大宽度
            width = 540;
        }

        var rem = width / 20; // 将屏幕宽度分成20份， 1份为1rem
        docEl.style.fontSize = rem + 'px';
    }

    win.addEventListener('resize', refreshRem, false);
    win.addEventListener('pageshow', refreshRem, false);

    refreshRem();

})(window);

//展示用户头像
function headImgBoradShow(url){
    var html='<div id="headimg-board" style="position:fixed;width:20rem;height:101vh;margin:auto;z-index:100;background-color:rgba(0,0,0,0.9);top:0;line-height:100vh" ><img src="'+url+'" width="100%"></div>';
    $('body').append(html);
    $('#headimg-board').click(function(){$(this).remove()});
}

//vip提示
function vipTip(usertype,viptip){
	if(usertype!=UserType.VIP){
		tipAlert('<div class="viptip-board">'
				+'<img src="/YuJian/Contents/images/viptip.png">'
				+'<p>'+viptip+'</p>'
				+'<button onclick="window.location=\'/YuJian/Recharge?selecttype=vip\'">成为VIP</button>'
		+'</div>',"15rem");
	}
}