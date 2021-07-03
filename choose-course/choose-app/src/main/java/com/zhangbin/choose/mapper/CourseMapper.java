package com.zhangbin.choose.mapper;

import com.zhangbin.choose.pojo.Course;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CourseMapper extends Mapper<Course> {

    /**
     * 通过学生ID查询课程信息
     *
     * @param id 学生ID
     * @return Course集合
     */
    @Select({"select * from tb_course tc,tb_student_course tsc where tsc.course_id = tc.id and tsc.student_id = #{id}"})
    List<Course> findByStudent(String id);

    /**
     * 通过学生ID查询学生未选课程
     *
     * @param id 学生ID
     * @return 课程Info
     */
    @Select("select * from tb_course where id not in" +
            " (select course_id from tb_student_course where student_id = #{id})")
    List<Course> findCourseStudentNot(String id);
}
