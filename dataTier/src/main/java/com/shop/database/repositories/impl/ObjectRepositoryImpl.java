package com.shop.database.repositories.impl;

import com.shop.database.entities.Object;
import com.shop.database.repositories.ObjectRepository;
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
public class ObjectRepositoryImpl implements ObjectRepository {
    private JdbcTemplate template;
    private static final String COLUMNS = "ID, NAME, TYPE";
    private static final String FIND_NAME_BY_ID = "SELECT NAME FROM LAB3_OBJECTS WHERE ID = ?";
    private static final String COUNT_ROWS = "SELECT COUNT(*) FROM LAB3_OBJECTS";
    private static final String FIND_BY_ID_QUERY = "SELECT " + COLUMNS + " FROM LAB3_OBJECTS WHERE ID = ?";
    private static final String FIND_BY_NAME_QUERY = "SELECT " + COLUMNS + " FROM LAB3_OBJECTS WHERE NAME = ?";
    private static final String FIND_BY_TYPE_QUERY = "SELECT " + COLUMNS + " FROM LAB3_OBJECTS WHERE TYPE = ?";
    private static final String CREATE_ROW = "INSERT INTO LAB3_OBJECTS VALUES (?, ?, ?)";

    public ObjectRepositoryImpl() {
    }

    @Autowired
    public ObjectRepositoryImpl (DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    public void insert(Object entity) {
        template.update(CREATE_ROW, new java.lang.Object[]{entity.getId(), entity.getName(), entity.getType()});
    }

    public void update(Object entity) {

    }

    public void delete(Object entity) {

    }

    public Object findById(int id) {
        return null;
    }

    public String findNameById(int id) {
        return template.queryForObject(FIND_NAME_BY_ID, String.class);
    }

    public int countRows() {
        return Integer.parseInt(template.queryForObject(COUNT_ROWS, String.class));
    }

    public List<Object> findByName(String name) {
        return template.query(FIND_BY_NAME_QUERY, new java.lang.Object[] {name}, new ObjectRowMapper());
    }

    public List<Object> findByType(int type) {
        return template.query(FIND_BY_TYPE_QUERY, new java.lang.Object[] {type}, new ObjectRowMapper());
    }

    private static class ObjectRowMapper implements RowMapper<Object> {
        public Object mapRow(ResultSet rs, int rownum) throws SQLException {
            Object object = new Object();
            object.setId(rs.getInt("ID"));
            object.setName(rs.getString("NAME"));
            object.setType(rs.getInt("TYPE"));
            return object;
        }
    }
}
