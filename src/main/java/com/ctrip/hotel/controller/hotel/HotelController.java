package com.ctrip.hotel.controller.hotel;

import com.ctrip.hotel.model.hotel.Hotel;
import com.ctrip.hotel.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 酒店Web控制器
 * @author zhao.yong
 * @datetime 2018/10/19
 **/
@Controller
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private IHotelService hotelService;

    @RequestMapping("/getHotelById/{hotelId}")
    @ResponseBody
    public Hotel getHotelById(@PathVariable Long hotelId) {
        return hotelService.queryHotelById(hotelId);
    }

    /**
     * 根据酒店ID查询酒店
     * @param hotelId
     * @param model
     * @return
     */
    @RequestMapping("/getHotel/{hotelId}")
    public String getHotel(@PathVariable Long hotelId, Model model) {
        Hotel hotel = hotelService.queryHotelById(hotelId);
        model.addAttribute("hotel",hotel);
        return "hotel/hotel_detail";
    }

    /**
     * 获取酒店列表
     * @param hotelName
     * @return
     */
    @RequestMapping("/getHotelList")
    public ModelAndView getHotelList(String hotelName){
        List<Hotel> hotels = hotelService.queryHotelList(hotelName);
        return new ModelAndView("hotel/hotel_list","hotels",hotels);
    }


    /**
     * 保存酒店
     * @param hotel
     * @return
     */
    @PostMapping("/saveHotel")
    public Hotel saveHotel(Hotel hotel){
        return hotelService.saveHotel(hotel);
    }
}
