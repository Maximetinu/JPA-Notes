package com.jpanotesproject.IO;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.jpanotesproject.daos.NoteDAO;
import com.jpanotesproject.daos.TagDAO;
import com.jpanotesproject.daos.UserDAO;
import com.jpanotesproject.model.AudioNote;
import com.jpanotesproject.model.Note;
import com.jpanotesproject.model.Tag;
import com.jpanotesproject.model.TextNote;
import com.jpanotesproject.model.User;

/*
 * This class is meant to be the interface of our application by managing user's input by a terminal menu (in this basic case)
 * Here services will be used, without creating dependencies with DAOs nor Model/Entities
 * ATM it is a testing main
 */
public class Application {


	public static void main(String[] args) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JPA Notes Project");
		EntityManager em = emfactory.createEntityManager();

		UserDAO uDAO = new UserDAO(em);
		NoteDAO nDAO = new NoteDAO(em);
		TagDAO tDAO = new TagDAO(em);

		Scanner keyboard = new Scanner(System.in);
		boolean stop = false;
		User current_user = null;
		String input;

		while (!stop) {

			if (current_user == null) {
				System.out.println("Choose an option:");
				System.out.println("1 - Register");
				System.out.println("2 - Login");
				System.out.println("3 - Exit");
				input = keyboard.nextLine();

				if ("1".equals(input)) {

					System.out.println("Name:");
					String user = keyboard.nextLine();
					
					User try_user = uDAO.findByUsername(user);
					
					if (try_user == null) {

						System.out.println("Password:");
						String password = keyboard.nextLine();
	
						System.out.println("Email:");
						String email = keyboard.nextLine();
	
						User new_user = null;
						

						em.getTransaction().begin();
						try {
							new_user = new User(user, password, email);
							uDAO.persist(new_user);
						} catch (Exception e) {
							e.getMessage();
						}
						em.getTransaction().commit();
	
						System.out.println("Registered successfully " + new_user.getUsername());
						current_user = new_user;
	
					} else {
						System.out.println("User " + user + " already exists");
					}
	        		
	            } else if ("2".equals(input)) {
	            	
	            	boolean repeat_user = true;
	            	boolean repeat_password = true;
	            	
	            	int tried = 0;
	            	
	            	while (repeat_user && tried<3) {
						System.out.println("Name:");
						String user = keyboard.nextLine();
	
						User try_user = uDAO.findByUsername(user);
	
						if (try_user != null && try_user.getUsername() != "") {
							tried = 0;
							repeat_user = false;
							while (repeat_password && tried<3) {
								System.out.println("Password:");
								String password = keyboard.nextLine();
								if (try_user.getPassword().equals(password)) {
									current_user = try_user;
									repeat_password = false;
								} else {
									System.out.println("Wrong password");
									tried++;
								}
							}
							
							
						} else {
							System.out.println("Wrong user");
							tried++;
						}
						
						if (tried >= 3) {
							System.out.println("Limit of attempts exceeded");
							stop = true;
						}
						
	            	}

				} else if ("3".equals(input)) {

					stop = true;

				}

			}

			if (current_user != null) {
				System.out.println("Choose an option:");
				System.out.println("1 - List notes");
				System.out.println("2 - List shared notes");
				System.out.println("3 - Show note");
				System.out.println("4 - New text note");
				System.out.println("5 - Edit note");
				System.out.println("6 - Exit");
	            input = keyboard.nextLine();
	            
	            
	            if ("1".equals(input)) {

					System.out.println("You have " + current_user.getOwnNotes().size() + " note(s)");
	            	for (Note note : current_user.getOwnNotes()) {
	        			System.out.println(note.getTitle() + " - " + note.getLastEditDate());
	        		}
	            		

	            } else if ("2".equals(input)) {


					System.out.println("You have " + current_user.getSharedNotes().size() + " shared note(s)");
	            	for (Note note : current_user.getSharedNotes().keySet()) {
	        			System.out.println(note.getTitle() + " - " + note.getLastEditDate());
	        		}
	            	
	            } else if ("3".equals(input)) {
					System.out.println("Note's title?");
					String title_note = keyboard.nextLine();
					
					Note note_retrieval = null;

	            	for (Note note : current_user.getOwnNotes()) {
	            		if (note.getTitle().equals(title_note)) {
	            			note_retrieval = note;
	            		}
	        		}
	            	
	            	if (note_retrieval == null) {
		            	for (Note note : current_user.getSharedNotes().keySet()) {
		            		if (note.getTitle().equals(title_note)) {
		            			note_retrieval = note;
		            		}
		        		}
	            	}
	            	
	            	if (note_retrieval == null) {
						System.out.println(title_note + " not found.");
	            	} else {
						System.out.println(note_retrieval.getTitle() + " - " + note_retrieval.getLastEditDate());
						System.out.println("Tags: ");
						
		            	for (Tag tag : note_retrieval.getTagsList()) {
		            		System.out.print(tag.getTagText() + " ");
		        		}
						
						System.out.println("\n-------\n");
						System.out.println(((TextNote)note_retrieval).getText());
						System.out.println("\n-------\n");
	            	}
	            	
	            } else if ("4".equals(input)) {


					System.out.println("Title:");
					String title = keyboard.nextLine();

					System.out.println("Text:");
					String text = keyboard.nextLine();
					
					TextNote new_textnote = null;

					em.getTransaction().begin();/**/
					try {
						new_textnote = new TextNote(current_user, title, text);
						nDAO.persist(new_textnote);
					} catch (Exception e) {
						e.getMessage();
					}
					em.getTransaction().commit();/**/

					System.out.println("Add tags to " + title);
					boolean stop_add_tags = false;
					while (!stop_add_tags) {
						System.out.println("Insert 0 to stop ");
			            String new_tag_str = keyboard.nextLine();
			            
			            if (!new_tag_str.equals("0") && new_tag_str != "" && new_tag_str != null) {

							em.getTransaction().begin();/**/
			            	Tag new_tag = null;
			            	
							try {
								new_tag = tDAO.findByTag(new_tag_str);

								if (new_tag == null) {
									new_tag = new Tag(new_tag_str);
									tDAO.persist(new_tag);
								}
								new_textnote.addTag(new_tag);
								em.getTransaction().commit();/**/
							} catch (Exception e) {
								System.out.println("Error inserting tag " + new_tag_str);
								e.getMessage();
							}
							System.out.println(new_tag_str + " added.");
			            } else if (new_tag_str.equals("0")) {
			            	stop_add_tags = true;
			            } else {
							System.out.println("Ignored. Try again");
			            }
			            
						
					}

					

					System.out.println("Saved.");
	            	
	            } else if ("5".equals(input)) {
	            	boolean can_edit = true;
	            	
					System.out.println("Note's title?");
					String title_note = keyboard.nextLine();
					
					Note note_retrieval = null;

	            	for (Note note : current_user.getOwnNotes()) {
	            		if (note.getTitle().equals(title_note)) {
	            			note_retrieval = note;
	            		}
	        		}
	            	
	            	if (note_retrieval == null) {
		            	for (Note note : current_user.getSharedNotes().keySet()) {
		            		if (note.getTitle().equals(title_note)) {
		            			note_retrieval = note;
								nDAO.persist(note_retrieval);
		            		}
		        		}
		            	
		            	if (note_retrieval != null && !note_retrieval.canReadAndWrite(current_user)) {
							System.out.println("You do not have permission to edit this note.");
		            		can_edit = false;
		            	}
	            	}
	            	
	            	if (note_retrieval == null) {
						System.out.println(title_note + " not found.");
	            	} else if(can_edit) {
	            		boolean stop_edit = false;

	            		while (!stop_edit) {
	            			System.out.println("Choose an option:");
	            			System.out.println("1 - Add tags");
	            			System.out.println("2 - Edit content");
	            			System.out.println("3 - Share");
	            			System.out.println("4 - Return");
	           				input = keyboard.nextLine();
	           				
	           				if ("1".equals(input)) {
	           					boolean stop_add_tags = false;
	        					em.getTransaction().begin();
	        					while (!stop_add_tags) {
	        						System.out.println("Insert 0 to stop ");
	        			            String new_tag_str = keyboard.nextLine();
	        			            
	        			            if (!new_tag_str.equals("0")) {

	        			            	Tag new_tag = null;
	        			            	
	        							try {
	        								new_tag = tDAO.findByTag(new_tag_str);
	        								if (new_tag == null) {
	        									new_tag = new Tag(new_tag_str);
	        									tDAO.persist(new_tag);
	        								}
	        								note_retrieval.addTag(new_tag);
	        							} catch (Exception e) {
	        								e.getMessage();
	        							}
	        							
	        							System.out.println(new_tag_str + " added.");
	        			            } else {
	        			            	stop_add_tags = true;
	        			            }
	        					}
	        					em.getTransaction().commit();

        						
	           				} else if  ("2".equals(input)) {
		           				String new_content = keyboard.nextLine();
		    					em.getTransaction().begin();
		           				try {
    								((TextNote)note_retrieval).setText(new_content);
    								
    							} catch (Exception e) {
    								e.getMessage();
    							}
		    					em.getTransaction().commit();
	        						
	           				} else if  ("3".equals(input)) {
		            			System.out.println("Share " + note_retrieval.getTitle() + " with: ");
		           				String share_with_user_str = keyboard.nextLine();
		           				
		           				User share_with_user = null;
		           				try {
    								share_with_user = uDAO.findByUsername(share_with_user_str);
    								uDAO.persist(share_with_user);
    								
    							} catch (Exception e) {
    								e.getMessage();
    							}
		           				
		           				if (share_with_user == null || share_with_user.getUsername().equals(current_user.getUsername())) {
			            			System.out.println("Impossible share with " + share_with_user_str);
		           				} else {
		        					em.getTransaction().begin();
		           					note_retrieval.shareWith(share_with_user);

		           					boolean repeat = true;
			           				while (repeat) {
				            			System.out.println("Can " + share_with_user_str + " edit " + note_retrieval.getTitle() + "? (y|n)");
				           				String can_w = keyboard.nextLine();

			           					
				           				if (can_w.equals("y") || can_w.equals("yes")) {
				           					share_with_user.shareNote(note_retrieval, 2);
				           					repeat = false;
				           				} else if (can_w.equals("n") || can_w.equals("no")) {
				           					share_with_user.shareNote(note_retrieval, 1);
				           					repeat = false;
				           				} 
		           					}

			           				em.getTransaction().commit();
		           				}
		            			
	           					
	           				} else if  ("4".equals(input)) {
	           					stop_edit = true;
	           				} 
	            		
	            		}
	            	}
	            	
	            } else if ("6".equals(input)) {
	            	
	            	current_user = null;
	            	
	            }

				
			}

			System.out.println("");
			
		}

		em.close();
		emfactory.close();
	}

}
