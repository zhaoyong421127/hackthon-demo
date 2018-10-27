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
public class HotelSingleInfo {
    private Long hotelId;
    private String hotelName;
    private Long roomId;
    private BigDecimal roomPrice;
    private BigDecimal costPrice;
    private Integer stayDays;
    private BigDecimal stayPrice;
    private Date effectDate;

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public BigDecimal getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(BigDecimal roomPrice) {
        this.roomPrice = roomPrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getStayDays() {
        return stayDays;
    }

    public void setStayDays(Integer stayDays) {
        this.stayDays = stayDays;
    }

    public BigDecimal getStayPrice() {
        return stayPrice;
    }

    public void setStayPrice(BigDecimal stayPrice) {
        this.stayPrice = stayPrice;
    }

    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
}
