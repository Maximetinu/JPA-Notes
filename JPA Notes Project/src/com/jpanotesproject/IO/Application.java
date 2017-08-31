package com.jpanotesproject.IO;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.jpanotesproject.daos.NoteDAO;
import com.jpanotesproject.daos.TagDAO;
import com.jpanotesproject.daos.UserDAO;
import com.jpanotesproject.model.Note;
import com.jpanotesproject.model.Tag;
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
		Note n1 = new Note(u1, "NOTA-METINU-A");
		Note n2 = new Note(u1, "NOTA-METINU-B");
		Note n3 = new Note(u1, "NOTA-METINU-C");

		Note n4 = new Note(u2, "NOTA-GANG-N");

		Note n5 = new Note(u3, "NOTA-OCELOTO-Y");
		Note n6 = new Note(u4, "NOTA-OCELOTO-X");
		Note n7 = new Note(u5, "NOTA-OCELOTO-Z");

		// SHARED_NOTES
		n3.shareWith(u3, 2);
		n3.shareWith(u2, 1);

		n4.shareWith(u4, 2);
		n4.shareWith(u1, 2);

		n5.shareWith(u2, 1); // COMO GANG YA TENÍA UNA NOTA COMPARTIDA NO SE HA
		// METIDO LA SEGUNDA
		// IGUAL PARA LAS NOTAS. N3 Y N4 SOLO SE HAN QUEDADO CON LA PRIMERA COMPARTICION
		// FALLO ENCONTRADO:
		// Cuando añadimos a Gang.sharedNotes la segunda nota, esta no se mete
		// directamente. O al menos tras los persist no está ahí.

		// TAGS CREATION
		Tag t1 = new Tag("T1");
		Tag t2 = new Tag("T2");
		Tag t3 = new Tag("T3");
		Tag t4 = new Tag("T4");
		Tag t5 = new Tag("T5");
		Tag t6 = new Tag("T6");
		Tag t7 = new Tag("T7");

		// TAG ASSIGNMENT
		n3.addTag(t1);
		n3.addTag(t2);
		n3.addTag(t3);

		n1.addTag(t1);
		n1.addTag(t7);

		n4.addTag(t3);
		n4.addTag(t5);

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

		/* ---------- END ------------- */

		em.getTransaction().commit();

		/*
		 * CUAL ES EL PROBLEMA Le compartimos a U2 (Gang) 2 notas diferentes, Metinu-C y
		 * Oceloto-Y Después de persistir a Gang y hacer el commit, en la BD Gang solo
		 * tiene a Metinu-C (la segunda no se ha metido) Luego, ¿el problema es solo en
		 * la BD (problema JPA) o en el Map sharedNotes (problema Java)? comprobemoslo
		 * ---> No tendría sentido que fuese fallo del Java.Map porque a un map
		 * SharedNotes<Note, Integer> le estamos metiendo dos notas diferentes, no es
		 * duplicate key.
		 * 
		 * CUAL ES EL PROBLEMA Lo primero que hago es imprimir las Shared Notes de Gang
		 * tras el persist y todo y joder, solo tiene 1, veamos por qué
		 * 
		 * 
		 * Para comprobarlo a continuación hacemos... Le meto la otra nota N5 a Gang de
		 * nuevio y efectivamente se mete. Todo apunta a que es fallo de JPA entonces
		 * 
		 * Comenzamos transacción nueva con el entity manager, persistimos a Gang de
		 * nuevo ahora con 2 Shared Notes, y vamos a comprobar... En este momento espero
		 * que solo tenga 1 y el problema sea al persistir... ¡Y no! Tras persistirlo
		 * sigue teniendo 2.
		 * 
		 * ¡Ah! A lo mejor pasa a tener 1 cuando haga el commit de la transacción. Si,
		 * claro, de hecho seguramente si no hago el commit no se suben los datos
		 * persistidos. Hago el commit y compruebo de nuevo...
		 * 
		 * ¡¡Jodeeer!!! Al imprimir el Map Se ha metido todo bien y Javi tiene dos
		 * notas!! A ver que pasa en la MySQL... WTFFF!!! Aparece la segunda de Gang con
		 * NULL en lo que debería ser la clave primaria, no tiene sentido porque la PK
		 * no puede ser NULL
		 * 
		 * Conclusiones que saco. El fallo parece estar en las anotaciones JPA. En algún
		 * lugar tendré que decirle que la clave primaria no es el USERNAME porque puede
		 * haber varios
		 * 
		 * Esto tendré que decirlo en la clase user, en las anotaciones de sharedNotes
		 * 
		 * NOTA: fijarse en
		 * http://www.logicbig.com/tutorials/java-ee-tutorial/jpa/map-with-entity-keys/
		 * que es el tutorial que me ha enseñado a mapear el Map
		 * 
		 * Lo que no entiendo es como insistiendole ha metido NULL en la clave primaria.
		 * Es que no tiene sentido. Es muy raro que haga esto solo si persisto dos veces
		 * con la nota compartida pero si es solo 1 vez no.
		 * 
		 * Por cierto, ocurre exactamente lo mismo en el otro Map, el de sharedUsers de
		 * la nota.
		 * 
		 * La solución debe de estar jugando con los parámetros del @JoinColumn dentro
		 * del @CollectionTable del Map
		 * https://www.eclipse.org/eclipselink/api/2.0.2/javax/persistence/JoinColumn.
		 * html
		 * 
		 * De alguna manera en los parámetros del CollectionTable y del JoinColumn debo
		 * poder decirle que la clave es otra cualquiera (a poder ser una suma de
		 * strings entre la nota y el usuario, o un autoincrement) y que el usuario sí
		 * que se puede repetir (unique = false)
		 */

		System.out.println(printMap(u2.getSharedNotes())); // SOLO TIENE 1
		n5.shareWith(u2, 1); // LE METEMOS LA OTRA NOTA
		System.out.println(printMap(u2.getSharedNotes())); // VEMOS QUE SE HA METIDO Y TIENE 2

		em.getTransaction().begin(); // COMENZAMOS NUEVA TRANSACCIÓN
		uDAO.persist(u2); // PERSISTIMOS A GANG Y VOLVEMOS A COMPROBAR...
		System.out.println(printMap(u2.getSharedNotes())); // TRAS PERSISTIRLO SIGUE TENIENDO 2

		em.getTransaction().commit(); // HACEMOS UN COMMIT
		System.out.println(printMap(u2.getSharedNotes())); // WTFF SE HA METIDO Y EN LA TABLA MYSQL APARECE NULL

		em.close();
		emfactory.close();
	}

	public static String printMap(Map<Note, Integer> map) {
		StringBuilder sb = new StringBuilder();
		Iterator<Entry<Note, Integer>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Note, Integer> entry = iter.next();
			sb.append(entry.getKey());
			sb.append('=').append('"');
			sb.append(entry.getValue());
			sb.append('"');
			if (iter.hasNext()) {
				sb.append(',').append(' ');
			}
		}
		return sb.toString();

	}

}
