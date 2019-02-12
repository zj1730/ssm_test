package cn.itcast.service;

import cn.itcast.domain.Items;

import java.util.List;

public interface ProductService {

    /**
     * 查询所有商品
     * @return
     */
    List<Items> findAll();

    /**
     * 根据id查询商品名称
     * @param id
     * @return
     */
    Items findById(String id);

    void update(Items items);

}
