package com.shop.database.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Created by said on 06.05.2017.
 */
@Entity
@Table(name = "LAB3_ATTRIBUTES")
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ATTRIBUTE_ID", length = 10, nullable = false)
    private int id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "OBJECT_TYPE_ID", referencedColumnName = "OBJECT_TYPE_ID")
    private ObjectType objectType;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "attribute", cascade = CascadeType.ALL)
    private List<Parameter> parameters;


    public Attribute() {
    }

    public Attribute(String name, ObjectType objectType) {
        this.name = name;
        this.objectType = objectType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObjectType getObjectType() {
        return objectType;
    }

    public void setObjectType(ObjectType objectType) {
        this.objectType = objectType;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
}
