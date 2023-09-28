package com.indium.capstone.service;

import com.indium.capstone.DAO.AssociateDAO;
import com.indium.capstone.DAO.AssociateDAOImpl;
import com.indium.capstone.modal.Associate;
import com.indium.capstone.modal.Skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AssociateServiceImp implements AssociateService{
    AssociateDAO associateDAO;
    private final SkillService skillService;
    public AssociateServiceImp(){
        this.associateDAO =new AssociateDAOImpl();
        this.skillService = new SkillServiceImpl();
    }
    @Override
    public boolean addAssociate(Associate associate) {
        return associateDAO.addAssociate(associate);
    }

    @Override
    public List<Associate> getAllAssociates() {
        return associateDAO.getAllAssociates();
    }

    @Override
    public Associate getAssociateById(int id) {
        return associateDAO.getAssociate(id);
    }

    @Override
    public boolean updateAssociate(Associate associate) {
        return associateDAO.updateAssociate(associate);
    }

    @Override
    public boolean deleteAssociate(int id) {
        return associateDAO.deleteAssociate(id);
    }

    @Override
    public List<Associate> searchAssociatesByName(String name) {
        return  associateDAO.searchAssociatesByName(name);
    }

    @Override
    public List<Associate> searchAssociatesByLocation(String location) {
        return  associateDAO.searchAssociatesByLocation(location);
    }

    @Override
    public List<Associate> searchAssociatesBySkills(String skill) {
        return  associateDAO.searchAssociatesBySkills(skill);
    }


    @Override
    public int getTotalAssociateCount() {
        List<Associate> associates = getAllAssociates();
        return associates.size();
    }

    @Override
    public int getTotalAssociatesWithSkillsCount(int minSkillsCount) {
        List<Associate> associates = getAllAssociates();
        List<Associate> getTotalAssociatesWithSkillsCount = associates.stream()
                .filter(as->as.getSkills().size()>=minSkillsCount)
                .collect(Collectors.toList());
        return getTotalAssociatesWithSkillsCount.size();
    }

    @Override
    public List<Integer> getAssociateIdsWithSkillsCount(int minSkillsCount) {
        List<Integer> associateIds = new ArrayList<>();
        for (Associate associate : getAllAssociates()) {
            List<Skill> skills = associate.getSkills();
            long skillsCount = skills.size();
            if (skillsCount >= minSkillsCount) {
                associateIds.add(associate.getId());
            }
        }
        return associateIds;
    }


    @Override
    public int getTotalAssociatesWithGivenSkills(String skills) {
        int count;
        List<Associate> assocites =  searchAssociatesBySkills(skills);
        count= assocites.size();
        return count;
    }

    @Override
    public List<Object[]> getAssociateWiseSkillCount() {
        List<Object[]> result = new ArrayList<>();
        List<Associate> associates = getAllAssociates();
        for (Associate associate : associates) {
            int primarySkillCount = 0;
            int secondarySkillCount = 0;
            for (Skill skill : associate.getSkills()) {
                if ("Primary".equals(skill.getCategory().name())) {
                    primarySkillCount++;
                } else if ("Secondary".equals(skill.getCategory().name())) {
                    secondarySkillCount++;
                }
            }
            Object[] associateInfo = {
                    associate.getId(),
                    associate.getName(),
                    primarySkillCount,
                    secondarySkillCount
            };
            result.add(associateInfo);
        }
        return result;
    }

    @Override
    public List<Object[]> getBusinessUnitWiseAssociateCount() {
        List<Object[]> result = new ArrayList<>();
        List<Associate> associates = getAllAssociates();
        Map<String, Integer> businessUnitCounts = new HashMap<>();
        for (Associate associate : associates) {
            String businessUnit = associate.getBusinessUnit();
            if (businessUnitCounts.containsKey(businessUnit)) {
                int count = businessUnitCounts.get(businessUnit);
                businessUnitCounts.put(businessUnit, count + 1);
            } else {
                businessUnitCounts.put(businessUnit, 1);
            }
        }
        for (Map.Entry<String, Integer> entry : businessUnitCounts.entrySet()) {
            String businessUnit = entry.getKey();
            int associateCount = entry.getValue();
            Object[] businessUnitInfo = { businessUnit, associateCount };
            result.add(businessUnitInfo);
        }
        return result;
    }

    @Override
    public List<Object[]> getLocationWiseSkillCount() {
        List<Object[]> result = new ArrayList<>();
        List<Associate> associates = getAllAssociates();
        Map<String, Map<String, Integer>> locationSkillCounts = new HashMap<>();
        for (Associate associate : associates) {
            String location = associate.getLocation();
            locationSkillCounts.putIfAbsent(location, new HashMap<>());
            Map<String, Integer> skillCountMap = locationSkillCounts.get(location);
            for (Skill skill : associate.getSkills()) {
                String skillName = skill.getName();
                skillCountMap.put(skillName, skillCountMap.getOrDefault(skillName, 0) + 1);
            }
        }
        for (Map.Entry<String, Map<String, Integer>> entry : locationSkillCounts.entrySet()) {
            String location = entry.getKey();
            Map<String, Integer> skillCountMap = entry.getValue();
            Object[] locationSkillInfo = { location, skillCountMap };
            result.add(locationSkillInfo);
        }
        return result;
    }

    @Override
    public List<Object[]> getSkillWiseAssociateCount() {
        List<Object[]> result = new ArrayList<>();
        List<Associate> associates = getAllAssociates();
        Map<String, Integer> skillAssociateCounts = new HashMap<>();
        for (Associate associate : associates) {
            for (Skill skill : associate.getSkills()) {
                String skillName = skill.getName();
                skillAssociateCounts.put(skillName, skillAssociateCounts.getOrDefault(skillName, 0) + 1);
            }
        }
        for (Map.Entry<String, Integer> entry : skillAssociateCounts.entrySet()) {
            String skillName = entry.getKey();
            int associateCount = entry.getValue();
            Object[] skillAssociateInfo = { skillName, associateCount };
            result.add(skillAssociateInfo);
        }
        return result;
    }

    @Override
    public List<Object[]> getSkillWiseAvgAssociateExperience() {
        List<Object[]> result = new ArrayList<>();
        List<Associate> associates = getAllAssociates();
        Map<String, Integer> skillTotalExperience = new HashMap<>();
        Map<String, Integer> skillAssociateCounts = new HashMap<>();
        for (Associate associate : associates) {
            for (Skill skill : associate.getSkills()) {
                String skillName = skill.getName();
                int experience = skill.getExperience();
                skillTotalExperience.put(skillName, skillTotalExperience.getOrDefault(skillName, 0) + experience);
                skillAssociateCounts.put(skillName, skillAssociateCounts.getOrDefault(skillName, 0) + 1);
            }
        }
        for (Map.Entry<String, Integer> entry : skillTotalExperience.entrySet()) {
            String skillName = entry.getKey();
            int totalExperience = entry.getValue();
            int associateCount = skillAssociateCounts.getOrDefault(skillName, 0);
            double avgExperience = associateCount > 0 ? (double) totalExperience / associateCount : 0;
            Object[] skillAvgExperienceInfo = { skillName, avgExperience };
            result.add(skillAvgExperienceInfo);
        }
        return result;
    }
}
