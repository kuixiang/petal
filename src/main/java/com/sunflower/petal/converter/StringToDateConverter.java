package com.sunflower.petal.converter;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  日期转换器
 */
public class StringToDateConverter implements Converter<String, Date> {
    private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private SimpleDateFormat dateTimeFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    private SimpleDateFormat dateFormatPattern = new SimpleDateFormat("yyyy-MM-dd");
    public void setDateFormatPattern(String dateFormatPattern) {
        this.dateFormatPattern = new SimpleDateFormat(dateFormatPattern);
    }
    @Override
    public Date convert(String source) {
        if(StringUtils.isEmpty(source)) {
            return null;
        }
        Date resultDate = null;
        // 先尝试解析 yyyy-MM-dd hh:mm:ss格式的日期
        try {
            resultDate = dateTimeFormat.parse(source);
        } catch (ParseException e) {
            try{
                resultDate = dateTimeFormat2.parse(source);
            }catch (ParseException e1){
                try {
                    resultDate = dateFormatPattern.parse(source);
                } catch (ParseException e2) {
                    e1.printStackTrace();
                }
            }
        }
        return resultDate;
    }
}