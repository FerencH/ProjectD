package com.habony.projectd.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.PersistenceContext;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.habony.common.Loggable;
import com.habony.projectd.enteties.Activity;

public class ProjectDateLazyDataModel extends LazyDataModel<ProjectDate> {

		private static final long serialVersionUID = 1L;
		 
		private EntityManager em;

		private final int projectID;
		private final List<com.habony.projectd.enteties.Thread> threads;
		
		public ProjectDateLazyDataModel(EntityManager em, int projectID, final List<com.habony.projectd.enteties.Thread> threads){
			
			this.em = em;
			this.projectID = projectID;
			this.threads = threads;
			
			Long o = (Long) em.createNamedQuery("Project.getProjectDateCount").setParameter(1, projectID).getSingleResult();
			

			super.setRowCount(o.intValue());
		}
		
		@Override
        public List<ProjectDate> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {

			Object filterValue = filters.get("globalFilter");
			
			// Get the dates for the requested page
			List<Date> dates =  em.createNamedQuery("Project.getProjectDates").setParameter(1, projectID).setParameter(2, first).setParameter(3, pageSize).getResultList();
			
			// Populate projectDates with date place holders of ProjectDate. One ProjectDate will hold all activities and todos on a certain date
			// Extract the first and last dates. (the query is using order by)
			HashMap<Date, ProjectDate> projectDates = new HashMap<Date, ProjectDate>();
			ProjectDate firstDate = null;
			ProjectDate lastDate = null;
			for (Date d : dates){
				lastDate = new ProjectDate(d);
				if (firstDate == null)
					firstDate = lastDate;
				projectDates.put(d, lastDate);
			}
			
			// If empty then no need to continue
			if (projectDates.size()==0)
				return new ArrayList<ProjectDate>(projectDates.values());
			
			
			// For each thread:
			// Use first and last dates to get the right activities from thread
			// Then populate projectDates with activity
			for (com.habony.projectd.enteties.Thread thread : threads){
				List<Activity> activities =  em.createNamedQuery("Activity.getActivities").setParameter("thread_id", thread.getId()).setParameter("minDate", firstDate.getDate()).setParameter("maxDate", lastDate.getDate()).getResultList();
				for (Activity activity : activities)
					projectDates.get(activity.getActivityDate()).addActivity(thread.getId(), activity);
			}

			List<ProjectDate> theList= new ArrayList<ProjectDate>(projectDates.values());

			// Sort the list here
			Collections.sort(theList, ProjectDate.getComparator());
			
			// TODO: add TODO-s also to the same list, sorting order is still date. new sql quiery
			// TODO: implement filters so that the user can choose what to show: all, todo-s or decisions, based on these filters polulate as follows:
			// all: will show all activities with or without decisions, with and without todos plus thread todos
			// todo: will show all activities with todos plus thread todos
			// decision: will show all activities with decisions
			return theList;
        }

}
