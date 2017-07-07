package com.shop.database.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * <code>ObjectType</code> is an organizational class for a group of objects
 * that defines attributes for each object in the group.
 */
@Entity
@Table(name = "LAB3_OBJECT_TYPES")
public class ObjectType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "OBJECT_TYPE_ID", length = 10, nullable = false)
    private int id;
    @Column(name = "NAME", unique = true)
    private String name;
    @Column(name = "ICON")
    private String icon;
    @Column(name = "product")
    private boolean product;
    @ManyToOne
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "OBJECT_TYPE_ID", updatable = false)
    private ObjectType parent;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "objectType", cascade = CascadeType.ALL)
    private List<Attribute> attributes;


    public ObjectType() {
    }

    public ObjectType(String name, ObjectType parent, List<Attribute> attributes) {
        this.name = name;
        this.parent = parent;
        this.attributes = attributes;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isProduct() {
        return product;
    }

    public void setProduct(boolean product) {
        this.product = product;
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
