package com.zhangbin.choose.mapper;

import com.zhangbin.choose.pojo.User;
import com.zhangbin.choose.pojo.UserInfo;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    /**
     * 查询用户和专业 并封装进UserInfo
     *
     * @return UserInfo集合信息
     */
    @Select("select id,major_id,department_id from tb_user")
    @Results(value = {
            @Result(column = "id", property = "user", one = @One(select = "com.zhangbin.choose.mapper.UserMapper.selectByPrimaryKey")),
            @Result(column = "department_id", property = "department", one = @One(select = "com.zhangbin.choose.mapper.DepartmentMapper.selectByPrimaryKey")),
            @Result(column = "major_id", property = "major", one = @One(select = "com.zhangbin.choose.mapper.MajorMapper.selectByPrimaryKey"))
    }
    )
    List<UserInfo> findAllInfo();

    /**
     * 查询用户和专业 并封装进UserInfo 使用用户ID
     *
     * @param id 用户ID
     * @return UserInfo集合信息
     */
    @Select("select id,department_id,major_id from tb_user where id = #{id}")
    @Results(value = {
            @Result(column = "id", property = "user", one = @One(select = "com.zhangbin.choose.mapper.UserMapper.selectByPrimaryKey")),
            @Result(column = "department_id", property = "department", one = @One(select = "com.zhangbin.choose.mapper.DepartmentMapper.selectByPrimaryKey")),
            @Result(column = "major_id", property = "major", one = @One(select = "com.zhangbin.choose.mapper.MajorMapper.selectByPrimaryKey"))
    }
    )
    UserInfo findInfoById(String id);

    /**
     * 根据类型查询用户和专业 并封装进UserInfo
     *
     * @return UserInfo集合信息
     */

    @Select("select id,department_id,major_id from tb_user where type = #{type}")
    @Results(value = {
            @Result(column = "id", property = "user", one = @One(select = "com.zhangbin.choose.mapper.UserMapper.selectByPrimaryKey")),
            @Result(column = "department_id", property = "department", one = @One(select = "com.zhangbin.choose.mapper.DepartmentMapper.selectByPrimaryKey")),
            @Result(column = "major_id", property = "major", one = @One(select = "com.zhangbin.choose.mapper.MajorMapper.selectByPrimaryKey"))
    }
    )
    List<UserInfo> findAllInfoByType(int type);

    /**
     * 通过教师ID查询到选课为该教师的所有学生
     *
     * @param id 教师ID
     * @return User(学生) 集合
     */

    @Select("select ts.id,ts.major_id,ts.department_id from tb_user ts,tb_course tc,tb_user tu,tb_student_course tsc where " +
            "tc.teacher_id = tu.id and tu.id= #{id} and ts.id = tsc.student_id and tc.id in " +
            "(select course_id from tb_student_course tsc,tb_course tc where tsc.course_id = tc.id)")
    @Results(value = {
            @Result(column = "id", property = "user", one = @One(select = "com.zhangbin.choose.mapper.UserMapper.selectByPrimaryKey")),
            @Result(column = "department_id", property = "department", one = @One(select = "com.zhangbin.choose.mapper.DepartmentMapper.selectByPrimaryKey")),
            @Result(column = "major_id", property = "major", one = @One(select = "com.zhangbin.choose.mapper.MajorMapper.selectByPrimaryKey"))
    })
    List<UserInfo> findByTeacher(String id);
}
