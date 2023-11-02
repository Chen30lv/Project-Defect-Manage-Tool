package com.cityu.defect.model.enums;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum DefectStatusEnum {
    /**
     * defectStatus
     * 1-Open 2-Fixed 3-Pending Retest 4-ReOpened
     * 5-Closed 6-Deffered 7-NotABug 8-Duplicate
     */
    OPEN("Open", "Open"),
    FIXED("Fixed", "Fixed"),
    PENDING_RETEST("Pending Retest", "Pending Retest"),
    REOPENED("ReOpened", "ReOpened"),
    CLOSED("Closed", "Closed"),
    DEFFERED("Deffered", "Deffered"),
    NOT_A_BUG("NotABug", "NotABug"),
    DUPLICATE("Duplicate", "Duplicate");

    private final String text;

    private final String value;

    DefectStatusEnum(String text, String value) {
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
    public static DefectStatusEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (DefectStatusEnum anEnum : DefectStatusEnum.values()) {
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
