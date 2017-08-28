package com.jpanotesproject.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/*
 * We still lack the BaseEntity interface, the Notes subclasses, the Tags and Users entities and relationships with Note, etc. This class has been built with testing purposes
 *///
@Entity
@Table
public abstract class Note extends BaseEntity{
   
   private String title;
   private String creationDate;
   @OneToOne
   private User author;
   @OneToMany
   private ArrayList<User> users_can_write;
   @OneToMany
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