package com.xxxx.skill.vo;

import com.xxxx.skill.validator.IsMoblie;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class LoginVO {
    @NotNull
    @IsMoblie
    private String moblie;

    @NotNull
    @Length(min=32)
    private String password;
}
