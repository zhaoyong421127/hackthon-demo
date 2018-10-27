package com.ctrip.hotel.dao;

import com.ctrip.hotel.domain.hotel.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 酒店DAO操作接口
 */
public interface HotelRepository extends PagingAndSortingRepository<Hotel,Long> {

     List<Hotel> queryHotelsByHotelNameLike(String hotelName);
}
