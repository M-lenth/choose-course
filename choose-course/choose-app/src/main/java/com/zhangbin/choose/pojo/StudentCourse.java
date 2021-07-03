package com.zhangbin.choose.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@ApiModel(description = "StudentCourse", value = "StudentCourse")
@Table(name = "tb_student_course")
public class StudentCourse implements Serializable {

    @ApiModelProperty(value = "主键", required = false)
    @Id
    @Column(name = "id")
    private Integer id;

    @ApiModelProperty(value = "课程ID", required = false)
    @Column(name = "course_id")
    private Integer courseId;

    @ApiModelProperty(value = "学生ID", required = false)
    @Column(name = "student_id")
    private String studentId;

    @ApiModelProperty(value = "是否测试", required = false)
    @Column(name = "is_test")
    private Integer isTest;

    @ApiModelProperty(value = "课程分数", required = false)
    @Column(name = "score")
    private Double score;


    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getIsTest() {
        return isTest;
    }

    public void setIsTest(Integer isTest) {
        this.isTest = isTest;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", studentId='" + studentId + '\'' +
                ", isTest=" + isTest +
                ", score=" + score +
                '}';
    }
}
