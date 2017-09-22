package com.jpanotesproject.businesslogic;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ LoginControllerTest.class, NoteControllerTest.class, TagControllerTest.class, UserControllerTest.class })
public class AllTestsBusinessLogic {

}
