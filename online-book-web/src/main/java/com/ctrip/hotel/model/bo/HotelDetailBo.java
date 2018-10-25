package com.ctrip.hotel.model.bo;

import com.ctrip.hotel.domain.hotel.Hotel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhao.yong
 * @datetime 2018/10/24
 **/
@Setter
@Getter
public class HotelDetailBo {

    private Hotel hotel;

    private RoomInfoBo roomInfoBo;

    public HotelDetailBo(Hotel hotel, RoomInfoBo roomInfoBo) {
        this.hotel = hotel;
        this.roomInfoBo = roomInfoBo;
    }
}
