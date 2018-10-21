package com.ctrip.hotel.dao;

import com.ctrip.hotel.model.hotel.Hotel;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<Hotel,Long> {

}
