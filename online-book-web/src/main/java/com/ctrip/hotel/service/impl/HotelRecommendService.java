package com.ctrip.hotel.service.impl;

import com.ctrip.hotel.domain.hotel.Hotel;
import com.ctrip.hotel.domain.room.ContinusStayRoomPrice;
import com.ctrip.hotel.domain.room.RoomPrice;
import com.ctrip.hotel.entiy.HotelResult;
import com.ctrip.hotel.entiy.HotelSingleInfo;
import com.ctrip.hotel.service.IContinueStayRoomPriceService;
import com.ctrip.hotel.service.IHotelRecomendService;
import com.ctrip.hotel.service.IHotelService;
import com.ctrip.hotel.service.IRoomPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Auther: w_chang
 * @Date: 2018/10/27 10:46
 * @Description:
 */
@Service
public class HotelRecommendService implements IHotelRecomendService{
    @Autowired
    IHotelService hotelService;
    @Autowired
    IRoomPriceService roomPriceService;
    @Autowired
    IContinueStayRoomPriceService continueStayRoomPriceService;


    BigDecimal sum = BigDecimal.ZERO;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public List<Hotel> getHotelsByPrice(Date startDate,Date endDate,int fromIndex,int pageSize) {

        List<Hotel> singleHotelList= new ArrayList<Hotel>();
        List<Hotel> stayHotelList= new ArrayList<Hotel>();
        List<HotelSingleInfo> hotelSingleInfoList = new ArrayList<HotelSingleInfo>();
        //计算用户输入天数
        int days = calDays(startDate,endDate);
        //1.单天：查找单天价格表，并找到价格排序
        //2.连住：首先要拆单，先查找最大的连住，然后迭代拆单，计算总价最低排序
        if(days==0){
            return null;
        }
        if(days==1){
            List<RoomPrice> roomPrices = roomPriceService.queryHotelRoomPriceListByPrice(startDate);
            if(!CollectionUtils.isEmpty(roomPrices)){
                Collections.sort(roomPrices,new Comparator<RoomPrice>() {
                    @Override
                    public int compare(RoomPrice o1, RoomPrice o2) {
                        return o1.hotelId.compareTo(o2.hotelId);
                    }
                });
                roomPrices.forEach((item)->{
                    HotelSingleInfo info = new HotelSingleInfo();
                    info.setHotelId(item.hotelId);
                    info.setRoomId(item.roomId);
                    info.setCostPrice(item.costPrice);
                    info.setRoomPrice(item.roomPrice);
                    info.setStayDays(1);
                    info.setHotelName(item.hotelName);
                    hotelSingleInfoList.add(info);
                });
            }

            if(!CollectionUtils.isEmpty(hotelSingleInfoList)){
                List<RoomPrice> roomPriceList = new ArrayList<>();
                List<Long> hotelIdSet = new ArrayList<>();

                int size = hotelSingleInfoList.size();
                for(HotelSingleInfo item:hotelSingleInfoList){
                    size--;
                    if(!hotelIdSet.contains(item.getHotelId())){
                        hotelIdSet.add(item.getHotelId());
                    }

                    if(hotelIdSet.size()>1){
                        //转折
                        Hotel hotel = new Hotel();
                        hotel.id = hotelIdSet.get(0);
                        List<RoomPrice> tmpList = new ArrayList<>();
                        tmpList.addAll(roomPriceList);
                        hotel.roomPriceList = tmpList;
                        singleHotelList.add(hotel);
                        roomPriceList.clear();
                        hotelIdSet.remove(0);
                    }
                    RoomPrice roomPrice = new RoomPrice();
                    roomPrice.hotelId = item.getHotelId();
                    roomPrice.roomId = item.getRoomId();
                    roomPrice.effectDate = startDate;
                    roomPrice.costPrice = item.getCostPrice();
                    roomPrice.roomPrice = item.getRoomPrice();
                    roomPriceList.add(roomPrice);
                    //收尾最后的roomPriceList
                    if(size==0){
                        Hotel hotel = new Hotel();
                        hotel.id = item.getHotelId();
                        hotel.hotelName = item.getHotelName();
                        hotel.roomPriceList = roomPriceList;
                        singleHotelList.add(hotel);
                    }
                }

            }
        }else{//连住
            //List<ContinusStayRoomPrice> continusStayRoomPriceList = continueStayRoomPriceService.queryContinueStayRoomPriceListByPrice(startDate,endDate,days);
            List<ContinusStayRoomPrice> continusStayRoomPriceList = continueStayRoomPriceService.queryStayPrices(startDate,endDate);
            if(!CollectionUtils.isEmpty(continusStayRoomPriceList)){
                    Collections.sort(continusStayRoomPriceList,new Comparator<ContinusStayRoomPrice>() {
                        @Override
                        public int compare(ContinusStayRoomPrice o1, ContinusStayRoomPrice o2) {
                            return o1.hotelId.compareTo(o2.hotelId);
                        }
                    });
                continusStayRoomPriceList.forEach((item)->{
                    HotelSingleInfo info = new HotelSingleInfo();
                    info.setHotelId(item.hotelId);
                    info.setRoomId(item.roomId);
                    info.setStayPrice(item.stayPrice);
                    info.setStayDays(item.stayDays);
                    info.setEffectDate(item.effectDate);
                    info.setHotelName(item.hotelName);
                    hotelSingleInfoList.add(info);
                });
            }

            List<HotelResult> results = new ArrayList<>();
            if(!CollectionUtils.isEmpty(hotelSingleInfoList)){
                List<ContinusStayRoomPrice> stayRoomPriceList = new ArrayList<>();
                List<Long> hotelIdSet = new ArrayList<>();
                int length = hotelSingleInfoList.size();
                for(HotelSingleInfo item:hotelSingleInfoList){
                    length--;
                    if(!hotelIdSet.contains(item.getHotelId())){
                        hotelIdSet.add(item.getHotelId());
                    }
                    if(hotelIdSet.size()>1){
                        //转折
                        Hotel hotel = new Hotel();
                        hotel.id = hotelIdSet.get(0);
                        hotel.hotelName =item.getHotelName();
                        List<ContinusStayRoomPrice> tmpList = new ArrayList<>();
                        tmpList.addAll(stayRoomPriceList);
                        hotel.continusStayRoomPriceList = tmpList;
                        stayHotelList.add(hotel);
                        stayRoomPriceList.clear();
                        hotelIdSet.remove(0);
                    }
                    ContinusStayRoomPrice price = new ContinusStayRoomPrice();
                    price.hotelId = item.getHotelId();
                    price.effectDate = item.getEffectDate();
                    price.roomId = item.getRoomId();
                    price.stayDays = item.getStayDays();
                    price.stayPrice = item.getStayPrice();
                    stayRoomPriceList.add(price);
                    if(length==0){
                        Hotel hotel = new Hotel();
                        hotel.id = item.getHotelId();
                        hotel.continusStayRoomPriceList = stayRoomPriceList;
                        stayHotelList.add(hotel);
                    }
                }
            }

            //前提：连住价格比单住价格便宜
            //拆单并计算总价
            Map<Long,BigDecimal> resMap =new HashMap<>();
            if(!CollectionUtils.isEmpty(stayHotelList)){
                stayHotelList.forEach((item)->{
                    //BigDecimal sum = splitOrder(days,item.continusStayRoomPriceList);
                    //查找单价
                    Map<String,BigDecimal> priceSum = new HashMap<>();
                    StringBuffer pathF = new StringBuffer("");
                    //Map<String,BigDecimal> priceSum2 = new HashMap<>();
                    List<BigDecimal> Sum2 = new ArrayList<>();
                    this.splitOrder(startDate,endDate,calDays(startDate,endDate),item.continusStayRoomPriceList,priceSum,pathF);
                    //Iterator it = priceSum.keySet().iterator();
                    priceSum.forEach((k,v)->{
                        String path = k;
                        BigDecimal sum = v;
                        String[] points = path.split(",");
                        List<Date> dates = new ArrayList<>();
                        for(int i=0;i<points.length;i++){
                            if(points[i].contains("%")){
                                String[] sub = points[i].split("%");
                                int daybetween = Integer.parseInt(sub[1]);
                                Date newBegin = new Date();
                                try {
                                    newBegin =  sf.parse(sub[0]);
                                } catch (ParseException e) {
                                    System.out.println("日期转换错误");
                                }
                                dates.add(newBegin);
                                for(int j=1;j<daybetween+1;j++){
                                    Date nextDay = new Date(newBegin.getTime()+1000*3600*24*j);
                                    dates.add(nextDay);
                                }

                            }
                        }

                        if(!CollectionUtils.isEmpty(dates)){
                            List<RoomPrice> roomPrices = roomPriceService.queryRoomPriceByRoomid(dates,item.id);
                            resMap.put(item.id,roomPrices.get(0).roomPrice);
                            for(Date date1:dates){
                                for(RoomPrice room:roomPrices){
                                    if(room.effectDate.compareTo(date1)==0){
                                        sum = sum.add(room.roomPrice);
                                        Sum2.add(sum);
                                        continue;
                                    }
                                }
                            }
                        }
                    });

                    //找出最小值
                    if(!CollectionUtils.isEmpty(Sum2)){
                        Collections.sort(Sum2,new Comparator<BigDecimal>() {
                            @Override
                            public int compare(BigDecimal o1, BigDecimal o2) {
                                return o1.compareTo(o2);
                            }
                        });
                        HotelResult rs = new HotelResult();
                        rs.setHotelId(item.id);
                        rs.setSum(Sum2.get(0));
                        results.add(rs);
                    }

                });
                //最终排序并选出前10条数据
                Collections.sort(results,new Comparator<HotelResult>() {
                    @Override
                    public int compare(HotelResult o1, HotelResult o2) {
                        return o1.getSum().compareTo(o2.getSum());
                    }
                });
                List<Long> ids = new ArrayList<>();
                int minss = Math.min(results.size(),10);
                results.subList(0,minss).forEach((il)->{
                    ids.add(il.getHotelId());
                });
                 List<Hotel>  rsHotels= hotelService.queryHotelByIds(ids);
                 rsHotels.forEach((ll)->{
                    ll.minPrice = resMap.get(ll.id);
                 });
                 return  rsHotels;
            }


        }


        return null;
    }


