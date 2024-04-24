package com.luka.r18.entity.response_object;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class ResponseCodeEntity <T>{
    private Integer code;
    private String message;
    private T data;
}
