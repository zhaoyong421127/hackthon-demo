package com.ctrip.hotel.domain.room;

import com.ctrip.hotel.domain.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 定价计划
 * @author zhao.yong
 * @datetime 2018/10/23
 **/
@Setter
@Getter
@Entity
@Table(name="t_rateplan")
public class RatePlan extends BaseModel {

    /**
     * 房型ID
     */
    private Long roomId;

    /**
     * 连住天数
     */
    private Long continusStayDays;

    /**
     * 连住优惠比例
     */
    private BigDecimal discountRatio;
}
