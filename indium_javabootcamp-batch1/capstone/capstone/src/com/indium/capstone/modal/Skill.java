package com.indium.capstone.modal;

public class Skill {
    int id;
    String name;
    String description;
    SkillCat category;
    int experience;
    int associateId;

    public Skill(int id, String name, String description, SkillCat category, int experience, int associateId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.experience = experience;
        this.associateId = associateId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SkillCat getCategory() {
        return category;
    }

    public void setCategory(SkillCat category) {
        this.category = category;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getAssociateId() {
        return associateId;
    }

    public void setAssociateId(int associateId) {
        this.associateId = associateId;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", experience=" + experience +
                ", associateId=" + associateId +
                '}';
    }
}
