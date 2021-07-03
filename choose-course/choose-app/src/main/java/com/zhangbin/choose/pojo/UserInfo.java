package com.zhangbin.choose.pojo;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private User user;
    private Department department;
    private Major major;

    public UserInfo(User user, Department department, Major major) {
        this.user = user;
        this.department = department;
        this.major = major;
    }

    public UserInfo() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "user=" + user +
                ", department=" + department +
                ", major=" + major +
                '}';
    }
}
