//updated in class diagram
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class that creates and handles a ProjectList
 * @author Bartosz Emanuel Ochedzan, Marketa Lapcikova, Timothy Johan Engkar
 * @version 1.0
 */
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
      if(!projects.contains(project))
      projects.add(project);
    }

    /**
     * Removes a Project from the list.
     * @param project the Project to remove from the list of type Project
     */
    public void removeProject(Project project){
      projects.remove(project);
  }

  /**
   * Removes a Project from a list
   * @param name String name of the project to be removed from the list
   */
  public void removeProject(String name)
    {
      for (int i = 0; i < projects.size(); i++)
      {
        if(projects.get(i).getName().equals(name))
        {
          projects.remove(i);

          break;
        }
      }
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

  /**
   * Gets the size of the ProjectList
   * @return int size of the ProjectList
   */
  public int size(){
    return projects.size();
  }

  /**
   * Gets the Project object on a specific index from the Project List
   * @param index in index of the wanted Project
   * @return Project type of project on the index if it exist, otherwise null
   */
  public Project get(int index){
    if(index < projects.size()){
      return projects.get(index);
    }
    return null;
  }

  /**
   * Gets a specific project of type Project from the ProjectList
   * @param project Project type of the project we are looking for
   * @return Project type of project if it exists, otherwise null
   */
  public Project getProject(Project project){
    for (int i = 0; i < projects.size(); i++) {
      if(projects.get(i).equals(project)){
        return projects.get(i);
      }
    }
    return null;
  }

  /**
   * Gets the project by its name
   * @param name the name as String of project we want to get
   * @return Project type of project of this name if it exists, otherwise null
   */
  public Project getProjectByName(String name){
    for (int i = 0; i < projects.size(); i++)
    {
      if(projects.get(i).getName().equals(name))
        return projects.get(i);
    }
    return null;
  }

  /**
   * Gets the information about all the project of this ProjectList as a String
   * @return String type of information about each project
   */
  public String toString(){
    String returnStr = "";
    for (int i = 0; i < projects.size(); i++) {
      returnStr += projects.get(i)+"\n";
    }
    return returnStr;
  }
}
