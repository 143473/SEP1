import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * An adapter to the projects file, making it easy to retrieve and store information.
 * @author Claudiu Cordunianu
 * @version 1.0
 */

public class ProjectsAdapter
{
  private MyFileIO mfio;
  private String fileName;

  /**
   * 1-argument constructor setting the file name.
   * @param fileName the name and path of the file where projects will be saved and retrieved
   */

  public ProjectsAdapter(String fileName)
  {
    this.fileName = fileName;
    mfio = new MyFileIO();
  }

  /**
   * Uses the MyFileIO class to retrieve a ProjectsList object with all Projects.
   * @return a ProjectsList object with all stored projects
   */

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

  /**
   * Use the MyFileIO class to save some projects.
   * @param projects the list of projects that will be saved
   */

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
