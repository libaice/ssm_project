package com.lagou.controller;


import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){
        List<ResourceCategory> allResourceCategory = resourceCategoryService.findAllResourceCategory();

        return new ResponseResult(true, 200, "查询所有分类信息成功", allResourceCategory);
    }


//    saveOrUpdateResourceCategory

    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory){
        if (resourceCategory.getId() == null){

            resourceCategoryService.saveResourceCategory(resourceCategory);
            return new ResponseResult(true, 200, "新增资源分类成功", null);
        }else {
            resourceCategoryService.updateResourceCategory(resourceCategory);
            return new ResponseResult(true, 200, "修改资源分类成功", null);
        }

    }



//     deleteResourceCategory
    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(Integer id){
        resourceCategoryService.deleteResourceCategory(id);
        return new ResponseResult(true, 200, "根据id删除资源分类成功", null);
    }



}
