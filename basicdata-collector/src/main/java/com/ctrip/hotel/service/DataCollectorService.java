package com.ctrip.hotel.service;

import com.ctrip.hotel.domain.hotel.Hotel;
import com.ctrip.hotel.domain.room.ContinusStayRoomPrice;
import com.ctrip.hotel.domain.room.RoomPrice;
import com.ctrip.hotel.repository.HotelRepository;
import com.ctrip.hotel.repository.RoomPriceRepository;
import com.ctrip.hotel.repository.StayRoomPriceRepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;

/**
 * @author zhao.yong
 * @datetime 2018/10/25
 **/
@Service
public class DataCollectorService {

    @Autowired
    private RoomPriceRepository roomPriceRepository;

    @Autowired
    private StayRoomPriceRepository stayRoomPriceRepository;

    @Autowired
    private HotelRepository hotelRepository;

    public  int excelDataImportHotel(String filePath) throws Exception {

        Set<Hotel> hotels = new HashSet<>();
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        try {
            OPCPackage pkg = OPCPackage.open(is);
            Workbook workbook = new XSSFWorkbook(pkg);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for(int i = 1;i<= lastRowNum;i++){
                Hotel hotel = new Hotel();
                Row row = sheet.getRow(i);
                Double hotelId = row.getCell(0).getNumericCellValue();
                Double masterHotelId = row.getCell(2).getNumericCellValue();
                String hotelName = row.getCell(1).getStringCellValue();
                Double cityId = row.getCell(3).getNumericCellValue();
                Double star = row.getCell(4).getNumericCellValue();

                //String hotelEName = row.getCell(5).getStringCellValue();
                hotel.id = hotelId.longValue();
                hotel.masterHotelId = masterHotelId.longValue();
                hotel.hotelName = hotelName;
                //hotel.hotelEname = hotelEName;
                hotel.createTime = new Date();
                hotel.updateTime = new Date();
                hotel.cityId = cityId.intValue();
                hotel.star = star.intValue();
                hotels.add(hotel);
                if(hotels.size() == 100){
                    hotelRepository.saveAll(hotels);
                    hotels.clear();
                }
            }

            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return hotels.size();
    }


    public  void excelDataImportRoomPrice(String filePath){
        List<RoomPrice> roomPriceList = new LinkedList<>();
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        try {
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for(int i = 1;i<= lastRowNum;i++){
                RoomPrice roomPrice = new RoomPrice();
                Row row = sheet.getRow(i);
                Double hotelId = row.getCell(0).getNumericCellValue();
                Double masterHotelId = row.getCell(2).getNumericCellValue();
                String hotelName = row.getCell(1).getStringCellValue();
                Double cityId = row.getCell(3).getNumericCellValue();
                Double star = row.getCell(4).getNumericCellValue();
                Double roomId = row.getCell(5).getNumericCellValue();
                Date effectDate = row.getCell(6).getDateCellValue();
                Double roomPirce = row.getCell(7).getNumericCellValue();
                Double costPrice = row.getCell(8).getNumericCellValue();
                Double persons = row.getCell(9).getNumericCellValue();

                //String hotelEName = row.getCell(5).getStringCellValue();
                //roomPrice.id = hotelId.longValue();
                roomPrice.masterHotelId = masterHotelId.longValue();
                roomPrice.hotelName = hotelName;
                //hotel.hotelEname = hotelEName;
                roomPrice.createTime = new Date();
                roomPrice.updateTime = new Date();
                roomPrice.effectDate = effectDate;
                roomPrice.cityId = cityId.intValue();
                roomPrice.star = star.intValue();
                roomPrice.hotelId = hotelId.longValue();
                roomPrice.roomId = roomId.longValue();
                roomPrice.roomPrice = new BigDecimal(roomPirce);
                roomPrice.costPrice = new BigDecimal(costPrice);
                roomPrice.persons = persons.intValue();
                roomPriceList.add(roomPrice);
                if(roomPriceList.size() == 100){
                    roomPriceRepository.saveAll(roomPriceList);
                    roomPriceList.clear();
                }
            }

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void excelDataImportStayRoomPrice(String filePath){
        String prefix = System.getProperty("user.dir");
        List<ContinusStayRoomPrice> roomPriceList = new LinkedList<>();
        try {
           InputStream is = new FileInputStream(prefix+"/"+filePath);
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for(int i = 1;i<= lastRowNum;i++){

                Row row = sheet.getRow(i);
                Double hotelId = row.getCell(0).getNumericCellValue();
                Double masterHotelId = row.getCell(2).getNumericCellValue();
                String hotelName = row.getCell(1).getStringCellValue();
                Double cityId = row.getCell(3).getNumericCellValue();
                Double star = row.getCell(4).getNumericCellValue();
                Double roomId = row.getCell(5).getNumericCellValue();
                Date effectDate = row.getCell(6).getDateCellValue();
                Double persons = row.getCell(7).getNumericCellValue();
                for(int j =0;j<=9;j++){
                    try {
                        ContinusStayRoomPrice roomPrice = new ContinusStayRoomPrice();
                        Double roomPirce = row.getCell(8 + i).getNumericCellValue();

                        Double costPrice = row.getCell(35 + i).getNumericCellValue();
                        if(roomPirce.equals(0) || costPrice.equals(0)){
                            continue;
                        }
                        roomPrice.masterHotelId = masterHotelId.longValue();
                        roomPrice.hotelName = hotelName;
                        //hotel.hotelEname = hotelEName;
                        roomPrice.createTime = new Date();
                        roomPrice.updateTime = new Date();
                        roomPrice.effectDate = effectDate;
                        roomPrice.cityId = cityId.intValue();
                        roomPrice.star = star.intValue();
                        roomPrice.hotelId = hotelId.longValue();
                        roomPrice.roomId = roomId.longValue();
                        roomPrice.stayPrice = new BigDecimal(roomPirce);
                        roomPrice.stayCostPrice = new BigDecimal(costPrice);
                        roomPrice.stayDays = j + 1;
                        roomPrice.persons = persons.intValue();
                        roomPriceList.add(roomPrice);
                    }catch (Exception e){
                        continue;
                    }
                }


                if(roomPriceList.size() == 100){
                    stayRoomPriceRepository.saveAll(roomPriceList);
                    roomPriceList.clear();
                }
            }
            if(roomPriceList.size() > 0){
                stayRoomPriceRepository.saveAll(roomPriceList);
                roomPriceList.clear();
            }
            workbook.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
