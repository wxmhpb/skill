package com.xxxx.skill.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public enum RespBeanEnum {

     SUCCESS(200,"success"),
     ERROR(500,"服务器异常"),
     LOGIN_ERROR(500211,"用户名或密码错误"),
     MOBLIE_ERROR(500212,"手机号码格式不正确");
    private final Integer code;
    private final String message;

}
