package com.ctrip.hotel.model.domain.room;

import com.ctrip.hotel.model.domain.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 房型数据模型
 */
@Setter
@Getter
@Table(name="t_room")
@Entity
public class Room  extends BaseModel {

    /**
     * 酒店ID
     */
    private Long hotelId;

    /**
     * 房型名称
     */
    private String roomName;

    /**
     * 房型图片
     */
    public String roomImgUrl;

}
