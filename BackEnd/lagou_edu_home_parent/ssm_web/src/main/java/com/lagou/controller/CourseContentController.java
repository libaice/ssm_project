package com.lagou.controller;


import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId( Integer courseId ){

        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);

        ResponseResult responseResult = new ResponseResult(true, 200, "章节及课时内容查询成功", list);

        return responseResult;


    }

//    回显章节对应的课时信息

    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){
        Course course = courseContentService.findCourseByCourseId(courseId);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询课程信息成功", course);

        return responseResult;

    }


//    新增章节信息 或 修改章节信息
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){

        //判断是否携带 章节id
        if (courseSection.getId() == null ){
            courseContentService.saveSection(courseSection);
            ResponseResult responseResult = new ResponseResult(true, 200, "新增章节成功", null);
            return responseResult;
        }else {
            courseContentService.updateSection(courseSection);
            ResponseResult responseResult = new ResponseResult(true, 200, "更新章节成功", null);
            return responseResult;
        }
    }



//    修改章节状态
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus( int id,int status ){
        courseContentService.updateSectionStatus(id, status);

        //数据响应
        HashMap<Object, Object> map = new HashMap<>();
        map.put("status", status);

        ResponseResult responseResult = new ResponseResult(true, 200, "修改章节状态成功", map);
        return responseResult;


    }

    //保存/ 修改课时信息
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson courseLesson){

        if (courseLesson.getId() == null ){
            //获取到的id值为空, 新增操作
            courseContentService.saveLesson(courseLesson);
            return new ResponseResult(true, 200, "新增课时信息成功", null);
        }else {
            //获得的id值不为空，执行对应的修改操作
            courseContentService.updateLesson(courseLesson);
            return new ResponseResult(true, 200, "更新课时信息成功", null);
        }

    }


}
