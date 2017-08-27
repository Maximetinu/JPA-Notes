package com.jpanotesproject.eclipselink.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User {

	   @Id 
	   private String name;
	   
	   private String password;
	   private String email;
	   private String registration_date;
	   
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
}
