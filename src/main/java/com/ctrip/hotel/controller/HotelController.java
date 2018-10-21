package com.ctrip.hotel.controller;

import com.ctrip.hotel.model.hotel.Hotel;
import com.ctrip.hotel.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhao.yong
 * @datetime 2018/10/19
 **/
@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private IHotelService hotelService;

    @RequestMapping("/getHotelById/{hotelId}")
    public Hotel getHotelById(@PathVariable Long hotelId) {
        return hotelService.queryHotelById(hotelId);
    }

    @PostMapping("/saveHotel")
    public Hotel saveHotel(Hotel hotel){
        return hotelService.saveHotel(hotel);
    }
}
