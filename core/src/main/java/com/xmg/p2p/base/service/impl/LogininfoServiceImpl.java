package com.xmg.p2p.base.service.impl;

import com.xmg.p2p.base.domain.Account;
import com.xmg.p2p.base.domain.Iplog;
import com.xmg.p2p.base.domain.Logininfo;
import com.xmg.p2p.base.domain.Userinfo;
import com.xmg.p2p.base.mapper.IplogMapper;
import com.xmg.p2p.base.mapper.LogininfoMapper;
import com.xmg.p2p.base.service.IAccountService;
import com.xmg.p2p.base.service.ILogininfoService;
import com.xmg.p2p.base.service.IUserinfoService;
import com.xmg.p2p.base.util.BidConst;
import com.xmg.p2p.base.util.MD5;
import com.xmg.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author wlm
 * @date 2021/9/2 - 15:44
 */
@Service
public class LogininfoServiceImpl implements ILogininfoService {

    @Autowired
    private LogininfoMapper logininfoMapper;

    @Autowired
    private IUserinfoService userinfoService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IplogMapper iplogMapper;

    @Override
    public void register(String username, String password) {
        //查询是否已注册
        int count = this.logininfoMapper.getCountByUsername(username);
        if (count <= 0){
            Logininfo li = new Logininfo();
            li.setUsername(username);
            li.setPassword(MD5.encode(password));
            li.setState(Logininfo.STATE_NORMAL);
            li.setUserType(Logininfo.USER_CLIENT);
            this.logininfoMapper.insert(li);

            //初始化账户信息和userinfo
            Account account = new Account();
            account.setId(li.getId());
            this.accountService.add(account);

            Userinfo userinfo = new Userinfo();
            userinfo.setId(li.getId());
            this.userinfoService.add(userinfo);
        } else {
            throw new RuntimeException("用户名已经存在!");
        }
    }

    @Override
    public boolean checkUsername(String username, String password) {
        return this.logininfoMapper.getCountByUsername(username)>0;
    }

    @Override
    public Logininfo login(String username, String password, String ip, int userType) {
        Logininfo current = this.logininfoMapper.login(username,
                MD5.encode(password), userType);
        Iplog iplog = new Iplog();
        iplog.setIp(ip);
        iplog.setUserName(username);
        iplog.setLoginTime(new Date());
        iplog.setUserType(userType);

        //放到UserContext里
        if (current != null) {
            UserContext.putCurrent(current);
            iplog.setState(Iplog.STATE_SUCCESS);
        }else {
            iplog.setState(Iplog.STATE_FAILED);
        }
        iplogMapper.insert(iplog);
        return current;
    }

    @Override
    public void initAdmin() {
        int count = this.logininfoMapper.getCountByUserType(Logininfo.USER_MANAGER);
        if (count == 0) {
            Logininfo admin = new Logininfo();
            admin.setUsername(BidConst.DEFAULT_ADMIN_NAME);
            admin.setPassword(MD5.encode(BidConst.DEFAULT_ADMIN_PASSWORD));
            admin.setUserType(Logininfo.USER_MANAGER);
            admin.setState(Logininfo.STATE_NORMAL);
            this.logininfoMapper.insert(admin);
        }
    }

}
