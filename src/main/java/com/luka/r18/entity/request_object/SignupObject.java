package com.luka.r18.entity.request_object;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
@Setter
@Getter
public class SignupObject implements Serializable {

    @Pattern(regexp = "^[a-zA-Z0-9]{4,16}$", message = "字母数字4~16位")
    private String username;
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]{8,18}$", message = "字母和数字混合8~16位")
    private String password;
    @Email(message = "邮箱")
    private String email;

}
