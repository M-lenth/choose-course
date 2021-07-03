package com.zhangbin.choose.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@ApiModel(description = "Major", value = "Major")
@Table(name = "tb_major")
public class Major implements Serializable {

    @ApiModelProperty(value = "专业主键ID", required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ApiModelProperty(value = "专业名称", required = false)
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "院系ID", required = false)
    @Column(name = "department_id")
    private Integer departmentId;

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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return name.equals("null") ? " " : name;
    }
}
