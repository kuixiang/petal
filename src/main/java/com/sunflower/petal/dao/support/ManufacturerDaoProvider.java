package com.sunflower.petal.dao.support;

import com.sunflower.petal.entity.Manufacturer;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by xiangkui on 2015/2/27.
 */
public class ManufacturerDaoProvider {
    private final String TNAME = "manufacturer";
    public String listByIds(Map map) {
        List<Long> ids = (List<Long>) map.get("ids");
        StringBuilder sb = new StringBuilder();
        sb.append("select * from "+ TNAME + " where id in (");
        for (int i = 0; i < ids.size(); i++) {
            sb.append(ids.get(i));
            if (i < ids.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }
//    public String insertAll(Map map) {
//        List<User> users = (List<User>) map.get("list");
//        StringBuilder sb = new StringBuilder();
//        sb.append("INSERT INTO User ");
//        sb.append("(id, name) ");
//        sb.append("VALUES ");
//        MessageFormat mf = new MessageFormat("(null, #'{'list[{0}].name})");
//        for (int i = 0; i < users.size(); i++) {
//            sb.append(mf.format(new Object[]{i}));
//            if (i < users.size() - 1) {
//                sb.append(",");
//            }
//        }
//        return sb.toString();
//    }
}
