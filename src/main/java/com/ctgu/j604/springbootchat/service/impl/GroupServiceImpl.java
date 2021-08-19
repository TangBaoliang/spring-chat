package com.ctgu.j604.springbootchat.service.impl;

import com.ctgu.j604.springbootchat.mapper.GroupAndUserMapper;
import com.ctgu.j604.springbootchat.model.GroupAndUser;
import com.ctgu.j604.springbootchat.model.GroupAndUserExample;
import com.ctgu.j604.springbootchat.service.GroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Resource
    private GroupAndUserMapper groupAndUserMapper;

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
}
