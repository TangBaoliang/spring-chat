package com.ctgu.j604.springbootchat.controller;

import com.ctgu.j604.springbootchat.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
public class IconUploadController {
    @RequestMapping("/icon/upload")
    public Result acceptIcon(HttpServletRequest request, MultipartFile userIcon){
        if(userIcon != null){
//            String originName = userIcon.getOriginalFilename();
//            int lastIndex = originName.lastIndexOf(".");
//            String suffix = originName.substring(lastIndex);
            File fileIfExist = new File("/root/user-icon/example.jpg");
            String path;
            if(fileIfExist.exists()){
                path="/root/user-icon/";
            }
            else{
                path = "C:\\user-icon\\";
            }
            String fileName = (String)request.getSession().getAttribute("userNum")+".jpg";

            File file = new File(path+fileName);
            try {
                userIcon.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
                return new Result(false,"服务器文件存入失败");
            }
            return new Result(true,"");
        }
        return new Result(false,"");
    }
}
