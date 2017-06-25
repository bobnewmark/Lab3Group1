package com.shop.database.repositories;

import com.shop.database.entities.Attribute;
import com.shop.database.entities.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <code>ParameterRepository</code> is a repository class that manages work with parameters.
 */
@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Integer> {

    List<Parameter> findByObject(Object object);
    List<Parameter> findByAttribute(Attribute attribute);
    List<Parameter> findByValue(String value);
    Parameter findById(int id);
    @Query("select p from Parameter p left join p.object o where o.id = :ob_id and o.id = p.object.id and p.attribute.id = :attr_id")
    Parameter findByObjectAndAttribute(@Param("ob_id") int ob_id, @Param("attr_id") int attr_id);
}
