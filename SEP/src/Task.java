//update in class diagram
import java.io.Serializable;

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
   * Four-Argument Constructor
   * @param name
   * @param description
   * @param estimatedTime
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
   * @param estimatedTime
   */
  public void setEstimatedTime(double estimatedTime)
  {
    this.estimatedTime = estimatedTime;
  }

  public void setSpentTime(double spentTime)
  {
    this.spentTime += spentTime;
  }

  /**
   * Gets the estimated time of completing the task
   * @return
   */
  public double getEstimatedTime()
  {
    return estimatedTime;
  }

  public double getSpentTime(){
    return spentTime;
  }

  /**
   * Sets the deadline for the task to be completed
   * @param deadline
   */
  public void setDeadline(MyDate deadline){
  this.deadline = deadline;
  }

  /**
   * Gets the deadline for the task that was set in the past
   * @return
   */
  public MyDate getDeadline(){
  return deadline;
  }

  /**
   * Checks if it is already past the deadline
   * @param deadline
   * @return
   */

  /**
   * Sets the employee that will be responsible for the task
   * @param responsibleEmployee
   */
  public void setResponsibleEmployee(AssignedEmployee responsibleEmployee){
    this.responsibleEmployee = responsibleEmployee;
  }

  /**
   * Gets employee that is responsible for the task
   * @return
   */
  public AssignedEmployee getResponsibleEmployee(){
  return responsibleEmployee;
  }

  /**
   * Sets progress status of the task
   * @param status
   */
  public void setProgressStatus(String status){
  this.status = status;
  }


  /**
   *
   * @return
   */
  public Task copy(){
    return new Task(name, description, estimatedTime, deadline, id, status, responsibleEmployee);
  }

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

  public String getName(){
    return name;
  }

  public void setName(String name){
    this.name = name;
  }

  public void setDescription(String description){
    this.description = description;
  }

  public String getDescription(){
    return description;
  }

  public void setId(int id){
    this.id =id;
  }

  public int getId(){
    return id;
  }

  public void setStatus(String status){
    this.status = status;
  }

  public String getStatus(){
    return status;
  }

  @Override public String toString()
  {
    return "Task{" + "id=" + id + ", name='" + name + '\'' + ", description='"
        + description + '\'' + ", estimatedTime=" + estimatedTime + ", status='"
        + status + '\'' + ", deadline=" + deadline + ", responsibleEmployee="
        + responsibleEmployee + ", spentTime=" + spentTime + '}';
  }
}
