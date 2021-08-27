package com.ctgu.j604.springbootchat.service;

import com.ctgu.j604.springbootchat.model.AutoSafetyMember;

public interface CTGUService {
    boolean autoReportDelete(AutoSafetyMember autoSafetyMember);
    boolean autoReportModify(AutoSafetyMember autoSafetyMember);
    boolean autoReportAdd(AutoSafetyMember autoSafetyMember);
    boolean autoReportIfExist(AutoSafetyMember autoSafetyMember);
}
