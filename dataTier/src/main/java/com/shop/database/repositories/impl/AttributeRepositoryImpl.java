package com.shop.database.repositories.impl;

import com.shop.database.entities.Attribute;
import com.shop.database.repositories.AttributeRepository;
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
public class AttributeRepositoryImpl implements AttributeRepository {

    private JdbcTemplate template;
    private static final String COLUMNS = "ID, NAME, TYPE";
    private static final String FIND_NAME_BY_ID = "SELECT NAME FROM LAB3_ATTRIBUTES WHERE ID = ?";
    private static final String COUNT_ROWS = "SELECT COUNT(*) FROM LAB3_ATTRIBUTES";
    private static final String FIND_BY_ID_QUERY = "SELECT " + COLUMNS + " FROM LAB3_ATTRIBUTES WHERE ID = ?";
    private static final String FIND_BY_NAME_QUERY = "SELECT " + COLUMNS + " FROM LAB3_ATTRIBUTES WHERE NAME = ?";
    private static final String FIND_BY_TYPE_QUERY = "SELECT " + COLUMNS + " FROM LAB3_ATTRIBUTES WHERE TYPE = ?";
    private static final String CREATE_ROW = "INSERT INTO LAB3_ATTRIBUTES VALUES (?, ?, ?)";

    public AttributeRepositoryImpl() {
    }

    public AttributeRepositoryImpl(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    public void insert(Attribute entity) {
        template.update(CREATE_ROW, new Object[]{entity.getId(), entity.getName(), entity.getType()});

    }

    public void update(Attribute entity) {

    }

    public void delete(Attribute entity) {

    }

    public Attribute findById(int id) {
        return null;
    }

    public String findNameById(int id) {
        return template.queryForObject(FIND_NAME_BY_ID, new Object[] {id}, String.class);
    }

    public int countRows() {
        return Integer.parseInt(template.queryForObject(COUNT_ROWS, String.class));
    }

    public List<Attribute> findByName(String name) {
        return template.query(FIND_BY_NAME_QUERY, new java.lang.Object[] {name}, new AttributeRowMapper());
    }

    public List<Attribute> findByType(int type) {
        return template.query(FIND_BY_TYPE_QUERY, new java.lang.Object[] {type}, new AttributeRowMapper());
    }

    private static class AttributeRowMapper implements RowMapper<Attribute> {
        public Attribute mapRow(ResultSet rs, int rownum) throws SQLException {
            Attribute attribute = new Attribute();
            attribute.setId(rs.getInt("ID"));
            attribute.setName(rs.getString("NAME"));
            attribute.setType(rs.getInt("TYPE"));
            return attribute;
        }
    }
}
