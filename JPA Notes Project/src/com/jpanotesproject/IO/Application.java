package com.jpanotesproject.IO;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.jpanotesproject.daos.NoteDAO;
import com.jpanotesproject.daos.TagDAO;
import com.jpanotesproject.daos.UserDAO;
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
		em.getTransaction().begin();

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

					System.out.println("Password:");
					String password = keyboard.nextLine();

					System.out.println("Email:");
					String email = keyboard.nextLine();

					User new_user = null;

					try {
						new_user = new User(user, password, email);
						uDAO.persist(new_user);
					} catch (Exception e) {
						e.getMessage();
					}

					System.out.println("Registered successfully " + new_user.getUsername());
					current_user = new_user;

				} else if ("2".equals(input)) {

					System.out.println("Name:");
					String user = keyboard.nextLine();

					System.out.println("Password:");
					String password = keyboard.nextLine();

					User try_user = uDAO.findByUsername(user);

					if (try_user.getUsername() != null && try_user.getUsername() != "") {
						if (try_user.getPassword().equals(password)) {
							current_user = try_user;
						} else {
							System.out.println("Wrong password");
							stop = true;
						}
					} else {
						System.out.println("Wrong user");
						stop = true;
					}

				} else if ("3".equals(input)) {

					stop = true;

				}

			}

			if (current_user != null) {

			}

			System.out.println("");

		}

		em.close();
		emfactory.close();
	}

}
