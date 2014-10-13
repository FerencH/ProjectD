package com.habony.projectd.common;

import java.io.Serializable;

public class ColumnModel implements Serializable{
	
	private static final long serialVersionUID = -6108991472188528399L;
	private String headerName;
	private int propertyID;
	
	public ColumnModel(String headerName, int propertyID){
		setHeaderName(headerName);
		setPropertyID(propertyID);
	}

	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public int getPropertyID() {
		return propertyID;
	}

	public void setPropertyID(int propertyID) {
		this.propertyID = propertyID;
	}

}
