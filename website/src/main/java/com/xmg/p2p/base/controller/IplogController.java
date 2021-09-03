package com.xmg.p2p.base.controller;

import com.xmg.p2p.base.query.IplogQueryObject;
import com.xmg.p2p.base.service.IIplogService;
import com.xmg.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wlm
 * @date 2021/9/3 - 17:37
 */
@Controller
public class IplogController {

    @Autowired
    private IIplogService iplogService;

    @RequestMapping("ipLog")
    public String iplogList(@ModelAttribute("qo") IplogQueryObject qo, Model model){
        qo.setUsername(UserContext.getCurrent().getUsername());
        model.addAttribute("pageResult", this.iplogService.query(qo));
        return "iplog_list";
    }
}
