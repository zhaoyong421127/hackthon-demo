package com.ctrip.hotel.service;


import com.ctrip.hotel.domain.room.ContinusStayRoomPrice;
import com.ctrip.hotel.domain.room.RoomPrice;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @author cw
 * @datetime 2018/10/27
 **/
public interface IContinueStayRoomPriceService {
    /*
    * 根据入住时间过滤并按价格排序排序
    * */

    List<ContinusStayRoomPrice> queryContinueStayRoomPriceListByPrice(Date effectDay,Integer stayDays);

    List<ContinusStayRoomPrice> queryStayPrices(Date effectDay,Date endDate);

}
