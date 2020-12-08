import java.util.ArrayList;

public class Requirement
{
  private int id;
  private double estimatedTime;
  private int importance;
  private ProgressStatus status;
  private ArrayList<Task> tasks;
  private MyDate deadline;
  private double spentTime;

  /**
   * Four-argument constructor that also sets the importance of a requirement
   *
   * @param estimatedTime
   * @param importance
   * @param responsibleEmployee
   * @param deadline
   */
  public Requirement(double estimatedTime, int importance, AssignedEmployee responsibleEmployee, MyDate deadline)
  {
    this.estimatedTime = estimatedTime;
    this.importance = importance;
    responsibleEmployee = new AssignedEmployee();
    deadline = new MyDate();
  }

  /**
   * Three-parameter constructor that does not set the importance of a requirement(To be set later)
   *
   * @param estimatedTime
   * @param responsibleEmployee
   * @param deadline
   */
  public Requirement(double estimatedTime, AssignedEmployee responsibleEmployee, MyDate deadline)
  {
    this.estimatedTime = estimatedTime;
    responsibleEmployee = new AssignedEmployee();
    deadline = new MyDate();
    importance = 0;
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

  }

  /**
   * Sets employee that will be responsible for the requirement
   *
   * @param responsibleEmployee
   */
  public void setResponsibleEmployee(AssignedEmployee responsibleEmployee)
  {
  setResponsibleEmployee(responsibleEmployee);
  }

  /**
   * Gets employee that is responsible for the requirement
   *
   * @return
   */
  public AssignedEmployee getResponsibleEmployee()
  {
   return getResponsibleEmployee();
  }

  /**
   * Sets progress status of the requirement
   *
   * @param progressStatus
   */
  public void setProgressStatus(ProgressStatus progressStatus)
  {
  this.status = progressStatus;
  }

  /**
   * Gets progress status of the requirement
   *
   * @return
   */
  public ProgressStatus getProgressStatus()
  {
    return status;
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
  public ArrayList<AssignedEmployee> getWorkingMembers()
  {

  }

  /**
   *
   * @return
   */
  public Requirement copy()
  {
    if(importance!=0){
      return Requirement(estimatedTime, importance, responsibleEmployee, deadline);
    }else return Requirement(estimatedTime, responsibleEmployee, deadline);
  }
}