package entity;

/**
 * Created by said on 02.05.2017.
 */
public class Parameter {
    private int id;
    private int objectId;
    private int attributeId;
    private String value;

    public Parameter() {
    }

    public Parameter(int objectId, int attributeId, String value) {
        this.id = objectId + attributeId;
        this.objectId = objectId;
        this.attributeId = attributeId;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
