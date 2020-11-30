import java.io.FileNotFoundException;
import java.io.IOException;

public class AssignedTasksAdapter
{
  private MyFileIO mfio;
  private String fileName;

  public AssignedTasksAdapter(String fileName)
  {
    this.fileName = fileName;
    mfio = new MyFileIO();
  }

  // Use the MyFileIO class to retrieve a AssignedTasksList object with all AssignedTasks
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
          tasksOnDate.add(result.get(i));
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

  public AssignedTasksList getAllTasksOnEmployee (AssignedEmployee employee)
  {
    AssignedTasksList tasksOnEmployee = new AssignedTasksList();

    try
    {
      AssignedTasksList result = (AssignedTasksList)mfio.readObjectFromFile(fileName);

      for (int i = 0; i < result.size(); i++)
      {
        if (result.get(i).getAssignedEmployee().equals(date))
        {
          tasksOnEmployee.add(result.get(i));
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

  // Use the MyFileIO class to save all AssignedTasks in the AssignedTasksList object
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
