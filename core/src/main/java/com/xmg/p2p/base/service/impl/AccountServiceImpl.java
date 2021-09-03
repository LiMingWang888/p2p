package com.xmg.p2p.base.service.impl;

import com.xmg.p2p.base.domain.Account;
import com.xmg.p2p.base.mapper.AccountMapper;
import com.xmg.p2p.base.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wlm
 * @date 2021/9/2 - 23:25
 */
@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void update(Account account) {
        int ret = this.accountMapper.updateByPrimaryKey(account);
        if(ret == 0){
            throw new RuntimeException("乐观锁失败，Account: "+account.getId());
        }
    }

    @Override
    public void add(Account account) {
        this.accountMapper.insert(account);
    }

    @Override
    public Account get(Long id) {
        return accountMapper.selectByPrimaryKey(id);
    }
}
