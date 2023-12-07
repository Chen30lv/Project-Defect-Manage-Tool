package com.cityu.defect.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum DefectToDoEnum {

    TODO("TODO", "TODO"),
    FINISHED("FINISHED", "FINISHED");

    private final String text;

    private final String value;

    DefectToDoEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * getValues
     *
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * getEnumByValue
     *
     * @param value
     * @return
     */
    public static DefectToDoEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (DefectToDoEnum anEnum : DefectToDoEnum.values()) {
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
