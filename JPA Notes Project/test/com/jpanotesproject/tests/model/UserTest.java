package com.jpanotesproject.tests.model;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.junit.Assert;
import org.junit.Test;

import com.jpanotesproject.model.BaseEntity;
import com.jpanotesproject.model.User;
import com.jpanotesproject.tests.helpers.AssertAnnotations;
import com.jpanotesproject.tests.helpers.ReflectTool;

public class UserTest {

	@Test
	public void baseEntityInheritance() {
		Assert.assertEquals(BaseEntity.class, User.class.getSuperclass());
	}

	@Test
	public void typeAnnotations() {
		AssertAnnotations.assertType(User.class, Entity.class, Table.class);
	}

	@Test
	public void fieldAnnotations() {
		AssertAnnotations.assertField(User.class, "username", Column.class);
		AssertAnnotations.assertField(User.class, "password", Column.class);
		AssertAnnotations.assertField(User.class, "registrationDate", Column.class);
		AssertAnnotations.assertField(User.class, "sharedNotes", ElementCollection.class, CollectionTable.class, MapKeyJoinColumn.class, Column.class);
		AssertAnnotations.assertField(User.class, "ownNotes", OneToMany.class, JoinTable.class);
	}

	@Test
	public void gettersAnnotations() {
		AssertAnnotations.assertMethod(User.class, "getUsername");
		AssertAnnotations.assertMethod(User.class, "getPassword");
		AssertAnnotations.assertMethod(User.class, "getRegistrationDate");
		AssertAnnotations.assertMethod(User.class, "getOwnNotes");
		AssertAnnotations.assertMethod(User.class, "getSharedNotes");
	}

	@Test
	public void entity() {
		// setup
		Entity a = ReflectTool.getClassAnnotation(User.class, Entity.class);

		// assert
		Assert.assertEquals("", a.name());
	}

	@Test
	public void table() {
		// setup
		Table a = ReflectTool.getClassAnnotation(User.class, Table.class);

		// assert
		Assert.assertEquals("User", a.name());
	}

	@Test
	public void username() {
		// setup
		Column c = ReflectTool.getFieldAnnotation(User.class, "username", Column.class);

		// assert
		Assert.assertEquals("username", c.name());
		Assert.assertEquals(true, c.unique());
		Assert.assertEquals(255, c.length());
	}

	@Test
	public void email() {
		// setup
		Column c = ReflectTool.getFieldAnnotation(User.class, "email", Column.class);

		// assert
		Assert.assertEquals("email", c.name());
		Assert.assertEquals(true, c.unique());
		Assert.assertEquals(255, c.length());
	}

	@Test
	public void registrationDate() {
		// setup
		Column c = ReflectTool.getFieldAnnotation(User.class, "registrationDate", Column.class);

		// assert
		Assert.assertEquals("resgistration_date", c.name());
	}

	@Test
	public void sharedNotes() {
		// setup
		Column column = ReflectTool.getFieldAnnotation(User.class, "sharedNotes", Column.class);
		MapKeyJoinColumn mapKeyJoinColumn = ReflectTool.getFieldAnnotation(User.class, "sharedNotes", MapKeyJoinColumn.class);
		CollectionTable collectionTable = ReflectTool.getFieldAnnotation(User.class, "sharedNotes", CollectionTable.class);
		JoinColumn joinColumnFromCollectionTable = collectionTable.joinColumns()[0];

		// assert
		Assert.assertEquals("permission_level", column.name());
		Assert.assertEquals("shared_note", mapKeyJoinColumn.name());
		Assert.assertEquals("id", mapKeyJoinColumn.referencedColumnName());
		Assert.assertEquals("User_Has_Shared_Notes", collectionTable.name());
		Assert.assertEquals("shared_to", joinColumnFromCollectionTable.name());
		Assert.assertEquals("username", joinColumnFromCollectionTable.referencedColumnName());
	}

	@Test
	public void ownNotes() {
		// setup
		JoinTable joinTable = ReflectTool.getFieldAnnotation(User.class, "ownNotes", JoinTable.class);
		JoinColumn directJoinColumn = joinTable.joinColumns()[0];
		JoinColumn inverseJoinColumn = joinTable.inverseJoinColumns()[0];

		// assert
		Assert.assertEquals("User_Has_Notes", joinTable.name());
		Assert.assertEquals("author_name", directJoinColumn.name());
		Assert.assertEquals("username", directJoinColumn.referencedColumnName());
		Assert.assertEquals("note_id", inverseJoinColumn.name());
		Assert.assertEquals("id", inverseJoinColumn.referencedColumnName());
	}
}
