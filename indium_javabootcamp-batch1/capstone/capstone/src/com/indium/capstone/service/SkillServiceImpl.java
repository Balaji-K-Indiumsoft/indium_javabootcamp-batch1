package com.indium.capstone.service;

import com.indium.capstone.DAO.SkillDAO;
import com.indium.capstone.DAO.SkillDAOImpl;
import com.indium.capstone.modal.Skill;

import java.util.List;

public class SkillServiceImpl implements SkillService{
    private SkillDAO skillDAO = new SkillDAOImpl();
    @Override
    public boolean addSkill(Skill skill, int id) {
        return  skillDAO.addSkill(skill,id);
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillDAO.getAllSkills();
    }

    @Override
    public Skill getSkillById(int id,int assId) {
        return skillDAO.getSkill(id,assId);
    }

    @Override
    public boolean updateSkill(Skill skill,int id) {
        return skillDAO.updateSkill(skill, id);
    }

    @Override
    public boolean deleteSkill(int id ,int assId) {
        return skillDAO.deleteSkill(id,assId);
    }

    @Override
    public boolean deleteSkillWithAssId(int id) {
        return skillDAO.deleteSkillsByAssociateId(id);
    }
    @Override
    public List<Skill> getSkillsByAssId(int id) {
        return	skillDAO.getSkillsByAssociate(id);
    }
}
