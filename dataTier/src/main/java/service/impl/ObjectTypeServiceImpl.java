package service.impl;

import entity.ObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import repository.ObjectTypeRepository;
import service.ObjectTypeService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by said on 02.05.2017.
 */
public class ObjectTypeServiceImpl implements ObjectTypeService {

    @Autowired
    private ObjectTypeRepository objectTypeRepository;

    public void addObjectType(ObjectType objectType) {
        objectTypeRepository.saveAndFlush(objectType);

    }

    public void deleteObjectType(ObjectType objectType) {
        List<ObjectType> list = new ArrayList<ObjectType>();
        list.add(objectType);
        objectTypeRepository.deleteInBatch(list);
    }

    public ObjectType getObjectTypeById(int id) {
        return objectTypeRepository.getObjectTypeById(id);
    }

    public void editObjectType(ObjectType objectType) {
        objectTypeRepository.saveAndFlush(objectType);

    }

    public List<ObjectType> getAllObjectTypes() {
        return objectTypeRepository.findAll();
    }
}
