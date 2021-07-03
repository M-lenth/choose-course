package com.zhangbin.choose.service;

import com.github.pagehelper.PageInfo;
import com.zhangbin.choose.pojo.UserInfo;
import com.zhangbin.choose.pojo.User;

import java.util.List;

public interface UserService {

    User findById(String id);

    /**
     * 通过ID查询用户
     *
     * @param id 用户ID
     * @return UserInfo
     */
    UserInfo findUserInfoById(String id);

    /**
     * 查询所有用户信息
     *
     * @return UserInfo集合
     */
    List<UserInfo> findAllUserInfo();

    /**
     * 查询所有用户
     *
     * @return User集合
     */
    List<User> findAll();

    /**
     * 添加用户
     *
     * @param user 添加的用户信息
     */
    void add(User user);

    /**
     * 删除用户
     *
     * @param id 用户ID
     */
    void delete(String id);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     */
    void update(User user);

    /**
     * 根据用户类型查找用户
     *
     * @param type 用户类型 0管理员 1教师 2学生
     * @return User集合
     */
    PageInfo<UserInfo> findByType(int page, int size, Integer type);

    /**
     * 通过类型查询
     *
     * @param type 类型
     * @return User集合
     */
    List<User> findByType(Integer type);

    /**
     * 条件查询
     *
     * @param user 用户信息
     * @return User集合
     */
    List<User> search(User user);

    /**
     * 分页查询
     *
     * @return PageInfo
     */
    PageInfo<UserInfo> findPage(int page, int size);

    /**
     * 分页条件查询
     *
     * @param user 条件
     * @param page 页数
     * @param size 每页大小
     * @return PageInfo
     */
    PageInfo<UserInfo> findPage(User user, int page, int size);

    /**
     * 使用专业ID进行查询
     *
     * @param majorId 专业ID
     * @return 用户集合
     */
    List<User> findByMajorId(int majorId);

    /**
     * 通过教师查询选该教师课所有学生
     *
     * @param id 教师ID
     * @param pageNum 页数
     * @return UserInfo
     */
    PageInfo<UserInfo> findByTeacher(int pageNum,String id);


}
