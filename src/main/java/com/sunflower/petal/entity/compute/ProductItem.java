package com.sunflower.petal.entity.compute;

/**
 * Created by xiangkui on 2015/3/3.
 */
public class ProductItem {
    private Long id;
    private Long productId;//产品
    private Long count;// 需要改产品count个

    //因变量
    //zaojia = product_zaojia * count
    private Double zaojia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Double getZaojia() {
        return zaojia;
    }

    public void setZaojia(Double zaojia) {
        this.zaojia = zaojia;
    }
}
