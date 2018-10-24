package com.ctrip.hotel.model.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zhao.yong
 * @description
 * @createtime 2018-10-2018/10/21 16:52
 */
@Setter
@Getter
@MappedSuperclass
public class BaseModel {

    /**
     * 数据表主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    /**
     * 更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}