package com.ctrip.hotel.service.impl;

import com.ctrip.hotel.dao.HotelRepository;
import com.ctrip.hotel.domain.hotel.Hotel;
import com.ctrip.hotel.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author zhao.yong
 * @datetime 2018/10/19
 **/
@Service
public class HotelServiceImpl implements IHotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel queryHotelById(Long hotelId) {
        return hotelRepository.findById(hotelId).get();
    }

    @Override
    public List<Hotel> queryHotelList(String hotelName) {
        if(StringUtils.isEmpty(hotelName)){
            return (List<Hotel>) hotelRepository.findAll();
        }
        return hotelRepository.queryHotelsByHotelNameLike(hotelName);
    }

    @Override
    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }
}
