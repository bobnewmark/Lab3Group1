package com.shop.database.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.lang.*;

/**
 * Created by said on 06.05.2017.
 */
@Entity
@Table(name = "LAB3_PARAMS")
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PARAMETER_ID", length = 10, nullable = false)
    private int id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "OBJECT_ID", referencedColumnName = "OBJECT_ID")
    private Object object;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ATTRIBUTE_ID", referencedColumnName = "ATTRIBUTE_ID")
    private Attribute attribute;
    @Column(name = "VALUE")
    private String value;
//    @Embedded
//    @Id
//    private Key id;

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

//    @Embeddable
//    public static class Key implements Serializable{
//        private int object_id;
//        private int attribute_id;
//        private String value;
//
//        public Key() {
//        }
//
//        public Key(Object object, Attribute attribute, String value) {
//            this.object_id = object.getId();
//            this.attribute_id = attribute.getId();
//            this.value = value;
//        }
//
//        public int getObject_id() {
//            return object_id;
//        }
//
//        public void setObject_id(int object_id) {
//            this.object_id = object_id;
//        }
//
//        public int getAttribute_id() {
//            return attribute_id;
//        }
//
//        public void setAttribute_id(int attribute_id) {
//            this.attribute_id = attribute_id;
//        }
//
//        public String getValue() {
//            return value;
//        }
//
//        public void setValue(String value) {
//            this.value = value;
//        }
//
//        @Override
//        public boolean equals(java.lang.Object o) {
//            if (this == o ) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Key key = (Key) o;
//            return object_id == key.object_id &&
//                    attribute_id == key.attribute_id &&
//                    Objects.equals(value, key.value);
//        }
//
//        @Override
//        public int hashCode() {
//            int result = object_id;
//            result = 31 * result + attribute_id;
//            result = 31 * result + value.hashCode();
//            return result;
//        }
//    }
}
