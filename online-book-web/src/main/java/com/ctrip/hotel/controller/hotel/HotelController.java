package com.ctrip.hotel.controller.hotel;

import com.ctrip.hotel.dao.HotelRepository;
import com.ctrip.hotel.model.bo.HotelDetailBo;
import com.ctrip.hotel.model.bo.RoomInfoBo;
import com.ctrip.hotel.model.domain.hotel.Hotel;
import com.ctrip.hotel.model.domain.room.RoomPrice;
import com.ctrip.hotel.service.IHotelService;
import com.ctrip.hotel.service.IRoomPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private IRoomPriceService roomPriceService;

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
        List<Long> roomIds = hotel.roomList.stream().map(room -> room.id).collect(Collectors.toList());
        List<RoomPrice> roomPrices = roomPriceService.queryRoomPrice(new Date(), roomIds);
        List<RoomInfoBo> roomInfoBos = new LinkedList<>();
        hotel.roomList.forEach(room -> {
            List<RoomPrice> priceList = roomPrices.stream().filter(roomPrice -> roomPrice.roomId.equals(room.id)).collect(Collectors.toList());
            RoomInfoBo roomInfoBo = new RoomInfoBo(room,priceList);
            roomInfoBos.add(roomInfoBo);
        });
        model.addAttribute("hotel",hotel);
        model.addAttribute("roomInfos",roomInfoBos);
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
