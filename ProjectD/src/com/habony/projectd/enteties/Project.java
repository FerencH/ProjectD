package com.habony.projectd.enteties;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the project database table.
 * 
 */
@Entity
@NamedQueries(value = 	{
		@NamedQuery(name="Project.getActiveProjectList", query="SELECT p FROM Project p where p.active=true order by p.priority"),				
		@NamedQuery(name="Project.getProjectList", query="SELECT p FROM Project p order by p.active desc, p.priority")
})

@NamedNativeQueries(value = {
		@NamedNativeQuery(name="Project.getProjectDateCount", query="select count(distinct a.activity_date) from thread t left outer join activity a on t.id=a.thread_id where project_id= ?"),
		@NamedNativeQuery(name="Project.getProjectDates", query="select distinct a.activity_date from thread t left outer join activity a on t.id=a.thread_id where project_id= ? order by a.activity_date limit ?, ?")
})

@NamedStoredProcedureQuery(name="Project.getProjectListSP", procedureName = "sp_getProjectList", parameters = {
	@StoredProcedureParameter(name = "isActive", mode = ParameterMode.IN, type = Boolean.class)
})
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	//----------------------------------------------------
	// Properties
	//----------------------------------------------------
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private boolean active;

	@Column(name="exit_criteria")
	private String exitCriteria;

	@Column(name="exit_criteria_fulfilled")
	private boolean exitCriteriaFulfilled;

	@Temporal(TemporalType.DATE)
	@Column(name="exit_date")
	private Date exitDate;

	@Column(name="exit_type_is_date")
	private boolean exitTypeIsDate;

	private String name;

	private byte priority;

	private String purpose;

	//bi-directional one-to-many association to Thread
	@OneToMany(mappedBy="project", fetch=FetchType.LAZY)
	private List<Thread> threads;
	// Can use this also for uni-directional
	// @OneToMany
	// @JoinColumn(name = "project_id")

	//----------------------------------------------------
	// Constructors
	//----------------------------------------------------
	
	public Project() {
	}
	
	public Project(String name, String purpose, byte priority, Date exitDate, boolean exitCriteriaFulfilled, boolean active) {
		this.name = name;
		this.purpose = purpose;
		this.priority = priority;
		this.exitDate = exitDate;
		this.exitTypeIsDate = true;
		this.exitCriteriaFulfilled = exitCriteriaFulfilled;
		this.active = active;
	}
	
	public Project(String name, String purpose, byte priority, String exitCriteria, boolean exitCriteriaFulfilled, boolean active) {
		this.name = name;
		this.purpose = purpose;
		this.priority = priority;
		this.exitCriteria = exitCriteria;
		this.exitTypeIsDate = false;
		this.exitCriteriaFulfilled = exitCriteriaFulfilled;
		this.active = active;
	}

	//----------------------------------------------------
	// Getters and Setters
	//----------------------------------------------------
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getExitCriteria() {
		return this.exitCriteria;
	}

	public void setExitCriteria(String exitCriteria) {
		this.exitCriteria = exitCriteria;
	}

	public boolean getExitCriteriaFulfilled() {
		return this.exitCriteriaFulfilled;
	}

	public void setExitCriteriaFulfilled(boolean exitCriteriaFulfilled) {
		this.exitCriteriaFulfilled = exitCriteriaFulfilled;
	}

	public Date getExitDate() {
		return this.exitDate;
	}

	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}

	public boolean getExitTypeIsDate() {
		return this.exitTypeIsDate;
	}

	public void setExitTypeIsDate(boolean exitTypeIsDate) {
		this.exitTypeIsDate = exitTypeIsDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getPriority() {
		return this.priority;
	}

	public void setPriority(byte priority) {
		this.priority = priority;
	}

	public String getPurpose() {
		return this.purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public List<Thread> getThreads() {
		return this.threads;
	}

	public void setThreads(List<Thread> threads) {
		this.threads = threads;
	}

	public Thread addThread(Thread thread) {
		getThreads().add(thread);
		thread.setProject(this);

		return thread;
	}

	public Thread removeThread(Thread thread) {
		getThreads().remove(thread);
		thread.setProject(null);

		return thread;
	}
	
	public int getComplexity(){
		int complexity = 0;
		for (Thread t : threads)
			complexity += t.getComplexity();
		return complexity;
	}
	
	public int getComplexityDone(){
		int complexityDone = 0;
		for (Thread t : threads)
			complexityDone += t.getComplexityDone();
		return complexityDone;
	}
	
	public String toString(){
		return "Project["+id+", "+name+"]";
	}

}