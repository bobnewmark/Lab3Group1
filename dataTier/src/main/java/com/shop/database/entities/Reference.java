package com.shop.database.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.*;
import java.util.Objects;

/**
 * Created by said on 06.05.2017.
 */
@Entity
@Table(name = "LAB3_REFERENCES")
public class Reference {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "REFERENCE_ID", length = 10, insertable = false, updatable = false, nullable = false)
    private int id;
    @ManyToOne
    @JoinColumn(name = "OBJECT_ID", referencedColumnName = "OBJECT_ID")
    private Object object;
    @ManyToOne
    @JoinColumn(name = "REF_OBJECT_ID", referencedColumnName = "OBJECT_ID")
    private Object refObject;
    @Column(name = "NAME")
    private String name;
//    @Embedded
//    @Id
//    private Key id;

    public Reference() {
    }

    public Reference(Object object, Object refObject, String name) {
        this.object = object;
        this.refObject = refObject;
        this.name = name;
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
//
//    public Key getId() {
//        return id;
//    }
//
//    public void setId(Key id) {
//        this.id = id;
//    }

//    @Embeddable
//    public static class Key implements Serializable {
//        private int object_id;
//        private int ref_object_id;
//        private String name;
//
//        public Key() {
//        }
//
//        public Key(Object object, Object refObject, String name) {
//            this.object_id = object.getId();
//            this.ref_object_id = refObject.getId();
//            this.name = name;
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
//        public int getRef_object_id() {
//            return ref_object_id;
//        }
//
//        public void setRef_object_id(int ref_object_id) {
//            this.ref_object_id = ref_object_id;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        @Override
//        public boolean equals(java.lang.Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Key key = (Key) o;
//            return object_id == key.object_id &&
//                    ref_object_id == key.ref_object_id &&
//                    Objects.equals(name, key.name);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(object_id, ref_object_id, name);
//        }
//    }


}
