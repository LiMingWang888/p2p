package com.xmg.p2p.base.service;

import com.xmg.p2p.base.domain.Userinfo;

/**
 * @author wlm
 * @date 2021/9/2 - 23:28
 */
public interface IUserinfoAccount {
    /**
     * 写完mapper之后立刻写service，因为这个update是支持乐观锁的
     */
    void update(Userinfo userinfo);
}
