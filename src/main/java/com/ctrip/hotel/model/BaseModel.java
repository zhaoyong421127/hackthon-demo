package com.ctrip.hotel.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @author zhao.yong
 * @description
 * @createtime 2018-10-2018/10/21 16:52
 */
public class BaseModel {

    /**
     * 数据表主键
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.DATE)
    private Date createTime;

    /**
     * 更新时间
     */
    @Temporal(TemporalType.DATE)
    private Date updateTime;
}
