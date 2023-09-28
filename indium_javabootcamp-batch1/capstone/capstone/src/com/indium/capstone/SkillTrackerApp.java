package com.indium.capstone;

import com.indium.capstone.modal.Associate;
import com.indium.capstone.modal.Skill;
import com.indium.capstone.modal.SkillCat;
import com.indium.capstone.service.AssociateService;
import com.indium.capstone.service.AssociateServiceImp;
import com.indium.capstone.service.SkillService;
import com.indium.capstone.service.SkillServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SkillTrackerApp {
    static Scanner scanner = new Scanner(System.in);
    static AssociateService associateService = new AssociateServiceImp();
    static SkillService skillService = new SkillServiceImpl();
    public static List<Skill> storeSkillTem = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Skill Tracker App Menu =====");
            System.out.println("1. Add Associate     4. Delete Associate");
            System.out.println("2. List Associates   5. Search Associate");
            System.out.println("3. Edit Associate    6. Show Metrics");
            System.out.println("7. Exit");


            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNewAssociate();
                    break;
                case 2:
                    listAssociates();
                    break;
                case 3:
                    editAssociate();
                    break;
                case 4:
                    deleteAssociate();
                    break;
                case 5:
                    searchAssociate();
                    break;
                case 6:
                    showKeyMetrics();
                    break;
                case 7:
                    System.out.println("\nThank you for using the Skill Tracker App. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    private static void addNewAssociate() {
        System.out.println("Enter Associate Name:");
        String name = scanner.nextLine();

        System.out.println("Enter Associate Age:");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter Business Unit:");
        String businessUnit = scanner.nextLine();

        System.out.println("Enter Email:");
        String email = scanner.nextLine();

        System.out.println("Enter Location:");
        String location = scanner.nextLine();
        while (true) {
            System.out.print("Enter Skill (or press '1' to finish adding skills): ");
            String skillName = scanner.nextLine();

            if (skillName.equalsIgnoreCase("1")) {
                if (storeSkillTem.isEmpty()) {
                    System.out.println("Please enter at least one skill");
                    continue;
                }
                break;
            }
            System.out.println("Enter Skill Description:");
            String description = scanner.nextLine();

            System.out.println("Select Skill Category");
            System.out.println("1. Primary \n2. Secondary \nEnter your choice");
            int categoryChoice = scanner.nextInt();
            SkillCat category = null ;
            if(categoryChoice == 1) {
                category = SkillCat.Primary;
            }else {
                category = SkillCat.Secondary;
            }
            System.out.println("Enter Skill Experience (in months):");
            int experience = scanner.nextInt();
            scanner.nextLine();
            Skill skill = new Skill(0, skillName, description, category, experience,0);
            storeSkillTem.add(skill);
        }
        Associate associate = new Associate(0, name, age, businessUnit, email, location, storeSkillTem, "", "");
        associateService.addAssociate(associate);
        System.out.println("Associate added successfully!");
    }

    private static void listAssociates(){
        List<Associate> associates = associateService.getAllAssociates();

        if (associates.isEmpty()) {
            System.out.println("No associates found.");
        } else {
            printAssociates(associates);
        }
    }

    private static void deleteAssociate(){
        System.out.println("Enter the associates ID to delete ");
        int idToDelete = scanner.nextInt();
      boolean associateDeletionSuccess=  skillService.deleteSkillWithAssId(idToDelete);
       boolean skillDeletionSuccess= associateService.deleteAssociate(idToDelete);
        if (associateDeletionSuccess) {
            System.out.println("Associate Successfully Deleted");
        } else {
            System.out.println("Associate not Found.");
        }

        if (skillDeletionSuccess) {
            System.out.println("Skills associated with the Associate Successfully Deleted");
        } else {
            System.out.println("No Skills found associated with the Associate.");
        }
    }
    private static void editAssociate(){
        System.out.println("Enter the Associate ID ");
        int associateId = scanner.nextInt();
        scanner.nextLine();
        Associate associate = associateService.getAssociateById(associateId);
        String newName = associate.getName();
        int newAge = associate.getAge();
        String newBusinessUnit = associate.getBusinessUnit();
        String newEmail = associate.getEmail();
        String newLocation = associate.getLocation();
        if (associate != null){
            while (true) {
                System.out.println("Editing Associate: " + associate.getName());
                System.out.println("1. Edit Name");
                System.out.println("2. Edit Age");
                System.out.println("3. Edit Business Unit");
                System.out.println("4. Edit Email");
                System.out.println("5. Edit Location");
                System.out.println("6. view all skills ");
                System.out.println("7. Add Skill");
                System.out.println("8. Edit Skill");
                System.out.println("9. Delete Skill");
                System.out.println("10. Back to Main Menu");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("Enter the new name:");
                        newName = scanner.nextLine();
                        break;
                    case 2:
                        System.out.println("Enter the new age:");
                        newAge = scanner.nextInt();
                        break;
                    case 3:
                        System.out.println("Enter the new business unit:");
                        newBusinessUnit = scanner.nextLine();
                        break;
                    case 4:
                        System.out.println("Enter the new email:");
                        newEmail = scanner.nextLine();
                        break;
                    case 5:
                        System.out.println("Enter the new location:");
                        newLocation = scanner.nextLine();
                        break;
                    case 6:
                        viewAllSkillWithAssId(associate);
                        break;
                    case 7:
                        addSkillToAssociate(associate);
                        break;
                    case 8:
                        editSkillForAssociate(associate);
                        break;
                    case 9:
                        deleteSkillForAssociate(associate);
                        break;
                    case 10:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
                System.out.println("1. Continue Edit \n2. Submit \nEnter your choice");
                short editChoice = scanner.nextShort();

                if(editChoice==1) {
                    continue;
                }else {
                    Associate updateAssociate = new Associate(associate.getId(), newName, newAge, newBusinessUnit, newEmail, newLocation, null, "", "");
                    Boolean updated = associateService.updateAssociate(updateAssociate);

                    if(updated) {
                        System.out.println("Associate details updated successfully!");
                    }else {
                        System.out.println("update failed");
                    }
                    return;
                }
            }
        } else {
            System.out.println("Associate not found with the given ID.");
        }
    }

   private static void  viewAllSkillWithAssId(Associate associate){
       List<Skill> skills = skillService.getSkillsByAssId(associate.getId());

       if(skills.isEmpty()){
           System.out.println("No Skills found.");
       }else{
           System.out.println("Associate Skills\n");
           for (Skill skill:skills) {
               System.out.println("Skill ID: "+skill.getId() +"Skill Name: "+ skill.getName() +"Skill Description: "+ skill.getDescription()+"Skill Category: "+ skill.getCategory()+"Skill Experience: "+skill.getExperience());
           }
       }
    }

    private  static void addSkillToAssociate(Associate associate){
        while (true) {
            System.out.println("Enter Skill or press '1' to Stop");
            String skillName = scanner.nextLine();
            if (skillName.equals("1")) {
                break;
            }
            System.out.println("Enter Skill Description:");
            String description = scanner.nextLine();

            System.out.println("Select Skill Category");
            System.out.println("1. Primary \n2. Secondary \nEnter your choice");
            int categoryChoice = scanner.nextInt();
            SkillCat category = null ;
            if(categoryChoice == 1) {
                category = SkillCat.Primary;
            }else {
                category = SkillCat.Secondary;
            }
            System.out.println("Enter Skill Experience (in months):");
            int experience = scanner.nextInt();
            scanner.nextLine();
            Skill skill = new Skill(0, skillName, description, category, experience,associate.getId());
            boolean addSkill = skillService.addSkill(skill, associate.getId());
            if(addSkill) {
                System.out.println("Skill Added successfully");
            }else {
                System.out.println("While Adding Skills Failed.");
            }
        }

    }

  private static void editSkillForAssociate(Associate ass) {
    System.out.println("Enter the skill ID for edit: ");
    int idToEdit = scanner.nextInt();
    scanner.nextLine();

    Skill skill = skillService.getSkillById(idToEdit, ass.getId());

    if (skill != null) {
        System.out.println("Current Skill Details:");
        printSkill(skill);

        System.out.println("Enter Skill Description:");
        String description = scanner.nextLine();

        System.out.println("Select Skill Category");
        System.out.println("1. Primary \n2. Secondary \nEnter your choice");
        int categoryChoice = scanner.nextInt();
        SkillCat category = (categoryChoice == 1) ? SkillCat.Primary : SkillCat.Secondary;

        System.out.println("Enter Skill Experience (in months):");
        int experience = scanner.nextInt();
        scanner.nextLine();

        skill.setDescription(description);
        skill.setCategory(category);
        skill.setExperience(experience);

        boolean updated = skillService.updateSkill(skill, idToEdit);

        if (updated) {
            System.out.println("Skill updated successfully!");
        } else {
            System.out.println("Failed to update the skill.");
        }
    } else {
        System.out.println("Skill not found.");
    }
}

    private static void printSkill(Skill skill) {
        System.out.println("Skill ID: " + skill.getId());
        System.out.println("Skill Name: " + skill.getName());
        System.out.println("Skill Description: " + skill.getDescription());
        System.out.println("Skill Category: " + skill.getCategory());
        System.out.println("Skill Experience: " + skill.getExperience() + " months");
    }

    private static void deleteSkillForAssociate(Associate ass){
        System.out.println("Enter the skill's ID to delete:");
        int idToDelete = scanner.nextInt();
        boolean skillDelete = skillService.deleteSkill(idToDelete,ass.getId());
        if(skillDelete) {
            System.out.println("Skill Successfully Deleted");
        }else {
            System.out.println("Skill not Found.");
        }
    }

    private static void searchAssociate(){
        System.out.println("Search Options:");
        System.out.println("1. Search by Name");
        System.out.println("2. Search by ID");
        System.out.println("3. Search by Location");
        System.out.println("4. Search by Skill");
        System.out.println("5. Back to Main Menu");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Enter the associate's name to search:");
                String nameToSearch = scanner.nextLine();
                searchAssociatesByName(nameToSearch);
                break;
            case 2:
                System.out.println("Enter the associate's ID to search:");
                int idToSearch = scanner.nextInt();
                searchAssociatesById(idToSearch);
                break;
            case 3:
                System.out.println("Enter the location to search:");
                String locationToSearch = scanner.nextLine();
                searchAssociatesByLocation(locationToSearch);
                break;
            case 4:
                System.out.println("Enter the skill to search :");
                String skillsToSearch = scanner.nextLine();
                searchAssociatesBySkills(skillsToSearch);
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }


    }

   private static void searchAssociatesByName(String nameToSearch){
       List<Associate> associates = associateService.searchAssociatesByName(nameToSearch);
       if (associates.isEmpty()) {
           System.out.println("No associates found with the given name.");
       } else {
           System.out.println("Associates with the name '" + nameToSearch + "':");
           printAssociates(associates);
       }
   }

   private static void searchAssociatesById(int idToSearch){
       Associate associate = associateService.getAssociateById(idToSearch);
       if (associate == null) {
           System.out.println("No associate found with the given ID.");
       } else {
           System.out.println("Associate with ID " + idToSearch + ":");
           printAssociate(associate);
       }
   }

   private static void searchAssociatesByLocation(String locationToSearch){
       List<Associate> associates = associateService.searchAssociatesByLocation(locationToSearch);

       if (associates.isEmpty()) {
           System.out.println("No associates found in the location '" + locationToSearch + "'.");
       } else {
           System.out.println("Associates in the location '" + locationToSearch + "':");
           printAssociates(associates);
       }
   }

   private static void searchAssociatesBySkills(String skillsToSearch){
       List<Associate> associates = associateService.searchAssociatesBySkills(skillsToSearch);

       if (associates.isEmpty()) {
           System.out.println("No associates found with the specified skills.");
       } else {
           System.out.println("Associates with the specified skills:");
           printAssociates(associates);
       }
   }

    private static void printAssociates(List<Associate> associates) {
        for (Associate associate : associates) {
            printAssociate(associate);
        }
    }

    private static void printAssociate(Associate associate) {
        System.out.println("ID: " + associate.getId());
        System.out.println("Name: " + associate.getName());
        System.out.println("Age: " + associate.getAge());
        System.out.println("Business Unit: " + associate.getBusinessUnit());
        System.out.println("Email: " + associate.getEmail());
        System.out.println("Location: " + associate.getLocation());
        System.out.println("Create Time: " + associate.getCreateTime());
        System.out.println("Update Time: " + associate.getUpdateTime());


        List<Skill> skills = associate.getSkills();
        if (skills != null && !skills.isEmpty()) {
            System.out.println("Skills:");
            for (Skill skill : skills) {
                System.out.println("  Skill ID: " + skill.getId());
                System.out.println("  Skill Name: " + skill.getName());
                System.out.println("  Skill Description: " + skill.getDescription());
                System.out.println("  Skill Category: " + skill.getCategory());
                System.out.println("  Skill Experience: " + skill.getExperience() + " months");
            }
        } else {
            System.out.println("No skills found for this associate.");
        }
    }

    private static void showKeyMetrics(){
        while (true) {
            System.out.println("=== Key Metrics Menu ===");
            System.out.println("1. Total Associates");
            System.out.println("2. Associates with Many Skills");
            System.out.println("3. List of Associate IDs who have more than N skills");
            System.out.println("4. Associates with Specific Skills");
            System.out.println("5. Skills per Associate (Primary, Secondary)");
            System.out.println("6. Associates by Business Unit");
            System.out.println("7. Skills by Location");
            System.out.println("8. Skill wise Associate Count");
            System.out.println("9. Average Experience per Skill");
            System.out.println("10. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    int totalAssociates = associateService.getTotalAssociateCount();
                    System.out.println("Total Associates: " + totalAssociates);
                    break;

                case 2:
                    System.out.print("Enter minimum skills count: ");
                    int minSkillsCount = scanner.nextInt();
                    int associatesWithSkillsCount = associateService.getTotalAssociatesWithSkillsCount(minSkillsCount);
                    System.out.println("Associates with at least " + minSkillsCount + " skills: " + associatesWithSkillsCount);
                    break;

                case 3:
                    System.out.print("Enter minimum skills count: ");
                    minSkillsCount = scanner.nextInt();
                    List<Integer> associateIdsWithSkillsCount = associateService.getAssociateIdsWithSkillsCount(minSkillsCount);
                    System.out.println("Associate IDs with at least " + minSkillsCount + " skills : " + associateIdsWithSkillsCount);
                    break;

                case 4:
                    System.out.print("Enter skills (comma-separated): ");
                    String skills = scanner.nextLine();
                    int associatesWithGivenSkills = associateService.getTotalAssociatesWithGivenSkills(skills);
                    System.out.println("Associates with skills (" + skills + "): " + associatesWithGivenSkills);
                    break;
                case 5:
                    List<Object[]> skillPerAssociate = associateService.getAssociateWiseSkillCount();
                    for (Object[] info : skillPerAssociate) {
                        System.out.println("Associate ID: " + info[0]);
                        System.out.println("Associate Name: " + info[1]);
                        System.out.println("Primary Skill Count: " + info[2]);
                        System.out.println("Secondary Skill Count: " + info[3]);
                        System.out.println("------");
                    }
                    break;

                case 6:
                    List<Object[]> associatesByBusinessUnit = associateService.getBusinessUnitWiseAssociateCount();
                    for (Object[] info : associatesByBusinessUnit) {
                        System.out.println("Business Unit: " + info[0]);
                        System.out.println("Associate Count: " + info[1]);
                        System.out.println("------");
                    }
                    break;

                case 7:
                    List<Object[]> skillsByLocation = associateService.getLocationWiseSkillCount();
                    for (Object[] info : skillsByLocation) {
                        System.out.println("Location: " + info[0]);
                        System.out.println("Skill Count: " + info[1]);
                    }
                    break;

                case 8:
                    List<Object[]> associatesPerSkill = associateService.getSkillWiseAssociateCount();
                    for (Object[] info : associatesPerSkill) {
                        System.out.println("Skill: " + info[0]);
                        System.out.println("Associate Count: " + info[1]);
                    }
                    break;
                case 9:
                    List<Object[]> avgExperiencePerSkill = associateService.getSkillWiseAvgAssociateExperience();
                    for (Object[] info : avgExperiencePerSkill) {
                        System.out.println("Skill: " + info[0]);
                        System.out.println("Average Experience: " + info[1]);
                    }
                    break;
                case 10:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

    }


}
