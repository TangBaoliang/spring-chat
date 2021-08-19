//检测浏览器刷新
window.onbeforeunload = function()
{
    //清除sessionStorage值
    sessionStorage.clear();

}


let preBubbleBoxSelf = '<div class="bubble-box-self clearfix"><img src="img/02.jpg"> <div class="bubble"><pre>';
let tailBubbleBox='</pre></div></div>';
let preBubbleBoxOthers = '<div class="bubble-box-others clearfix"><img src="img/02.jpg"> <div class="bubble"><pre>';

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
        else if(res["msgTypeCode"]==2){

            if(res["fromUserNum"]===curChatUserNum){
                $(".message-item-box").append(preBubbleBoxOthers+res["message"]+tailBubbleBox)
                localStorageAppend(curChatUserNum,preBubbleBoxOthers+res["message"]+tailBubbleBox);
                sendReadConfirm();
            }
            else{
                let str = "[data-userNum='"+res['fromUserNum']+"']";
                let count = Number($(str).find(".message-count").text());
                $(str).find(".message-count").html(count+1);
                $(str).find(".message-count").show();

                let sess = sessionStorage.getItem(res['fromUserNum']);
                if(sess!==null){
                    sessionStorage.setItem(res['fromUserNum'],sess+preBubbleBoxOthers+res["message"]+tailBubbleBox);
                }
                else{
                    sessionStorage.setItem(res['fromUserNum'],preBubbleBoxOthers+res["message"]+tailBubbleBox);
                }

            }
        }


    }

    //3.连接断开时出发的事件
    ws.onclose = function(){

    }


    /**********************绑定webSocket事件***********************end*/


    //点击选择要发送信息的联系人
    $(".friend-items,.group-items").on("click",function (){
        curChatUserNum = this.getAttribute("data-userNum");

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
            let json = {"msgTypeCode":2,"toUserNum":curChatUserNum,"message":preMsg};
            ws.send(JSON.stringify(json));
            $(".message-item-box").append(preBubbleBoxSelf+preMsg+tailBubbleBox);
            localStorageAppend(curChatUserNum,preBubbleBoxSelf+preMsg+tailBubbleBox)
        }
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

})

