package com.zhangbin.choose.service;

import com.github.pagehelper.PageInfo;
import com.zhangbin.choose.pojo.Major;
import com.zhangbin.choose.pojo.MajorInfo;

import java.util.List;

public interface MajorService {

    /**
     * 根据ID查找Major
     *
     * @param id Major ID
     * @return Major
     */
    Major findById(Integer id);

    /**
     * 查询所有Major
     *
     * @return Major集合
     */
    List<Major> findAll();

    /**
     * 添加Major
     *
     * @param major 需要添加的Major
     */
    void add(Major major);

    /**
     * 更新Major信息
     *
     * @param major 需要更新的Major信息
     */
    void update(Major major);

    /**
     * 根据ID删除Major
     *
     * @param id Major ID
     */
    void delete(Integer id);

    /**
     * 根据原系ID查询专业
     *
     * @param departmentId 院系ID
     * @return 专业集合
     */
    List<Major> findByDepartmentId(Integer departmentId);

    /**
     * 获取所有的专业学院信息
     *
     * @return MajorInfo集合
     */
    PageInfo<MajorInfo> findAllInfo(int pageNum);

    /**
     * 根据专业ID获取Major Info信息
     *
     * @param id 专业ID
     * @return MajorInfo
     */
    MajorInfo findInfoById(Integer id);

    /**
     * 根据学院名称获取MajorInfo
     *
     * @param name 学院名称
     * @return MajorInfo集合
     */
    PageInfo<MajorInfo> findByDepartmentName(int pageNum, String name);

}
