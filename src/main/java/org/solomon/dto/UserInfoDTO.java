package org.solomon.dto;

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
    private String id;
    private String username;
    private String password;

}
