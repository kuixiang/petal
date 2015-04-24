package com.sunflower.petal.service;

import com.sunflower.petal.service.support.ImportStatus;
import static junit.framework.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.File;

/**
 * Created by xiangkui on 14-2-23.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath*:springMVC.xml")
public class ExcelPumberServiceTest {
   @Resource
    private ExcelPumberService pumber;
    private String filePath=ClassLoader.getSystemResource("test.xls").getPath();

    private String dirPath=ClassLoader.getSystemResource("").getPath()+"test";
//    @Test
    public void testProduceInfo() throws Exception {
        ImportStatus status= pumber.produceInfo(new File(filePath));
        assertEquals(status.getResult(), ImportStatus.Status.SUCCESS);
        File testFiles=new File(dirPath);
        for (File file : testFiles.listFiles()) {
             status= pumber.produceInfo(file);
            assertEquals(status.getResult(), ImportStatus.Status.SUCCESS);
        }
    }


}
