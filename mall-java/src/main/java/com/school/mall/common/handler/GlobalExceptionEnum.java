package com.school.mall.common.handler;

import lombok.Getter;

@Getter
public enum GlobalExceptionEnum {
    LOGIN_FAILED(50001, "登录失败"),
    UNKNOWN_REASON(50000, "未知错误"),
    SAVE_FAILED(50002, "保存失败"),
    UPDATE_FAILED(50003, "更新失败"),
    QUERY_FAILED(50004, "查询失败"),
    FAILED_TO_DELETE(50005, "删除失败"),
    IMPORT_FAILED(50006, "导入失败"),
    TOKEN_INVALIDATION(50008, "token失效"),
    TOKEN_AUTHENTICATION_FAILED(50009, "token认证失败"),
    TO_LOG_IN_ELSEWHERE(50010, "以在其他地方登录"),
    BE_OVERDUE_TOKEN(50011, "更新token");


    private Integer code;

    private String message;

    GlobalExceptionEnum(Integer code, String message) {

        this.code = code;
        this.message = message;
    }
}
