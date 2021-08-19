package com.ctgu.j604.springbootchat.mapper;

import com.ctgu.j604.springbootchat.model.GroupAndUser;
import com.ctgu.j604.springbootchat.model.GroupAndUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupAndUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_and_user
     *
     * @mbg.generated Thu Aug 19 14:21:56 CST 2021
     */
    long countByExample(GroupAndUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_and_user
     *
     * @mbg.generated Thu Aug 19 14:21:56 CST 2021
     */
    int deleteByExample(GroupAndUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_and_user
     *
     * @mbg.generated Thu Aug 19 14:21:56 CST 2021
     */
    int insert(GroupAndUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_and_user
     *
     * @mbg.generated Thu Aug 19 14:21:56 CST 2021
     */
    int insertSelective(GroupAndUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_and_user
     *
     * @mbg.generated Thu Aug 19 14:21:56 CST 2021
     */
    List<GroupAndUser> selectByExample(GroupAndUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_and_user
     *
     * @mbg.generated Thu Aug 19 14:21:56 CST 2021
     */
    int updateByExampleSelective(@Param("record") GroupAndUser record, @Param("example") GroupAndUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_and_user
     *
     * @mbg.generated Thu Aug 19 14:21:56 CST 2021
     */
    int updateByExample(@Param("record") GroupAndUser record, @Param("example") GroupAndUserExample example);
}