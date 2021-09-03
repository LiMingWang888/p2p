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

    private Long parentId;  //对应的SystemDictionary ID

    private String title;   //显示名称
    private int sequence;   //在该分类中的排序
}
