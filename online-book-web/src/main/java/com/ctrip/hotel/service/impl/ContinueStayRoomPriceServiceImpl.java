package com.ctrip.hotel.service.impl;

import com.ctrip.hotel.dao.ContinueStayRoomPriceRepository;
import com.ctrip.hotel.dao.RoomPriceRepository;
import com.ctrip.hotel.domain.room.ContinusStayRoomPrice;
import com.ctrip.hotel.domain.room.RoomPrice;
import com.ctrip.hotel.service.IContinueStayRoomPriceService;
import com.ctrip.hotel.service.IRoomPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author cw
 * @datetime 2018/10/27
 **/
@Service
public class ContinueStayRoomPriceServiceImpl implements IContinueStayRoomPriceService {
    @Autowired
    private ContinueStayRoomPriceRepository ContinueStayRoomPriceRepository;

    @Override
    public List<ContinusStayRoomPrice> queryContinueStayRoomPriceListByPrice(Date effectDay,Integer stayDays) {
        Pageable pageable = new PageRequest(0,10);
        return ContinueStayRoomPriceRepository.findTopByEffectDateEqualsAndStayDaysLessThanEqualOrderByStayPrice(pageable,effectDay,stayDays);
    }

    @Override
    public List<ContinusStayRoomPrice> queryStayPrices(Date startDate, Date endDate) {
        return ContinueStayRoomPriceRepository.queryContinusStayRoom(startDate,endDate);
    }
}
