package com.xmg.p2p.base.util;

import com.xmg.p2p.base.domain.Logininfo;
import com.xmg.p2p.base.vo.VerifyCodeVO;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author wlm
 * @date 2021/9/2 - 19:30
 */
public class UserContext {

    public static final String USER_IN_SESSION = "logininfo";
    public static final String VERIFYCODE_IN_SESSION = "verifycode_in_session";

    private static HttpSession getSession(){
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }

    public static void putCurrent(Logininfo current) {
        // 得到session,并把current放到session中
        getSession().setAttribute(USER_IN_SESSION, current);
    }

    public static Logininfo getCurrent() {
        return (Logininfo) getSession().getAttribute(USER_IN_SESSION);
    }

    public static void putVerifyCode(VerifyCodeVO vc) {
        getSession().setAttribute(VERIFYCODE_IN_SESSION, vc);
    }

    /**
     * 得到当前的短信验证码
     *
     * @return
     */
    public static VerifyCodeVO getCurrentVerifyCode() {
        return (VerifyCodeVO) getSession().getAttribute(VERIFYCODE_IN_SESSION);
    }
}
