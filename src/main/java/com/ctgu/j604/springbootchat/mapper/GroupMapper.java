package com.ctgu.j604.springbootchat.mapper;

import com.ctgu.j604.springbootchat.model.Group;
import com.ctgu.j604.springbootchat.model.GroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbg.generated Sat Aug 21 23:34:51 CST 2021
     */
    long countByExample(GroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbg.generated Sat Aug 21 23:34:51 CST 2021
     */
    int deleteByExample(GroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbg.generated Sat Aug 21 23:34:51 CST 2021
     */
    int deleteByPrimaryKey(Integer groupId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbg.generated Sat Aug 21 23:34:51 CST 2021
     */
    int insert(Group record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbg.generated Sat Aug 21 23:34:51 CST 2021
     */
    int insertSelective(Group record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbg.generated Sat Aug 21 23:34:51 CST 2021
     */
    List<Group> selectByExample(GroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbg.generated Sat Aug 21 23:34:51 CST 2021
     */
    Group selectByPrimaryKey(Integer groupId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbg.generated Sat Aug 21 23:34:51 CST 2021
     */
    int updateByExampleSelective(@Param("record") Group record, @Param("example") GroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbg.generated Sat Aug 21 23:34:51 CST 2021
     */
    int updateByExample(@Param("record") Group record, @Param("example") GroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbg.generated Sat Aug 21 23:34:51 CST 2021
     */
    int updateByPrimaryKeySelective(Group record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbg.generated Sat Aug 21 23:34:51 CST 2021
     */
    int updateByPrimaryKey(Group record);
}