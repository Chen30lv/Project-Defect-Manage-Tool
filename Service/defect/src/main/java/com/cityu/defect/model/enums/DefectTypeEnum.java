package com.cityu.defect.model.enums;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum DefectTypeEnum {
    /**
     * defectType 1-Functional Defect 2-Logical Defect
     * 3-Workflow Defect
     * 4-Unit Level Defect 5-System-level Integration Defect
     * 6-Security Defect
     */
    FUNCTIONAL_DEFECT("Functional Defect", "Functional Defect"),
    LOGICAL_DEFECT("Logical Defect", "Logical Defect"),
    WORKFLOW_DEFECT("Workflow Defect","Workflow Defect"),
    UNIT_LEVEL_DEFECT("Unit Level Defect","Unit Level Defect"),
    SYSTEM_LEVEL_INTEGRATION_DEFECT("System-level Integration Defect","System-level Integration Defect"),
    SECURITY_DEFECT("Security Defect","Security Defect");



    private final String text;

    private final String value;

    DefectTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static DefectTypeEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (DefectTypeEnum anEnum : DefectTypeEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
