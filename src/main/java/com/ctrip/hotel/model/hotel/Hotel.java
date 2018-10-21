package com.ctrip.hotel.model.hotel;

import com.ctrip.hotel.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * 酒店数据
 * @author zhao.yong
 * @datetime 2018/10/19
 **/
@Setter
@Getter
@Entity
@Table(name="t_hotel")
public class Hotel extends BaseModel {

    /**
     * 酒店名称
     */
    private String hotelName;
    /**
     * 酒店星级
     */
    private Integer star;

    /**
     * 酒店描述
     */
    @Column(name="description")
    private String desc;


}
