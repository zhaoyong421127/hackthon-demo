package com.ctrip.hotel.entiy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: w_chang
 * @Date: 2018/10/27 15:09
 * @Description:
 */
@Component
public class HotelResult {
    private Long hotelId;
    private BigDecimal sum;

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
