package com.luka.r18.entity.request_object;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Setter
@Getter
public class UpdatePasswordObject {
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]{8,18}$", message = "字母和数字混合8~16位")
    private String newPassword;
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]{8,18}$", message = "字母和数字混合8~16位")
    private String oldPassword;
}
