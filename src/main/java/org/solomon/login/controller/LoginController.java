package org.solomon.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.solomon.login.dto.UserInfoDTO;
import org.solomon.login.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * LoginController
 * 
 * @author likf
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String findUserInfoList() {
        return "login";
    }

    @RequestMapping(value = "/login.do")
    public String login(String userName, String password, Model model, HttpServletRequest request,
            HttpServletResponse response) {
        UserInfoDTO userInfoDTO = userInfoService.getUserInfoForLogin(userName, password);
        if (userInfoDTO != null) {
            request.getSession().setAttribute("userInfoDTO", userInfoDTO);
            return "redirect:home";
        } else {
            model.addAttribute("error", "用户名或密码错误，请重新输入。");
            return "login";
        }
    }

    @RequestMapping(value = "/loginOut.do")
    public String loginOut(HttpServletRequest request) {
        request.getSession().removeAttribute("userInfoDTO");
        return "login";
    }

}
