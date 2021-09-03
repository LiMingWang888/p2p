package com.xmg.p2p.base.service;

/**
 * @author wlm
 * @date 2021/9/3 - 23:23
 */
public interface IVerifyCodeService {

    /**
     * 给指定手机发送验证码
     */
    void sendVerifyCode(String phoneNumber);

    boolean verify(String phoneNumber, String verifyCode);
}
