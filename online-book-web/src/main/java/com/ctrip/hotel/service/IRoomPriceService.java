package com.ctrip.hotel.service;


import com.ctrip.hotel.domain.room.RoomPrice;

import java.util.Date;
import java.util.List;

/**
 * @author zhao.yong
 * @datetime 2018/10/24
 **/
public interface IRoomPriceService {
    List<RoomPrice> queryRoomPrice(Date effectDate, List<Long> roomIds);
}
