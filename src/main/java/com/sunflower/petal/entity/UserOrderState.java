/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.sunflower.petal.entity;

/**
 * Created by xiangkui on 2015/8/26.
 */
public enum  UserOrderState {
    NEW("新建立"),PROCESSING("处理中"),DONE("完成"),DISCARD("废弃");
    private String value;
    UserOrderState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public String getView() {
      switch (this){
          case NEW:
              return "NEW";
          case PROCESSING:
              return "PROCESSING";
          case DONE:
              return "DONE";
          case DISCARD:
              return "DISCARD";
          default:
              throw new IllegalStateException("");
      }
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
