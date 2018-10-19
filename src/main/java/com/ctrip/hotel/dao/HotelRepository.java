package com.ctrip.hotel.dao;

import com.ctrip.hotel.model.Hotel;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<Hotel,Long> {

}
