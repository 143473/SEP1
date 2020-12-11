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
    AssignedEmployee scrumMaster;
    AssignedEmployee projectCreator;
    AssignedEmployee productOwner;
  }

  public Project(String name, String description){
    this.name = name;
    this.description =description;
    requirements = new ArrayList<Requirement>();
    assignedEmployeeList = new AssignedEmployeeList();
    ProgressStatus progressStatus = new ProgressStatus();
    this.status = progressStatus.chooseStatus(0);
    this.statusNumber = 0;
  }

  public boolean equals(Object obj){
    if(!(obj instanceof Project)){
      return false;
    }
    Project temp = (Project) obj;
    return temp.name.equals(name) && temp.description.equals(description) && temp.status.equals(status) &&
            temp.statusNumber == statusNumber && temp.requirements.equals(requirements) && temp.assignedEmployeeList.equals(assignedEmployeeList);
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
  public void setProgressStatus(int index){
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
}
