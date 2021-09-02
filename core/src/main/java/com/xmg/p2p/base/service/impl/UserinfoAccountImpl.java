package com.xmg.p2p.base.service.impl;

import com.xmg.p2p.base.domain.Userinfo;
import com.xmg.p2p.base.mapper.UserinfoMapper;
import com.xmg.p2p.base.service.IUserinfoAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wlm
 * @date 2021/9/2 - 23:29
 */
@Service
public class UserinfoAccountImpl implements IUserinfoAccount {

    @Autowired
    private UserinfoMapper userinfoMapper;

    @Override
    public void update(Userinfo userinfo) {
        int ret = this.userinfoMapper.updateByPrimaryKey(userinfo);
        if(ret == 0){
            throw new RuntimeException("乐观锁失败，Account: "+userinfo.getId());
        }
    }
}
