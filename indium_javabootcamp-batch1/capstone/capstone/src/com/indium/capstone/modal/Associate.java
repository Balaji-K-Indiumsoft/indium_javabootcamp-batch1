package com.indium.capstone.modal;

import java.util.List;

public class Associate {
    int id;
    String name;
    int age;
    String businessUnit;
    String email;
    String location;
    List<Skill> skills;
    String createTime;
    String updateTime;

    public Associate(int id, String name, int age, String businessUnit, String email, String location, List<Skill> skills, String createTime, String updateTime) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.businessUnit = businessUnit;
        this.email = email;
        this.location = location;
        this.skills = skills;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
