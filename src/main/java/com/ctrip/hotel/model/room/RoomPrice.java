package com.ctrip.hotel.model.room;

import com.ctrip.hotel.model.BaseModel;
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

    private Long hotelId;

    private Long roomId;

    @Column(scale = 2)
    private BigDecimal roomPrice;

    @Temporal(TemporalType.TIMESTAMP)
    private Date effectDate;

    /**
     * 售卖渠道  CTRIP,B2B,QUNAR,ELONG
     */
    private String saleChannel;

}
