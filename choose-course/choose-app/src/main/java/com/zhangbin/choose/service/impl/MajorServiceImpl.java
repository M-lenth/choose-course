package com.zhangbin.choose.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangbin.choose.pojo.Major;
import com.zhangbin.choose.pojo.MajorInfo;
import com.zhangbin.choose.service.MajorService;
import com.zhangbin.choose.mapper.MajorMapper;
import com.zhangbin.choose.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorMapper mapper;

    /**
     * 根据ID查找Major
     *
     * @param id Major ID
     * @return Major
     */
    @Override
    public Major findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 查询全部专业
     *
     * @return Major集合
     */
    @Override
    public List<Major> findAll() {
        return mapper.selectAll();
    }

    /**
     * 添加Major
     *
     * @param major 需要添加的Major
     */
    @Override
    public void add(Major major) {
        mapper.insertSelective(major);
    }

    /**
     * 修改Major信息
     *
     * @param major 需要更新的Major信息
     */
    @Override
    public void update(Major major) {
        mapper.updateByPrimaryKeySelective(major);
    }

    /**
     * 根据ID删除Major
     *
     * @param id Major ID
     */
    @Override
    public void delete(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据院系ID查询Major
     *
     * @param departmentId 院系ID
     * @return Major集合
     */
    @Override
    public List<Major> findByDepartmentId(Integer departmentId) {
        // 封装Major
        Major major = new Major();
        major.setDepartmentId(departmentId);
        // 返回查询的结果
        return mapper.select(major);
    }

    /**
     * 获取所有的专业学院信息
     *
     * @return MajorInfo集合
     */
    @Override
    public PageInfo<MajorInfo> findAllInfo(int pageNum) {
        PageHelper.startPage(pageNum, PageUtil.SIZE);
        return new PageInfo<>(mapper.findAllInfo());
    }

    /**
     * 根据专业ID获取Major Info信息
     *
     * @param id 专业ID
     * @return MajorInfo
     */
    @Override
    public MajorInfo findInfoById(Integer id) {
        return mapper.findInfoById(id);
    }

    /**
     * 根据学院名称获取MajorInfo
     *
     * @param name 学院名称
     * @return MajorInfo集合
     */
    @Override
    public PageInfo<MajorInfo> findByDepartmentName(int pageNum, String name) {
        PageHelper.startPage(pageNum, PageUtil.SIZE);
        return new PageInfo<>(mapper.findByDepartmentName("%" + name + "%"));
    }
}
