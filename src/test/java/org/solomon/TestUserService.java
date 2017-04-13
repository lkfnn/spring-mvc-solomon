package org.solomon;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.solomon.dto.UserInfoDTO;
import org.solomon.service.UserService;
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
    private UserService userService;

    @Test
    public void testfindUserInfoList() {
        List<UserInfoDTO> list = userService.findUserInfoList();
        log.info(JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss"));
    }

}
