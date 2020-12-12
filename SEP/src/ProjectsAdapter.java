import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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

  public ProjectList getAllProjects()
  {
    ProjectList projects = new ProjectList();

    try
    {
      projects = (ProjectList) mfio.readObjectFromFile(fileName);
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



  public void deleteProject (int indexInList){
    ProjectList projectList = getAllProjects();
    for (int i = 0; i < projectList.size(); i++) {
      if(i == indexInList){
        projectList.removeProject(projectList.get(i));
      }
    }
    saveProjects(projectList);
  }

  /**
   * Use the MyFileIO class to save some projects.
   * @param projects the list of projects that will be saved
   */

  public void saveProjects(ProjectList projects)
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

  public ProjectList getProjectsByName(String searchingFor, ProjectList projectList){
    ProjectList resultProjects = new ProjectList();

    searchingFor = searchingFor.toLowerCase();
    for (int i = 0; i < projectList.size(); i++) {
      if(projectList.get(i).getName().toLowerCase().contains(searchingFor)){
        resultProjects.addProject(projectList.get(i));
      }
    }
    return resultProjects;
  }

  public ArrayList<Requirement> getRequirementsByName(String searchingFor, ArrayList<Requirement> requirements){
    ArrayList<Requirement> resultRequirements = new ArrayList<Requirement>();

    searchingFor = searchingFor.toLowerCase();
    for (int i = 0; i < requirements.size(); i++) {
      if(requirements.get(i).getName().toLowerCase().contains(searchingFor)){
        resultRequirements.add(requirements.get(i));
      }
    }
    return resultRequirements;
  }

  public ArrayList<Task> getTasksByName(String searchingFor, ArrayList<Task> tasks){
    ArrayList<Task> resultTasks = new ArrayList<Task>();

    searchingFor = searchingFor.toLowerCase();
    for (int i = 0; i < tasks.size(); i++) {
      if(tasks.get(i).getName().toLowerCase().contains(searchingFor)){
        resultTasks.add(tasks.get(i));
      }
    }
    return resultTasks;
  }

  public Project getSelectedProject(int index){
    if(index < getAllProjects().size()){
      return getAllProjects().get(index);
    }
    return null;
  }

  public Requirement getSelectedRequirement(int projectIndex, int requirementIndex){
    Project selectedProject = getSelectedProject(projectIndex);
    if(selectedProject != null){
      if(requirementIndex < selectedProject.getRequirements().size()){
        return selectedProject.getRequirements().get(requirementIndex);
      }
    }
    return null;
  }
}
