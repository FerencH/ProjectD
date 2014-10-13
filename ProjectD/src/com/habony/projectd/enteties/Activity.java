package com.habony.projectd.enteties;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the activity database table.
 * 
 */
@Entity
@NamedQuery(name="Activity.getActivities", query="SELECT a FROM Activity a where a.thread.id = :thread_id and a.activityDate >= :minDate and a.activityDate <= :maxDate")
public class Activity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="activity_date")
	private Date activityDate;

	private String name;

	private String purpose;

	//bi-directional many-to-one association to Thread
	@ManyToOne
	private Thread thread;

	//bi-directional many-to-one association to Attachment
	@OneToMany(mappedBy="activity", fetch=FetchType.EAGER)
	private List<Attachment> attachments;

	//bi-directional many-to-one association to Decision
	@OneToMany(mappedBy="activity", fetch=FetchType.EAGER)
	private List<Decision> decisions;

	//bi-directional many-to-one association to Todo
	@OneToMany(mappedBy="activity", fetch=FetchType.EAGER)
	private List<Todo> todos;

	public Activity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getActivityDate() {
		return this.activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPurpose() {
		return this.purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Thread getThread() {
		return this.thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public List<Attachment> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public Attachment addAttachment(Attachment attachment) {
		getAttachments().add(attachment);
		attachment.setActivity(this);

		return attachment;
	}

	public Attachment removeAttachment(Attachment attachment) {
		getAttachments().remove(attachment);
		attachment.setActivity(null);

		return attachment;
	}

	public List<Decision> getDecisions() {
		return this.decisions;
	}

	public void setDecisions(List<Decision> decisions) {
		this.decisions = decisions;
	}

	public Decision addDecision(Decision decision) {
		getDecisions().add(decision);
		decision.setActivity(this);

		return decision;
	}

	public Decision removeDecision(Decision decision) {
		getDecisions().remove(decision);
		decision.setActivity(null);

		return decision;
	}

	public List<Todo> getTodos() {
		return this.todos;
	}

	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}

	public Todo addTodo(Todo todo) {
		getTodos().add(todo);
		todo.setActivity(this);

		return todo;
	}

	public Todo removeTodo(Todo todo) {
		getTodos().remove(todo);
		todo.setActivity(null);

		return todo;
	}

}