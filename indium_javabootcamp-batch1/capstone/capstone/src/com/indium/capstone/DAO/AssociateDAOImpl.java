package com.indium.capstone.DAO;

import com.indium.capstone.DataBaseConnection;
import com.indium.capstone.SkillTrackerApp;
import com.indium.capstone.modal.Associate;
import com.indium.capstone.modal.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AssociateDAOImpl implements AssociateDAO{
    SkillDAO skillDAO = new SkillDAOImpl();
    @Override
    public boolean addAssociate(Associate associate) {
        Timestamp currentime = new Timestamp(System.currentTimeMillis());
        boolean status = false;
        int generatedAssociateId =-1;
        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement pm = connection.prepareStatement("INSERT INTO associates(name, age, business_unit, email, location, create_time, update_time) VALUES(?, ?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS)){
            pm.setString(1, associate.getName());
            pm.setInt(2,associate.getAge());
            pm.setString(3,associate.getBusinessUnit());
            pm.setString(4, associate.getEmail());
            pm.setString(5, associate.getLocation());
            pm.setTimestamp(6, currentime);
            pm.setTimestamp(7, currentime);

            status = pm.executeUpdate()>0;
            ResultSet genkey = pm.getGeneratedKeys();
            if(genkey.next()){
                generatedAssociateId = genkey.getInt(1);
            }
            if(generatedAssociateId >0){
                for (Skill skill: SkillTrackerApp.storeSkillTem) {
                skillDAO.addSkill(skill, generatedAssociateId);
                }
                SkillTrackerApp.storeSkillTem.clear();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public boolean updateAssociate(Associate associate) {
        Timestamp updateTime = new Timestamp(System.currentTimeMillis());
        boolean status = false;

        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement pm = connection.prepareStatement(
                "UPDATE associates SET name=?, age=?, business_unit=?, email=?, location=?,update_time=? WHERE id=?")){
        pm.setString(1,associate.getName());
        pm.setInt(2,associate.getAge());
        pm.setString(3,associate.getBusinessUnit());
        pm.setString(4,associate.getEmail());
        pm.setString(5,associate.getLocation());
        pm.setTimestamp(6,updateTime);
        pm.setInt(7,associate.getId());

        status = pm.executeUpdate()>0;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean deleteAssociate(int id) {
        boolean status = false;
        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement pm = connection.prepareStatement(
                "DELETE FROM associates WHERE id=?")){
    pm.setInt(1,id);

    status = pm.executeUpdate() >0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public Associate getAssociate(int associateId) {
        Associate associate = null;
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement pm = connection.prepareStatement("SELECT * FROM associates WHERE id=?")) {

            pm.setInt(1, associateId);

            ResultSet resultSet = pm.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String businessUnit = resultSet.getString("business_unit");
                String email = resultSet.getString("email");
                String location = resultSet.getString("location");
                String createTime = resultSet.getString("create_time");
                String updateTime = resultSet.getString("update_time");
                List<Skill> skills = skillDAO.getSkillsByAssociate(associateId);

                if (associate == null) {
                    associate = new Associate(id, name, age, businessUnit, email, location, skills, createTime, updateTime);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return associate;
    }

    @Override
    public List<Associate> getAllAssociates() {
        List<Associate> associates = new ArrayList<>();
        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement pm = connection.prepareStatement("SELECT * FROM associates")){
        ResultSet resultSet = pm.executeQuery();

        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name =  resultSet.getString("name");
            int age = resultSet.getInt("age");
            String businessUnit = resultSet.getString("business_unit");
            String email = resultSet.getString("email");
            String location = resultSet.getString("location");
            String createTime = resultSet.getString("create_time");
            String updateTime = resultSet.getString("update_time");

            List<Skill> allSkill = skillDAO.getAllSkills();
            List<Skill> skills =allSkill.stream().filter(e -> e.getAssociateId() == id)
                    .collect(Collectors.toList());
            Associate associate = new Associate(id, name, age, businessUnit, email, location, skills, createTime, updateTime);
            associates.add(associate);
        }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return associates;
    }

    @Override
    public List<Associate> searchAssociatesByName(String name) {
        List<Associate> associates = new ArrayList<>();
        associates = getAllAssociates();
        List<Associate> filterAssociates =   associates.stream()
                .filter(asso->asso.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
        return filterAssociates;
    }

    @Override
    public List<Associate> searchAssociatesByLocation(String location) {
        List<Associate> associates = new ArrayList<>();
        associates = getAllAssociates();
        List<Associate> filterAssociates =   associates.stream()
                .filter(asso->asso.getLocation().equalsIgnoreCase(location)).collect(Collectors.toList());
        return filterAssociates;
    }

    @Override
    public List<Associate> searchAssociatesBySkills(String skills) {
        List<Associate> associates = new ArrayList<>();
        associates = getAllAssociates();
        List<Associate> filterAssociates =   associates.stream()
                .filter(asso->asso.getSkills().stream().anyMatch(sk->sk.getName().equalsIgnoreCase(skills))).collect(Collectors.toList());
        return filterAssociates;
    }
}
