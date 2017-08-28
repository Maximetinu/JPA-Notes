package com.jpanotesproject.eclipselink.entity;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/*
 * We still lack the BaseEntity interface, the Notes subclasses, the Tags and Users entities and relationships with Note, etc. This class has been built with testing purposes
 *///
@Entity
@Table
public abstract class Note {
	
   @Id 
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long id;
   
   private String title;
   private String creationDate;
   private User author;
   private ArrayList<User> users_can_write;
   private ArrayList<User> users_can_read;
   
   
   public Note(User author, String creation_date, String title) {
      super( );
      this.author = author;
      this.title = title;
      
      addPermissionWriteToUser(author);
      addPermissionReadToUser(author);
      setCreationDate(creation_date);
   }
   
   public Note( ) {
      super();
   }
   
   public void addPermissionWriteToUser(User user) {
	   users_can_write.add(user);
   }
   
   public void addPermissionReadToUser(User user) {
	   users_can_read.add(user);
   } 

   public void removePermissionWriteToUser(User user) {
	   users_can_write.remove(user);
   }

   public void removePermissionReadToUser(User user) {
	   users_can_read.remove(user);
   }
   
   public boolean isPossibleRead(User user) {
	   return users_can_read.contains(user);
   }
   
   public boolean isPossibleWrite(User user) {
	   return users_can_write.contains(user);
   }
   
   public String getCreationDate( ) {
      return creationDate;
   }
   
   private void setCreationDate(String creationDate) {
      this.creationDate = creationDate;
   }
   
   public String getTitle( ) {
      return title;
   }
   
   public void setTitle(String title) {
      this.title = title;
   }
   
   @Override
   public String toString() {
      return "Note [creationDate=" + creationDate + ", title=" + title + "]";
   }
}