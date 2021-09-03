package com.xmg.p2p.base.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author wlm
 * @date 2021/9/3 - 23:26
 */
@Getter
@Setter
public class VerifyCodeVO {

    private String verifyCode;//验证码
    private String phoneNumber;//发送验证码的手机号
    private Date lastSendTime;//最近成功发送时间
}
