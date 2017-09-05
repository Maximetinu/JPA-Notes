package com.jpanotesproject.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract  class Note extends BaseEntity {

	@Column(name = "TITLE")
	private String title;
	@Column(name = "CREATION_DATE")
	private java.sql.Timestamp creationDate;
	@Column(name = "LAST_EDIT_DATE")
	private java.sql.Timestamp lastEditDate;;

	@ManyToOne
	@JoinColumn(name = "AUTHOR")
	private User author;

	@ElementCollection(fetch=FetchType.EAGER)
    @MapKey
    @CollectionTable(name = "NOTE_SHARED_USERS")
	private Map<User, Integer> sharedUsers;
	
	@ManyToMany
	@JoinTable(
		      name="NOTE_TAGS")//,
		      //joinColumns=@JoinColumn(name="EMP_ID", referencedColumnName="ID"),
		      //inverseJoinColumns=@JoinColumn(name="PROJ_ID", referencedColumnName="ID"))
	private List<Tag> tags;

	public Note(User author, String title) {
		super();
		this.author = author;
		this.title = title;
		
		long now = new java.util.Date().getTime();
		this.creationDate = new java.sql.Timestamp(now);
		this.lastEditDate = creationDate;
		
		tags = new ArrayList<Tag>();
		
		sharedUsers = new HashMap<User, Integer>();
	}
	
	public void addTag(Tag t) {
		tags.add(t);
	}
	
	public void removeTag(Tag t) {
		tags.remove(t);
	}
	
	public List<Tag> getTagsList(){
		return tags;
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
