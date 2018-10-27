package com.ctrip.hotel;

import static org.junit.Assert.assertTrue;

import com.ctrip.hotel.service.DataCollectorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class AppTest 
{
    @Autowired
    private DataCollectorService dataCollectorService;
    /**
     * Rigorous Test :-)
     */
    @Test
    public void loadInitData() throws InterruptedException {

        CountDownLatch cdh = new CountDownLatch(3);
        new Thread(()->{
            int ret = dataCollectorService.excelDataImportHotel("data/star3-10.15-10.31-roomprice.xlsx");
            System.out.println("导入数据大小:"+ret);
            cdh.countDown();
        });
        new Thread(()->{
            int ret = dataCollectorService.excelDataImportHotel("data/star4-10.15-10.31-roomprice.xlsx");
            System.out.println("导入数据大小:"+ret);
            cdh.countDown();
        });
        new Thread(()->{
           int ret1 = dataCollectorService.excelDataImportHotel("data/star5-6-10.15-10.31-roomprice.xlsx");

           cdh.countDown();
        }).start();

        cdh.await();

    }


    @Test
    public void loadRoomPrice() throws InterruptedException {
        CountDownLatch cdh = new CountDownLatch(3);
        new Thread(()->{

            dataCollectorService.excelDataImportRoomPrice("data/star3-11.01-11.15-roomprice.xlsx");
            cdh.countDown();
        });
        new Thread(()->{

            dataCollectorService.excelDataImportRoomPrice("data/star4-10.15-10.31-roomprice.xlsx");
            cdh.countDown();
        });
        new Thread(()->{
            dataCollectorService.excelDataImportRoomPrice("data/star4-11.01-11.15-roomprice.xlsx");
            cdh.countDown();
        });
        cdh.await();
        System.out.println("导致房价成功!");
    }

    @Test
    public void loadStayRoomPrice(){
        CountDownLatch cdh = new CountDownLatch(3);

    }
}
