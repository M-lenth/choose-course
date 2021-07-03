package com.zhangbin.choose.service;

import com.github.pagehelper.PageInfo;
import com.zhangbin.choose.pojo.Course;

import java.util.List;

public interface CourseService {

    /**
     * 查询全部课程
     *
     * @return Course集合
     */
    List<Course> findAll();

    /**
     * 分页查询全部课程
     *
     * @param pageNum 页数
     * @return PageInfo
     */
    PageInfo<Course> findPageAll(int pageNum);

    /**
     * 删除课程
     *
     * @param id 删除的课程ID
     */
    void delete(Integer id);

    /**
     * 根据ID进行查询
     *
     * @param id 　课程ID
     */
    Course findById(Integer id);

    /**
     * 修改课程信息
     *
     * @param course 课程的信息对象
     */
    void update(Course course);

    /**
     * 添加课程信息
     *
     * @param course 课程信息
     */
    void add(Course course);

    /**
     * 通过学生ID查询课程s信息
     *
     * @param id 学生ID
     * @return Course集合
     */
    PageInfo<Course> findByStudent(int pageNum, String id);

    /**
     * 通过学生ID查询学生未选课程
     *
     * @param id 学生ID
     * @return 课程Info
     */
    PageInfo<Course> findCourseStudentNot(Integer pageNum, String id);
}
