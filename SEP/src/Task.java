import java.io.Serializable;

/**
 * A class that creates and handles a Requirement
 * @author Bartosz Emanuel Ochedzan, Marketa Lapcikova, Timothy Johan Engkar
 * @version 1.0
 */
public class Task implements Serializable
{
  private int id;
  private String name;
  private String description;
  private double estimatedTime;
  private String status;
  private MyDate deadline;
  private AssignedEmployee responsibleEmployee;
  private double spentTime;

  /**
   * 7-argument constructor initializing all the fields
   * @param name String type of name of the task
   * @param description String type of description of the task
   * @param estimatedTime double type of estimated time for the task
   * @param deadline MyDate type of deadline for the task
   * @param id int type of id of the task
   * @param status String type of status of the task
   * @param responsibleEmployee AssignedEmployee type of employee responsible for the task
   */
  public Task(String name, String description, double estimatedTime, MyDate deadline, int id, String status, AssignedEmployee responsibleEmployee){
    this.name = name;
    this.description = description;
    this.estimatedTime = estimatedTime;
    this.responsibleEmployee = responsibleEmployee;
    this.deadline = deadline;
    this.id = id;
    this.status = status;
    spentTime = 0;
  }

  /**
   * Sets estimated time that will be needed for completing the task
   * @param estimatedTime double type of time estimation
   */
  public void setEstimatedTime(double estimatedTime)
  {
    this.estimatedTime = estimatedTime;
  }

  /**
   * Sets the time spent on this task, adds it to current time
   * @param spentTime double time that is about to be added to current spent time
   */
  public void setSpentTime(double spentTime)
  {
    this.spentTime += spentTime;
  }

  /**
   * Gets the estimated time of completing the task
   * @return double type of time estimated to complete the task
   */
  public double getEstimatedTime()
  {
    return estimatedTime;
  }

  /**
   * Gets the time spent on the task
   * @return double type of time spent
   */
  public double getSpentTime(){
    return spentTime;
  }

  /**
   * Sets the deadline for the task to be completed
   * @param deadline MyDate type of deadline of the task
   */
  public void setDeadline(MyDate deadline){
  this.deadline = deadline;
  }

  /**
   * Gets the deadline for the task that was set in the past
   * @return deadline MyDate type of deadline of the task
   */
  public MyDate getDeadline(){
  return deadline;
  }

  /**
   * Sets the employee that will be responsible for the task
   * @param responsibleEmployee AssignedEmployee responsible for the task
   */
  public void setResponsibleEmployee(AssignedEmployee responsibleEmployee){
    this.responsibleEmployee = responsibleEmployee;
  }

  /**
   * Gets employee that is responsible for the task
   * @return responsibleEmployee AssignedEmployee responsible for the task
   */
  public AssignedEmployee getResponsibleEmployee(){
  return responsibleEmployee;
  }

  /**
   * Sets progress status of the task
   * @param status String type of the task
   */
  public void setProgressStatus(String status){
  this.status = status;
  }

  /**
   * Gets the copy of this task
   * @return Task type of this task as a copy
   */
  public Task copy(){
    return new Task(name, description, estimatedTime, deadline, id, status, responsibleEmployee);
  }

  /**
   * Compares the Task with another object
   * @param obj the object we are comparing this Task to
   * @return true if the two objects are equal, false if otherwise
   */
  public boolean equals(Object obj) {
    if (!(obj instanceof Task)) {
      return false;
    }
    Task other = (Task) obj;
    return id == other.id &&
            estimatedTime == other.estimatedTime &&
            name.equals(other.name) &&
            description.equals(other.description) &&
            status.equals(other.status) &&
            deadline.equals(other.deadline) &&
            responsibleEmployee.equals(other.responsibleEmployee);
  }

  /**
   * Gets the name of the task
   * @return String type of the name of the task
   */
  public String getName(){
    return name;
  }

  /**
   * Sets the name of the task
   * @param name String type of the name of the task
   */
  public void setName(String name){
    this.name = name;
  }

  /**
   * Sets the description of the task
   * @param description String type of description of the task
   */
  public void setDescription(String description){
    this.description = description;
  }

  /**
   * Gets the description of the task
   * @return description String type of description of the task
   */
  public String getDescription(){
    return description;
  }

  /**
   * Sets the id of the task
   * @param id int type of id of the task
   */
  public void setId(int id){
    this.id =id;
  }

  /**
   * Gets the id of the task
   * @return id int type of id of the task
   */
  public int getId(){
    return id;
  }

  /**
   * Sets the status of the task
   * @param status String type of status of the task
   */
  public void setStatus(String status){
    this.status = status;
  }

  /**
   * Gets the status of the task
   * @return status String type of status of the task
   */
  public String getStatus(){
    return status;
  }

  /**
   * Gets the information desired about the task as String
   * @return String type of information about the task
   */
  @Override public String toString()
  {
    return "Task{" + "id=" + id + ", name='" + name + '\'' + ", description='"
        + description + '\'' + ", estimatedTime=" + estimatedTime + ", status='"
        + status + '\'' + ", deadline=" + deadline + ", responsibleEmployee="
        + responsibleEmployee + ", spentTime=" + spentTime + '}';
  }
}
