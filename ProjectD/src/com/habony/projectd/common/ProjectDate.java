package com.habony.projectd.common;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.habony.projectd.enteties.Activity;


public class ProjectDate {
	private Date date;
	private HashMap<Integer, LinkedList<CellContent>> threadIDtoThreadContent = new HashMap<Integer, LinkedList<CellContent>>(); 

	
	public ProjectDate(Date date){
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public List<CellContent> getThreadContent(int thread_id){
		return threadIDtoThreadContent.get(thread_id);
	}

	public void addActivity(int thread_id, Activity activity){
		LinkedList<CellContent> threadContent = threadIDtoThreadContent.get(thread_id);
		
		if (threadContent == null){
			threadContent = new LinkedList<CellContent>();
			threadIDtoThreadContent.put(thread_id, threadContent);
		}
		
		threadContent.add(new CellContent(activity));
	}
	
	public static Comparator<ProjectDate> getComparator(){
		return new Comparator<ProjectDate>(){
			
			@Override
			public int compare(ProjectDate o1, ProjectDate o2) {
				if (o1==null && o2==null)
					return 0;
				if (o1==null)
					return -1;
				if (o2==null)
					return 1;
				return o1.getDate().compareTo(o2.getDate());
			}
			
		};
	}
	
}
