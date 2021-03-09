package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    //    分页广告查询
    @RequestMapping("/findAllPromotionAd")
    public ResponseResult findAllByPage( PromotionAdVO promotionAdVO){
        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVO);

        ResponseResult responseResult = new ResponseResult(true, 200, "分页查询成功", pageInfo);
        return responseResult;

    }

    //图片上传对应的方法
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
//        1. 判断上传的文件是否为空
        if (file.isEmpty()){
            throw new RuntimeException();
        }

        //2. 获取项目部署路径
        String realPath = request.getServletContext().getRealPath("/");

        //截取路径
        String substring = realPath.substring(0, realPath.indexOf("ssm-web"));

        //获取 原文件名
        String originalFilename = file.getOriginalFilename();

        //生成新文件名
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //文件上传
        String uploadPath = substring + "upload\\";

        File filePath = new File(uploadPath, newFileName);

        //如果目录不存在，则创建目录
        if (!filePath.getParentFile().exists() ){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录成功" + filePath );
        }

        file.transferTo(filePath);

        //文件名和文件路径  返回，进行响应
        HashMap<String, String> map = new HashMap<>();
        map.put("fileName" , newFileName );

        //
        map.put("filePath", " http://localhost:8080/upload/"+newFileName);

        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);

        return responseResult;
    }


//    广告动态上下线
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus( Integer id, Integer status ){
        promotionAdService.updatePromotionAdStatus(id, status);

        ResponseResult responseResult = new ResponseResult(true, 200, "广告动态上下线成功", null);

        return responseResult;
    }




    //新建或修改广告信息
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd ){

        Date date = new Date();
        if (promotionAd.getId() == null){
            promotionAd.setCreateTime(date);
            promotionAd.setUpdateTime(date);
            promotionAdService.savePromotionAd(promotionAd);

            return new ResponseResult(true, 200, "新增广告成功", null);
        } else {
            promotionAd.setUpdateTime(date);

            promotionAdService.updatePromotionAd(promotionAd);
            return new ResponseResult(true, 200, "修该广告信息成功", null);
        }
    }


    //根据id 回显对应的广告信息
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(int id){
        PromotionAd promotionAdById = promotionAdService.findPromotionAdById(id);
        return new ResponseResult(true, 200, "回显成功", promotionAdById);

    }

}
