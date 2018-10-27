package com.ctrip.hotel.service.impl;

import com.ctrip.hotel.dao.HotelRepository;
import com.ctrip.hotel.domain.hotel.Hotel;
import com.ctrip.hotel.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.Iterator;
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
            PageRequest pageable = PageRequest.of(0,100);
             Page<Hotel> pages =  hotelRepository.findAll(pageable);
             return pages.getContent();
        }
        return hotelRepository.queryHotelsByHotelNameLike(hotelName);
    }

    @Override
    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> queryHotelByIds(List<Long> ids) {
        return (List<Hotel>) hotelRepository.findAllById(ids);
    }

    public List<Hotel> queryAllHotels(){
        return (List<Hotel>) hotelRepository.findAll();
    }
}
