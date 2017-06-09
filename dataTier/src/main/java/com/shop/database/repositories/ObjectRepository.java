package com.shop.database.repositories;

import com.shop.database.entities.Object;
import com.shop.database.entities.ObjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by said on 09.05.2017.
 */
public interface ObjectRepository extends JpaRepository<Object, Integer> {

    Object findById(int id);
    List<Object> findByName(String name);
    @Query("select o from Parameter p left join p.object o where p.attribute.name = :name and p.value = :value and p.attribute.objectType.name = :otName")
    List<Object> findByAttrAndObjectType(@Param("otName") String name, @Param("name") String otName, @Param("value") String value);
    List<Object>findByObjectType(ObjectType objectType);
    @Query("Select c from Object c where lower(c.name) like lower(CONCAT('%',:keyword,'%'))")
    List<Object> findByNameContaining(@Param("keyword") String keyword);
    List<Object> findByParent(Object object);

}
