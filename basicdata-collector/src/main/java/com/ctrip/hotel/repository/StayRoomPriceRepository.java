package com.ctrip.hotel.repository;

import com.ctrip.hotel.domain.room.ContinusStayRoomPrice;
import com.ctrip.hotel.domain.room.RoomPrice;
import org.springframework.data.repository.CrudRepository;

/**
 * @author zhao.yong
 * @datetime 2018/10/27
 **/
public interface StayRoomPriceRepository extends CrudRepository<ContinusStayRoomPrice,Long>{
}
