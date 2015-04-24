package com.sunflower.petal.controller;

import com.sunflower.petal.service.ExcelPumberService;
import com.sunflower.petal.service.support.ImportStatus;
import com.sunflower.petal.utils.FileUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by xiangkui on 14-3-17.
 * 文件上传控制
 * */

@Controller
@RequestMapping("/fileloader")
public class FileLoaderCtrl {
    private String fileUploadDirectory="E:\\temp\\";
    private static final Logger log = LoggerFactory.getLogger(FileLoaderCtrl.class);

    @Autowired
    private ExcelPumberService excelPumberService;
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    ImportStatus upload(HttpServletRequest request, HttpServletResponse response) {
        ImportStatus status=new ImportStatus();
        status.setResult(ImportStatus.Status.UNKWON);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        File repository = new File(fileUploadDirectory);
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();
                if (item.isFormField()) {
                    ;
                } else {
                    String fieldName = item.getFieldName();
                    String fileName = item.getName();
                    String contentType = item.getContentType();
                    boolean isInMemory = item.isInMemory();
                    long sizeInBytes = item.getSize();
                    String newFilename = UUID.randomUUID().toString() + "."+FileUtil.getExtName(fileName);
                    File uploadedFile = new File(fileUploadDirectory + "/" + newFilename);
                        try {
                            item.write(uploadedFile);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    //通知导数
                     status=excelPumberService.produceInfo(uploadedFile);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        return status;
    }
}
