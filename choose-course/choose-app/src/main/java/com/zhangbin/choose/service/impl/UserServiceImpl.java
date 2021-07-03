package com.zhangbin.choose.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangbin.choose.mapper.UserMapper;
import com.zhangbin.choose.service.UserService;
import com.zhangbin.choose.mapper.CourseMapper;
import com.zhangbin.choose.mapper.MajorMapper;
import com.zhangbin.choose.mapper.StudentCourseMapper;
import com.zhangbin.choose.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zhangbin.choose.pojo.User;
import com.zhangbin.choose.pojo.UserInfo;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private StudentCourseMapper scMapper;
    @Autowired
    private MajorMapper majorMapper;

    /**
     * 通过ID查询用户
     *
     * @param id 用户ID
     * @return User
     */
    @Override
    public User findById(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 通过ID查询用户
     *
     * @param id 用户ID
     * @return UserInfo
     */
    @Override
    public UserInfo findUserInfoById(String id) {
        return mapper.findInfoById(id);
    }

    /**
     * 获取所有用户的信息
     *
     * @return User Info集合
     */
    @Override
    public List<UserInfo> findAllUserInfo() {
        return mapper.findAllInfo();
    }

    /**
     * 查询所有用户
     *
     * @return User集合
     */
    @Override
    public List<User> findAll() {
        return mapper.selectAll();
    }

    /**
     * 添加User
     *
     * @param user 添加的用户信息
     */
    @Override
    public void add(User user) {
        mapper.insertSelective(user);
    }

    /**
     * 根据ID删除User
     *
     * @param id 用户ID
     */
    @Override
    public void delete(String id) {
        mapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改User信息
     *
     * @param user 用户信息
     */
    @Override
    public void update(User user) {
        mapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 根据User类型查找User
     *
     * @param type 用户类型 0管理员 1教师 2学生
     * @return User集合
     */
    @Override
    public PageInfo<UserInfo> findByType(int page, int size, Integer type) {
        // 分页
        PageHelper.startPage(page, size);
        PageInfo<UserInfo> info = null;
        if (type != -1) {
            info = new PageInfo<>(mapper.findAllInfoByType(type));
        } else {
            info = new PageInfo<>(mapper.findAllInfo());
        }
        // 返回查找的数据
        return info;
    }

    /**
     * 通过类型查询用户
     *
     * @param type 类型
     * @return User集合
     */
    @Override
    public List<User> findByType(Integer type) {
        // 封装用户信息
        User user = new User();
        user.setType(type);
        // 查询结果
        return mapper.select(user);
    }

    /**
     * 通过User信息条件查询
     *
     * @param user 用户信息
     * @return User集合
     */
    @Override
    public List<User> search(User user) {
        return mapper.selectByExample(createExample(user));
    }

    /**
     * 构建条件查询对象
     *
     * @param user User信息
     * @return Example
     */
    private Example createExample(User user) {
        // 创建Example对象
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        // 判断User是否为空
        if (user != null) {

        }
        return example;
    }

    /**
     * 分页查询
     *
     * @return PageInfo
     */
    @Override
    public PageInfo<UserInfo> findPage(int page, int size) {
        // 开启静态分页
        PageHelper.startPage(page, size);
        return new PageInfo<>(mapper.findAllInfo());
    }

    /**
     * 分页条件查询
     *
     * @param user 条件
     * @param page 页数
     * @param size 每页大小
     * @return PageInfo
     */
    @Override
    public PageInfo<UserInfo> findPage(User user, int page, int size) {
        return null;
    }

    /**
     * 使用专业ID进行查询
     *
     * @param majorId 专业ID
     * @return 用户集合
     */
    @Override
    public List<User> findByMajorId(int majorId) {
        // 封装
        User user = new User();
        user.setMajorId(majorId);
        // 查询
        return mapper.select(user);
    }

    /**
     * 通过教师查询选该教师课所有学生
     *
     * @param id      教师ID
     * @param pageNum 页数
     * @return UserInfo
     */
    @Override
    public PageInfo<UserInfo> findByTeacher(int pageNum, String id) {
        PageHelper.startPage(pageNum, PageUtil.SIZE);
        return new PageInfo<>(mapper.findByTeacher(id));
    }

}
