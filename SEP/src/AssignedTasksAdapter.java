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
   * @param fileName the name and path of the file where AssignedTasks will be saved and retrieved
   */

  public AssignedTasksAdapter(String fileName)
  {
    this.fileName = fileName;
    mfio = new MyFileIO();
  }

  /**
   * Uses the MyFileIO class to retrieve an AssignedTasksList object with all AssignedTasks.
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
   * Use the MyFileIO class to retrieve all assignedTasks for a given date.
   * @param date the date to retrieve the assignedTasks for
   * @return an AssignedTasksList object with assignedTasks for the given date
   */

  public AssignedTasksList getAllTasksOnDate (MyDate date)
  {
    AssignedTasksList tasksOnDate = new AssignedTasksList();

    try
    {
      AssignedTasksList result = (AssignedTasksList)mfio.readObjectFromFile(fileName);

      for (int i = 0; i < result.size(); i++)
      {
        if (result.get(i).getDate().equals(date))
        {
          tasksOnDate.addAssignedTask(result.get(i));
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
    return tasksOnDate;
  }

  /**
   * Use the MyFileIO class to retrieve all assignedTasks for a given employee.
   * @param employee the employee to retrieve the assignedTasks for
   * @return an AssignedTasksList object with assignedTasks for the given employee
   */
  public AssignedTasksList getAllTasksOnEmployee (AssignedEmployee employee)
  {
    AssignedTasksList tasksOnEmployee = new AssignedTasksList();

    try
    {
      AssignedTasksList result = (AssignedTasksList)mfio.readObjectFromFile(fileName);

      for (int i = 0; i < result.size(); i++)
      {
        if (result.get(i).getAssignedEmployee().equals(employee))
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

}
