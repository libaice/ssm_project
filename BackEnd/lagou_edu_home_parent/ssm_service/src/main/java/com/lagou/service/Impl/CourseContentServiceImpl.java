package com.lagou.service.Impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper courseContentMapper;

    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {
        List<CourseSection> list = courseContentMapper.findSectionAndLesson(courseId);
        return list;
    }


//    回显课程信息
    @Override
    public Course findCourseByCourseId(int courseId) {
        Course course = courseContentMapper.findCourseByCourseId(courseId);

        return course;

    }

//    新增章节信息
    @Override
    public void saveSection(CourseSection courseSection) {
        //1.补全信息
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);

        //2. 调用 courseContentMapper 方法
        courseContentMapper.saveSection(courseSection);

    }

    @Override
    public void updateSection(CourseSection courseSection) {
        //1.补全信息

        courseSection.setUpdateTime(new Date());

        //
        courseContentMapper.updateSection(courseSection);

    }

//    封装数据， 调用mapper
    @Override
    public void updateSectionStatus(int id, int status) {

        //封装数据
        CourseSection courseSection = new CourseSection();
        courseSection.setStatus(status);
        courseSection.setUpdateTime(new Date());
        courseSection.setId(id);

        //调用mapper
        courseContentMapper.updateSectionStatus(courseSection);


    }

    //新增课时
    @Override
    public void saveLesson(CourseLesson courseLesson) {

        Date date = new Date();
        courseLesson.setCreateTime(date);
        courseLesson.setUpdateTime(date);

        courseContentMapper.saveLesson(courseLesson);

    }
    // 修改课时
    @Override
    public void updateLesson(CourseLesson courseLesson) {
        //补全信息
        courseLesson.setUpdateTime(new Date());

        courseContentMapper.updateLesson(courseLesson);

    }


}
