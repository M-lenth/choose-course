package com.zhangbin.choose.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@ApiModel(description = "User", value = "User")
@Table(name = "tb_user")
public class User implements Serializable {

    @ApiModelProperty(value = "用户编号", required = false)
    @Id
    @Column(name = "id")
    private String id;

    @ApiModelProperty(value = "用户密码", required = false)
    @Column(name = "password")
    private String password;

    @ApiModelProperty(value = "用户姓名", required = false)
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "学生年级，管理员与教师为空", required = false)
    @Column(name = "grade")
    private String grade;

    @ApiModelProperty(value = "用户类型 0管理员 1教师 2学生", required = false)
    @Column(name = "type")
    private Integer type;

    @ApiModelProperty(value = "院系", required = false)
    @Column(name = "department_id")
    private Integer departmentId;

    @ApiModelProperty(value = "专业ID", required = false)
    @Column(name = "major_id")
    private Integer majorId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                ", type=" + type +
                ", departmentId=" + departmentId +
                ", majorId=" + majorId +
                '}';
    }
}
