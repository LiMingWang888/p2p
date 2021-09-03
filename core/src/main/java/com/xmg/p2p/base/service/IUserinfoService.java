package com.xmg.p2p.base.service;

import com.xmg.p2p.base.domain.Userinfo;

/**
 * @author wlm
 * @date 2021/9/2 - 23:28
 */
public interface IUserinfoService {
    /**
     * 写完mapper之后立刻写service，因为这个update是支持乐观锁的
     */
    void update(Userinfo userinfo);

    void add(Userinfo userinfo);

    Userinfo get(Long id);

    void bindPhone(String phoneNumber, String verifyCode);
}
