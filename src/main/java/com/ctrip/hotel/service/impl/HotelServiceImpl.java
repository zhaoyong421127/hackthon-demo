package com.ctrip.hotel.service.impl;

import com.ctrip.hotel.dao.HotelRepository;
import com.ctrip.hotel.model.hotel.Hotel;
import com.ctrip.hotel.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }
}
