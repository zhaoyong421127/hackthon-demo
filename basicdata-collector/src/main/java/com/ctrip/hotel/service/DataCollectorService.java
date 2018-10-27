package com.ctrip.hotel.service;

import com.ctrip.hotel.domain.hotel.Hotel;
import com.ctrip.hotel.repository.HotelRepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhao.yong
 * @datetime 2018/10/25
 **/
@Service
public class DataCollectorService {

    @Autowired
    private HotelRepository hotelRepository;

    public  int excelDataImportHotel(){

        List<Hotel> hotels = new LinkedList<>();
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("data/RoomPrice(sample).xlsx");
        try {
            //OPCPackage pkg = OPCPackage.open(myInputStream);
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            Row row = sheet.getRow(0);
            System.out.println(row.getCell(0).getNumericCellValue());
           /* for(int i = 1;i<= lastRowNum;i++){
                Hotel hotel = new Hotel();
                Row row = sheet.getRow(i);
                Double hotelId = row.getCell(0).getNumericCellValue();
                Double masterHotelId = row.getCell(3).getNumericCellValue();
                String hotelName = row.getCell(4).getStringCellValue();
                String hotelEName = row.getCell(5).getStringCellValue();
                hotel.id = hotelId.longValue();
                hotel.masterHotelId = masterHotelId.longValue();
                hotel.hotelName = hotelName;
                hotel.hotelEname = hotelEName;
                hotel.createTime = new Date();
                hotel.updateTime = new Date();
                hotels.add(hotel);
            }*/

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //hotelRepository.saveAll(hotels);

        return hotels.size();
    }

    public  void excelDataImportRoom(){
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("data/RoomPrice(sample).xlsx");
    }

    public  void excelDataImportRoomPrice(){

    }

}
