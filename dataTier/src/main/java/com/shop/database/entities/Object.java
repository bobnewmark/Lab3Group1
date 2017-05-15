package com.shop.database.entities;
import javax.persistence.*;
import java.util.List;

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
    @ManyToOne
    @JoinColumn(name = "OBJECT_TYPE_ID", referencedColumnName = "OBJECT_TYPE_ID")
    private ObjectType objectType;
    @ManyToOne
    @JoinColumn(name = "PARENT_ID", insertable = false, updatable = false)
    private Object parent;

    public Object() {
    }

    public Object(String name, ObjectType objectType) {
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

    public Object getParent() {
        return parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;
    }
}
