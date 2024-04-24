package com.luka.r18.entity.request_object;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Setter
@Getter
public class PageObject {
    @Min(0)
    private Integer page = 0;
    @Min(1)
    private Integer size = 10;
    private String keyword = "";
}
