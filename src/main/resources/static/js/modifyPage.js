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
})