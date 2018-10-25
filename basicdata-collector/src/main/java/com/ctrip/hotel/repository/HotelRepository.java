package com.ctrip.hotel.repository;

import com.ctrip.hotel.domain.hotel.Hotel;
import org.springframework.data.repository.CrudRepository;

/**
 * @author zhao.yong
 * @datetime 2018/10/25
 **/
public interface HotelRepository extends CrudRepository<Hotel,Long>{

}
