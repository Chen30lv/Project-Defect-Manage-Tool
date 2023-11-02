package com.cityu.defect.constant;

public interface DefectInfoConstant {
    /**
     * defectStatus
     * 1-Open 2-Fixed 3-Pending Retest 4-ReOpened
     * 5-Closed 6-Deffered 7-NotABug 8-Duplicate
     */
    String DEFECT_STATUS = "defectStatus";
    /**
     * defectType 1-Functional Defect 2-Logical Defect
     * 3-Workflow Defect
     * 4-Unit Level Defect 5-System-level Integration Defect
     * 6-Security Defect
     */
    String DEFECT_TYPE = "defectType";
    /**
     * defectLevel Low Medium High Critical
     */
    String DEFECT_LEVEL = "defectLevel";
}
