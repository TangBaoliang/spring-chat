$(document).ready(function (){
    $("#setting-icon").on("click",function (){
        $("#modify-part").toggle(100);
        $(".home-part-navi").toggle(100);
    })
    // $("#modify-icon-btn").on("click",function (){
    //     $("#file-input").click();
    //     $("#icon-submit-btn").show();
    //     $(this).hide();
    // })

    // $("#icon-submit-btn").on("click",function(){
    //     $("#modify-icon-btn").show();
    //     refreshAllImg(document.getElementById("my-chat-head"));
    // })

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


    /**********************************头像上传以及预览*********************************/
    let file = null;
    $('#my-chat-head-big').on('click', function(event) {
        $('#file-input').trigger('click');
        $("#icon-submit-btn").show();
    });
    $('#file-input').on('change', function(event) {
        // console.log(event);
        file = event.target.files[0];

        let fileReader = new FileReader();

        fileReader.readAsDataURL(file);
        fileReader.onload = function(event) {
            $('#my-chat-head-big').attr('src', event.target.result);
        };

    });
    $('#icon-submit-btn').on('click', function(event) {
        let filename = null;
        try {
            filename = file.name.split('.')[1];
        } catch (error) {
            alert('请先选择上传的文件');
            return;
        }

        if (file == null) {
            alert('请先选择上传的文件');
            return;
        } else if (file.size > 8056000) {
            alert('文件超出4MB限制');
            return;
        } else if (!(file.type == 'image/jpg' || file.type == 'image/png' || file.type == 'image/jpeg')) {
            alert('文件类型不正确1');
            return;
        } else if (!(filename == 'jpg' || filename == 'jpeg' || filename == 'png')) {
            alert('文件类型不正确2');
            return;
        }

        let formData = new FormData();
        formData.append('userIcon', file);
        $.ajax({
            type: 'POST',
            url: '/icon/upload',
            data: formData,
            processData: false,
            contentType: false,
            dataType: 'json',
            success: function(response) {
                if (response["flag"]){
                    alert("头像上传成功");
                    refreshAllImg();
                }
                else{
                    alert("头像上传失败！");
                }
            },
            error: function(err) {
                alert("与服务器连接错误！");
            },
        });
    });
    /**********************************end头像上传以及预览*********************************/
})