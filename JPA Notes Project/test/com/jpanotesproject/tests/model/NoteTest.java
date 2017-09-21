package com.jpanotesproject.tests.model;

import java.lang.reflect.Modifier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.junit.Assert;
import org.junit.Test;

import com.jpanotesproject.model.BaseEntity;
import com.jpanotesproject.model.Note;
import com.jpanotesproject.tests.helpers.AssertAnnotations;
import com.jpanotesproject.tests.helpers.ReflectTool;

public class NoteTest {

	@Test
	public void typeAnnotations() {
		AssertAnnotations.assertType(Note.class, Entity.class, Inheritance.class);
	}
	
	@Test
	public void abstractClass() {
		Modifier.isAbstract(BaseEntity.class.getModifiers());
	}

	@Test
	public void fieldAnnotations() {
		AssertAnnotations.assertField(Note.class, "title", Column.class);
		AssertAnnotations.assertField(Note.class, "creationDate", Column.class);
		AssertAnnotations.assertField(Note.class, "lastEditDate", Column.class);
		AssertAnnotations.assertField(Note.class, "author", JoinColumn.class, ManyToOne.class);
		AssertAnnotations.assertField(Note.class, "tags", ManyToMany.class, JoinTable.class);
	}

	@Test
	public void gettersAnnotations() {
		AssertAnnotations.assertMethod(Note.class, "getTagsList");
		AssertAnnotations.assertMethod(Note.class, "getTitle");
	}

	@Test
	public void entity() {
		// setup
		Entity a = ReflectTool.getClassAnnotation(Note.class, Entity.class);

		// assert
		Assert.assertEquals("", a.name());
	}

	@Test
	public void Inheritance() {
		// setup
		Inheritance a = ReflectTool.getClassAnnotation(Note.class, Inheritance.class);

		// assert
		Assert.assertEquals(InheritanceType.JOINED, a.strategy());
	}

	@Test
	public void baseEntityInheritance() {
		Assert.assertEquals(BaseEntity.class, Note.class.getSuperclass());
	}
	
	@Test
	public void title() {
		// setup
		Column c = ReflectTool.getFieldAnnotation(Note.class, "title", Column.class);

		// assert
		Assert.assertEquals("title", c.name());
		Assert.assertEquals(255, c.length());
	}

	@Test
	public void creationDate() {
		// setup
		Column c = ReflectTool.getFieldAnnotation(Note.class, "creationDate", Column.class);

		// assert
		Assert.assertEquals("creation_date", c.name());
	}

	@Test
	public void lastEditDate() {
		// setup
		Column c = ReflectTool.getFieldAnnotation(Note.class, "lastEditDate", Column.class);

		// assert
		Assert.assertEquals("last_edit_date", c.name());
	}

	@Test
	public void author() {
		// setup
		JoinColumn joinColumn = ReflectTool.getFieldAnnotation(Note.class, "author", JoinColumn.class);

		// assert
		Assert.assertEquals("username", joinColumn.referencedColumnName());
		Assert.assertEquals("author", joinColumn.name());
	}

	@Test
	public void tags() {
		// setup
		JoinTable joinTable = ReflectTool.getFieldAnnotation(Note.class, "tags", JoinTable.class);
		JoinColumn directJoinColumn = joinTable.joinColumns()[0];
		JoinColumn inverseJoinColumn = joinTable.inverseJoinColumns()[0];

		// assert
		Assert.assertEquals("Note_Has_Tags", joinTable.name());
		Assert.assertEquals("note_id", directJoinColumn.name());
		Assert.assertEquals("id", directJoinColumn.referencedColumnName());
		Assert.assertEquals("tag_text", inverseJoinColumn.name());
		Assert.assertEquals("tag_text", inverseJoinColumn.referencedColumnName());
	}
}
