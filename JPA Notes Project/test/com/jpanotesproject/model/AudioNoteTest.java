package com.jpanotesproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.junit.Assert;
import org.junit.Test;

import com.jpanotesproject.helpers.AssertAnnotations;
import com.jpanotesproject.helpers.ReflectTool;

public class AudioNoteTest {

	@Test
	public void noteInheritance() {
		Assert.assertEquals(Note.class, AudioNote.class.getSuperclass());
	}

	@Test
	public void typeAnnotations() {
		AssertAnnotations.assertType(AudioNote.class, Entity.class, Table.class);
	}

	@Test
	public void fieldAnnotations() {
		AssertAnnotations.assertField(AudioNote.class, "audio", Column.class);
	}

	@Test
	public void gettersAnnotations() {
		AssertAnnotations.assertMethod(AudioNote.class, "getAudio");
	}

	@Test
	public void entity() {
		// setup
		Entity a = ReflectTool.getClassAnnotation(AudioNote.class, Entity.class);

		// assert
		Assert.assertEquals("", a.name());
	}

	@Test
	public void table() {
		// setup
		Table a = ReflectTool.getClassAnnotation(AudioNote.class, Table.class);

		// assert
		Assert.assertEquals("Audio_Note", a.name());
	}

	@Test
	public void content() {
		// setup
		Column c = ReflectTool.getFieldAnnotation(AudioNote.class, "audio", Column.class);

		// assert
		Assert.assertEquals("content", c.name());
	}
}
