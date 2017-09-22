package com.jpanotesproject.daos;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BaseDAOTest.class, TagDAOTest.class, UserDAOTest.class })
public class AllTestsDAOS {

}
