package com.ctrip.hotel.domain.hotel;

import com.ctrip.hotel.domain.BaseModel;
import com.ctrip.hotel.domain.room.Room;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;



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
     * 母酒店ID
     */
    public Long masterHotelId;
    /**
     * 酒店名称
     */
    public String hotelName;
    /**
     * 酒店英文名
     */
    public String hotelEname;
    /**
     * 酒店星级
     */
    public Integer star;
    /**
     * 酒店所属城市名
     */
    public String cityName;

    /**
     * 酒店标题
     */
    public String hotelTitle;

    /**
     * 售卖级别
     */
    public String saleLevel;

    /**
     * 品质保障
     */
    public String qualityPromise;

    /**
     * 城市ID
     */
    public Integer cityId;
    /**
     * 酒店描述
     */
    @Column(name="description")
    public String desc;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "hotelId")
    public List<Room> roomList;


    @Override
    public boolean equals(Object obj) {
        Hotel hotel = (Hotel) obj;

        return hotel.masterHotelId.equals(this.masterHotelId) && hotel.id.equals(this.id);
    }
}
