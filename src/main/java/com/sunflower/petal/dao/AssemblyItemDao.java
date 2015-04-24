package com.sunflower.petal.dao;

import com.sunflower.petal.entity.ext.AssemblyItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xiangkui on 14-2-15.
 */
@Component
public interface AssemblyItemDao {
    @Insert(" insert into assemblyitem (materialId,assemblyruleId,count,beizhu) values " +
            "(#{item.material.id},#{item.ruleId},#{item.count},#{item.beizhu})")
    void add(@Param("item") AssemblyItem assemblyItem);

    @Delete("delete * from assemblyitem where assemblyruleId=#{id}")
    void deleteByRule(@Param("id")Long id);

    @Delete("delete * from assemblyitem where materialId=#{id}")
    int deleteByMaterial(@Param("id") Long id);

    @Update("update assemblyitem set count=#{item.count},beizhu=#{item.beizhu} where assemblyruleId=#{item.ruleId} and materialId=#{item.material.id}")
    void updateByRuleAndMaterial(@Param("item") AssemblyItem item);


    @Select(" select * from assemblyitem where assemblyruleId=#{id}")
    List<AssemblyItem> listByRule(@Param("id") Long id);

    @Select(" select * from assemblyitem where materialId=#{id}")
    List<AssemblyItem> listByMaterial(@Param("id") Long id);
}