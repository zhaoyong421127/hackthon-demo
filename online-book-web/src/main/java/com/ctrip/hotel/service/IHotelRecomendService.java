package com.ctrip.hotel.service;


import com.ctrip.hotel.domain.hotel.Hotel;

import java.util.Date;
import java.util.List;

/**
 * 酒店服务
 * @author cw
 * @datetime 2018/10/27
 **/
public interface IHotelRecomendService {
    List<Hotel> getHotelsByPrice(Date startDate, Date endDate,int fromIndex,int pageSize);

}
