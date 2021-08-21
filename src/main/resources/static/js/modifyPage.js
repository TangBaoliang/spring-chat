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


})