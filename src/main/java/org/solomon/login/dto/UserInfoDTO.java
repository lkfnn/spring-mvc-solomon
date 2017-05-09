package org.solomon.login.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * UserDTO
 * 
 * @author likf
 *
 */
@Data
public class UserInfoDTO implements Serializable {

    private static final long serialVersionUID = 7698862379923111158L;
    private String userID;
    private String personID;
    private String personName;
    private String deptID;
    private String deptName;
    private String userName;
    private String password;

}
