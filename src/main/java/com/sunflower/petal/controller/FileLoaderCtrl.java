package com.sunflower.petal.controller;

import com.alibaba.fastjson.JSON;
import com.sunflower.petal.dao.FileUpLoadDao;
import com.sunflower.petal.entity.FileUpload;
import com.sunflower.petal.service.ProductService;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created by xiangkui on 14-3-17.
 * 文件上传控制
 * */
@Controller
@RequestMapping("/fileloader")
public class FileLoaderCtrl {
    @Value("#{file.upload.directory}")
    private String fileUploadDirectory;

    private static final Logger log = LoggerFactory.getLogger(FileLoaderCtrl.class);
    @Autowired
    private FileUpLoadDao fileUploadDao;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/upload/{type}", method = RequestMethod.POST)
    public String addUser(@RequestParam("file") CommonsMultipartFile[] files,@PathVariable("type")String type,
                          HttpServletRequest request){
        for(int i = 0;i<files.length;i++){
            if(!files[i].isEmpty()){
                //拿到输出流，同时重命名上传的文件
                FileOutputStream os = null;
                //拿到上传文件的输入流
                FileInputStream in = null;
                try {
                    Date date = new Date();
                    //拿到输出流，同时重命名上传的文件
                    String fileName = files[i].getOriginalFilename();
                    String name = fileUploadDirectory
                            +type
                            + DateFormatUtils.format(date,"yyyyMMdd")+File.separator
                            +new Date().getTime() + "_"+fileName;
                     os = new FileOutputStream(name);
                    //拿到上传文件的输入流
                     in = (FileInputStream) files[i].getInputStream();
                    //以写字节的方式写文件
                    int b = 0;
                    while((b=in.read()) != -1){
                        os.write(b);
                    }
                    os.flush();
                    //文件url信息写入数据库
                    FileUpload fileUploadItem = new FileUpload();
                    fileUploadItem.setName(fileName);
                    fileUploadItem.setUrl(name);
                    fileUploadItem.setType(type);
                    fileUploadDao.add(fileUploadItem);
                    //通知相关模块文件上传成功做后续处理（文件已经上传成功）
                    return "redirect:./upload"+type+"?"+"fileUpload="+ JSON.toJSONString(fileUploadItem);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        os.close();
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "/success";
    }
    /*
    * 上传的文件类型是 ProductImage
    * @return 文件上传的在系统中的url位置信息
    * */
    @RequestMapping("/uploadProductImage")
    public @ResponseBody FileUpload uploadProductImage(@RequestParam("fileUpload") FileUpload fileUpload,
                                   @RequestParam("productId") Long productId){
        Long fileUploadId = fileUpload.getId();
        productService.updateProductImage(productId,fileUploadId);
        return fileUpload;
    }

}
