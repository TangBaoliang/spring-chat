package com.ctgu.j604.springbootchat.service.impl;

import com.ctgu.j604.springbootchat.mapper.AutoSafetyMemberMapper;
import com.ctgu.j604.springbootchat.model.AutoSafetyMember;
import com.ctgu.j604.springbootchat.model.AutoSafetyMemberExample;
import com.ctgu.j604.springbootchat.service.CTGUService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CTGUServiceImpl implements CTGUService {
    @Resource
    private AutoSafetyMemberMapper autoSafetyMemberMapper;

    @Override
    public boolean autoReportDelete(AutoSafetyMember autoSafetyMember) {
        AutoSafetyMemberExample autoSafetyMemberExample = new AutoSafetyMemberExample();
        autoSafetyMemberExample.createCriteria().andPasswordEqualTo(autoSafetyMember.getPassword()).andStuNumEqualTo(autoSafetyMember.getStuNum());
        try {
            if(autoSafetyMemberMapper.deleteByExample(autoSafetyMemberExample)>0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean autoReportModify(AutoSafetyMember autoSafetyMember) {
        try {
            if(autoSafetyMemberMapper.updateByPrimaryKey(autoSafetyMember)>0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean autoReportAdd(AutoSafetyMember autoSafetyMember) {
        try {
            if(autoSafetyMemberMapper.insertSelective(autoSafetyMember)>0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean autoReportIfExist(AutoSafetyMember autoSafetyMember) {
        AutoSafetyMember autoSafetyMember1 = autoSafetyMemberMapper.selectByPrimaryKey(autoSafetyMember.getStuNum());
        return autoSafetyMember1==null ? false:true;
    }
}
