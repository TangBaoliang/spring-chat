//检测浏览器刷新
window.onbeforeunload = function()
{
    //清除sessionStorage值
    sessionStorage.clear();

    refreshAllImg(null);
}

//刷新所有图片
function refreshAllImg(ob){
    if (ob!==null){
        ob.src = ob.src+'?time='+new Date().getTime();
    }
    else {
        let imgs = document.getElementsByTagName("img");
        for (let img of imgs) {
            img.src = img.src+'?time='+(new Date()).getSeconds();
        }
    }
}

let myIconSrc = document.getElementById("my-chat-head").src;
let preBubbleBoxSelf = '<div class="bubble-box-self clearfix"><img src="'+myIconSrc+'"> <div class="bubble"><pre>';
let tailBubbleBox='</pre></div></div>';
let preBubbleBoxOthers = ['<div class="bubble-box-others clearfix"><img src="/img/user-icon/','','.jpg"/><div class="bubble"><pre>'];
let chatType='';
let ifEnterKeyCanSend = true; //回车键弹起是否能够发送，主要是让Ctrl+key按下产生换行后阻止回车键弹起发送消息的事件
let commentLabelSelf = '<div class="comment-label-self">'+$("#my-nickname").text()+'</div>';
let curChatUserNum='';

$(document).ready(function (){

    //隐藏聊天窗口
    $(".message-part *").hide();

    //全局变量，方便添加元素


    let urlPath = window.document.location.href;  //浏览器显示地址 http://10.15.5.83:5555/ISV/demo.aspx?a=1&b=2
    let docPath = window.document.location.pathname; //文件在服务器相对地址 /ISV/demo.aspx
    let index = urlPath.indexOf(docPath);
    let serverPath = urlPath.substring(7, index);//服务器地址 http://10.15.5.83:5555

    let ws = new WebSocket("ws://"+serverPath+"/chat")
    /*begin*********************绑定webSocket事件************************/


    //1.绑定连接建立后触发的事件
    ws.onopen=function (){

    }

    //2.绑定数据接收时出发的事件
    ws.onmessage = function(evt){
        //获取消息体的Json字符串，不是json对象
        let dataStr = evt.data;
        //把该字符串转化成Json对像
        let res = JSON.parse(dataStr);

        if(res["msgTypeCode"]==1){
            alert("用户"+res["message"]+"于"+res["sendTime"]+"上线！"+"\n你可以和他聊天了");
        }
        else if(res["msgTypeCode"]==2 || res["msgTypeCode"]==4){

            let  bubbleContent = '<div class="comment-label-others">';

            if (res["msgTypeCode"]==2 ){
                preBubbleBoxOthers[1] = res['fromUserNum'];//设置头像地址

                let findStr = "[data-Num='"+res['fromUserNum']+"']";
                bubbleContent += $(findStr).find(".friend-comment-p").text() + '</div>' + res["message"];
            }
            else{
                preBubbleBoxOthers[1] = res["fromMemberNum"];//设置头像地址

                bubbleContent += res["message"][1] + '</div>' + res["message"][0];
            }
            let preBleBoxForOther = preBubbleBoxOthers[0]+preBubbleBoxOthers[1]+preBubbleBoxOthers[2];
            if(res["fromUserNum"]===curChatUserNum){
                $(".message-item-box").append(preBleBoxForOther+bubbleContent+tailBubbleBox);
                localStorageAppend(curChatUserNum,preBleBoxForOther+bubbleContent+tailBubbleBox);
                sendReadConfirm();
            }
            else{
                let str = "[data-Num='"+res['fromUserNum']+"']";
                let count = Number($(str).find(".message-count").text());
                $(str).find(".message-count").html(count+1);
                $(str).find(".message-count").show();
                sessionStorageAppend(res['fromUserNum'],preBleBoxForOther+bubbleContent+tailBubbleBox);
            }
        }

    }

    //3.连接断开时出发的事件
    ws.onclose = function(){

    }


    /**********************绑定webSocket事件***********************end*/


    //点击选择要发送信息的联系人
    $(".friend-items").on("click",function (){
        curChatUserNum = this.getAttribute("data-userNum");
        chatType = 2;
        //窗口的动画
        $(".message-part *").hide();
        $(".message-part *").show(300);

        //消息先存进localStorage,通过localStorage导入聊天窗口
        sessToLocalStorage(curChatUserNum);

        $(".message-item-box").html(localStorage.getItem(curChatUserNum));
        sendReadConfirm();
        $(this).find(".message-count").text("").hide(1000);
        $("#chat-box-title").html(this.title);
    })

    $(".group-items").on("click",function (){
        curChatUserNum = this.getAttribute("data-groupNum");
        chatType = 4;
        //窗口的动画
        $(".message-part *").hide();
        $(".message-part *").show(300);

        //消息先存进localStorage,通过localStorage导入聊天窗口
        sessToLocalStorage(curChatUserNum);

        $(".message-item-box").html(localStorage.getItem(curChatUserNum));
        sendReadConfirm();
        $(this).find(".message-count").text("").hide(1000);
        $("#chat-box-title").html(this.title);
    })

    $("#close-btn").on("click",function(){
        $(".message-part *").hide(300);
        curChatUserNum = '';
    })

    $("#link-search").on("input",function (){
        let searchWord=this.value;
        let pattern = new RegExp(searchWord);
        $(".friend-items,.group-items").each(function (){
            let res = pattern.test(this.title) || pattern.test(this.getAttribute("data-userNum"));
            if(searchWord==='' || res){
                $(this).show();
            }
            else{
                $(this).hide();
            }
        }
        )
    })


    //发送按钮的绑定事件
    $("#send-btn").on("click",function(){
        let preMsg = $("#pre-send-txt").val();
        $("#pre-send-txt").val('');
        if (!preMsg.match(/^\s+$/) && preMsg!==''){
            let json = {"msgTypeCode":chatType,"toUserNum":curChatUserNum,"message":preMsg,"sendTime":new Date().getTime()};
            ws.send(JSON.stringify(json));
            $(".message-item-box").append(commentLabelSelf+preBubbleBoxSelf+commentLabelSelf+preMsg+tailBubbleBox);
            localStorageAppend(curChatUserNum,preBubbleBoxSelf+commentLabelSelf+preMsg+tailBubbleBox)
        }
    })

    $("#submit").click(function(){
        $.ajax({
            type: "POST",
            url: "/search",
            data: $("#Search").serialize(),
            dataType: "json",
            success: function(data){
                $(".user_table").html("");
                $(".remindinfo").html("");
                if(data.length === 0){
                    $(".remindinfo").html("不存在此用户");
                }
                else{
                    for (let i=0; i<data.length; i++) {
                        $(".user_table").append("<tr>" + "<td>"+data[i].userNum +"</td>"+"<td>"+data[i].nickName+"</td>"+ "<td>"+"<input id='addfriend' data-num='"+data[i].userNum+ "' value='+ 好友' type='button'>"+"</td>"+"</tr>");
                    }
                    $("#addfriend").on("click",function(){
                        let preMsg = $("#checkmsg").val();
                        $("checkmsg").val('');
                        chatType = 5;
                        let json = {"msgTypeCode":chatType,"toUserNum":this.getAttribute("data-num"),"message":preMsg,"sendTime":new Date().getTime()};
                        ws.send(JSON.stringify(json));
                        $("#systeminfo").append("<div class='system-items'>"+"<p class='friend-comment-p'>"+  +"</p>"+"<p class='nickname-p'>"+  +"</p>"+"<p>"+ preMsg +"</p>"+"</div>"+"<div class='message-count'>"+"</div>");
                    })
                }
            }
        })
    })

    //回车键弹起发送消息
    $("#pre-send-txt").keyup(function (event){
        if(event.keyCode===13 && ifEnterKeyCanSend){
            $("#send-btn").click();
        }
        ifEnterKeyCanSend=true;
    })

    //ctrl和回车键按下后换行
    $("#pre-send-txt").keydown(function (event){
        if(event.keyCode===13 && event.ctrlKey){
            let txt = $("#pre-send-txt").val();
            $("#pre-send-txt").val(txt+"\n");
            ifEnterKeyCanSend=false;
        }

    })

    $("#chatmsg").click(function(){
        $("#systeminfo").hide(300);
        $("#chattingmsg").show(300);
        $(".message-part *").hide(300);
    })

    $("#applymsg").click(function(){
        $("#chattingmsg").hide(300);
        $("#systeminfo").show(300);
        $(".message-part *").hide(300);
    })
    function sendReadConfirm(){

        //3代表此消息是已读消息确认
        let json = {"msgTypeCode":3,"toUserNum":curChatUserNum,"message":''};

        ws.send(JSON.stringify(json));
    }

    function sessToLocalStorage(key){
        let sess = sessionStorage.getItem(key);

        if(sess!==null){
            let local = localStorage.getItem(key);
            if (local!==null){
                localStorage.setItem(key,local+sess);
            }
            else{
                localStorage.setItem(key,sess);
            }
        }
        sessionStorage.removeItem(key);
    }

    function localStorageAppend(key, value){
        let local = localStorage.getItem(key);
        if(local!==null){
            localStorage.setItem(key,local+value);
        }
        else{
            localStorage.setItem(key,value);
        }
    }
    function sessionStorageAppend(key, value){
        let sess = sessionStorage.getItem(key);
        if(sess!==null){
            sessionStorage.setItem(key,sess+value);
        }
        else{
            sessionStorage.setItem(key,value);
        }
    }
})

