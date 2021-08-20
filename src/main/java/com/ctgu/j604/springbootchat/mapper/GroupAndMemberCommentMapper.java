package com.ctgu.j604.springbootchat.mapper;

import com.ctgu.j604.springbootchat.model.GroupAndMemberComment;
import com.ctgu.j604.springbootchat.model.GroupAndMemberCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupAndMemberCommentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_and_member_comment
     *
     * @mbg.generated Fri Aug 20 15:12:56 CST 2021
     */
    long countByExample(GroupAndMemberCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_and_member_comment
     *
     * @mbg.generated Fri Aug 20 15:12:56 CST 2021
     */
    int deleteByExample(GroupAndMemberCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_and_member_comment
     *
     * @mbg.generated Fri Aug 20 15:12:56 CST 2021
     */
    int insert(GroupAndMemberComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_and_member_comment
     *
     * @mbg.generated Fri Aug 20 15:12:56 CST 2021
     */
    int insertSelective(GroupAndMemberComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_and_member_comment
     *
     * @mbg.generated Fri Aug 20 15:12:56 CST 2021
     */
    List<GroupAndMemberComment> selectByExample(GroupAndMemberCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_and_member_comment
     *
     * @mbg.generated Fri Aug 20 15:12:56 CST 2021
     */
    int updateByExampleSelective(@Param("record") GroupAndMemberComment record, @Param("example") GroupAndMemberCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_and_member_comment
     *
     * @mbg.generated Fri Aug 20 15:12:56 CST 2021
     */
    int updateByExample(@Param("record") GroupAndMemberComment record, @Param("example") GroupAndMemberCommentExample example);
}