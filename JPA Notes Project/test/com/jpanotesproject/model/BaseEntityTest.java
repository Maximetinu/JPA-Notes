package com.jpanotesproject.model;

import java.lang.reflect.Modifier;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.junit.Assert;
import org.junit.Test;

import com.jpanotesproject.helpers.AssertAnnotations;
import com.jpanotesproject.helpers.ReflectTool;

public class BaseEntityTest {

	// Test que, usando reflección, pruebe que es abstract (Igual con Note)

	@Test
	public void abstractClass() {
		Assert.assertTrue(Modifier.isAbstract(BaseEntity.class.getModifiers()));
	}

	@Test
	public void typeAnnotations() {
		AssertAnnotations.assertType(BaseEntity.class, MappedSuperclass.class);
	}

	@Test
	public void fieldAnnotations() {
		AssertAnnotations.assertField(BaseEntity.class, "id", Column.class, Id.class, GeneratedValue.class, Basic.class);
	}

	@Test
	public void id() {
		// setup
		Column column = ReflectTool.getFieldAnnotation(BaseEntity.class, "id", Column.class);
		Basic basic = ReflectTool.getFieldAnnotation(BaseEntity.class, "id", Basic.class);
		GeneratedValue generatedValue = ReflectTool.getFieldAnnotation(BaseEntity.class, "id", GeneratedValue.class);

		// assert
		Assert.assertEquals("id", column.name());
		Assert.assertFalse(column.nullable());
		Assert.assertEquals("BIGINT UNSIGNED", column.columnDefinition());
		Assert.assertFalse(basic.optional());
		Assert.assertEquals(GenerationType.AUTO, generatedValue.strategy());
	}

}
