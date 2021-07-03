package com.zhangbin.choose.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangbin.choose.mapper.CourseMapper;
import com.zhangbin.choose.pojo.Course;
import com.zhangbin.choose.service.CourseService;
import com.zhangbin.choose.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper mapper;

    /**
     * 查询全部课程
     *
     * @return Course集合
     */
    @Override
    public List<Course> findAll() {
        return mapper.selectAll();
    }

    /**
     * 分页查询全部课程
     *
     * @return PageInfo
     */
    @Override
    public PageInfo<Course> findPageAll(int pageNum) {
        PageHelper.startPage(pageNum, PageUtil.SIZE);
        return new PageInfo<>(mapper.selectAll());
    }

    /**
     * 删除
     *
     * @param id 删除的课程ID
     */
    @Override
    public void delete(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    /**
     * 通过主键查询
     *
     * @param id 　课程ID
     * @return 课程
     */
    @Override
    public Course findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 修改课程信息
     *
     * @param course 课程的信息对象
     */
    @Override
    public void update(Course course) {
        mapper.updateByPrimaryKeySelective(course);
    }

    /**
     * 添加课程信息
     *
     * @param course 课程信息
     */
    @Override
    public void add(Course course) {
        mapper.insertSelective(course);
    }

    /**
     * 通过学生ID查询课程信息
     *
     * @param id 学生ID
     * @return Course集合
     */
    @Override
    public PageInfo<Course> findByStudent(int pageNum, String id) {
        PageHelper.startPage(pageNum, PageUtil.SIZE);
        return new PageInfo<>(mapper.findByStudent(id));
    }

    /**
     * 通过学生ID查询学生未选课程
     *
     * @param id 学生ID
     * @return 课程Info
     */
    @Override
    public PageInfo<Course> findCourseStudentNot(Integer pageNum, String id) {
        PageHelper.startPage(pageNum,PageUtil.SIZE);
        return new PageInfo<>(mapper.findCourseStudentNot(id));
    }
}
