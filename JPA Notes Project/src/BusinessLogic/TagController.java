package BusinessLogic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.jpanotesproject.daos.TagDAO;
import com.jpanotesproject.daos.UserDAO;
import com.jpanotesproject.model.Tag;

public class TagController {

	
	private static TagController instance = null;


	private static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JPA Notes Project");
	private static EntityManager em = emfactory.createEntityManager();
	private static TagDAO tDAO  = new TagDAO(em);;
	
	private TagController() {
	}
	
	public static TagController getInstance() {
		if (instance == null) {
			instance = new TagController();
		}
		return instance;
	}
	
	public boolean exist(String tag_str) {
		return tDAO.findByTag(tag_str) != null;
	}
	
	public Tag getTag(String tag_str) {
		return tDAO.findByTag(tag_str);
	}
	
	public Tag registerNewTag(String tag) {
		Tag result = null;

		em.getTransaction().begin();
		try {
			Tag new_tag = new Tag(tag);
			tDAO.persist(new_tag);
			result = new_tag;
		} catch (Exception e) {
			e.getMessage();
		}
		em.getTransaction().commit();
		
		return result;
	}

	public Tag getTagForce(String tag_str) {
		Tag tag = null;

		if (!exist(tag_str)) {
			tag = registerNewTag(tag_str);
		} else {
			tag = getTag(tag_str);
		}
		
		return tag;
	}
	
}
