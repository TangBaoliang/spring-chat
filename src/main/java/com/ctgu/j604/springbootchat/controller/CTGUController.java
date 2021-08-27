package com.ctgu.j604.springbootchat.controller;

import com.ctgu.j604.springbootchat.model.AutoSafetyMember;
import com.ctgu.j604.springbootchat.service.CTGUService;
import com.ctgu.j604.springbootchat.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CTGUController {
    @Resource
    private CTGUService ctguService;

    @RequestMapping("/ctgu/auto-report")
    @ResponseBody
    public Result addOrModify(AutoSafetyMember autoSafetyMember){
        autoSafetyMember.setStuNum(autoSafetyMember.getStuNum().replace(" ",""));
        autoSafetyMember.setPassword(autoSafetyMember.getPassword().replace(" ",""));
        if(autoSafetyMember.getStuNum()==null||autoSafetyMember.getPassword()==null||autoSafetyMember.getPassword().equals("")||autoSafetyMember.getStuNum().equals("")){
            return new Result(false,"小子别乱搞");
        }
        if(!ctguService.autoReportIfExist(autoSafetyMember)){
            return new Result(ctguService.autoReportAdd(autoSafetyMember),"添加成功！");
        }
        else{
            return new Result(ctguService.autoReportModify(autoSafetyMember),"修改密码成功！");
        }
    }

    @RequestMapping("/ctgu/close-auto-report")
    @ResponseBody
    public Result close(AutoSafetyMember autoSafetyMember){
        autoSafetyMember.setStuNum(autoSafetyMember.getStuNum().replace(" ",""));
        autoSafetyMember.setPassword(autoSafetyMember.getPassword().replace(" ",""));
        if(autoSafetyMember.getStuNum()==null||autoSafetyMember.getPassword()==null||autoSafetyMember.getPassword().equals("")||autoSafetyMember.getStuNum().equals("")){
            return new Result(false,"小子别乱搞");
        }
        if(ctguService.autoReportDelete(autoSafetyMember)){
            return new Result(true,"取消成功！");
        }
        else{
            return new Result(false,"取消失败！密码错误或者根本未开启过自动报平安");
        }
    }
}
