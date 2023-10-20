package com.cityu.defect.model.enums;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum DefectTypeEnum {
    /**
     * defectType 功能未实现-1 通用异常未处理-2 界面优化-3 安全相关-4 性能问题-5 兼容性-6
     */
    FUNCTION_ISSUE("功能未实现", "function_issue"),
    EXCEPTION_ISSUE("通用异常未处理", "exception_issue"),
    UI_ISSUE("界面优化","ui_issue"),
    SECURITY_ISSUE("安全相关","security_issue"),
    PERFORMANCE_ISSUE("性能问题","performance_issue"),
    COMPATIBILITY_ISSUE("兼容性","compatibility_issue");



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
