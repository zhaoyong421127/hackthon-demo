package com.ctrip.hotel.dao;

import com.ctrip.hotel.domain.room.RoomPrice;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * @author zhao.yong
 * @datetime 2018/10/24
 **/
public interface RoomPriceRepository extends CrudRepository<RoomPrice,Long>{

    List<RoomPrice> queryRoomPricesByEffectDateAndRoomIdIsIn(Date effectDate,List<Long> roomId);
}
