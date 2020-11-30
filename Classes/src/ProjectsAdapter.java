import java.io.FileNotFoundException;
import java.io.IOException;

public class ProjectsAdapter
{
  private MyFileIO mfio;
  private String fileName;

  public ProjectsAdapter(String fileName)
  {
    this.fileName = fileName;
    mfio = new MyFileIO();
  }

  // Use the MyFileIO class to retrieve a ProjectsList object with all Projects
  public ProjectsList getAllProjects()
  {
    ProjectsList projects = new ProjectsList();

    try
    {
      projects = (ProjectsList) mfio.readObjectFromFile(fileName);
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
    return projects;
  }

  // Use the MyFileIO class to save all Employees in the EmployeesList object
  public void saveProjects(ProjectsList projects)
  {
    try
    {
      mfio.writeToFile(fileName, projects);
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
