package com.lagou.service.Impl;

import com.lagou.dao.PromotionSpaceMapper;
import com.lagou.domain.PromotionSpace;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {

    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;

    @Override
    public List<PromotionSpace> findAllPromotionSpace() {
        List<PromotionSpace> allPromotionSpace = promotionSpaceMapper.findAllPromotionSpace();
        return allPromotionSpace;
    }

//    添加广告位
    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {

        //1. 封装数据
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());
        Date date = new Date();
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        promotionSpace.setIsDel(0);

        //2. 调用mapper 方法
        promotionSpaceMapper.savePromotionSpace( promotionSpace );



    }

    @Override
    public PromotionSpace findPromotionSpace(int id) {
        PromotionSpace promotionSpace = promotionSpaceMapper.findPromotionSpace(id);
        return promotionSpace;
    }

    // 修改广告位
    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {

        // 1. 封装数据
        promotionSpace.setUpdateTime(new Date());

        promotionSpaceMapper.updatePromotionSpace(promotionSpace);
    }

}
