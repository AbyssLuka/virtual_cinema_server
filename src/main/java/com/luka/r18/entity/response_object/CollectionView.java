package com.luka.r18.entity.response_object;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CollectionView {
    private String uuid;
    private String userUuid;
    private String viewUuid;
    private Date createTime;
    private VideoView videoViewData;
}
