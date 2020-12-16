import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class that creates and handles a Project
 * @author Bartosz Emanuel Ochedzan, Marketa Lapcikova, Timothy Johan Engkar
 * @version 1.0
 */
public class Project implements Serializable
{
  private String name;
  private String description;
  private String status;
  private int statusNumber;
  private ArrayList<Requirement> requirements;

  private AssignedEmployeeList assignedEmployeeList;

  private AssignedEmployee scrumMaster;
  private AssignedEmployee projectCreator;
  private AssignedEmployee productOwner;

    /**
   * 3-argument constructor initializing all the fields
   * @param name  the name of the project as String
   * @param description the description of the project as String
   * @param statusNumber the status of the project as int
   */
  public Project(String name, String description, int statusNumber){
    this.name = name;
    this.description = description;
    requirements = new ArrayList<Requirement>();
    assignedEmployeeList = new AssignedEmployeeList();
    ProgressStatus progressStatus = new ProgressStatus();
    this.status = progressStatus.chooseStatus(statusNumber);
    this.statusNumber = statusNumber;
    scrumMaster = new AssignedEmployee();
    projectCreator = new AssignedEmployee();
    productOwner = new AssignedEmployee();
  }

  /**
   * 2-argument constructor initializing all the fields
   * @param name the name of project as String
   * @param description the description of the project as String
   */
  public Project(String name, String description){
    this.name = name;
    this.description =description;
    requirements = new ArrayList<Requirement>();
    assignedEmployeeList = new AssignedEmployeeList();
    ProgressStatus progressStatus = new ProgressStatus();
    this.status = progressStatus.chooseStatus(0);
    this.statusNumber = 0;
    projectCreator = new AssignedEmployee();
    productOwner = new AssignedEmployee();
    scrumMaster = new AssignedEmployee();
  }

  /**
   * Gets all the requirements of the project as ArrayList
   * @return ArrayList of all requirements for this project
   */
  public ArrayList<Requirement> getRequirements(){
    return requirements;
  }

  /**
   * Gets a specific requirement of the project given as parameter
   * @param requirement requirement of type Requirement we want to get from the project
   * @return requirement of type Requirement if there is one, otherwise null
   */
  public Requirement getRequirement(Requirement requirement){
    for (int i = 0; i < requirements.size(); i++)
    {
      if(requirements.get(i).equals(requirement))
        return requirements.get(i);
    }
    return null;
  }

  /**
   * Gets the requirement of the project by its name
   * @param requirementName name of the requirement as String
   * @return requirement of type Requirement
   */
  public Requirement getRequirementByName(String requirementName){
    for (int i = 0; i < requirements.size(); i++) {
      if(requirements.get(i).getName().equals(requirementName)){
        return requirements.get(i);
      }
    }
    return null;
  }

  /**
   * Sets the name of a project.
   * @param name name of a project as String
   */
  public void setName(String name)
  {
    this.name = name;
  }

  /**
   * Gets the name of a project.
   * @return the name of a project as String
   */
  public String getName()
  {
    return name;
  }

  /**
   * Sets the project´s description
   * @param description description of a project as String
   */
  public void setDescription(String description)
  {
    this.description = description;
  }

  /**
   * Gets the project´s description
   * @return the project´s description as String
   */
  public String getDescription()
  {
    return description;
  }

  /**
   * Gets all the assigned employees
   * @return List of all assigned employees to the project as AssignedEmployeeList
   */
  public AssignedEmployeeList getAssignedEmployeeList(){
    return assignedEmployeeList;
  }

  /**
   * Gets project´s progress status
   * @return project status as String
   */
  public String getStatus(){
    return status;
  }

  /**
   * Gets the status number
   * @return status number of the project as int
   */
  public int getStatusNumber(){
    return statusNumber;
  }

  /**
   * Sets project´s progress status
   * @param index what progress status change to as int
   */
  public void setStatus(int index){
    ProgressStatus progressStatus = new ProgressStatus();
    this.status = progressStatus.chooseStatus(index);
  }

  /**
   * Adds a team member to the project
   * @param teamMember desired team member of type AssignedEmployee
   */
  public void addTeamMember(AssignedEmployee teamMember){
    this.assignedEmployeeList.addAssignedEmployee(teamMember);
  }

  /**
   * Removes a team member from the project
   * @param teamMember desired team member of type AssignedEmployee
   */
  public void removeTeamMember(AssignedEmployee teamMember){
  this.assignedEmployeeList.removeEmployee(teamMember);
  }

  /**
   * Removes all the team members from the project
   */
  public void removeAllTeamMembers(){
    for (int i = 0; i < assignedEmployeeList.size(); i++) {
      assignedEmployeeList.removeEmployee(assignedEmployeeList.get(i));
    }
  }
  /**
   * Adds a requirement to the project
   * @param requirement requirement that is about to be added to the project of type Requirement
   */
  public void addRequirement(Requirement requirement){
    this.requirements.add(requirement);
  }

  /**
   * Removes a requirement from the project
   * @param requirement requirement that is about to be removed from the project
   */
  public void removeRequirement(Requirement requirement){
  this.requirements.remove(requirement);
  }

  /**
   * Sets the scrum master of the project to the the given one
   * @param scrumMaster AssignedEmployee that should be set as a scrum master
   */
  public void setScrumMaster(AssignedEmployee scrumMaster){
    this.scrumMaster = scrumMaster;
  }

  /**
   * Sets the project creator of the project to the the given one
   * @param projectCreator AssignedEmployee that should be set as a project creator
   */
  public void setProjectCreator(AssignedEmployee projectCreator){
    this.projectCreator = projectCreator;
  }

  /**
   * Sets the product owner of the project to the the given one
   * @param productOwner AssignedEmployee that should be set as a product owner
   */
  public void setProductOwner(AssignedEmployee productOwner){
    this.productOwner = productOwner;
  }

  /**
   * Gets the scrum master of the project
   * @return AssignedEmployee that is a scrum master
   */
  public AssignedEmployee getScrumMaster(){
    return scrumMaster;
  }

  /**
   * Gets the project creator of the project
   * @return AssignedEmployee that is a project creator
   */
  public AssignedEmployee getProjectCreator(){
    return projectCreator;
  }

  /**
   * Gets the product owner of the project
   * @return AssignedEmployee that is a product owner
   */
  public AssignedEmployee getProductOwner(){
    return productOwner;
  }

  /**
   * Gets the information about the project, its name, as String
   * @return name of the project as String
   */
  public String toString(){
    return getName();
  }

  /**
   * Compares the Project with another object
   * @param obj the object we are comparing this Project to
   * @return true if the two objects are equal, false if otherwise
   */
  public boolean equals(Object obj){
    if(!(obj instanceof Project)){
      return false;
    }
    Project temp = (Project) obj;
    return temp.getName().equals(name) && temp.getDescription().equals(description) && temp.getStatus().equals(status) &&
        temp.getStatusNumber() == statusNumber && temp.getRequirements().equals(requirements) && temp.getAssignedEmployeeList().equals(assignedEmployeeList)
        && temp.getProductOwner().equals(productOwner) && temp.getScrumMaster().equals(scrumMaster) && getProjectCreator().equals(projectCreator);
  }
}
