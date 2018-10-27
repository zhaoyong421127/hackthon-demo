package com.ctrip.hotel.domain.room;

import com.ctrip.hotel.domain.BaseModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 连住房价表
 * @author zhao.yong
 * @datetime 2018/10/27
 **/
@Entity
@Setter
@Getter
@Table(name = "t_stay_roomprice")
public class ContinusStayRoomPrice extends BaseModel{

    public Long hotelId;

    public Integer cityId;

    public Long masterHotelId;

    public String hotelName;
    public Long roomId;
    public Integer star;
    @Temporal(TemporalType.DATE)
    public Date effectDate;
    public Integer persons;
    @Column(scale = 2)
    public BigDecimal stayPrice;
    @Column(scale = 2)
    public BigDecimal stayCostPrice;
    public Integer stayDays;

}
