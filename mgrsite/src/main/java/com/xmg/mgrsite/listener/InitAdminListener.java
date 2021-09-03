package com.xmg.mgrsite.listener;

import com.xmg.p2p.base.service.ILogininfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author wlm
 * @date 2021/9/3 - 21:02
 */
@Component
public class InitAdminListener
        implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ILogininfoService logininfoService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.logininfoService.initAdmin();
    }
}
