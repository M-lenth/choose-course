package com.zhangbin.choose.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangbin.choose.service.DepartmentService;
import com.zhangbin.choose.mapper.DepartmentMapper;
import com.zhangbin.choose.pojo.Department;
import com.zhangbin.choose.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper mapper;

    /**
     * 查询所有院系
     *
     * @return 院系集合
     */
    @Override
    public List<Department> findAll() {
        return mapper.selectAll();
    }

    /**
     * 查询全部并分页
     *
     * @return PageInfo
     */
    @Override
    public PageInfo<Department> findAllPage(int pageNum) {
        // 分页
        PageHelper.startPage(pageNum, PageUtil.SIZE);
        return new PageInfo<>(mapper.selectAll());
    }

    /**
     * 根据ID查询院系
     *
     * @param id 院系ID
     * @return 院系
     */
    @Override
    public Department findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 修改学院信息
     *
     * @param department 学院信息
     */
    @Override
    public void update(Department department) {
        mapper.updateByPrimaryKeySelective(department);
    }

    /**
     * 根据ID删除学院
     *
     * @param id ID
     */
    @Override
    public void delete(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加学院
     *
     * @param department 学院信息
     */
    @Override
    public void add(Department department) {
        mapper.insertSelective(department);
    }
}
