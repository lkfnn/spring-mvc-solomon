package org.solomon.login.service;

import org.solomon.login.dao.UserInfoMapper;
import org.solomon.login.dto.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService
 * 
 * @author likf
 */
@Service
public class UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserInfoDTO getUserInfoForLogin(String userName, String password) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUserName(userName);
        userInfoDTO.setPassword(password);
        return userInfoMapper.getUserInfoForLogin(userInfoDTO);
    }

}
