package com.xmg.p2p.base.service.impl;

import com.xmg.p2p.base.domain.Logininfo;
import com.xmg.p2p.base.mapper.LogininfoMapper;
import com.xmg.p2p.base.service.ILogininfoService;
import com.xmg.p2p.base.util.MD5;
import com.xmg.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wlm
 * @date 2021/9/2 - 15:44
 */
@Service
public class LogininfoServiceImpl implements ILogininfoService {

    @Autowired
    private LogininfoMapper logininfoMapper;

    @Override
    public void register(String username, String password) {
        //查询是否已注册
        int count = this.logininfoMapper.getCountByUsername(username);
        if (count <= 0){
            Logininfo li = new Logininfo();
            li.setUsername(username);
            li.setPassword(password);
            li.setState(Logininfo.STATE_NORMAL);
            this.logininfoMapper.insert(li);
        } else {
            throw new RuntimeException("用户名已经存在!");
        }
    }

    @Override
    public boolean checkUsername(String username, String password) {
        return this.logininfoMapper.getCountByUsername(username)>0;
    }

    @Override
    public void login(String username, String password) {
        Logininfo current = this.logininfoMapper.login(username,
                MD5.encode(password));

        //放到UserContext里
        if (current != null) {
            UserContext.putCurrent(current);
        }else {
            throw new RuntimeException("用户名或密码错误!");
        }
    }
}