    /*
    * 按照日期规则查找最优解
    * */

    private void splitOrder(Date startDay,Date endDay,int days,List<ContinusStayRoomPrice> stayRoomPrices,Map<String,BigDecimal> priceSum,StringBuffer path ){

        if(days<1){
            /*priceSum.clear();
            path=new StringBuffer("");*/
            return;
        }

        List<ContinusStayRoomPrice> stayRoomPrices2 = new ArrayList<>();
        stayRoomPrices2.addAll(stayRoomPrices);
        stayRoomPrices2.remove(stayRoomPrices.get(0));


        ContinusStayRoomPrice price1 = stayRoomPrices.get(0);
        Date firstStartDay = price1.effectDate;
        Date firstEndDay = new Date(firstStartDay.getTime()+1000*3600*24*price1.stayDays);
        int daysOver = calDays(firstStartDay,firstEndDay);
        int disp1 = calDays(startDay,firstStartDay)-1;
        if(disp1>0){//填空
            path.append(sf.format(startDay)+"%"+disp1+",") ;
        }
        path.append(price1.hotelId+",") ;
        sum = sum.add(new BigDecimal(price1.stayPrice.doubleValue()*daysOver));

        if(stayRoomPrices.size()>1){
            ContinusStayRoomPrice price2 = stayRoomPrices.get(1);
            Date secondStartDay = price2.effectDate;
            Date secodnEndDay = new Date(price2.effectDate.getTime()+1000*3600*24*price2.stayDays);
            int daysOver2 = calDays(secondStartDay,secodnEndDay);

            if(firstEndDay.before(secondStartDay)){
                int disDay = calDays(firstEndDay,secondStartDay)-1;
                if(disDay>0){//补空
                    String start2 = sf.format(firstEndDay);
                    path.append(start2+"%"+disDay+",");
                }
                path.append(price2.id+",");
                sum = sum.add(new BigDecimal(price2.stayPrice.doubleValue()*daysOver2));

                int newDays = calDays(secodnEndDay,endDay);
                stayRoomPrices2.remove(stayRoomPrices.get(1));
                if(!CollectionUtils.isEmpty(stayRoomPrices2)){
                    splitOrder(secodnEndDay,endDay,newDays,stayRoomPrices2,priceSum,path);
                }else{
                    path.append(sf.format(secodnEndDay)+"%"+newDays+",");
                    BigDecimal bd = sum;
                    String s = path.toString();
                    priceSum.put(s,bd);
                    sum = BigDecimal.ZERO;
                    path = new StringBuffer("");
                }
            }else{
                int disDay = calDays(firstEndDay,endDay)-1;
                if(disDay>0){
                    path.append(firstEndDay+"%"+disDay+",");
                }
                priceSum.put(path.toString(),sum);
                return;
            }



        }
    }


    /**
     *拆单
     * days>1
     */
    private BigDecimal splitOrder(int days, List<ContinusStayRoomPrice> stayRoomPrices){
        BigDecimal sum = new BigDecimal(0);
        if(CollectionUtils.isEmpty(stayRoomPrices)){
            return sum;
        }
        List<ContinusStayRoomPrice> prices = new ArrayList<>();
        prices.addAll(stayRoomPrices);
        int x = days/prices.get(0).stayDays;
        int stayDays = prices.get(0).stayDays;
        int left =  days - x;
        sum.add(new BigDecimal(x * prices.get(0).stayPrice.doubleValue()));
        if(left>1){
            Iterator it = prices.iterator();
            while(it.hasNext()){
                ContinusStayRoomPrice price = (ContinusStayRoomPrice)it.next();
                if(price.stayDays.intValue()==stayDays){
                    it.remove();
                }
            }
            splitOrder(left,prices);
        }

        return sum;
    }

    private int calDays(Date date1,Date date2){
        if(date2.before(date1)){
            System.out.println("结束日期要比开始日期大！");
            return 0;
        }
      return    (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
    }

}
