package com.xmg.mgrsite.base;

import com.xmg.p2p.base.query.IplogQueryObject;
import com.xmg.p2p.base.query.PageResult;
import com.xmg.p2p.base.service.IIplogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wlm
 * @date 2021/9/3 - 21:32
 */
@Controller
public class IplogController {

    @Autowired
    private IIplogService iplogService;

    @RequestMapping("ipLog")
    public String ipLog(@ModelAttribute("qo")IplogQueryObject qo, Model model){
        PageResult result = this.iplogService.query(qo);
        model.addAttribute("pageResult", result);
        return "ipLog/list";
    }
}
