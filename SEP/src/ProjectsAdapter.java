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
   * 1-argument constructor setting the file name
   * @param fileName the name and path of the file where projects will be saved and retrieved
   */

  public ProjectsAdapter(String fileName)
  {
    this.fileName = fileName;
    mfio = new MyFileIO();
  }

  /**
   * Uses the MyFileIO class to retrieve a ProjectsList object with all Projects
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

  /**
   * Uses the MyFileIO class to retrieve an ArrayList<Requirement> object with all requirements of given project
   * @return an ArrayList<Requirement> object with all stored requirements of given project
   * @param name String type of name of the desired project
   */
  public ArrayList<Requirement> getAllRequirements(String name)
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
    return  projects.getProjectByName(name).getRequirements();
  }

  /**
   * Uses the MyFileIO class to retrieve an ArrayList<Task> object with all tasks of given project and requirement
   * @param nameOfProject String name of the desired project
   * @param indexOfRequirement int index of the desired requirement
   * @return ArrayList<Task> object with all the tasks of the requirement of given project
   */
  public ArrayList<Task> getAllTasks(String nameOfProject, int indexOfRequirement)
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
    return  projects.getProjectByName(nameOfProject).getRequirements().get(indexOfRequirement).getTasks();
  }

  /**
   * Deletes a given project from the project list and saves it
   * @param name String type of name of project we want to delete
   */
  public void deleteProject (String name){
    ProjectList projectList = getAllProjects();
    for (int i = 0; i < projectList.size(); i++) {
      if(projectList.get(i).getName().equals(name)){
        projectList.removeProject(projectList.get(i));
      }
    }
    saveProjects(projectList);
  }

  /**
   * Deletes a given project from the project list and saves it
   * @param project Project type of prject we want to delete
   */
  public void deleteProject (Project project){
    ProjectList projectList = getAllProjects();
    for (int i = 0; i < projectList.size(); i++) {
      if(projectList.get(i).equals(project)){
        projectList.removeProject(projectList.get(i));
      }
    }
    saveProjects(projectList);
  }

  /**
   * Deletes desired requirement from the desired project and saves it
   * @param project Project type of project we want the requirement to be deleted from
   * @param requirementIndex the index of desired requirement
   */
  public void deleteRequirement(Project project, int requirementIndex){
    ProjectList projectList = getAllProjects();
    projectList.getProject(project).removeRequirement(projectList.getProject(project).getRequirements().get(requirementIndex));
    saveProjects(projectList);
  }

  /**
   * Deletes desired task from the desired requirement of project and saves it
   * @param project Project type of project we want the task to be deleted from
   * @param requirementIndex  the index of desired requirement
   * @param taskIndex  the index of desired task
   */
  public void deleteTask(Project project, int requirementIndex, int taskIndex){
    ProjectList projectList = getAllProjects();
    projectList.getProjectByName(project.getName()).getRequirements().get(requirementIndex).removeTask(projectList.getProjectByName(project.getName()).getRequirements().get(requirementIndex).getTasks().get(taskIndex));
    saveProjects(projectList);
  }

  /**
   * Use the MyFileIO class to save some projects
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

  /**
   * Gets all the projects as ProjectList which name includes the String we are searching for, no mater the lower or upper case
   * @param searchingFor String we are searching for
   * @param projectList the ProjectList we are searching in
   * @return ProjectList of projects which name includes given String
   */
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

  /**
   * Gets all the requirements as ArrayList<Requirement> which name includes the String we are searching for, no mater the lower or upper case
   * @param searchingFor String we are searching for
   * @param requirements the ArrayList<Requirement> we are searching in
   * @return ArrayList<Requirement> of requirements which name includes given String
   */
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


  /**
   * Gets all the tasks as ArrayList<Task> which name includes the String we are searching for, no mater the lower or upper case
   * @param searchingFor String we are searching for
   * @param tasks the ArrayList<Task> we are searching in
   * @return ArrayList<Task> of tasks which name includes given String
   */
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

  /**
   * Gets the desired project which name corresponds to the desired one
   * @param name String type of name of project we want to get
   * @return Project type of desired project from the ProjectList
   */
  public Project getSelectedProject(String name){
    ProjectList allProjects = getAllProjects();
    for (int i = 0; i < allProjects.size(); i++) {
      if(allProjects.get(i).getName().equals(name)){
        return allProjects.get(i);
      }
    }
    return null;
  }

  /**
   * Gets the desired requirement which index corresponds to the desired one from a given project
   * @param projectName String type of name of project we want to get the requirement from
   * @param requirementIndex int type of index of the requirement
   * @return Requirement type of desired requirement from the given Project from the ProjectList
   */
  public Requirement getSelectedRequirement(String projectName, int requirementIndex){
    Project selectedProject = getSelectedProject(projectName);
    if(selectedProject != null){
      if(requirementIndex < selectedProject.getRequirements().size()){
        return selectedProject.getRequirements().get(requirementIndex);
      }
    }
    return null;
  }

  /**
   * Gets the desired task which index corresponds to the desired one from a given requirement and given project
   * @param projectName String type of name of project we want to get the task from
   * @param requirementIndex int type of index of the requirement we want ot get the task from
   * @param taskIndex int type of index of the task
   * @return Task type of desired task from the given Requirement from the Project from the ProjectList
   */
  public Task getSelectedTask(String projectName, int requirementIndex, int taskIndex){
    Requirement selectedRequirement = getSelectedRequirement(projectName, requirementIndex);
    if(selectedRequirement != null){
      if(taskIndex < selectedRequirement.getTasks().size()){
        return selectedRequirement.getTasks().get(taskIndex);
      }
    }
    return null;
  }

  /**
   * Gets all the projects the given employee has been part of
   * @param employee Employee type of desired employee whose projects we want to get
   * @return ProjectList of projects the given employee has ever been part of
   */
  public ProjectList getProjectsOfEmployee(Employee employee){
    ProjectList projectsOfEmployee = new ProjectList();
    ProjectList allProjects = getAllProjects();
    for (int i = 0; i < allProjects.size(); i++) {
      if(allProjects.get(i).getAssignedEmployeeList().containsNormalEmployee(employee)){
        projectsOfEmployee.addProject(allProjects.get(i));
      }
    }
    return projectsOfEmployee;
  }

  /**
   * Gets all the coworkets the given employee has been working on the same project with
   * @param employee Employee type of desired employee whose coworkers we want to get
   * @return EmployeeList of employees the given employee has ever been working on the same project with
   */
  public EmployeeList getCoworkersOfEmployee(Employee employee){
    ProjectList projectsOfEmployee = getProjectsOfEmployee(employee);
    EmployeeList coWorkersOfEmployee = new EmployeeList();
    for (int i = 0; i < projectsOfEmployee.size(); i++) {
      AssignedEmployeeList assignedEmployeeList = projectsOfEmployee.get(i).getAssignedEmployeeList();
      for (int j = 0; j < assignedEmployeeList.size(); j++) {
        if(!coWorkersOfEmployee.containsEmployee(assignedEmployeeList.getNormalEmployeeOnIndex(j)) && !assignedEmployeeList.getNormalEmployeeOnIndex(j).equals(employee)){
          coWorkersOfEmployee.addEmployee(assignedEmployeeList.getNormalEmployeeOnIndex(j));
        }
      }
    }
    return coWorkersOfEmployee;
  }
}
