package com.zhangbin.choose.mapper;

import com.zhangbin.choose.pojo.Major;
import com.zhangbin.choose.pojo.MajorInfo;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MajorMapper extends Mapper<Major> {


    /**
     * 获取所有的专业学院信息
     *
     * @return MajorInfo集合
     */
    @Select("select id,department_id from tb_major")
    @Results({
            @Result(column = "department_id", property = "department", one = @One(select = "com.zhangbin.choose.mapper.DepartmentMapper.selectByPrimaryKey")),
            @Result(column = "id", property = "major",
                    one = @One(select = "com.zhangbin.choose.mapper.MajorMapper.selectByPrimaryKey"))
    }
    )
    List<MajorInfo> findAllInfo();

    /**
     * 根据专业ID获取Major Info信息
     *
     * @param id 专业ID
     * @return MajorInfo
     */
    @Select("select id,department_id from tb_major where id = #{id}")
    @Results({
            @Result(column = "department_id", property = "department", one = @One(select = "com.zhangbin.choose.mapper.DepartmentMapper.selectByPrimaryKey")),
            @Result(column = "id", property = "major",
                    one = @One(select = "com.zhangbin.choose.mapper.MajorMapper.selectByPrimaryKey"))
    }
    )
    MajorInfo findInfoById(Integer id);

    /**
     * 根据学院名称获取MajorInfo
     *
     * @param name 学院名称
     * @return MajorInfo集合
     */
    @Select("select tm.id,tm.department_id from tb_major tm,tb_department td where tm.department_id = td.id and td.name like #{name}")
    @Results({
            @Result(column = "department_id", property = "department", one = @One(select = "com.zhangbin.choose.mapper.DepartmentMapper.selectByPrimaryKey")),
            @Result(column = "id", property = "major",
                    one = @One(select = "com.zhangbin.choose.mapper.MajorMapper.selectByPrimaryKey"))
    }
    )
    List<MajorInfo> findByDepartmentName(String name);

}
