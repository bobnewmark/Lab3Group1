package com.shop.database.entities;

import java.lang.*;

/**
 * Created by said on 06.05.2017.
 */
public class Parameter {

    private int objectId;
    private int attributeId;
    private String value;

    public Parameter() {
    }

    public Parameter(int objectId, int attributeId, String value) {
        this.objectId = objectId;
        this.attributeId = attributeId;
        this.value = value;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
