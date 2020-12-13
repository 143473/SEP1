import java.io.Serializable;
import java.util.ArrayList;

public class Requirement implements Serializable
{
  private int id;
  private String name, userStory;
  private double estimatedTime;
  private int importance;
  private String status;
  private ArrayList<Task> tasks;
  private MyDate deadline;
  private double spentTime;
  private AssignedEmployeeList employeeList;
  private AssignedEmployee responsibleEmployee;



  public Requirement(String name, String userStory, double estimatedTime, int importance, AssignedEmployee responsibleEmployee, MyDate deadline, int id, String status)
  {
    this.name = name;
    this.userStory = userStory;
    this.estimatedTime = estimatedTime;
    this.importance = importance;
    this.responsibleEmployee = responsibleEmployee;
    this.deadline = deadline;
    tasks = new ArrayList<Task>();
    this.id = id;
    spentTime = 0;
    this.status = status;
  }


  public Requirement(String name, String userStory, double estimatedTime, MyDate deadline, int id)
  {
    this.name = name;
    this.userStory = userStory;
    this.estimatedTime = estimatedTime;
    this.deadline = deadline;
    importance = 1;
    tasks = new ArrayList<Task>();
    status = "Not started";
    spentTime = 0;
  }

  public String getName()
  {
    return name;
  }

  public String getUserStory()
  {
    return userStory;
  }
  /**
   * Gets progress status of the requirement
   *
   * @return a String of the status
   */
  public String getStatus(){
    return status;
  }
  /**
   * Sets progress status of the requirement
   * @param index from 1 to 4
   */
  public void setStatus(int index){
    ProgressStatus progressStatus = new ProgressStatus();
    this.status = progressStatus.chooseStatus(index);
  }

  public void setId(int id){
    this.id = id;
  }

  public void setName(String name){
    this.name = name;
  }

  public void setUserStory(String userStory){
    this.userStory = userStory;
  }

  public void setStatus(String status){
    this.status = status;
  }

  /**
   * Sets importance of the requirement
   *
   * @param importance with a value from 1 to 3
   */
  public void setImportance(int importance)
  {
    this.importance = importance;
  }

  /**
   * Gets importance of the requirement
   *
   * @return with value from 1 to 3
   */
  public int getImportance()
  {
    return importance;
  }

  /**
   * Gets time tha
   *
   * @return
   */
  public double getSpentTime()
  {
    return spentTime;
  }

  /**
   * Checks if any task has been assigned to this requirement
   *
   * @return true if it is, false if it is not
   */
  public boolean isAssignedTask()
  {
  return false;
  }

  /**
   * Sets estimated time that requirement will be finished in
   *
   * @param estimatedTime
   */
  public void setEstimatedTime(double estimatedTime)
  {
    this.estimatedTime = estimatedTime;
  }

  /**
   * Gets estimated time that requirement will be finished in
   *
   * @return
   */
  public double getEstimatedTime()
  {
    return estimatedTime;
  }

  /**
   * Sets deadline for the requirement
   *
   * @param deadline
   */
  public void setDeadline(MyDate deadline)
  {
  this.deadline = deadline;
  }

  /**
   * Gets deadiine for the requirement
   *
   * @return
   */
  public MyDate getDeadline()
  {
  return deadline;
  }

  /**
   * Checks if it is already past the deadline
   *
   * @param deadline deadline that was set
   * @return true if it is, false if it is not
   */
  public boolean isPastDeadline(MyDate deadline)
  {
  return false;
  }

  /**
   * Sets employee that will be responsible for the requirement
   *
   * @param responsibleEmployee
   */
  public void setResponsibleEmployee(AssignedEmployee responsibleEmployee)
  {
    this.responsibleEmployee = responsibleEmployee;
  }

  /**
   * Gets employee that is responsible for the requirement
   *
   * @return
   */
  public AssignedEmployee getResponsibleEmployee()
  {
   return responsibleEmployee;
  }

  /**
   * Sets progress status of the requirement
   *
   * @param progressStatus
   */
  public void setProgressStatus(String progressStatus)
  {
  this.status = progressStatus;
  }




  /**
   * Adds a task to the requirement
   *
   * @param task
   */
  public void addTask(Task task)
  {
  this.tasks.add(task);
  }

  /**
   * Removes a task from the requirement
   *
   * @param task
   */
  public void removeTask(Task task)
  {
  this.tasks.remove(task);
  }

  /**
   * Gets list of all members that are working on that requirement
   *
   * @return
   */
  public AssignedEmployeeList getWorkingMembers()
  {
  return employeeList;
  }

  /**
   *
   * @return
   */
  public Requirement copy()
  {
    if(importance!=1){
      return new Requirement(name, userStory, estimatedTime, importance, responsibleEmployee, deadline, id, status);
    }else return new Requirement(name, userStory, estimatedTime, deadline, id);
  }
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Requirement))
    {
      return false;
    }
    Requirement temp = (Requirement) obj;
    return temp.getName().equals(name) && temp.getUserStory().equals(userStory)
        && temp.getStatus().equals(status) && temp.getEstimatedTime() == estimatedTime && temp.deadline == deadline;
  }

  public ArrayList<Task> getTasks(){
    return tasks;
  }

  public int getId(){
    return id;
  }

  public String toString(){
    return name;
  }


}