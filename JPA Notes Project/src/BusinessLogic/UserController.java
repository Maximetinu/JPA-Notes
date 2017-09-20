package BusinessLogic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.jpanotesproject.daos.UserDAO;

public class UserController {

	
	private static UserController instance = null;


	private static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JPA Notes Project");
	private static EntityManager em = emfactory.createEntityManager();
	private static UserDAO uDAO  = new UserDAO(em);;
	
	private UserController() {
	}
	
	public static UserController getInstance() {
		if (instance == null) {
			instance = new UserController();
		}
		return instance;
	}
	
	
	

}
