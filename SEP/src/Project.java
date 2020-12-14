import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class that creates a Project with a name and a description
 * @author Bartosz Ochedzan
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

  /*
  public Project(){
    name = "";
    description = "";
    requirements = new ArrayList<Requirement>();
    assignedEmployeeList = new AssignedEmployeeList();
    ProgressStatus progressStatus = new ProgressStatus();
    status = progressStatus.chooseStatus(0);
    statusNumber = 0;
    scrumMaster = new AssignedEmployee();
    projectCreator = new AssignedEmployee();
    productOwner = new AssignedEmployee();
  }
*/

  /**
   * Three-argument constructor.
   * @param name
   * @param description
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

  public boolean equals(Object obj){
    if(!(obj instanceof Project)){
      return false;
    }
    Project temp = (Project) obj;
    return temp.getName().equals(name) && temp.getDescription().equals(description) && temp.getStatus().equals(status) &&
            temp.getStatusNumber() == statusNumber && temp.getRequirements().equals(requirements) && temp.getAssignedEmployeeList().equals(assignedEmployeeList)
            && temp.getProductOwner().equals(productOwner) && temp.getScrumMaster().equals(scrumMaster) && getProjectCreator().equals(projectCreator);
  }

  public ArrayList<Requirement> getRequirements(){
    return requirements;
  }

  public Requirement getRequirement(Requirement requirement){
    for (int i = 0; i < requirements.size(); i++) {
      if(requirements.get(i).equals(requirement)){
        return requirements.get(i);
      }
    }
    return null;
  }

  /**
   * Sets the name of a project.
   * @param name name of a project
   */
  public void setName(String name)
  {
    this.name = name;
  }

  /**
   * Gets the name of a project.
   * @return the name of a project
   */
  public String getName()
  {
    return name;
  }

  /**
   * Sets the project´s description
   * @param description description of a project
   */
  public void setDescription(String description)
  {
    this.description = description;
  }

  /**
   * Gets the project´s description
   * @return the project´s description
   */
  public String getDescription()
  {
    return description;
  }

  /**
   * Checks if project contains any assigned tasks
   * @return true if contains, false if not
   */
  public boolean isAssignedTask(){
return false;
  }

  /**
   * Gets all the assigned employees
   * @return List of all assigned employees to the project
   */
  public AssignedEmployeeList getAssignedEmployeeList(){
    return assignedEmployeeList;
  }

  /**
   * Gets project´s progress status
   * @return project status
   */
  public String getStatus(){
    return status;
  }

  public int getStatusNumber(){
    return statusNumber;
  }
  /**
   * Sets project´s progress status
   * @param index what progress staus change to
   */
  public void setStatus(int index){
    ProgressStatus progressStatus = new ProgressStatus();
    this.status = progressStatus.chooseStatus(index);
  }

  /**
   * Adds a team member to the project
   * @param teamMember desired team member
   */
  public void addTeamMember(AssignedEmployee teamMember){
    this.assignedEmployeeList.addAssignedEmployee(teamMember);
  }

  /**
   * Removes a team member from the project
   * @param teamMember desired team member
   */
  public void removeTeamMember(AssignedEmployee teamMember){
  this.assignedEmployeeList.removeEmployee(teamMember);
  }


  public void removeAllTeamMembers(){
    for (int i = 0; i < assignedEmployeeList.size(); i++) {
      assignedEmployeeList.removeEmployee(assignedEmployeeList.get(i));
    }
  }
  /**
   * Adds a requirement to the project
   * @param requirement requirement that is about to be added to the project
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

  public void setScrumMaster(AssignedEmployee scrumMaster){
    this.scrumMaster = scrumMaster;
  }
  public void setProjectCreator(AssignedEmployee projectCreator){
    this.projectCreator = projectCreator;
  }
  public void setProductOwner(AssignedEmployee productOwner){
    this.productOwner = productOwner;
  }
  public AssignedEmployee getScrumMaster(){
    return scrumMaster;
  }
  public AssignedEmployee getProjectCreator(){
    return projectCreator;
  }
  public AssignedEmployee getProductOwner(){
    return productOwner;
  }
  public String toString(){
    return getName()+"\n"+getAssignedEmployeeList();
  }
}
