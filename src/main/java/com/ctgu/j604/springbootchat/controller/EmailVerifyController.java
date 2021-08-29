package com.ctgu.j604.springbootchat.controller;

import com.ctgu.j604.springbootchat.service.VerifyService;
import com.ctgu.j604.springbootchat.utils.EmailUtils;
import com.ctgu.j604.springbootchat.utils.MessageUtils;
import com.ctgu.j604.springbootchat.utils.NumUtils;
import com.ctgu.j604.springbootchat.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class EmailVerifyController {
    @Resource
    private VerifyService verifyService;

    @RequestMapping("/email/registrationqualification")
    public Result getQualification(HttpServletRequest request, String email){
        String sessionId = request.getSession().getId();
        String verifyCode = NumUtils.getNumStringByCounts(6);
        boolean flag = verifyService.sendEmailAndSaveVerifyCode(verifyCode,sessionId,email);

        if(flag){
            return new Result(flag,"已发送验证邮件到您的邮箱，请注意查收！");
        }
        else{
            return new Result(flag,"邮件发送失败，请稍后再试！");
        }
    }

    @RequestMapping("/emailVerify/{sessionId}/{verifyCode}")
    public ModelAndView checkQualification(@PathVariable("sessionId") String sessionId, @PathVariable("verifyCode") String verifyCode){
        ModelAndView modelAndView = new ModelAndView();
        boolean res = verifyService.checkClick(sessionId,verifyCode);
        if(res){
            modelAndView.setViewName("checkResSuccess");
        }
        else{
            modelAndView.setViewName("checkResFail");
        }
        return modelAndView;
    }


}
