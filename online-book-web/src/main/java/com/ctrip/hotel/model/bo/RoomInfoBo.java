package com.ctrip.hotel.model.bo;

import com.ctrip.hotel.domain.room.Room;
import com.ctrip.hotel.domain.room.RoomPrice;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author zhao.yong
 * @datetime 2018/10/24
 **/
@Setter
@Getter
public class RoomInfoBo {

    private Room room;
    private List<RoomPrice> roomPriceList;

    public RoomInfoBo(Room room, List<RoomPrice> roomPriceList) {
        this.room = room;
        this.roomPriceList = roomPriceList;
    }
}
