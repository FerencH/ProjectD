package com.habony.projectd.common;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import com.habony.common.Loggable;
import com.habony.projectd.ejbs.ProjectEJB;
import com.habony.projectd.enteties.Project;

@ManagedBean(name="projectController")
@SessionScoped
@Loggable
public class ProjectController implements Serializable{

	private static final long serialVersionUID = 8345760187637787728L;

	@Inject
	private ProjectEJB projectEJB;

	private List<Project> filteredProjects;
	private List<Project> allProjects;
	
	@PostConstruct
	public void loadAllProjects(){
		allProjects =  projectEJB.getAllProjects();
	}
	
	//
	// Getters and Setters
	//
	public List<Project> getFilteredProjects() {
		return filteredProjects;
	}

	public void setFilteredProjects(List<Project> filteredProjects) {
		this.filteredProjects = filteredProjects;
	}

	public void setAllProjects(List<Project> allProjects) {
		this.allProjects = allProjects;
	}
	
	public List<Project> getAllProjects(){
		return allProjects;
	}
	
}
