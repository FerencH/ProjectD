package com.habony.projectd.common;

import com.habony.projectd.enteties.Activity;
import com.habony.projectd.enteties.Todo;

public class CellContent {
	
	private boolean bActivity;
	private Activity activity;
	private Todo todo;
	private String type = "none";
	
	public CellContent(Activity activity){
		this.activity = activity;
		setbActivity(true);
		setType("activity");
	}
	
	public CellContent(Todo todo){
		this.todo = todo;
		setbActivity(false);
		setType("todo");
	}



	public Activity getActivity() {
		return activity;
	}

	public Todo getTodo() {
		return todo;
	}

	public boolean isbActivity() {
		return bActivity;
	}

	public void setbActivity(boolean bActivity) {
		this.bActivity = bActivity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}




}
