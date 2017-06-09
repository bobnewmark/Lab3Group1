package com.shop.database.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by said on 06.05.2017.
 */
@Entity
@Table(name = "LAB3_OBJECTS")
public class Object {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "OBJECT_ID", length = 10, insertable = false, updatable = false, nullable = false)
    private int id;
    @Column(name = "NAME")
    private String name;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "OBJECT_TYPE_ID", referencedColumnName = "OBJECT_TYPE_ID")
    private ObjectType objectType;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "PARENT_ID", insertable = false, updatable = false)
    private Object parent;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "object", cascade = CascadeType.ALL)
    private List<Parameter> parameters = new ArrayList<>();
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "object", cascade = CascadeType.ALL)
    private List<Reference> references;
    @Transient
    private Map<String, Parameter> mapParameters = new HashMap<>();

    public Object() {
    }

    public Object(String name, ObjectType objectType, Object parent) {
        this.name = name;
        this.objectType = objectType;
        this.parent = parent;
    }public Map<String, Parameter> getMapParameters() {
        return mapParameters;
    }

    public void setMapParameters(Map<String, Parameter> mapParameters) {
        this.mapParameters = mapParameters;
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

    public Object getParent() {
        return parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }
}
