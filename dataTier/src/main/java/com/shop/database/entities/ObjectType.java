package com.shop.database.entities;

/**
 * Created by said on 06.05.2017.
 */
public class ObjectType {

    private int id;
    private String name;

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
}
