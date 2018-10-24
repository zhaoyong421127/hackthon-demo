package com.ctrip.hotel.dao;

import com.ctrip.hotel.model.bo.HotelDetailBo;
import com.ctrip.hotel.model.domain.hotel.Hotel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/**
 * 酒店DAO操作接口
 */
public interface HotelRepository extends CrudRepository<Hotel,Long> {

     List<Hotel> queryHotelsByHotelNameLike(String hotelName);
}
