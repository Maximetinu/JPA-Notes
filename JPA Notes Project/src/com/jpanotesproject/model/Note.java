package com.jpanotesproject.model;

import java.util.HashMap;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public /* abstract */ class Note extends BaseEntity {
	
	private String title;
	private java.sql.Timestamp creationDate;
	private java.sql.Timestamp lastEditDate;;

	@ManyToOne
	private User author;

	@ElementCollection
	private HashMap<User, Integer> sharedUsers;

	public Note(User author, String title) {
		super();
		this.author = author;
		this.title = title;
		
		long now = new java.util.Date().getTime();
		this.creationDate = new java.sql.Timestamp(now);
		this.lastEditDate = creationDate;
		
		sharedUsers = new HashMap<User, Integer>();
	}

	public Note() {
		super();
	}
	
	public boolean canRead(User user) {
		return sharedUsers.containsKey(user) && sharedUsers.get(user) >= 1;
	}

	public boolean canReadAndWrite(User user) {
		return sharedUsers.containsKey(user) && sharedUsers.get(user) == 2;
	}
	
	public void setPermission(User user, Integer permissionLevel) {
		if (permissionLevel > 0)
			sharedUsers.put(user, permissionLevel);
		else
			sharedUsers.remove(user);
	}

	public java.sql.Timestamp getCreationDate() {
		return creationDate;
	}
	
	private void updateLastEditDate() {
		long now = new java.util.Date().getTime();
		this.lastEditDate = new java.sql.Timestamp(now);
	}
	
	public java.sql.Timestamp getLastEditDate(){
		return lastEditDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		this.updateLastEditDate();
	}

	@Override
	public String toString() {
		return "Note [creationDate=" + creationDate + ", title=" + title + "]";
	}
}