package entity;

/**
 * Created by said on 02.05.2017.
 */
public class Reference {
    private int id;
    private int objectId;
    private int referenceObjId;
    private String name;

    public Reference() {
    }


    public Reference(int objectId, int referenceObjId, String name) {
        this.id = objectId + referenceObjId;
        this.objectId = objectId;
        this.referenceObjId = referenceObjId;
        this.name = name;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getReferenceObjId() {
        return referenceObjId;
    }

    public void setReferenceObjId(int referenceObjId) {
        this.referenceObjId = referenceObjId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
