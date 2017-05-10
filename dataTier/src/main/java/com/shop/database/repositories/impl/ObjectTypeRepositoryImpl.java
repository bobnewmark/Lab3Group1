package com.shop.database.repositories.impl;

import com.shop.database.entities.ObjectType;
import com.shop.database.repositories.ObjectTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by said on 06.05.2017.
 */
@Repository
public class ObjectTypeRepositoryImpl implements ObjectTypeRepository {

    private JdbcTemplate template;
    private static final String COLUMNS = "ID, NAME";
    private static final String FIND_NAME_BY_ID = "SELECT NAME FROM LAB3_OBJECT_TYPES WHERE ID = ?";
    private static final String COUNT_ROWS = "SELECT COUNT(*) FROM LAB3_OBJECT_TYPES";
    private static final String FIND_BY_ID_QUERY = "SELECT " + COLUMNS + " FROM LAB3_OBJECT_TYPES WHERE ID = ?";
    private static final String FIND_BY_NAME_QUERY = "SELECT " + COLUMNS + " FROM LAB3_OBJECT_TYPES WHERE NAME = ?";
    private static final String CREATE_ROW = "INSERT INTO LAB3_OBJECT_TYPES VALUES (?, ?)";

    public ObjectTypeRepositoryImpl() {
    }

    public String findNameById(int id) {
        return template.queryForObject(FIND_NAME_BY_ID, new Object[] {id}, String.class);
    }

    public int countRows() {
        return Integer.parseInt(template.queryForObject(COUNT_ROWS, String.class));
    }


    @Autowired
    public ObjectTypeRepositoryImpl(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }


    public void insert(ObjectType entity) {
        template.update(CREATE_ROW, new Object[]{entity.getId(), entity.getName()});

    }

    public void update(ObjectType entity) {

    }

    public void delete(ObjectType entity) {

    }

    public ObjectType findById(int id) {
        return (ObjectType) template.query(FIND_BY_ID_QUERY, new Object[]{id}, new ObjectTypeRowMapper());
    }

    public List<ObjectType> findByName(String name) {
        return template.query(FIND_BY_NAME_QUERY, new Object[]{name}, new ObjectTypeRowMapper());
    }

    private static class ObjectTypeRowMapper implements RowMapper<ObjectType> {
        public ObjectType mapRow(ResultSet rs, int rownum) throws SQLException {
            ObjectType objectType = new ObjectType();
            objectType.setId(rs.getInt("ID"));
            objectType.setName(rs.getString("NAME"));
            return objectType;
        }
    }
}
