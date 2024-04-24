package com.luka.r18.entity.request_object;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Setter
@Getter
public class LoginObject {

    @Pattern(regexp = "^[a-zA-Z0-9]{4,16}$", message = "字母或数字4~16位")
    private String username;
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]{8,18}$", message = "字母和数字混合8~16位")
    private String password;
    @Pattern(regexp = "^[a-zA-Z0-9]{4}$", message = "字母或数字4位")
    private String code;

}
