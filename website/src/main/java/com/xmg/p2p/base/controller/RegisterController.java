package com.xmg.p2p.base.controller;

import com.xmg.p2p.base.domain.Logininfo;
import com.xmg.p2p.base.service.ILogininfoService;
import com.xmg.p2p.base.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wlm
 * @date 2021/9/2 - 16:13
 */
@Controller
public class RegisterController {

    @Autowired
    private ILogininfoService logininfoService;

    @RequestMapping("register")
    @ResponseBody
    public JSONResult register(String username, String password){
        JSONResult result = new JSONResult();
        try {
            this.logininfoService.register(username, password);
        } catch (RuntimeException e) {
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;

    }

    @RequestMapping("checkUsername")
    @ResponseBody
    public boolean checkUsername(String username, String password){
        return !logininfoService.checkUsername(username, password);
    }

    @RequestMapping("login")
    @ResponseBody
    public JSONResult login(String username, String password,
            HttpServletRequest request){
        JSONResult result = new JSONResult();
        Logininfo current = this.logininfoService.login(username, password,
                request.getRemoteAddr(), Logininfo.USER_CLIENT);
        if (current == null) {
            result.setSuccess(false);
            result.setMsg("用户名或密码错误");
        }
        return result;
    }
}
