package com.ctrip.hotel.domain.room;

import com.ctrip.hotel.domain.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhao.yong
 * @description 房价数据
 * @createtime 2018/10/21 16:57
 */
@Setter
@Getter
@Table(name="t_roomprice")
@Entity
public class RoomPrice extends BaseModel {

    public Long hotelId;

    public String hotelName;

    public Long masterHotelId;

    public Integer cityId;

    public Integer star;

    public Long roomId;

    /**
     * 售卖价
     */
    @Column(scale = 2)
    public BigDecimal roomPrice;

    /**
     * 成本价
     */
    public BigDecimal costPrice;

    /**
     * 入住人数
     */
    public Integer persons;

    @Temporal(TemporalType.DATE)
    public Date effectDate;

    /**
     * 售卖渠道  CTRIP,B2B,QUNAR,ELONG
     */
    public String saleChannel;

    /**
     * 床型
     */
    public String bedType;

    /**
     * 早餐
     */
    public String breakfast;
    /**
     * 无线
     */
    public String wireless;
    /**
     * 政策
     */
    public String policy;



}
