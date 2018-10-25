package com.ctrip.hotel;

import static org.junit.Assert.assertTrue;

import com.ctrip.hotel.service.DataCollectorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void loadInitData(){
        int ret = dataCollectorService.excelDataImportHotel();
        System.out.println("导入数据大小:"+ret);
    }
}
