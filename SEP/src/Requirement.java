import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class that creates and handles a Requirement
 * @author Bartosz Emanuel Ochedzan, Marketa Lapcikova, Timothy Johan Engkar
 * @version 1.0
 */
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
  private Project project;

  /**
   * 7-arguments constructor initializing the fields
   * @param name the name of the requirement as String
   * @param userStory the user story of the requirement as String
   * @param importance the importance of the requirement as int
   * @param responsibleEmployee the employee responsible for this requirement of type AssignedEmployee
   * @param deadline the deadline of this requirement as MyDate
   * @param id the id of this requirement as int
   * @param status the status of this requirement as String
   */
  public Requirement(String name, String userStory, int importance, AssignedEmployee responsibleEmployee, MyDate deadline, int id, String status)
  {
    this.name = name;
    this.userStory = userStory;
    this.estimatedTime = 0;
    this.importance = importance;
    this.responsibleEmployee = responsibleEmployee;
    this.deadline = deadline;
    tasks = new ArrayList<Task>();
    this.id = id;
    spentTime = 0;
    this.status = status;
    this.project = null;
  }

  /**
   * 4-argument constructor initializing the fields
   * @param name the name of the requirement as String
   * @param userStory the user story of the requirement as String
   * @param deadline the deadline of this requirement as MyDate
   * @param id the id of this requirement as int
   */
  public Requirement(String name, String userStory,  MyDate deadline, int id)
  {
    this.name = name;
    this.userStory = userStory;
    this.estimatedTime = 0;
    this.deadline = deadline;
    importance = 1;
    tasks = new ArrayList<Task>();
    status = "Not started";
    spentTime = 0;
    this.project = null;
    this.id = id;
  }

  /**
   * Gets the name of this requirement
   * @return name of requirement as String
   */
  public String getName()
  {
    return name;
  }

  /**
   * Sets the Project of this requirement
   * @param project Project type of project of this requirement
   */
  public void setProject(Project project)
  {
    this.project = project;
  }

  /**
   * Gets the Project this requirement belongs to
   * @return Project type of project this requirement belongs to
   */
  public Project getProject()
  {
    return project;
  }

  /**
   * Gets the user story of this requirement
   * @return String type of user story of the requirement
   */
  public String getUserStory()
  {
    return userStory;
  }

  /**
   * Gets progress status of the requirement
   * @return a String of the status
   */
  public String getStatus(){
    return status;
  }

  /**
   * Sets progress status of the requirement
   * @param index from 0 to 3
   */
  public void setStatus(int index){
    ProgressStatus progressStatus = new ProgressStatus();
    this.status = progressStatus.chooseStatus(index);
  }

  /**
   * Sets the id of the requirement
   * @param id int type of id
   */
  public void setId(int id){
    this.id = id;
  }

  /**
   * Sets the name of the requirement
   * @param name String type of name
   */
  public void setName(String name){
    this.name = name;
  }

  /**
   * Sets the user story of the requirement
   * @param userStory String type of user story
   */
  public void setUserStory(String userStory){
    this.userStory = userStory;
  }

  /**
   * Sets the status of requirement
   * @param status String type of status
   */
  public void setStatus(String status){
    this.status = status;
  }

  /**
   * Sets importance of the requirement
   * @param importance with a value from 1 to 3
   */
  public void setImportance(int importance)
  {
    this.importance = importance;
  }

  /**
   * Gets importance of the requirement
   * @return with value from 1 to 3
   */
  public int getImportance()
  {
    return importance;
  }

  /**
   * Gets time that was spent on this requirement, thus spent on all the tasks belonging to this requirement
   * @return double of time spent on this requirement
   */
  public double getSpentTime()
  {
    double sum = 0;
    for (int i = 0; i < tasks.size(); i++)
    {
      sum+= tasks.get(i).getSpentTime();
    }
    this.spentTime = sum;
    return spentTime;
  }

  /**
   * Gets the estimated time for this requirement, thus sum of all estimated times of tasks belonging to this requirement
   * @return
   */
  public double getEstimatedTime()
  {
    double sum = 0;
    for (int i = 0; i < tasks.size(); i++)
    {
      sum+= tasks.get(i).getEstimatedTime();
    }
    this.estimatedTime = sum;
    return estimatedTime;
  }

  /**
   * Sets deadline for the requirement
   * @param deadline MyDate type of deadline of the requirement
   */
  public void setDeadline(MyDate deadline)
  {
  this.deadline = deadline;
  }

  /**
   * Gets deadline for the requirement
   * @return MyDate type of deadline of the requirement
   */
  public MyDate getDeadline()
  {
  return deadline;
  }

  /**
   * Gets a specific task belonging to this requirement
   * @param task Task type of task we want to get
   * @return Task type of given task from the tasks belonging to this requirement
   */
  public Task getTask(Task task){
    for (int i = 0; i < tasks.size(); i++) {
      if(tasks.get(i).equals(task)){
        return tasks.get(i);
      }
    }
    return null;
  }

  /**
   * Gets the task belonging to this requirement by its name
   * @param taskName the name of desired task as String
   * @return Task type of desired task
   */
  public Task getTaskByName(String taskName){
    for (int i = 0; i < tasks.size(); i++) {
      if(tasks.get(i).getName().equals(taskName)){
        return tasks.get(i);
      }
    }
    return null;
  }

  /**
   * Sets employee that will be responsible for the requirement
   * @param responsibleEmployee of type AssignedEmployee that is responsible for this requirement
   */
  public void setResponsibleEmployee(AssignedEmployee responsibleEmployee)
  {
    this.responsibleEmployee = responsibleEmployee;
  }

  /**
   * Gets employee that is responsible for the requirement
   * @return responsibleEmployee of type AssignedEmployee that is responsible for this requirement
   */
  public AssignedEmployee getResponsibleEmployee()
  {
   return responsibleEmployee;
  }

  /**
   * Sets progress status of the requirement
   * @param progressStatus of type String of this requirement
   */
  public void setProgressStatus(String progressStatus)
  {
  this.status = progressStatus;
  }

  /**
   * Adds a task to the requirement
   * @param task of type Task we want to add to the requirement
   */
  public void addTask(Task task)
  {
  this.tasks.add(task);
  }

  /**
   * Removes a task from the requirement
   * @param task of type Task we want to remove from the requirement
   */
  public void removeTask(Task task)
  {
  this.tasks.remove(task);
  }

  /**
   * Gets list of all members that are working on that requirement
   * @return AssignedEmployeeList of all members working on given requirement
   */
  public AssignedEmployeeList getWorkingMembers()
  {
  return employeeList;
  }

  /**
   * Gets the copy of the requirement
   * @return Requirement type of the requirement as copy
   */
  public Requirement copy()
  {
    if(importance!=1){
      return new Requirement(name, userStory,  importance, responsibleEmployee, deadline, id, status);
    }else return new Requirement(name, userStory,  deadline, id);
  }

  /**
   * Compares the Requirement with another object
   * @param obj the object we are comparing this Requirement to
   * @return true if the two objects are equal, false if otherwise
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Requirement))
    {
      return false;
    }
    Requirement temp = (Requirement) obj;
    return temp.getName().equals(name) && temp.getUserStory().equals(userStory)
        && temp.getStatus().equals(status) && temp.getEstimatedTime() == estimatedTime && temp.deadline.equals(deadline);
  }

  /**
   * Gets all the tasks belonging to this requirement
   * @return ArrayList<Task> of all tasks of this requirement
   */
  public ArrayList<Task> getTasks(){
    return tasks;
  }

  /**
   * Gets the id of this requirement
   * @return int type of id
   */
  public int getId(){
    return id;
  }

  /**
   * Gets the information, name, about the requirement as String
   * @return String type of information about the requirement
   */
  public String toString(){
    return name;
  }
}