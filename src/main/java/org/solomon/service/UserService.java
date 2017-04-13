package org.solomon.service;

import java.util.List;

import org.solomon.dao.PersonMapper;
import org.solomon.dto.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService
 * 
 * @author likf
 */
@Service
public class UserService {

    @Autowired
    private PersonMapper personMapper;

    public void insertUserInfo(UserInfoDTO UserInfoDTO) {
        personMapper.insertUserInfo(UserInfoDTO);
    }

    public void deleteUserInfoById(String id) {
        personMapper.deleteUserInfoById(id);
    }

    public void updatePasswordById(UserInfoDTO UserInfoDTO) {
        personMapper.updatePasswordById(UserInfoDTO);
    }

    public UserInfoDTO getUserInfoById(String id) {
        return personMapper.getUserInfoById(id);
    }

    public List<UserInfoDTO> findUserInfoList() {
        return personMapper.findUserInfoList();
    }

}
