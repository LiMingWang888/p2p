package com.xmg.p2p.base.service.impl;

import com.xmg.p2p.base.service.IVerifyCodeService;
import com.xmg.p2p.base.util.BidConst;
import com.xmg.p2p.base.util.DateUtil;
import com.xmg.p2p.base.util.UserContext;
import com.xmg.p2p.base.vo.VerifyCodeVO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @author wlm
 * @date 2021/9/3 - 23:24
 */
@Service
public class VerifyCodeServiceImpl implements IVerifyCodeService {

    @Override
    public void sendVerifyCode(String phoneNumber) {
        VerifyCodeVO vo = UserContext.getCurrentVerifyCode();
        if (vo == null
                || DateUtil.secondsBetween(new Date(), vo.getLastSendTime()) > 90) {
            //正常发送验证码信息
            //生成一个验证码
            String verifyCode = UUID.randomUUID().toString().substring(0, 4);
            //发送短信
            System.out.println("您手机号: "+phoneNumber + "  ,验证码为： "+ verifyCode);

            //把手机号码，验证码，发送时间装配到vo中并保存到session
            vo = new VerifyCodeVO();
            vo.setVerifyCode(verifyCode);
            vo.setLastSendTime(new Date());
            vo.setPhoneNumber(phoneNumber);
            UserContext.putVerifyCode(vo);
        }  else {
            throw new RuntimeException("发送过于频繁！");
        }
    }

    @Override
    public boolean verify(String phoneNumber, String verifyCode) {
        VerifyCodeVO vo = UserContext.getCurrentVerifyCode();
        if(vo != null
                && vo.getPhoneNumber().equals(phoneNumber)
                && vo.getVerifyCode().equalsIgnoreCase(verifyCode)
                && DateUtil.secondsBetween(new Date(), vo.getLastSendTime()) <= BidConst.VERIFYCODE_VAILDATE_SECOND) {
            return true;
        }
        return false;
    }
}
