package com.shop.database.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Created by said on 06.05.2017.
 */
@Entity
@Table(name = "LAB3_OBJECT_TYPES")
public class ObjectType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "OBJECT_TYPE_ID", length = 10, nullable = false)
    private int id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "OBJECT_TYPE_ID", insertable = false, updatable = false)
    private ObjectType parent;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "objectType", cascade = CascadeType.ALL)
    private List<Attribute> attributes;


    public ObjectType() {
    }

    public ObjectType(int id, String name) {
        this.id = id;
        this.name = name;
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

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public ObjectType getParent() {
        return parent;
    }

    public void setParent(ObjectType parent) {
        this.parent = parent;
    }
}
