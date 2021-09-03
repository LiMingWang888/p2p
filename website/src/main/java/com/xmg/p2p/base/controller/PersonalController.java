package com.xmg.p2p.base.controller;

import com.xmg.p2p.base.domain.Logininfo;
import com.xmg.p2p.base.service.IAccountService;
import com.xmg.p2p.base.service.IUserinfoService;
import com.xmg.p2p.base.util.JSONResult;
import com.xmg.p2p.base.util.RequireLogin;
import com.xmg.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wlm
 * @date 2021/9/3 - 15:04
 */
@Controller
public class PersonalController {

    @Autowired
    private IUserinfoService userinfoService;

    @Autowired
    private IAccountService accountService;

    @RequireLogin
    @RequestMapping("/personal")
    public String personalCenter(Model model){
        Logininfo current = UserContext.getCurrent();
        model.addAttribute("userinfo", this.userinfoService.get(current.getId()));
        model.addAttribute("account", this.accountService.get(current.getId()));

        return "personal";
    }

    @RequireLogin
    @ResponseBody
    @RequestMapping("bindPhone")
    public JSONResult bindPhone(String phoneNumber, String verifyCode){
        JSONResult result = new JSONResult();
        try {
            this.userinfoService.bindPhone(phoneNumber, verifyCode);
        } catch (RuntimeException e){
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
