import java.util.ArrayList;

public class Task
{
  private int id;
  private String name;
  private String description;
  private double estimatedTime;
  private ProgressStatus status;
  private MyDate deadline;
  private double spentTime;
  private ArrayList<AssignedEmployee> employeeList;
  /**
   * Four-Argument Constructor
   * @param name
   * @param description
   * @param estimatedTime
   * @param responsibleEmployee
   */
  public Task(String name, String description,MyDate deadline, double estimatedTime, AssignedEmployee responsibleEmployee){
    this.name = name;
    this.description = description;
    this.estimatedTime = estimatedTime;
    responsibleEmployee = new AssignedEmployee(responsibleEmployee.getFirstName(),responsibleEmployee.getLastName(),responsibleEmployee.getDateOfBirth(),responsibleEmployee.getStatus());

    spentTime = 0;
  }

  /**
   * Gets time spent on that task
   * @return
   */
  public double getSpentTime(){
  return spentTime;
  }

  /**
   * Sets estimated time that will be needed for completing the task
   * @param estimatedTime
   */
  public void setEstimatedTime(double estimatedTime)
  {
    this.estimatedTime = estimatedTime;
  }

  /**
   * Gets the estimated time of completing the task
   * @return
   */
  public double getEstimatedTime()
  {
    return estimatedTime;
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
  public boolean isPastDeadline(MyDate deadline){
  return false;
  }

  /**
   * Sets the employee that will be responsible for the task
   * @param employee
   */
  public void setResponsibleEmployee(AssignedEmployee employee){
  setResponsibleEmployee(employee);
  }

  /**
   * Gets employee that is responsible for the task
   * @return
   */
  public AssignedEmployee getResponsibleEmployee(){
  return getResponsibleEmployee();
  }

  /**
   * Sets progress status of the task
   * @param progressStatus
   */
  public void setProgressStatus(ProgressStatus progressStatus){
  this.status = progressStatus;
  }

  /**
   * Gets progress status of the task
   * @return
   */
  public ProgressStatus getProgressStatus(){
  return status;
  }

  /**
   * Gets list of all members that are working on that requirement
   * @return
   */
  public ArrayList<AssignedEmployee> getWorkingMembers(){
  return employeeList;
}

  /**
   *
   * @return
   */
  public Task copy(){
    return new Task(name, description,deadline, estimatedTime,getResponsibleEmployee());
  }
}
