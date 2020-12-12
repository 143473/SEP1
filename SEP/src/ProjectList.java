import java.io.Serializable;
import java.util.ArrayList;


public class ProjectList implements Serializable
{
  private ArrayList<Project> projects;

    /**
     * No-argument constructor initializing the ProjectList.
     */
    public ProjectList(){
      projects = new ArrayList<Project>();
    }
    /**
     * Adds a project to the list.
     * @param project the Project to add to the list
     */
    public void addProject(Project project){
      projects.add(project);
    }
    /**
     * Removes a Project from the list.
     * @param project the Project to remove from the list
     */
    public void removeProject(Project project){
      projects.remove(project);
  }

  /**
   * Checks if ArrayList of Project objects contains specific project
   * @param project the project we are looking for
   * @return true if the ArrayList does and false if does not contain the Project object
   */
  public boolean containsProject(Project project){
    if(projects.contains(project)){
      return true;
    }
    return false;
  }

  public int size(){
    return projects.size();
  }

  public Project get(int index){
    return projects.get(index);
  }
}
