package cn.itcast.service.impl;

import cn.itcast.dao.ProductDao;
import cn.itcast.domain.Items;
import cn.itcast.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    public List<Items> findAll() {
        return productDao.findAll();
    }

    public Items findById(String id) {

        return productDao.findById(id);
    }

    public void update(Items items) {
         productDao.update(items);

    }
}
