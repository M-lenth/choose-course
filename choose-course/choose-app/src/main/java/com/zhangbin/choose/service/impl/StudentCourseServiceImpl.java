package com.zhangbin.choose.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangbin.choose.mapper.StudentCourseMapper;
import com.zhangbin.choose.pojo.StudentCourse;
import com.zhangbin.choose.service.StudentCourseService;
import com.zhangbin.choose.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {
    @Autowired
    private StudentCourseMapper mapper;

    /**
     * 查询所有学生课程
     *
     * @return StudentCourse集合
     */
    @Override
    public List<StudentCourse> findAll() {
        return mapper.selectAll();
    }

    /**
     * 根据ID查询
     *
     * @param id 学生课程ID
     * @return StudentCourse
     */
    @Override
    public StudentCourse findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 根据学生ID查找 学生课程
     *
     * @param id 学生ID
     * @return StudentCourse集合
     */
    @Override
    public PageInfo<StudentCourse> findByStudentId(String id, int pageNum) {
        PageHelper.startPage(pageNum, PageUtil.SIZE);
        // 封装StudentCourse
        StudentCourse sc = new StudentCourse();
        sc.setStudentId(id);
        return new PageInfo<>(mapper.select(sc));
    }

    /**
     * 根据课程ID查找 学生课程
     *
     * @param id 课程ID
     * @return StudentCourse集合
     */
    @Override
    public List<StudentCourse> findByCourseId(Integer id) {
        // 封装StudentCourse
        StudentCourse sc = new StudentCourse();
        sc.setCourseId(id);
        return mapper.select(sc);
    }

    /**
     * 根据课程ID与学生ID删除信息
     *
     * @param scId 学生课程ID
     */
    @Override
    public void delete(Integer scId) {
        mapper.deleteByPrimaryKey(scId);
    }

    /**
     * 添加选课信息
     *
     * @param sc 选课信息对象
     */
    @Override
    public void add(StudentCourse sc) {
        List<StudentCourse> select = mapper.select(sc);
        System.out.println("select:" + select.size());
        if (select.size() != 0) {
            // 数据存在 直接返回
            return;
        }
        mapper.insertSelective(sc);
    }
}
