package com.jpanotesproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.junit.Assert;
import org.junit.Test;

import com.jpanotesproject.helpers.AssertAnnotations;
import com.jpanotesproject.helpers.ReflectTool;

public class TextNoteTest {

	@Test
	public void noteInheritance() {
		Assert.assertEquals(Note.class, TextNote.class.getSuperclass());
	}

	@Test
	public void typeAnnotations() {
		AssertAnnotations.assertType(TextNote.class, Entity.class, Table.class);
	}

	@Test
	public void fieldAnnotations() {
		AssertAnnotations.assertField(TextNote.class, "text", Column.class);
	}

	@Test
	public void gettersAnnotations() {
		AssertAnnotations.assertMethod(TextNote.class, "getText");
	}

	@Test
	public void entity() {
		// setup
		Entity a = ReflectTool.getClassAnnotation(TextNote.class, Entity.class);

		// assert
		Assert.assertEquals("", a.name());
	}

	@Test
	public void table() {
		// setup
		Table a = ReflectTool.getClassAnnotation(TextNote.class, Table.class);

		// assert
		Assert.assertEquals("Text_Note", a.name());
	}

	@Test
	public void content() {
		// setup
		Column c = ReflectTool.getFieldAnnotation(TextNote.class, "text", Column.class);

		// assert
		Assert.assertEquals("content", c.name());
	}
}
