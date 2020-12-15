//updated in class diagram
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * An adapter to the AssignedTasks file, making it easy to retrieve and store information.
 * @author Claudiu Cordunianu
 * @version 1.0
 */

public class AssignedTasksAdapter
{
  private MyFileIO mfio;
  private String fileName;

  /**
   * 1-argument constructor setting the file name.
   *
   * @param fileName the name and path of the file where AssignedTasks will be saved and retrieved
   */

  public AssignedTasksAdapter(String fileName)
  {
    this.fileName = fileName;
    mfio = new MyFileIO();
  }

  /**
   * Uses the MyFileIO class to retrieve an AssignedTasksList object with all AssignedTasks.
   *
   * @return an AssignedTasksList object with all stored assignedTasks
   */

  public AssignedTasksList getAllAssignedTasks()
  {
    AssignedTasksList assignedTasks = new AssignedTasksList();

    try
    {
      assignedTasks = (AssignedTasksList) mfio.readObjectFromFile(fileName);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return assignedTasks;
  }

  /**
   * Use the MyFileIO class to retrieve all assignedTasks for a given employee.
   *
   * @param employee the employee to retrieve the assignedTasks for
   * @return an AssignedTasksList object with assignedTasks for the given employee
   */
  public AssignedTasksList getAllTasksOnEmployee(Employee employee)
  {
    AssignedTasksList tasksOnEmployee = new AssignedTasksList();
    try
    {
      AssignedTasksList result = (AssignedTasksList) mfio.readObjectFromFile(fileName);

      for (int i = 0; i < result.size(); i++)
      {
        if (result.get(i).checkIfEmployeeIsAssigned(employee))
        {
          tasksOnEmployee.addAssignedTask(result.get(i));
        }
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return tasksOnEmployee;
  }

  /**
   * Use the MyFileIO class to save some assignedTasks.
   *
   * @param assignedTasks the list of assignedTasks that will be saved
   */

  public void saveAssignedTasks(AssignedTasksList assignedTasks)
  {
    try
    {
      mfio.writeToFile(fileName, assignedTasks);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }
  }

  public EmployeeList getAllAssignedEmployees()
  {
    AssignedTasksList assignedTasks = new AssignedTasksList();

    try
    {
      assignedTasks = (AssignedTasksList) mfio.readObjectFromFile(fileName);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    EmployeeList assignedEmployees = new EmployeeList();
    for (int i = 0; i < assignedTasks.size(); i++)
    {
      Employee thisEmployee = new Employee(
          assignedTasks.get(i).getAssignedEmployee().getFirstName(),
          assignedTasks.get(i).getAssignedEmployee().getLastName(),
          assignedTasks.get(i).getAssignedEmployee().getDateOfBirth());
      if (!assignedEmployees.containsEmployee(thisEmployee))
      {
        assignedEmployees.addEmployee(thisEmployee);
      }
    }
    return assignedEmployees;
  }

  public double getTotalEstimatedTimeOnEmployee(Employee employee)
  {
    AssignedTasksList allAssignedTasks = getAllAssignedTasks();
    AssignedTasksList allEndedTasks = new AssignedTasksList();
    for (int i = 0; i < allAssignedTasks.size(); i++)
    {
      if (allAssignedTasks.get(i).getStatus().equals("Ended")
          || allAssignedTasks.get(i).getStatus().equals("Approved")
          || allAssignedTasks.get(i).getStatus().equals("Rejected"))
      {
        allEndedTasks.addAssignedTask(allAssignedTasks.get(i));
      }
    }
    double totalEstimatedTime = 0;
    for (int i = 0; i < allEndedTasks.size(); i++)
    {
      if (allEndedTasks.get(i).getAssignedEmployee().getFirstName().equals(employee.getFirstName()) && allEndedTasks.get(i)
          .getAssignedEmployee().getLastName().equals(employee.getLastName())
          && allEndedTasks.get(i).getAssignedEmployee().getDateOfBirth().equals(employee.getDateOfBirth()))
      {
        totalEstimatedTime += allEndedTasks.get(i).getEstimatedTime();
      }
    }
    return totalEstimatedTime;
  }
}