package com.jpanotesproject.eclipselink.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * We still lack the BaseEntity interface, the Notes subclasses, the Tags and Users entities and relationships with Note, etc. This class has been built with testing purposes
 */
@Entity
@Table
public class Note {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO) 	
   private String title;
   private int creationDate;
   
   public Note(int creation_date, String title) {
      super( );
      this.creationDate = creation_date;
      this.title = title;
   }

   public Note( ) {
      super();
   }
//
   public int getCreationDate( ) {
      return creationDate;
   }
   
   public void setCreationDate(int creationDate) {
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