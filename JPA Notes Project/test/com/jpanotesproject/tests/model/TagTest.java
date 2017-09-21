package com.jpanotesproject.tests.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.junit.Assert;
import org.junit.Test;

import com.jpanotesproject.model.BaseEntity;
import com.jpanotesproject.model.Tag;
import com.jpanotesproject.tests.helpers.AssertAnnotations;
import com.jpanotesproject.tests.helpers.ReflectTool;

public class TagTest {

	@Test
	public void typeAnnotations() {
		AssertAnnotations.assertType(Tag.class, Entity.class, Table.class);
	}

	@Test
	public void fieldAnnotations() {
		AssertAnnotations.assertField(Tag.class, "tag_text", Column.class);
	}

	@Test
	public void gettersAnnotations() {
		AssertAnnotations.assertMethod(Tag.class, "getTagText");
	}

	@Test
	public void entity() {
		// setup
		Entity a = ReflectTool.getClassAnnotation(Tag.class, Entity.class);

		// assert
		Assert.assertEquals("", a.name());
	}

	@Test
	public void table() {
		// setup
		Table a = ReflectTool.getClassAnnotation(Tag.class, Table.class);

		// assert
		Assert.assertEquals("Tag", a.name());
	}

	@Test
	public void tag_text() {
		// setup
		Column c = ReflectTool.getFieldAnnotation(Tag.class, "tag_text", Column.class);

		// assert
		Assert.assertEquals("tag_text", c.name());
		Assert.assertEquals(true, c.unique());
		Assert.assertEquals(255, c.length());
	}
	

	@Test
	public void baseEntityInheritance() {
		Assert.assertEquals(BaseEntity.class, Tag.class.getSuperclass());
	}

}
