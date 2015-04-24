package com.sunflower.petal.common.json;

import net.sf.json.JsonConfig;

import java.sql.Timestamp;


public class WebJsonConfig extends JsonConfig {

	public static WebJsonConfig getInstance() {
		return instance;
	}

	private static WebJsonConfig instance = new WebJsonConfig();

	private WebJsonConfig() {
		this.registerJsonValueProcessor(java.util.Date.class, new MyDateJsonValueProcessor());
		this.registerJsonValueProcessor(java.sql.Date.class, new MyDateJsonValueProcessor());
		this.registerJsonValueProcessor(Timestamp.class, new MyDateJsonValueProcessor());
        IgnoreFieldPropertyFilterImpl filter = new IgnoreFieldPropertyFilterImpl(null);
        this.setJsonPropertyFilter(filter);
	}

}
