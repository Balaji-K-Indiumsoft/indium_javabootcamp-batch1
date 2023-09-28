package com.indium.capstone.DAO;

import com.indium.capstone.modal.Skill;

import java.util.List;

public interface SkillDAO {

    boolean addSkill(Skill skill, int associateId);
    boolean updateSkill(Skill skill,int associateId);
    boolean deleteSkill(int skillId, int assId);
    Skill getSkill(int skillId,int assId);
    List<Skill> getAllSkills();
    List<Skill> getSkillsByAssociate(int associateId);
    boolean deleteSkillsByAssociateId(int assId);
}
