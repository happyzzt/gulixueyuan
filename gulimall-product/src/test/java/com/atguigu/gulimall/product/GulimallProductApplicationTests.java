package com.atguigu.gulimall.product;

import com.atguigu.gulimall.product.entity.BrandEntity;
import com.atguigu.gulimall.product.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallProductApplicationTests {

    @Autowired
    BrandService brandService;

    @Test
    public void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("zzt");
        brandEntity.setLogo("朱忠涛");
        brandEntity.setDescript("朱氏描述");
        brandEntity.setShowStatus(1);
        brandEntity.setFirstLetter("z");
        brandEntity.setSort(1);
        brandService.save(brandEntity);
        System.out.println("123");
    }

}
