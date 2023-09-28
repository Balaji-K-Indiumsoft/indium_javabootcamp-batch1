package com.indium.capstone.service;

import com.indium.capstone.modal.Skill;

import java.util.List;

public interface SkillService {
    boolean addSkill(Skill skill, int id);
    List<Skill> getAllSkills();
    Skill getSkillById(int id,int assId);
    boolean updateSkill(Skill skill,int id);
    boolean deleteSkill(int id, int assId);
    boolean deleteSkillWithAssId(int id);
    List<Skill> getSkillsByAssId(int id);
}
