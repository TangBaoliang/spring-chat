package com.ctgu.j604.springbootchat.model;

import java.io.Serializable;

public class AutoSafetyMember implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column auto_report_safety.stu_num
     *
     * @mbg.generated Fri Aug 27 23:12:51 CST 2021
     */
    private String stuNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column auto_report_safety.password
     *
     * @mbg.generated Fri Aug 27 23:12:51 CST 2021
     */
    private String password;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auto_report_safety.stu_num
     *
     * @return the value of auto_report_safety.stu_num
     *
     * @mbg.generated Fri Aug 27 23:12:51 CST 2021
     */
    public String getStuNum() {
        return stuNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auto_report_safety.stu_num
     *
     * @param stuNum the value for auto_report_safety.stu_num
     *
     * @mbg.generated Fri Aug 27 23:12:51 CST 2021
     */
    public void setStuNum(String stuNum) {
        this.stuNum = stuNum == null ? null : stuNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auto_report_safety.password
     *
     * @return the value of auto_report_safety.password
     *
     * @mbg.generated Fri Aug 27 23:12:51 CST 2021
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auto_report_safety.password
     *
     * @param password the value for auto_report_safety.password
     *
     * @mbg.generated Fri Aug 27 23:12:51 CST 2021
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}