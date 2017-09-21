package com.jpanotesproject.tests.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.junit.Assert;
import org.junit.Test;

import com.jpanotesproject.model.ImageNote;
import com.jpanotesproject.model.Note;
import com.jpanotesproject.tests.helpers.AssertAnnotations;
import com.jpanotesproject.tests.helpers.ReflectTool;

public class ImageNoteTest {

	@Test
	public void noteInheritance() {
		Assert.assertEquals(Note.class, ImageNote.class.getSuperclass());
	}

	@Test
	public void typeAnnotations() {
		AssertAnnotations.assertType(ImageNote.class, Entity.class, Table.class);
	}

	@Test
	public void fieldAnnotations() {
		AssertAnnotations.assertField(ImageNote.class, "img", Column.class);
	}

	@Test
	public void gettersAnnotations() {
		AssertAnnotations.assertMethod(ImageNote.class, "getImage");
	}

	@Test
	public void entity() {
		// setup
		Entity a = ReflectTool.getClassAnnotation(ImageNote.class, Entity.class);

		// assert
		Assert.assertEquals("", a.name());
	}

	@Test
	public void table() {
		// setup
		Table a = ReflectTool.getClassAnnotation(ImageNote.class, Table.class);

		// assert
		Assert.assertEquals("Image_Note", a.name());
	}

	@Test
	public void content() {
		// setup
		Column c = ReflectTool.getFieldAnnotation(ImageNote.class, "img", Column.class);

		// assert
		Assert.assertEquals("content", c.name());
	}
}
