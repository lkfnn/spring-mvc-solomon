package org.solomon.login.dao;

import org.solomon.login.dto.UserInfoDTO;

/**
 * PersonMapper
 * 
 * @author likf
 *
 */
public interface UserInfoMapper {

    UserInfoDTO getUserInfoForLogin(UserInfoDTO userInfoDTO);

}
