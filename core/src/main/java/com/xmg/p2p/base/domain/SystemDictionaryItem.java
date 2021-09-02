package com.xmg.p2p.base.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 数据字典明细
 * @author wlm
 * @date 2021/9/2 - 22:46
 */
@Setter
@Getter
public class SystemDictionaryItem extends BaseDomain{

    private Long parentId;

    private String title;
    private int sequence;
}
