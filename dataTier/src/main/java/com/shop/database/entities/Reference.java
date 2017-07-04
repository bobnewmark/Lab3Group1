package com.shop.database.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * <code>Reference</code> is a realization of an attribute, but instead of having some value
 * it is a link to another object.
 */
@Entity
@Table(name = "LAB3_REFERENCES")
public class Reference {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "REFERENCE_ID", length = 10, insertable = false, updatable = false, nullable = false)
    private int id;
    @JsonIgnore
    @ManyToOne
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "OBJECT_ID", referencedColumnName = "OBJECT_ID")
    private Object object;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "REF_OBJECT_ID", referencedColumnName = "OBJECT_ID")
    private Object refObject;
    @Column(name = "NAME")
    private String name;
    @ManyToOne
    @JoinColumn(name = "ATTRIBUTE_ID", referencedColumnName = "ATTRIBUTE_ID")
    private Attribute attribute;

    public Reference() {
    }

    public Reference(Object object, Object refObject, String name, Attribute attribute) {
        this.object = object;
        this.refObject = refObject;
        this.name = name;
        this.attribute = attribute;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Object getRefObject() {
        return refObject;
    }

    public void setRefObject(Object refObject) {
        this.refObject = refObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }
}
