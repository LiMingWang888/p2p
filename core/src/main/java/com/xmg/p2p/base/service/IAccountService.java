package com.xmg.p2p.base.service;

import com.xmg.p2p.base.domain.Account;

/**
 * @author wlm
 * @date 2021/9/2 - 23:24
 */
public interface IAccountService {

    /**
     * 写完mapper之后立刻写service，因为这个update是支持乐观锁的
     */
    void update(Account account);

    void add(Account account);

    Account get(Long id);
}
