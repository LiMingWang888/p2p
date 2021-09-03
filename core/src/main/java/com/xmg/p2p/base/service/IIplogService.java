package com.xmg.p2p.base.service;

import com.xmg.p2p.base.query.IplogQueryObject;
import com.xmg.p2p.base.query.PageResult;

import java.util.Date;

/**
 * @author wlm
 * @date 2021/9/3 - 17:43
 */
public interface IIplogService {

    PageResult query(IplogQueryObject qo);

    Date selectLastLoginTime(String username);
}
