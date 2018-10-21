package com.ctrip.hotel.model.hotel;

import com.ctrip.hotel.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 酒店入住点评
 * @author zhao.yong
 * @datetime 2018/10/19
 **/
@Setter
@Getter
@Entity
@Table(name="t_hotel_ratingscore")
public class HotelRatingScore  extends BaseModel {
    /**
     * 酒店ID
     */
    private Long hotelId;
    /**
     * 酒店入住点评用户ID
     */
    private Long userId;
    /**
     * 位置评分
     */
    private Double locationScore;
    /**
     * 设施评分
     */
    private Double facilityScore;

    /**
     * 服务评分
     */
    private Double serviceScore;

    /**
     * 卫生评分
     */
    private Double healthyScore;
}
