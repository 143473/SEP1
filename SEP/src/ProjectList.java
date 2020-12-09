import java.util.ArrayList;


public class ProjectList
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
     * @param projects the Project to add to the list
     */
    public void addProject(Project projects){
      this.projects.add(projects);
    }
    /**
     * Removes a Project from the list.
     * @param projects the Project to remove from the list
     */
    public void removeProject(Project projects){
      this.projects.remove(projects);
  }
}
