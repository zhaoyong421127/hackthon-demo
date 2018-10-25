package com.ctrip.hotel.service.impl;

import com.ctrip.hotel.dao.RoomPriceRepository;
import com.ctrip.hotel.domain.room.RoomPrice;
import com.ctrip.hotel.service.IRoomPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zhao.yong
 * @datetime 2018/10/24
 **/
@Service
public class RoomPriceServiceImpl implements IRoomPriceService {
    @Autowired
    private RoomPriceRepository roomPriceRepository;

    @Override
    public List<RoomPrice> queryRoomPrice(Date effectDate, List<Long> roomIds) {
        return roomPriceRepository.queryRoomPricesByEffectDateAndRoomIdIsIn(effectDate,roomIds);
    }
}
