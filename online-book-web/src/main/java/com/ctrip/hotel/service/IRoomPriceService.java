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
    /*
    * 按照酒店id查找房型对应的价格从低到高排序
    * */
    List<RoomPrice> queryHotelRoomPriceListByHotelId(Long hotelId);

    /*
    *按照入住时间筛选并按照价格排序出酒店单价
    * */
    List<RoomPrice> queryHotelRoomPriceListByPrice(Date effectDay);

    /*
    * 根据房型id查询价格列表
    * */
    public List<RoomPrice> queryRoomPriceByRoomid(List<Date> date,Long hotelId);

}
