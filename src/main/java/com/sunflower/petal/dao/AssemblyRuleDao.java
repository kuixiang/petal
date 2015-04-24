package com.sunflower.petal.dao;

import com.sunflower.petal.entity.AssemblyRule;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by xiangkui on 14-2-22.
 */
@Component
public interface AssemblyRuleDao {
    @Insert("insert into assemblyrule(id,productId) values(#{rule.id},#{rule.product.id})")
    public void add(@Param("rule")AssemblyRule rule);

    @Delete("delete from assemblyrule where id=#{id}")
    public void delete(@Param("id") Long id);
}
