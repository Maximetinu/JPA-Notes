package com.jpanotesproject.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class User extends BaseEntity{

	   @Column(unique=true) 
	   private String name;
	   
	   private String password;
	   private String email;
	   private String registration_date;
	   
	   private ArrayList<Note> notes;
	   
	   public User(String name, String password, String email, String registration_date) {
	      super( );
	      this.name = name;
	      this.password = password;
	      this.email = email;
	      this.registration_date = registration_date;
	   }
	   
	   public User() {
		      super();
	   }
	   
	   public void addNote(Note note) {
		   notes.add(note);
	   }
}
