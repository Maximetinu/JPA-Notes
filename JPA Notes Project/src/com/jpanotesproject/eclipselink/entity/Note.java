package com.jpanotesproject.eclipselink.entity;

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
   
   public Note(String creation_date, String title) {
      super( );
      this.creationDate = creation_date;
      this.title = title;
   }
   
   public Note( ) {
      super();
   }
   
   public String getCreationDate( ) {
      return creationDate;
   }
   
   public void setCreationDate(String creationDate) {
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