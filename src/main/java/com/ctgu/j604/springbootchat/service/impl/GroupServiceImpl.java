package com.ctgu.j604.springbootchat.service.impl;

import com.ctgu.j604.springbootchat.mapper.GroupAndMemberCommentMapper;
import com.ctgu.j604.springbootchat.mapper.GroupAndUserMapper;
import com.ctgu.j604.springbootchat.mapper.GroupLinkUserMapper;
import com.ctgu.j604.springbootchat.mapper.GroupMapper;
import com.ctgu.j604.springbootchat.model.*;
import com.ctgu.j604.springbootchat.service.GroupService;
import com.ctgu.j604.springbootchat.utils.NumUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Resource
    private GroupAndUserMapper groupAndUserMapper;
    @Resource
    private GroupAndMemberCommentMapper groupAndMemberCommentMapper;
    @Resource
    private GroupMapper groupMapper;
    @Resource
    private GroupLinkUserMapper groupLinkUserMapper;


    @Override
    public List<GroupAndUser> getAllGroup(Integer userId) {
            GroupAndUserExample groupAndUserExample = new GroupAndUserExample();
            groupAndUserExample.createCriteria().andUserIdEqualTo(userId);
            return groupAndUserMapper.selectByExample(groupAndUserExample);
    }

    @Override
    public List<String> getAllUsersByGroupNum(String groupNum) {
            GroupAndUserExample groupAndUserExample = new GroupAndUserExample();
            groupAndUserExample.createCriteria().andGroupNumEqualTo(groupNum);
            List<String> stringList = new ArrayList<>();
            List<GroupAndUser> list = groupAndUserMapper.selectByExample(groupAndUserExample);
            for (GroupAndUser g: list) {
                stringList.add(g.getUserNum());
            }
            return stringList;
    }

    @Override
    public String getMemberCommentByNumAndUserId(String groupNum, Integer userId) {
        GroupAndMemberCommentExample groupAndMemberCommentExample = new GroupAndMemberCommentExample();
        groupAndMemberCommentExample.createCriteria().andGroupNumEqualTo(groupNum).andUserIdEqualTo(userId);
        List<GroupAndMemberComment> list = groupAndMemberCommentMapper.selectByExample(groupAndMemberCommentExample);
        if (list!=null && list.size()>0){
            return list.get(0).getMemberComment();
        }
        else{
            return null;
        }
    }

    @Override
    public String createGroup(String groupName, TUser tUser) {
        String num = null;
        while(true){
            num = NumUtils.getNumStringByCounts(8);
            if(!checkGroupIfExist(num)){
                break;
            }
        }
        Group newGroup = new Group();
        newGroup.setGroupNum(num);
        newGroup.setGroupName(groupName);
        newGroup.setCreateTime(new Date());
        newGroup.setGroupMemberCount(1);
        newGroup.setGroupMasterId(tUser.getUserId());
        groupMapper.insertSelective(newGroup);

        addMember(num,tUser.getUserId());
        return num;
    }

    @Override
    public boolean checkGroupIfExist(String groupNum) {
        GroupExample groupExample = new GroupExample();
        groupExample.createCriteria().andGroupNumEqualTo(groupNum);
        List<Group> list = groupMapper.selectByExample(groupExample);
        if (list!=null && list.size()>0){
            return true;
        }
        return false;

    }

    @Override
    public boolean addMember(String groupNum, Integer userId) {
        GroupAndUserExample groupAndUserExample = new GroupAndUserExample();
        groupAndUserExample.createCriteria().andGroupNumEqualTo(groupNum).andUserIdEqualTo(userId);
        List<GroupAndUser> groupAndUserList = groupAndUserMapper.selectByExample(groupAndUserExample);
        if(groupAndUserList!=null && groupAndUserList.size()>0){
            return false;
        }

        GroupLinkUser groupLinkUser = new GroupLinkUser();
        groupLinkUser.setAddTime(new Date());

        GroupExample groupExample = new GroupExample();
        groupExample.createCriteria().andGroupNumEqualTo(groupNum);

        List<Group> list = groupMapper.selectByExample(groupExample);
        if(list!=null && list.size()<=0){
            return false;
        }
        groupLinkUser.setGroupId(list.get(0).getGroupId());
        groupLinkUser.setUserId(userId);
        if(groupLinkUserMapper.insert(groupLinkUser)>0){
            return true;
        }
        return false;
    }


}
