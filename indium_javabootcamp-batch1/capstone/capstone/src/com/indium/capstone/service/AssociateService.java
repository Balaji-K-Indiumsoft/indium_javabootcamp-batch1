package com.indium.capstone.service;

import com.indium.capstone.modal.Associate;

import java.util.List;
import java.util.Map;

public interface AssociateService {

    boolean addAssociate(Associate associate);
    List<Associate> getAllAssociates();
    Associate getAssociateById(int id);
    boolean updateAssociate(Associate associate);
    boolean deleteAssociate(int id);
    List<Associate> searchAssociatesByName(String name);
    List<Associate> searchAssociatesByLocation(String location);
    List<Associate> searchAssociatesBySkills(String skill);

    int getTotalAssociateCount();

    int getTotalAssociatesWithSkillsCount(int minSkillsCount);

    List<Integer> getAssociateIdsWithSkillsCount(int minSkillsCount);

    int getTotalAssociatesWithGivenSkills(String skills);

    List<Object[]> getAssociateWiseSkillCount();

    List<Object[]> getBusinessUnitWiseAssociateCount();

    List<Object[]> getLocationWiseSkillCount();

    List<Object[]> getSkillWiseAssociateCount();

    List<Object[]> getSkillWiseAvgAssociateExperience();
}
