package com.habony.projectd.ejbs;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.*;

import org.eclipse.persistence.queries.QueryResultsCachePolicy;

import com.habony.common.Loggable;
import com.habony.projectd.enteties.*;

@Stateless
//@RolesAllowed({"users", "admin"})
@Loggable
public class ProjectEJB {

	@PersistenceContext(unitName = "Projectd_PU") 
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public List<Project> getAllProjects() {
		
		// 1st implementation
		return em.createNamedQuery("Project.getProjectList", Project.class).getResultList();
		
		// "2nd implementation
		//StoredProcedureQuery query = em.createNamedStoredProcedureQuery("sp_");
		//query.setParameter("isActive", new Boolean(true));
		//return query.getResultList();
	}
	
	/*public Project findProjectById(int id){
		return em.find(Project.class, id);
	}*/
	
	public void saveProject(Project project){
		if (project.getId() == 0)
			em.persist(project);
		else
			em.merge(project);
	}
	
	public void deleteProject(Project project){
		em.remove(em.merge(project));
	}
	
	public List<com.habony.projectd.enteties.Thread> getProjectThreads(int projectID){
		return (List<com.habony.projectd.enteties.Thread>)em.createNamedQuery("Thread.getProjectThreads").setParameter("project_id", projectID).getResultList();
	}
	
	//public int getProjectDateCount(int projectID){
	//	 return (Integer)em.createNamedQuery("Project.getProjectDateCount").setParameter("project_id", projectID).getSingleResult();
	//}

}
