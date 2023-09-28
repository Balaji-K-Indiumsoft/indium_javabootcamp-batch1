package com.indium.capstone.DAO;

import com.indium.capstone.modal.Associate;

import java.util.List;

public interface AssociateDAO {

    boolean addAssociate(Associate associate);
    boolean updateAssociate(Associate associate);
    boolean deleteAssociate(int id);
    Associate getAssociate(int associateId);
    List<Associate> getAllAssociates();
    List<Associate> searchAssociatesByName(String name);
    List<Associate> searchAssociatesByLocation(String location);
    List<Associate> searchAssociatesBySkills(String skills);

}
