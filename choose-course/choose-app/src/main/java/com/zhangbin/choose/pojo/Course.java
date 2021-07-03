package com.zhangbin.choose.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@ApiModel(description = "Course", value = "Course")
@Table(name = "tb_course")
public class Course implements Serializable {

    @ApiModelProperty(value = "课程ID", required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ApiModelProperty(value = "课程名称", required = false)
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "学分", required = false)
    @Column(name = "credit")
    private Double credit;

    @ApiModelProperty(value = "任课教师", required = false)
    @Column(name = "teacher_id")
    private String teacherId;

    @ApiModelProperty(value = "上课类型 0 网课 1 线下", required = false)
    @Column(name = "type")
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", credit=" + credit +
                ", teacherId='" + teacherId + '\'' +
                ", type=" + type +
                '}';
    }
}
