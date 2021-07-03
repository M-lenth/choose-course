package com.zhangbin.choose.service;

import com.github.pagehelper.PageInfo;
import com.zhangbin.choose.pojo.StudentCourse;

import java.util.List;

public interface StudentCourseService {
    /**
     * 查询所有学生课程
     *
     * @return StudentCourse集合
     */
    List<StudentCourse> findAll();

    /**
     * 根据ID查询
     *
     * @param id 学生课程ID
     * @return StudentCourse
     */
    StudentCourse findById(Integer id);

    /**
     * 根据学生ID查找 学生课程
     *
     * @param id 学生ID
     * @return StudentCourse集合
     */
    PageInfo<StudentCourse> findByStudentId(String id, int pageNum);

    /**
     * 根据课程ID查找 学生课程
     *
     * @param id 课程ID
     * @return StudentCourse集合
     */
    List<StudentCourse> findByCourseId(Integer id);

    /**
     * 根据学生ID与课程ID删除信息
     *
     * @param scId 学生课程ID
     */
    void delete(Integer scId);

    /**
     * 添加选课信息
     *
     * @param sc 选课信息对象
     */
    void add(StudentCourse sc);
}
