package com.jpanotesproject.IO;

import com.jpanotesproject.daos.NotesCRUDService;

/*
 * This class is meant to be the interface of our application by managing user's input by a terminal menu (in this basic case)
 * Here services will be used, without creating dependencies with DAOs nor Model/Entities
 * ATM it is a testing main
 */
public class Application {

	public static void main(String[] args) {
		
		// If delete() is commented the row must be manually deleted for avoiding duplicate primary key insert
		// create() creates the table in case it doesn't exist. If it exists it works too

		NotesCRUDService notesService = new NotesCRUDService();
		
		notesService.create();
		notesService.read();
		notesService.update();
		///notesService.delete();

	}

}
