package com.sunflower.petal.dao;
import com.sunflower.petal.entity.ProductMaterialRL;
import com.sunflower.petal.entity.ext.AssemblyItem;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * Created by xiangkui on 2015/2/28.
 */
public interface ProductMaterialDao {
    public final String TNAME = "product_material";
    public final String COLUMS = "productId,materialId,count,beizhu";
    public final String VALUES = "#{productId},#{materialId},#{count},#{beizhu}";

    public final String UPDATES = "id=#{id},productId=#{productId},materialId=#{materialId},count=#{count},beizhu=#{beizhu}";
    public final String MATERIAL_NAME = "material";
    public final String PRODUCT_NAME = "product";
    public void addRLByProductId(@Param("productId")Long productId,@Param("materialIds") Long[] materialIds,@Param("beizhu") String beizhu);
    public void addRLByMaterialId(@Param("materialId")Long materialId,@Param("productIds") Long[] productIds,@Param("beizhu") String beizhu);


    @Select("select * from "+TNAME+" where materialId=#{materialId}")
    public List<ProductMaterialRL> queryByMaterialId(@Param("materialId") Long materialId);

    @Select("select * from "+TNAME+" where productId=#{productId}")
    public List<ProductMaterialRL> queryByProductId(@Param("productId") Long productId);

    @Delete("delete from "+TNAME+" where materialId = #{materialId}")
    int deleteBLByMaterialId(@Param("materialId")Long materialId);

    @Delete("delete from "+TNAME+" where productId = #{productId}")
    int deleteBLByProductId(@Param("productId")Long productId);

    @Select("select "
            +TNAME +".id,"
            +TNAME +".productId as productId,"
            +TNAME +".materialId as materialId,"
            +TNAME +".count as count,"
            +TNAME +".beizhu as beizhu,"
            +MATERIAL_NAME +".id as materialId,"
            +MATERIAL_NAME +".name as materialName,"
            +MATERIAL_NAME +".guige as materialGuige,"
            +MATERIAL_NAME +".yanse as materialYanse,"
            +MATERIAL_NAME +".danwei as materialDanwei,"
            +MATERIAL_NAME +".jinjia as materialDanjia,"
            +PRODUCT_NAME + ".name as productName,"
            +"1"
            +" from "+ TNAME + "," + MATERIAL_NAME +"," + PRODUCT_NAME
            + " where "
            + TNAME +".materialId"+"="+ MATERIAL_NAME +".id"
            +" and " + TNAME +".productId"+"="+ PRODUCT_NAME +".id"
            +" and "+TNAME+".productId"+"=#{productId}"
            +" and 1=1"
    )
    List<AssemblyItem> queryAssemblyItemByProductId(Long productId);

    @Insert("insert into "+TNAME+"("+COLUMS+")"+" values "+"("+VALUES+")")
    void add(ProductMaterialRL relation);

    @Delete("delete from "+TNAME+" where id=#{id}")
    void deleteById(Long id);

    @Update("update "+TNAME+" set "+UPDATES+" where id=#{id}")
    void update(ProductMaterialRL productMaterialRL);

}
