package com.shop.database.entities;

import java.lang.*;

/**
 * Created by said on 06.05.2017.
 */
public class Reference {

    private int objectId;
    private int referenceId;
    private String name;

    public Reference() {
    }

    public Reference(int objectId, int referenceId, String name) {
        this.objectId = objectId;
        this.referenceId = referenceId;
        this.name = name;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(int referenceId) {
        this.referenceId = referenceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
