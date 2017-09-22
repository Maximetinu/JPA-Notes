package com.jpanotesproject.daos;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jpanotesproject.mocks.EntityManagerMock;
import com.jpanotesproject.model.BaseEntity;

public class BaseDAOTest {

	// Setting Up Mocking structure

	public class MockingEntity extends BaseEntity {
		public MockingEntity() {
			super();
			super.id = null;
		}

		public MockingEntity(long id) {
			super();
			super.id = id;
		}
	}

	public class MockingDAO extends BaseDAO<MockingEntity> {
		public MockingDAO(EntityManager context) {
			super(MockingEntity.class, context);
		}

	}

	MockingDAO mockDAO;
	EntityManagerMock em;

	@Before
	public void setUp() {
		em = new EntityManagerMock();
		mockDAO = new MockingDAO(em);
	}

	@Test
	public void persistTest() {
		MockingEntity entityMock = new MockingEntity();

		mockDAO.persist(entityMock);
		Assert.assertTrue(em.isPersistCalled());
		Assert.assertFalse(em.isMergeCalled());

		em.resetMockFlags();

		entityMock = new MockingEntity(10);
		mockDAO.persist(entityMock);
		Assert.assertTrue(em.isMergeCalled());
		Assert.assertFalse(em.isPersistCalled());
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeTest() {
		MockingEntity entityMock = new MockingEntity();
		mockDAO.remove(entityMock);

		entityMock = new MockingEntity(999);
		mockDAO.remove(entityMock);
		Assert.assertTrue(em.isRemoveCalled());
	}

	@Test
	public void findByIDTest() {
		mockDAO.findById(999);
		Assert.assertTrue(em.isFindCalled());
	}

}
