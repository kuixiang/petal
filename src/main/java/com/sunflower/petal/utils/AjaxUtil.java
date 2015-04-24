package com.sunflower.petal.utils;

import com.sunflower.petal.common.json.WebJsonConfig;
import com.sunflower.petal.entity.DataTableResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AjaxUtil {

    private static Log logger = LogFactory.getLog(AjaxUtil.class);

//    public static JSONObject dataTableJson(Page page){
//        JSONObject json = new JSONObject();
//        json.put("draw",1);
//        if (page == null) {
//            json.put("recordsTotal", 0);
//            json.put("recordsFiltered",0);
//            json.put("data", ArrayUtils.EMPTY_OBJECT_ARRAY);
//            return json;
//        }
//        json.put("recordsTotal", page.getTotalPages());
////        json.put("page", page.getNumber()+1);
//        json.put("recordsFiltered", page.getTotalElements());
//        List dataList = page.getContent();
//        if (dataList != null && dataList.size() > 0) {
//            JSONArray jsonArray = new JSONArray();
//            for (Object item : dataList) {
//                Class c = item.getClass();
//                if (c == int.class || c == Integer.class ||
//                        c == long.class || c == Long.class ||
//                        c == float.class || c == Float.class ||
//                        c == double.class || c == Double.class ||
//                        c == boolean.class || c == Boolean.class ||
//                        c == byte.class || c == Byte.class ||
//                        c == char.class || c == Byte.class ||
//                        c == short.class || c == Short.class ||
//                        c == String.class) {
//                    jsonArray.add(item);
//                } else {
//                    jsonArray.add(JSONObject.fromObject(item, WebJsonConfig.getInstance()));
//                }
//            }
//            json.put("data", jsonArray);
//        } else {
//            json.put("data", ArrayUtils.EMPTY_OBJECT_ARRAY);
//        }
//        return json;
//    }

public static JSONObject dataTableJson(DataTableResponse response){
    return JSONObject.fromObject(response);
}
public static JSONObject dataTableJson(List list){
    JSONObject json = new JSONObject();
    json.put("draw",1);
    json.put("recordsTotal", list.size());
    json.put("recordsFiltered", list.size());
    List dataList = list;
    if (dataList != null && dataList.size() > 0) {
        JSONArray jsonArray = new JSONArray();
        for (Object item : dataList) {
            Class c = item.getClass();
            if (c == int.class || c == Integer.class ||
                    c == long.class || c == Long.class ||
                    c == float.class || c == Float.class ||
                    c == double.class || c == Double.class ||
                    c == boolean.class || c == Boolean.class ||
                    c == byte.class || c == Byte.class ||
                    c == char.class || c == Byte.class ||
                    c == short.class || c == Short.class ||
                    c == String.class) {
                jsonArray.add(item);
            } else {
                jsonArray.add(JSONObject.fromObject(item, WebJsonConfig.getInstance()));
            }
        }
        json.put("data", jsonArray);
    } else {
        json.put("data", ArrayUtils.EMPTY_OBJECT_ARRAY);
    }
    return json;
}


    public static JSONObject jqGridJson(Page page){
        JSONObject json = new JSONObject();
        if (page == null) {
            json.put("records", 0);
            json.put("page", 1);
            json.put("rows", ArrayUtils.EMPTY_OBJECT_ARRAY);
            return json;
        }
        json.put("total", page.getTotalPages());
        json.put("page", page.getNumber()+1);
        json.put("records", page.getTotalElements());
        List dataList = page.getContent();
        if (dataList != null && dataList.size() > 0) {
            JSONArray jsonArray = new JSONArray();
            for (Object item : dataList) {
                Class c = item.getClass();
                if (c == int.class || c == Integer.class ||
                        c == long.class || c == Long.class ||
                        c == float.class || c == Float.class ||
                        c == double.class || c == Double.class ||
                        c == boolean.class || c == Boolean.class ||
                        c == byte.class || c == Byte.class ||
                        c == char.class || c == Byte.class ||
                        c == short.class || c == Short.class ||
                        c == String.class) {
                    jsonArray.add(item);
                } else {
                    jsonArray.add(JSONObject.fromObject(item, WebJsonConfig.getInstance()));
                }
            }
            json.put("rows", jsonArray);
        } else {
            json.put("rows", ArrayUtils.EMPTY_OBJECT_ARRAY);
        }
        return json;
    }


    public static JSONObject page2Json(long total, JSONArray jsonArray) {
        JSONObject json = new JSONObject();
        json.put("totalProperty", total);
        json.put("dataList", jsonArray);
        return json;
    }

    public static JSONObject list2Json(List dataList) {
        if (dataList == null || dataList.size() == 0) {
            return page2Json(null);
        }

        JSONObject json = new JSONObject();
        json.put("totalProperty", dataList.size());

        JSONArray jsonArray = new JSONArray();
        for (Object item : dataList) {
            Class c = item.getClass();
            if (c == int.class || c == Integer.class ||
                    c == long.class || c == Long.class ||
                    c == float.class || c == Float.class ||
                    c == double.class || c == Double.class ||
                    c == boolean.class || c == Boolean.class ||
                    c == byte.class || c == Byte.class ||
                    c == char.class || c == Byte.class ||
                    c == short.class || c == Short.class ||
                    c == String.class) {
                jsonArray.add(item);
            } else {
                jsonArray.add(JSONObject.fromObject(item, WebJsonConfig.getInstance()));
            }
        }
        json.put("dataList", jsonArray);

        return json;
    }

    public static JSONObject page2Json(Page page) {
        JSONObject json = new JSONObject();
        if (page == null) {
            json.put("totalProperty", 0);
            json.put("dataList", ArrayUtils.EMPTY_OBJECT_ARRAY);
            return json;
        }

        json.put("totalProperty", page.getTotalElements());
        List dataList = page.getContent();
        if (dataList != null && dataList.size() > 0) {
            JSONArray jsonArray = new JSONArray();
            for (Object item : dataList) {
                Class c = item.getClass();
                if (c == int.class || c == Integer.class ||
                        c == long.class || c == Long.class ||
                        c == float.class || c == Float.class ||
                        c == double.class || c == Double.class ||
                        c == boolean.class || c == Boolean.class ||
                        c == byte.class || c == Byte.class ||
                        c == char.class || c == Byte.class ||
                        c == short.class || c == Short.class ||
                        c == String.class) {
                    jsonArray.add(item);
                } else {
                    jsonArray.add(JSONObject.fromObject(item, WebJsonConfig.getInstance()));
                }
            }
            json.put("dataList", jsonArray);
        } else {
            json.put("dataList", ArrayUtils.EMPTY_OBJECT_ARRAY);
        }
        return json;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static JSONObject output(boolean success, String msg, JSONObject extData) {
        Map map = new HashMap();
        map.put("_success", success);
        map.put("success", success);

        map.put("_code", success ? 0 : 1);
        map.put("_msg", msg);
        map.put("errorMessage", msg);

        if (extData == null || extData.equals("null")) {
            extData = new JSONObject();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(map);
        jsonObject
                .put("data", extData);

        return jsonObject;
    }

    public static JSONObject success(String msg) {
        return output(true, msg, null);
    }

    public static JSONObject success(String msg, Object object) {
        JSONObject json = JSONObject.fromObject(object, WebJsonConfig.getInstance());
        return output(true, msg, json);
    }


    public static String emptyVm() {
        return "commons/empty";
    }

    public static JSONObject failure(String msg) {
        return output(false, msg, null);
    }

    public static JSONObject failure(String msg, Object object) {
        JSONObject json = JSONObject.fromObject(object, WebJsonConfig.getInstance());
        return output(false, msg, json);
    }

    public static JSONObject emptyPage() {
        return page2Json(null);
    }

}
