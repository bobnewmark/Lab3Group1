package com.shop.database.repositories;

import com.shop.database.entities.Object;
import com.shop.database.entities.ObjectType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <code>ObjectRepository</code> is a repository class that manages work with objects.
 */
@Repository
public interface ObjectRepository extends JpaRepository<Object, Integer> {

    List<Object> findByName(String name);
    @Query("select o from Parameter p left join p.object o where p.attribute.name = :name and p.value = :value and p.attribute.objectType.name = :otName")
    List<Object> findByAttrAndObjectType(@Param("otName") String name, @Param("name") String otName, @Param("value") String value);
    List<Object>findByObjectType(ObjectType objectType);
    @Query("Select c from Object c where lower(c.name) like lower(CONCAT('%',:keyword,'%')) and c.parent is not null")
    Page<Object> findByNameContaining(@Param("keyword") String keyword, Pageable pageable);
    List<Object> findByParent(Object object);
    @Query("select o from Parameter p left join p.object o where p.attribute.name = :name")
    List<Object> findObjectByParameterName (@Param("name") String name);
    @Query("select o from Parameter p left join p.object o where p.attribute.name = :name and p.attribute.objectType.name = :otName order by p.value desc")
    List<Object> findByAttrAndName(@Param("otName") String otName, @Param("name") String name, Pageable pageable);
    @Query("Select c from Object c where c.objectType.name in :names")
    Page<Object> findByTypes(@Param("names") List<String> names, Pageable pageable);
    @Query("Select c from Object c where c.objectType.name = :name")
    Page<Object> findByTypeName(@Param("name") String name, Pageable pageable);
    @Query("Select c from Object c where c.objectType.product = true")
    Page<Object> findAllProducts(Pageable pageable);
}
