/**
 * A class that creates a Project with a name and a description
 * @author Bartosz Ochedzan
 * @version 1.0
 */
public class Project
{
  private String name;
  private String description;

  /**
   * Two-argument constructor.
   * @param name
   * @param description
   */
  public Project(String name, String description){
    this.name = name;
    this.description = description;
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

  }

  /**
   * Gets all the assigned employees
   * @return List of all assigned employees to the project
   */
  public ArrayList<AssignedEmployee> getEmployees(){
  return assignedEmployee.get();
}

  /**
   * Gets project´s progress status
   * @return project status
   */
  public ProgressStatus getProgressStatus(){
    return progresStatus.copy();
  }

  /**
   * Sets project´s progress status
   * @param status what progress staus change to
   */
  public void setProgressStatus(ProgressStatus status){

  }

  /**
   * Adds a team member to the project
   * @param teamMember desired team member
   */
  public void addTeamMember(AssignedEmployee teamMember){
    teamMember = new AssignedEmployee();
  }

  /**
   * Removes a team member from the project
   * @param teamMember desired team member
   */
  public void removeTeamMember(AssignedEmployee teamMember){

  }

  /**
   * Adds a requirement to the project
   * @param requirement requirement that is about to be added to the project
   */
  public void addRequirement(Requirement requirement){
    requirement = new Requirement();
  }

  /**
   * Removes a requirement from the project
   * @param requirement requirement that is about to be removed from the project
   */
  public void removeRequirement(Requirement requirement){

  }
}
