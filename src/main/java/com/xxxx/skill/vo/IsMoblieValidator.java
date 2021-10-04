package com.xxxx.skill.vo;
//手机号校验规则


import com.xxxx.skill.utils.ValidatorUtil;
import com.xxxx.skill.validator.IsMoblie;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsMoblieValidator implements ConstraintValidator<IsMoblie,String> {
    private boolean required = false;

    @Override
    public void initialize(IsMoblie constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (required) {
            return ValidatorUtil.isMoblie(value);
        } else {
            if (StringUtils.isEmpty(value)) {
                return true;
            } else {
                return ValidatorUtil.isMoblie(value);

            }
        }
    }
}
