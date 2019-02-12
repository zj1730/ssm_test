package cn.itcast.dao;

import cn.itcast.domain.Items;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ProductDao {

    @Select("select * from items")
    List<Items> findAll();

    @Select("select * from items where id=#{id}")
    Items findById(String id);

    @Update("update items set name=#{name} ,price=#{price}, createtime=#{createtime},detail=#{detail},pic=#{pic} where id=#{id}")
    void update(Items items);

}
