package com.sunflower.petal.dao;

import com.sunflower.petal.entity.Manufacturer;
import com.sunflower.petal.entity.MaterialManufacturerRL;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by xiangkui on 14-2-13
 */
public interface MaterialManufacturerDao {
    public final String TNAME = "material_manufacturer";

    public void addRLByMaterialId(@Param("materialId")Long materialId,@Param("manufacturerIds") Long[] manufacturerIds,@Param("beizhu") String beizhu);

    public void addRLByManufacturerId(@Param("manufacturerId")Long manufacturerId,@Param("materialIds") Long[] materialIds,@Param("beizhu") String beizhu);

    @Select("select * from "+TNAME+" where materialId=#{materialId}")
    public List<MaterialManufacturerRL> queryByMaterialId(@Param("materialId") Long materialId);

    @Select("select * from "+TNAME+" where manufacturerId=#{manufacturerId}")
    public List<MaterialManufacturerRL> queryByManufacturerId(@Param("manufacturerId") Long manufacturerId);

    @Delete("delete from "+TNAME+" where materialId = #{materialId}")
    int deleteBLByMaterialId(@Param("materialId")Long materialId);

    @Delete("delete from "+TNAME+" where manufacturerId = #{manufacturerId}")
    int deleteBLByManufacturerId(@Param("manufacturerId")Long manufacturerId);

}
