package com.zhangbin.choose.pojo;

import java.io.Serializable;

public class MajorInfo implements Serializable {

    private Department department;
    private Major major;

    public MajorInfo() {
    }

    public MajorInfo(Department department, Major major) {
        this.department = department;
        this.major = major;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "MajorInfo{" +
                "department=" + department +
                ", major=" + major +
                '}';
    }
}
