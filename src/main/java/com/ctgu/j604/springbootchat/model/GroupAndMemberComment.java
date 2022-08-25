package com.ctgu.j604.springbootchat.model;

import java.io.Serializable;

public class GroupAndMemberComment implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_and_member_comment.group_num
     *
     * @mbg.generated Fri Aug 20 15:12:56 CST 2021
     */
    private String groupNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_and_member_comment.user_id
     *
     * @mbg.generated Fri Aug 20 15:12:56 CST 2021
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_and_member_comment.member_comment
     *
     * @mbg.generated Fri Aug 20 15:12:56 CST 2021
     */
    private String memberComment;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_and_member_comment.group_num
     *
     * @return the value of group_and_member_comment.group_num
     *
     * @mbg.generated Fri Aug 20 15:12:56 CST 2021
     */
    public String getGroupNum() {
        return groupNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_and_member_comment.group_num
     *
     * @param groupNum the value for group_and_member_comment.group_num
     *
     * @mbg.generated Fri Aug 20 15:12:56 CST 2021
     */
    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum == null ? null : groupNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_and_member_comment.user_id
     *
     * @return the value of group_and_member_comment.user_id
     *
     * @mbg.generated Fri Aug 20 15:12:56 CST 2021
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_and_member_comment.user_id
     *
     * @param userId the value for group_and_member_comment.user_id
     *
     * @mbg.generated Fri Aug 20 15:12:56 CST 2021
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_and_member_comment.member_comment
     *
     * @return the value of group_and_member_comment.member_comment
     *
     * @mbg.generated Fri Aug 20 15:12:56 CST 2021
     */
    public String getMemberComment() {
        return memberComment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_and_member_comment.member_comment
     *
     * @param memberComment the value for group_and_member_comment.member_comment
     *
     * @mbg.generated Fri Aug 20 15:12:56 CST 2021
     */
    public void setMemberComment(String memberComment) {
        this.memberComment = memberComment == null ? null : memberComment.trim();
    }
}