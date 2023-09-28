package com.indium.capstone.DAO;

import com.indium.capstone.DataBaseConnection;
import com.indium.capstone.modal.Skill;
import com.indium.capstone.modal.SkillCat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillDAOImpl implements SkillDAO{

    @Override
    public boolean addSkill(Skill skill, int associateId) {
        boolean status = false;
        int skillId =-1;
        SkillCat cat  = null;
        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement pm = connection.prepareStatement(
                    "INSERT INTO skills(name, description, category, experience ,associate_id) VALUES(?, ?, ?, ?,?)", Statement.RETURN_GENERATED_KEYS)){
            pm.setString(1, skill.getName());
            pm.setString(2, skill.getDescription());
            pm.setString(3,skill.getCategory().name());
            pm.setInt(4, skill.getExperience());
            pm.setInt(5,associateId);
            status = pm.executeUpdate()>0;
            if (status){
                ResultSet genKeys = pm.getGeneratedKeys();
                if (genKeys.next()){
                    skillId = genKeys.getInt(1);
                }
                cat = skill.getCategory();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public boolean updateSkill(Skill skill, int associateId) {
        boolean status =false;
        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement pm = connection.prepareStatement(
                "UPDATE skills SET name=?, description=?, category=?, experience=? WHERE id=?"
        )){
        pm.setString(1, skill.getName());
        pm.setString(2, skill.getDescription());
        pm.setString(3,skill.getCategory().name());
        pm.setInt(4,skill.getExperience());
        pm.setInt(5, skill.getAssociateId());

        status = pm.executeUpdate()>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public boolean deleteSkill(int skillId, int assId) {
        boolean status = false;
        try(Connection connection =DataBaseConnection.getConnection();
        PreparedStatement pm = connection.prepareStatement(
                "DELETE FROM skills WHERE id=? AND associate_id=?"
        )){
        pm.setInt(1,skillId);
        pm.setInt(2,assId);

        status = pm.executeUpdate()>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public Skill getSkill(int skillId, int assId) {
        Skill skill = null;
        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement pm = connection.prepareStatement(
                "SELECT * FROM skills WHERE id=? AND associate_id=?"
        )){
            pm.setInt(1, skillId);
            pm.setInt(2, assId);
    ResultSet resultSet = pm.executeQuery();
    while (resultSet.next()){
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        String categoryStr = resultSet.getString("category");
        int experience = resultSet.getInt("experience");
        int associateId = resultSet.getInt("associate_id");

        SkillCat cat = null;

        switch (categoryStr){
            case "Primary":
                cat = SkillCat.Primary;
            break;
            case "Secondary":
                cat = SkillCat.Secondary;
                break;
        }
        skill = new Skill(id, name, description, cat, experience,associateId);
    }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return skill;
    }

    @Override
    public List<Skill> getAllSkills() {
        List<Skill> skills = new ArrayList<>();
        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement pm = connection.prepareStatement(
                "SELECT * FROM skills"
        )){
ResultSet resultSet = pm.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String categoryStr = resultSet.getString("category");
                int experience = resultSet.getInt("experience");
                int associateId = resultSet.getInt("associate_id");
                SkillCat cat= null;

                switch (categoryStr){
                    case "Primary":
                        cat = SkillCat.Primary;
                        break;
                    case "Secondary":
                        cat = SkillCat.Secondary;
                }
                skills.add(new Skill(id, name, description, cat, experience,associateId));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return skills;
    }

    @Override
    public List<Skill> getSkillsByAssociate(int associateId) {
        List<Skill> skills = new ArrayList<>();
        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement pm = connection.prepareStatement(
                "SELECT * FROM skills WHERE associate_id=?"
        )){
            pm.setInt(1, associateId);
        ResultSet  resultSet = pm.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String categoryStr = resultSet.getString("category");
                int experience = resultSet.getInt("experience");
                SkillCat cat= null;

                switch (categoryStr) {
                    case "Primary":
                        cat = SkillCat.Primary;
                        break;
                    case "Secondary":
                        cat = SkillCat.Secondary;
                        break;
                }

                skills.add(new Skill(id, name, description, cat, experience,associateId));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return skills;
    }

    @Override
    public boolean deleteSkillsByAssociateId(int assId) {
        boolean status = false;
        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement pm = connection.prepareStatement(
                "DELETE FROM skills WHERE associate_id=?"
        )){
            pm.setInt(1, assId);

            status = pm.executeUpdate() >0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }
}
