package com.shop.database.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <code>Object</code> is entity class, a structural unit for any object in the project as user,
 * or shop item, or shopping cart.
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
    @ManyToOne
    @JoinColumn(name = "OBJECT_TYPE_ID", referencedColumnName = "OBJECT_TYPE_ID")
    private ObjectType objectType;
    @ManyToOne
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "OBJECT_ID")
    private Object parent;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "object", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Parameter> parameters = new ArrayList<Parameter>();
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "object")
    private List<Reference> references = new ArrayList<>();
    @Transient
    private Map<String, Parameter> mapParameters = new HashMap<String, Parameter>();

    public Object() {
    }

    public Object(String name, ObjectType objectType, Object parent) {
        this.name = name;
        this.objectType = objectType;
        this.parent = parent;
    }
    public Map<String, Parameter> getMapParameters() {
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
        for(Parameter param : parameters){
            mapParameters.put(param.getAttribute().getName(), param);
        }
        this.parameters = parameters;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }
}
