package com.lagou.service.Impl;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.domain.ResourceCategory;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        List<ResourceCategory> allResourceCategory = resourceCategoryMapper.findAllResourceCategory();

        return allResourceCategory;
    }

    //添加资源分类
    @Override
    public void saveResourceCategory(ResourceCategory resourceCategory) {
        // 补全信息：日期， 创建人
        Date date = new Date();
        resourceCategory.setCreatedTime(date);
        resourceCategory.setUpdatedTime(date);

        //默认指定为系统创建
        resourceCategory.setCreatedBy("system");
        resourceCategory.setUpdatedBy("system");

        resourceCategoryMapper.saveResourceCategory(resourceCategory);
    }

    //更新资源分类
    @Override
    public void updateResourceCategory(ResourceCategory resourceCategory) {

        //补全所需的 更新时间和更新人
        Date date = new Date();
        resourceCategory.setUpdatedTime(date);
        resourceCategory.setUpdatedBy("system");

        resourceCategoryMapper.updateResourceCategory(resourceCategory);

    }

    //根据id 删除资源分类
    @Override
    public void deleteResourceCategory(Integer id) {
        resourceCategoryMapper.deleteResourceCategory(id);
    }


}
