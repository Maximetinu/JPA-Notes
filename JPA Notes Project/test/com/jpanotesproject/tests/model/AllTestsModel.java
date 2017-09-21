package com.jpanotesproject.tests.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AudioNoteTest.class, BaseEntityTest.class, ImageNoteTest.class, NoteTest.class, TagTest.class, TextNoteTest.class, UserTest.class })
public class AllTestsModel {

}
