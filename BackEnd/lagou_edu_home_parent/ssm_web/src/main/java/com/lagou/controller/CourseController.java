package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//组合 @Controller   @ResponseBody
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

//    多条件进行课程查询
    @RequestMapping("/findCourseByCondition")
    private ResponseResult findCourseByCondition (@RequestBody CourseVO courseVO){

        //调用service
        List<Course> list = courseService.findCourseByCondition(courseVO);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);

        return responseResult;

    }

    //图片上传
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {

        //  1. 判断上传的文件是否为空
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


    //    新增课程信息
    //  新增与修改在一个方法中， 根据前台发过来的信息中是否携带id值来判断操作为新增或修改
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO  ) throws InvocationTargetException, IllegalAccessException {

        if (courseVO.getId() == null){
            //调用service
            courseService.saveCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "新增成功", null);
            return responseResult;
        }else {
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);
            return responseResult;
        }

    }


//    根据id 查询具体的课程信息
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){
        CourseVO courseVO = courseService.findCourseById(id);

        ResponseResult responseResult = new ResponseResult(true, 200, "根据id查询课程信息成功", courseVO);
        return responseResult;
    }


//    课程状态管理
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id, Integer status){

        //调用service, 传递参数， 完成课程状态的变更
        courseService.updateCourseStatus(id, status);

        //响应数据
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);

        ResponseResult responseResult = new ResponseResult(true, 200, "课程状态变更成功", map);

        return responseResult;


    }


}
