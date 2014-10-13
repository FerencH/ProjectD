package com.habony.projectd.enteties;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the decision database table.
 * 
 */
@Entity
@NamedQuery(name="Decision.findAll", query="SELECT d FROM Decision d")
public class Decision implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	@Lob
	private String note;

	private String why;

	//bi-directional many-to-one association to Activity
	@ManyToOne
	private Activity activity;

	public Decision() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getWhy() {
		return this.why;
	}

	public void setWhy(String why) {
		this.why = why;
	}

	public Activity getActivity() {
		return this.activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

}