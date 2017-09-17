package com.jpanotesproject.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.junit.Test;

import com.jpanotesproject.testing.AssertAnnotations;

public class BaseEntityTest {

	// Test que, usando reflección, pruebe que es abstract (Igual con Note)

	@Test
	public void typeAnnotations() {
		AssertAnnotations.assertType(BaseEntity.class, MappedSuperclass.class);
	}

	@Test
	public void fieldAnnotations() {
		AssertAnnotations.assertField(BaseEntity.class, "id", Column.class, Id.class, GeneratedValue.class, Basic.class);
	}

}
