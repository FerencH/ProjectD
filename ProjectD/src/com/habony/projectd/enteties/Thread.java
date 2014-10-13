package com.habony.projectd.enteties;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the thread database table.
 * 
 */
@Entity
@NamedQuery (name="Thread.getProjectThreads", query="SELECT t FROM Thread t where t.project.id = :project_id")
public class Thread implements Serializable {
	private static final long serialVersionUID = 1L;

	//----------------------------------------------------
	// Properties
	//----------------------------------------------------
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int complexity;

	@Column(name="complexity_done")
	private short complexityDone;

	private String name;

	private byte priority;

	private String purpose;

	//bi-directional one-to-many association to Activity
	@OneToMany(mappedBy="thread", fetch=FetchType.EAGER)
	private List<Activity> activities;

	//bi-directional many-to-one association to Project
	@ManyToOne
	private Project project;

	//bi-directional one-to-many association to Todo
	@OneToMany(mappedBy="thread", fetch=FetchType.EAGER)
	private List<Todo> todos;

	//----------------------------------------------------
	// Constructors
	//----------------------------------------------------
	
	public Thread() {
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

	public int getComplexity() {
		return this.complexity;
	}

	public void setComplexity(int complexity) {
		this.complexity = complexity;
	}

	public short getComplexityDone() {
		return this.complexityDone;
	}

	public void setComplexityDone(short complexityDone) {
		this.complexityDone = complexityDone;
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

	public List<Activity> getActivities() {
		return this.activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public Activity addActivity(Activity activity) {
		getActivities().add(activity);
		activity.setThread(this);

		return activity;
	}

	public Activity removeActivity(Activity activity) {
		getActivities().remove(activity);
		activity.setThread(null);

		return activity;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Todo> getTodos() {
		return this.todos;
	}

	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}

	public Todo addTodo(Todo todo) {
		getTodos().add(todo);
		todo.setThread(this);

		return todo;
	}

	public Todo removeTodo(Todo todo) {
		getTodos().remove(todo);
		todo.setThread(null);

		return todo;
	}

}