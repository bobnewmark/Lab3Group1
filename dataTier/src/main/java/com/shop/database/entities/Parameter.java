package com.shop.database.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;



@Entity
@Table(name = "LAB3_PARAMETERS")
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PARAMETER_ID", length = 10, nullable = false)
    private int id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "OBJECT_ID", referencedColumnName = "OBJECT_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Object object;
    @ManyToOne
    @JoinColumn(name = "ATTRIBUTE_ID", referencedColumnName = "ATTRIBUTE_ID")
    private Attribute attribute;
    @Column(name = "VALUE")
    private String value;


    public Parameter() {
    }

    public Parameter(Object object, Attribute attribute, String value) {
        this.object = object;
        this.attribute = attribute;
        this.value = value;
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

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
