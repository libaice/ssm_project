package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    //    分页查询广告集合
    public List<PromotionAd> findAllPromotionAdByPage();

    //动态上下线
    public void updatePromotionAdStatus(PromotionAd promotionAd);


    //新建广告  点击提交按钮，将页面内容保存到数据库
    public void savePromotionAd(PromotionAd promotionAd);

    //修改广告
    public void updatePromotionAd(PromotionAd promotionAd);

    //回显广告信息
    public PromotionAd findPromotionAdById(int id);




}
