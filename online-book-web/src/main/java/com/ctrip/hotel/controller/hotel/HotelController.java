package com.ctrip.hotel.controller.hotel;

import com.ctrip.hotel.domain.hotel.Hotel;
import com.ctrip.hotel.domain.room.RoomPrice;
import com.ctrip.hotel.model.bo.RoomInfoBo;

import com.ctrip.hotel.service.IHotelRecomendService;
import com.ctrip.hotel.service.IHotelService;
import com.ctrip.hotel.service.IRoomPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Autowired
    private IHotelRecomendService hotelRecomendService;

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


    /**
     *
     * 功能描述: 低价结果-阶段一
     */

    @PostMapping("/hotelSortByPrice")
    public ModelAndView hotelSortByPrice(String startDate,String endDate) throws ParseException {
        if(StringUtils.isEmpty(startDate)||StringUtils.isEmpty(endDate)){
            return new ModelAndView("hotel/hotel_list","hotels",null);
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        List<Hotel> hotels = hotelRecomendService.getHotelsByPrice( sf.parse(startDate),  sf.parse(endDate), 0, 100);
        return new ModelAndView("hotel/hotel_list","hotels",hotels);
    }

}
