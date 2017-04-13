package org.solomon.controller;

import java.util.List;

import org.solomon.dto.UserInfoDTO;
import org.solomon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloWorldController
 * 
 * @author likf
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findUserInfoList", method = RequestMethod.GET)
    public List<UserInfoDTO> findUserInfoList() {
        List<UserInfoDTO> list = userService.findUserInfoList();
        return list;
    }

    @RequestMapping(value = "/insertUserInfo", method = RequestMethod.POST)
    public void insertUserInfo(UserInfoDTO UserInfoDTO) {
        userService.insertUserInfo(UserInfoDTO);
    }

    @RequestMapping(value = "/deleteUserInfoById", method = RequestMethod.DELETE)
    public void deleteUserInfoById(String id) {
        userService.deleteUserInfoById(id);
    }

    @RequestMapping(value = "/updatePasswordById", method = RequestMethod.PUT)
    public void updatePasswordById(UserInfoDTO UserInfoDTO) {
        userService.updatePasswordById(UserInfoDTO);
    }

    @RequestMapping(value = "/getUserInfoById", method = RequestMethod.GET)
    public UserInfoDTO getUserInfoById(String id) {
        return userService.getUserInfoById(id);
    }

}
