package com.xmg.p2p.base.service;

import com.xmg.p2p.base.domain.Logininfo;

/**
 * 登录相关服务
 * @author wlm
 * @date 2021/9/2 - 15:23
 */
public interface ILogininfoService {

    /**
     * 用户注册
     * @param username
     * @param password
     */
    void register(String username, String password);

    boolean checkUsername(String username, String password);

    Logininfo login(String username, String password, String ip, int userType);

    void initAdmin();
}
