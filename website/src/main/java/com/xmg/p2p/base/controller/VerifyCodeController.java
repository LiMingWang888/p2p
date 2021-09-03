package com.xmg.p2p.base.controller;

import com.xmg.p2p.base.service.IVerifyCodeService;
import com.xmg.p2p.base.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wlm
 * @date 2021/9/3 - 22:59
 */
@Controller
public class VerifyCodeController {

    @Autowired
    private IVerifyCodeService verifyCodeService;


    @RequestMapping("sendVerifyCode")
    @ResponseBody
    public JSONResult sendVerifyCode(String phoneNumber){
        JSONResult result = new JSONResult();
        try {
            this.verifyCodeService.sendVerifyCode(phoneNumber);
        } catch (RuntimeException e) {
            result.setMsg(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }


}
