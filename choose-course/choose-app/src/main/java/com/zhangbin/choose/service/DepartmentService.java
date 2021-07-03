package com.zhangbin.choose.service;

import com.github.pagehelper.PageInfo;
import com.zhangbin.choose.pojo.Department;

import java.util.List;

public interface DepartmentService {

    /**
     * 查询所有院系
     *
     * @return 院系集合
     */
    List<Department> findAll();

    /**
     * 查询全部并分页
     *
     * @return PageInfo
     */
    PageInfo<Department> findAllPage(int pageNum);

    /**
     * 根据ID查询院系
     *
     * @param id 院系ID
     * @return 院系
     */
    Department findById(Integer id);

    /**
     * 修改学院信息
     *
     * @param department 学院信息
     */
    void update(Department department);

    /**
     * 根据ID删除学院
     *
     * @param id ID
     */
    void delete(Integer id);

    /**
     * 添加学院
     *
     * @param department 学院信息
     */
    void add(Department department);

}
