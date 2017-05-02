package repository;

import entity.ObjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by said on 02.05.2017.
 */
public interface ObjectTypeRepository extends JpaRepository<ObjectType, Integer> {

    @Query("SELECT * FROM OBJECT_TYPES WHERE OBJECT_TYPE_ID = ?1")
    ObjectType getObjectTypeById(int id);
}
