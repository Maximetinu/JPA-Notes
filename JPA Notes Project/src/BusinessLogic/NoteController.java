package BusinessLogic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.jpanotesproject.daos.NoteDAO;
import com.jpanotesproject.daos.TagDAO;
import com.jpanotesproject.daos.UserDAO;

public class NoteController {

	
	private static NoteController instance = null;


	private static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JPA Notes Project");
	private static EntityManager em = emfactory.createEntityManager();
	private static NoteDAO nDAO  = new NoteDAO(em);
	private static TagDAO tDAO  = new TagDAO(em);
	
	private NoteController() {
	}
	
	public static NoteController getInstance() {
		if (instance == null) {
			instance = new NoteController();
		}
		return instance;
	}
	
}
