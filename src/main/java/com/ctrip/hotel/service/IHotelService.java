package com.ctrip.hotel.service;

import com.ctrip.hotel.model.Hotel;

/**
 * 酒店服务
 * @author zhao.yong
 * @datetime 2018/10/19
 **/
public interface IHotelService {

    Hotel queryHotelById(Long hotelId);

    Hotel saveHotel(Hotel hotel);
}
