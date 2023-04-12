package com.luka.r18;

import com.luka.r18.mappers.AnimeInfoLinkMapper;
import com.luka.r18.mappers.UserDataMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class R18ApplicationTests {

    @Resource
    UserDataMapper userDataMapper;

    @Test
    void contextLoads() {

    }

}
