package org.solomon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.solomon.login.dto.UserInfoDTO;
import org.solomon.login.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import lombok.extern.java.Log;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/applicationContext.xml" })
@Transactional
@Log
public class TestUserService {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void testfindUserInfoList() {
        UserInfoDTO userInfoDTO = userInfoService.getUserInfoForLogin("admin", "123456");
        log.info(JSON.toJSONStringWithDateFormat(userInfoDTO, "yyyy-MM-dd HH:mm:ss"));
    }

}
