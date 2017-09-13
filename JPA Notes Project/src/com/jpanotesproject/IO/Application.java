package com.jpanotesproject.IO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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

		// Tag entity working
		// TODO: Uncomment from persistence.xml User and Note (one by one), debug and
		// correct

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JPA Notes Project");
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		/*
		 * inheritance_jpa // NotesCRUDService tDAO = new NotesCRUDService(); // tDAO.create();
		 * 
		 * Tag t = new Tag("haaa"); TagDAO tDAO = new TagDAO(em); tDAO.persist(t);
		 * 
		 * User user = new User("user_a", "my_password", "johnsnow@agagaga.com"); UserDAO userDAO = new UserDAO(em); userDAO.persist(user);
		 * 
		 * TextNote text_note = new TextNote(user, "title of the note", "text text text"); NotesDAO notesDAO = new NotesDAO(em); notesDAO.persist(text_note);
		 */

		// REALLY NIGGA
		// USUARIOS: Metinu, Gang, Oceloto, Penquiu, Ulises

		// NOTAS: NOTA-METINU-A, NOTA-METINU-B, NOTA-METINU-C -> METINU
		// NOTA-GANG-N -> GANG
		// NOTA-OCELOTO-X, NOTA-OCELOTO-Y, NOTA-OCELOTO-Z -> OCELOTO

		// NOTAS_COMPARTIDAS:
		// NOTA-METINU-C -> OCELOTO(2), GANG(1)
		// NOTA-GANG-N -> PENQUIU(2), METINU(2)
		// NOTA-OCELOTO-Y -> GANG(1)

		// TAGS: NOTA-METINU-C -> T1, T2, T3
		// NOTA-METINU-A -> T1, T7
		// NOTA-GANG-N -> T3, T5

		/* --------- START ------------- */

		/// MODEL
		// USERS
		User u1 = new User("metinu", "miwe117", "maximetinu@gmail.com");
		User u2 = new User("gang", "gangRulses", "ultrajavierman@hotmail.com");
		User u3 = new User("oceloto", "ocelotito_guapo1", "maxiguerrero@hotmail.com");
		User u4 = new User("penquiu", "mickeyesmiamigo", "penquiu@ono.com");
		User u5 = new User("ulises", "contraUlisescom", "ulyssesdarkline@gmail.com");

		// NOTES
		TextNote n1 = new TextNote(u1, "NOTA-METINU-A", "Javi guapo A");
		TextNote n2 = new TextNote(u1, "NOTA-METINU-B", "Javi guapo");
		TextNote n3 = new TextNote(u1, "NOTA-METINU-C", "Javi guapo");

		TextNote n4 = new TextNote(u2, "NOTA-GANG-N", "Javi guapo N");

		TextNote n5 = new TextNote(u3, "NOTA-OCELOTO-Y", "Javi guapo");
		TextNote n6 = new TextNote(u4, "NOTA-OCELOTO-X", "Javi guapo");
		TextNote n7 = new TextNote(u5, "NOTA-OCELOTO-Z", "Javi guapo");

		// TAGS CREATION
		Tag t1 = new Tag("T1");
		Tag t2 = new Tag("T2");
		Tag t3 = new Tag("T3");
		Tag t4 = new Tag("T4");
		Tag t5 = new Tag("T5");
		Tag t6 = new Tag("T6");
		Tag t7 = new Tag("T7");

		/// DAOS
		// DAOS CREATION
		UserDAO uDAO = new UserDAO(em);
		NoteDAO nDAO = new NoteDAO(em);
		TagDAO tDAO = new TagDAO(em);

		// PERSIST

		uDAO.persist(u1);
		uDAO.persist(u2);
		uDAO.persist(u3);
		uDAO.persist(u4);
		uDAO.persist(u5);

		nDAO.persist(n1);
		nDAO.persist(n2);
		nDAO.persist(n3);
		nDAO.persist(n4);
		nDAO.persist(n5);
		nDAO.persist(n6);
		nDAO.persist(n7);

		tDAO.persist(t1);
		tDAO.persist(t2);
		tDAO.persist(t3);
		tDAO.persist(t4);
		tDAO.persist(t5);
		tDAO.persist(t6);
		tDAO.persist(t7);

		// SHARED_NOTES
		n3.shareWith(u3, 2);
		n3.shareWith(u2, 1);

		n4.shareWith(u4, 2);
		n4.shareWith(u1, 2);

		n5.shareWith(u2, 1);

		// TAG ASSIGNMENT
		n3.addTag(t1);
		n3.addTag(t2);
		n3.addTag(t3);

		n1.addTag(t1);
		n1.addTag(t7);

		n4.addTag(t3);
		n4.addTag(t5);

		/////////////////////////////////////////////// NOW LET'S READ AND MODIFY ////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("------------------------------------ READ AND MODIFY ----------------------------------------");
		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------------------------");

		//// Reading N3 NOTA-METINU-C shared to Oceloto (u3) (ReadAndWrite) and Gang (u2) (OnlyRead)

		User uMetinu;
		uMetinu = uDAO.findById(1); // I know 1 is the ID of Metinu's User (checking the DB with phpmyadmin)

		// Now, is u Metinu?

		System.out.println(uMetinu.getUsername()); // IMPRIME "metinu", --> OK

		List<Note> metinusNotes = uMetinu.getOwnNotes();

		// TODO: null exception control
		// TODO: que tengamos que recorrer la lista de esta forma nos da una pista de que probablemente en lugar de una lista necesitemos un set (que adem�s no tiene repetidos)
		Note noteMetinuC = null;
		for (final Note note : metinusNotes) {
			if (note.getTitle() == "NOTA-METINU-C") {
				noteMetinuC = note;
			}
		}
		// NO ME SALE CAMBIARLO A SET. SI LO CAMBIO noteMetinuC sigue siendo NULL

		if (noteMetinuC != null) {
			System.out.println("NOTA-METINU-C est� en el Array de notas propias de Metinu");
			if (noteMetinuC.canReadAndWrite(u3))
				System.out.println("Oceloto tiene compartida NOTA-METINU-C con permisos de lectura y escritura");
			if (noteMetinuC.canReadOnly(u2))
				System.out.println("Gang tiene compartida NOTA-METINU-C con permisos de solo lectura");
			if (u3.getSharedNotes().containsKey(noteMetinuC))
				System.out.println("NOTA-METINU-C tambi�n est� en el sharedNotes de Oceloto");
			if (u2.getSharedNotes().containsKey(noteMetinuC))
				System.out.println("NOTA-METINU-C tambi�n est� en el sharedNotes de Gang");
			if (noteMetinuC.getSharedUsers().containsKey(u3))
				System.out.println("Oceloto tambi�n est� en el sharedUsers de NOTA-METINU-C");
			if (noteMetinuC.getSharedUsers().containsKey(u2))
				System.out.println("Gang tambi�n est� en el sharedUsers de NOTA-METINU-C");
		} // --> TODO OK, TODO SE IMPRIME CORRECTAMENTE Y COMO SE ESPERABA, TAL Y COMO EST� EN LA BD

		// AHORA CAMBIEMOS EL NOMBRE DE METINU A MAXIMETINU, DEJEMOS DE COMPARTIR LA NOTA A OCELOTO Y DEMOSLE A GANG PERMISOS TAMBI�N DE ESCRITURA
		// POR �LTIMO, TAMBI�N LE A�ADIREMOS A METINU UNA NUEVA NOTA DE AUDIO LLAMADA NOTA-METINU-AUDIO (as� de paso probamos la herencia)

		uMetinu.setUsername("Maximetinu");
		noteMetinuC.setPermission(u3, 0);
		noteMetinuC.setPermission(u2, 2);

		// Volvemos a hacer las pruebas (aunque se puede comprobar tambi�n en phpmyadmin, y de hecho se debe por si algo se duplica en lugar de modificarse, etc
		if (noteMetinuC != null) {
			if (!noteMetinuC.canReadAndWrite(u3))
				System.out.println("Oceloto YA NO tiene compartida NOTA-METINU-C con permisos de lectura y escritura");
			if (!noteMetinuC.canReadOnly(u2))
				System.out.println("Gang YA NO tiene compartida NOTA-METINU-C con permisos de solo lectura");
			if (noteMetinuC.canReadAndWrite(u2))
				System.out.println("Gang AHORA SI tiene compartida NOTA-METINU-C con permisos de lectura y escritura");
			if (!u3.getSharedNotes().containsKey(noteMetinuC))
				System.out.println("NOTA-METINU-C YA NO est� en el sharedNotes de Oceloto");
			if (u2.getSharedNotes().containsKey(noteMetinuC))
				System.out.println("NOTA-METINU-C tambi�n est� en el sharedNotes de Gang");
			if (!noteMetinuC.getSharedUsers().containsKey(u3))
				System.out.println("Oceloto YA NO est� en el sharedUsers de NOTA-METINU-C");
			if (noteMetinuC.getSharedUsers().containsKey(u2))
				System.out.println("Gang tambi�n est� en el sharedUsers de NOTA-METINU-C");
		} // --> TODO OK (aunque he tenido que corregir cosillas). Tambi�n en phpmyadmin veo como las colecciones tienen 1 entrada menos

		Note newMetinuNote = new AudioNote(uMetinu, "NOTA-METINU-AUDIO", "AUDIOAUDIOAUDIOAUDIOAUDIO");
		nDAO.persist(newMetinuNote); // Persistir justo despu�s del new, que si no se guarda solo el estado final y no las posibles modificaciones
		// (si el estado final fuese el mismo que el inicial no lo notar�amos)

		// Como al crear la nota ya se est� a�adiendo, esto lo a�adir�a por duplicado. Si fuera un Set no pasar�a, si comprobasemos duplicados al a�adir a la List tampoco, pero en ambos casos nunca se
		// rellenar�a ownNotes y da nulos
		// uMetinu.addAuthorNote(newMetinuNote);

		Note audioNoteTest = null;
		metinusNotes = uMetinu.getOwnNotes();
		for (final Note note : metinusNotes) {
			if (note.getTitle() == "NOTA-METINU-AUDIO") {
				audioNoteTest = note;
			}
		}

		if (audioNoteTest != null) {
			System.out.println("NOTA-METINU-AUDIO est� en el Array de notas propias de Metinu");
		} /// --> TODO OK

		// CONCLUSI�N: LECTURA Y MODIFICACI�N FUNCIONAN, PERO FALTAN COSAS

		// - En lugar de buscar al usuario Metinu por el ID (que en la aplicaci�n ni se conocer�), hay que implementar el m�todo UserDAO.findByUsername(), consultando con SQL

		// - Aparte de FindByUsername, se pueden implementar List<Note> UserDAO.getUserSharedNotes(u) por ejemplo, pero ser�a redundante porque es m�s f�cil hacer
		// UserDAO.findByUsername("Metinu").getSharedNotes().

		// - IMPORTANTE: deber�amos cambiar la lista de OwnNotes por un set, y tambi�n la lista de tags de las notas. O eso o comprobar duplicados. Pero cuidado que al hacerlo no funciona bien, hay
		// que encontrar por qu�

		User user_retrieval  = uDAO.findByUsername("penquiu");
		System.out.println("----> USER RETRIEVAL => " + user_retrieval.getUsername());
	
		
		
		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------------------------");
		em.getTransaction().commit();

		em.close();
		emfactory.close();
	}

}
