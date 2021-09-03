package com.xmg.mgrsite.base;

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
 * @date 2021/9/3 - 20:31
 */
@Controller
public class LoginController {

    @Autowired
    private ILogininfoService logininfoService;

    @RequestMapping("login")
    @ResponseBody
    public JSONResult login(String username, String password,
            HttpServletRequest request){
        JSONResult result = new JSONResult();
        Logininfo current = this.logininfoService.login(username, password,
                request.getRemoteAddr(), Logininfo.USER_MANAGER);
        if (current == null) {
            result.setSuccess(false);
            result.setMsg("用户名或密码错误");
        }
        return result;
    }

    /**
     * 后台首页
     */
    @RequestMapping("index")
    public String index(){
        return "main";
    }
}
