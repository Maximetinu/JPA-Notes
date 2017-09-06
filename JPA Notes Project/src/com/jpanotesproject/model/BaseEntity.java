package com.jpanotesproject.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity{
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT UNSIGNED")
    protected Long id;
 
    public Long getId() {
        return id;
    }
 
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getId() != null ? this.getId().hashCode() : 0);
 
        return hash;
    }
 
    @Override
    public boolean equals(Object object) {
    if (this == object)
            return true;
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;
 
        BaseEntity other = (BaseEntity) object;
        if (this.getId() != other.getId() && (this.getId() == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
 
    @Override
    public String toString() {
        return this.getClass().getName() + " [ID=" + id + "]";
    }
}
