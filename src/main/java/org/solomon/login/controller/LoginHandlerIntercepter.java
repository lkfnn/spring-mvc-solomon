package org.solomon.login.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.solomon.login.dto.UserInfoDTO;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 登陆拦截器，判断用户是否登陆？ 未登陆，则转到登陆界面。
 * 
 * @author likf
 */
public class LoginHandlerIntercepter extends HandlerInterceptorAdapter {

    /**
     * 不拦截地址列表
     */
    private List<String> uncheckUrls;

    /**
     * 获取不拦截地址列表
     * 
     * @return uncheckUrls 不拦截地址列表
     */
    public List<String> getUncheckUrls() {
        return uncheckUrls;
    }

    /**
     * 设置不拦截地址列表
     * 
     * @param uncheckUrls
     *            不拦截地址列表
     */
    public void setUncheckUrls(List<String> uncheckUrls) {
        this.uncheckUrls = uncheckUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        String requestUrl = request.getRequestURI();
        if (uncheckUrls.contains(requestUrl)) {
            return true;
        } else {
            HttpSession session = request.getSession();
            UserInfoDTO userInfoDTO = (UserInfoDTO) session.getAttribute("userInfoDTO");
            if (userInfoDTO != null) {
                return true;
            } else {
                response.sendRedirect("/");
                return false;
            }
        }
    }

}
