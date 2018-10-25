package com.ctrip.hotel.service;


import com.ctrip.hotel.domain.hotel.Hotel;

import java.util.List;

/**
 * 酒店服务
 * @author zhao.yong
 * @datetime 2018/10/19
 **/
public interface IHotelService {

    Hotel queryHotelById(Long hotelId);

    List<Hotel> queryHotelList(String hotelName);

    Hotel saveHotel(Hotel hotel);
}
