package com.ctrip.hotel.dao;

import com.ctrip.hotel.domain.room.RoomPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

/**
 * @author zhao.yong
 * @datetime 2018/10/24
 **/
public interface RoomPriceRepository extends PagingAndSortingRepository<RoomPrice,Long>{

    List<RoomPrice> queryRoomPricesByEffectDateAndRoomIdIsIn(Date effectDate,List<Long> roomId);
    /*
    *根据酒店id按照价格排序
    * */
    List<RoomPrice> findRoomPriceByHotelIdOrderByRoomPrice(Long hotelId);

    /*
    * 根据入住日期过滤并排序
    * */
    /*@Query("select * from hackthondb.t_roomprice where effect_date = ?1 order by room_price limit 0,1000")
    List<RoomPrice> findRoomPricesByEffectDate(Date effectDay);*/

    Page<RoomPrice> findTopByEffectDateEqualsOrderByRoomPrice(Pageable page, Date effectDay);

    List<RoomPrice> findRoomPricesByEffectDateInAndHotelIdEqualsOrderByRoomPrice(List<Date> date,Long hotelId);

}
