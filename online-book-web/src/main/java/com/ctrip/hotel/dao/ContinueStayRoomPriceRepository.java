package com.ctrip.hotel.dao;

import com.ctrip.hotel.domain.room.ContinusStayRoomPrice;
import com.ctrip.hotel.domain.room.RoomPrice;
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
public interface ContinueStayRoomPriceRepository extends PagingAndSortingRepository<ContinusStayRoomPrice,Long> {
    /*
    * 根据入住日期过滤，并按照单天价格进行排序
    * */
    /*@Query(" select * from hackthondb.t_stay_roomprice where effect_date = ?1 and stay_days <= ?2 order by stay_price limit 0,50000")
    List<ContinusStayRoomPrice> findContinusStayRoomPricesByEffectDateEqualsAndStayDaysLessThanEqualOrderByStayPrice(Date effectDay,Integer stayDays);
*/
    List<ContinusStayRoomPrice> findTopByEffectDateEqualsAndStayDaysLessThanEqualOrderByStayPrice(Pageable page,Date effectDate,Integer stayDays );


    @Query(value ="SELECT * FROM hackthondb.t_stay_roomprice where effect_date>=?1 and date_add(effect_date,interval stay_days day)<?2 ",nativeQuery = true)
    List<ContinusStayRoomPrice > queryContinusStayRoom(Date start, Date end);

}
