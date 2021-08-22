$(document).ready(function (){
    $("#setting-icon").on("click",function (){
        $("#modify-part").toggle(100);
        $(".home-part-navi").toggle(100);
    })
    $("#modify-icon-btn").on("click",function (){
        $("#file-input").click();
        $("#icon-submit-btn").show();
        $(this).hide();
    })

    $("#icon-submit-btn").on("click",function(){
        $("#modify-icon-btn").show();
        refreshAllImg(document.getElementById("my-chat-head"));
    })

    $("#add-btn").on("click",function(){
        $("#add-list").toggle(300);
    })

    $("#window-close-btn").on("click",function(){
        $("#friend-add-window").slideUp(400);
    })

    $("#add-friend-btn").on("click",function(){
        $("#friend-add-window").slideDown(200);
    })

    $(".friend-items input").on("click",function (e){
        e.stopPropagation();
    })

    $("#group-add-confirm").on("click",function(){

        $(".checkbox-for-friend").hide(400);
        $("#group-create-box").hide(400);

        let toInviteUserNums = [];
        let i = 0;
        $("input[name='toInviteFriends']:checked").each(function(){
            toInviteUserNums[i++] = $(this).val();
        })

        $.ajax({
            url: "group/create",
            async: false,
            type: 'POST',
            data: JSON.stringify(toInviteUserNums),
            contentType: 'application/json;charset=UTF-8',
            success: function (resultData) {
                if(resultData["flag"]){
                    alert("群聊创建成功！等待好友同意加入");
                }
                else{
                    alert("发起群聊失败！");
                }
            },
            error: function () {

            }
        });
    })

    $("#group-create-btn").on("click",function (){
        $(".checkbox-for-friend").show(400);
        $("#group-create-box").show(400);
    })

    $("#group-add-cancel").on("click",function(){
        $(".checkbox-for-friend").hide(400);
        $("#group-create-box").hide(400);
    })

})