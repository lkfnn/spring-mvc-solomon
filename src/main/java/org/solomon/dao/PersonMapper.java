package org.solomon.dao;

import java.util.List;

import org.solomon.dto.UserInfoDTO;

/**
 * PersonMapper
 * 
 * @author likf
 *
 */
public interface PersonMapper {

    Integer insertUserInfo(UserInfoDTO userDTO);

    Integer deleteUserInfoById(String id);

    Integer updatePasswordById(UserInfoDTO userDTO);

    UserInfoDTO getUserInfoById(String id);

    List<UserInfoDTO> findUserInfoList();

}
