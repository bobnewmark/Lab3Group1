package com.shop.database.repositories.impl;

import com.shop.database.entities.Parameter;
import com.shop.database.repositories.ParameterRepository;
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
public class ParameterRepositoryImpl implements ParameterRepository {

    private JdbcTemplate template;
    private static final String COLUMNS = "OBJECT_ID, ATTRIBUTE_ID, VALUE";
    private static final String COUNT_ROWS = "SELECT COUNT(*) FROM LAB3_PARAMS";
    private static final String FIND_BY_OBJECT_ID_QUERY = "SELECT " + COLUMNS + " FROM LAB3_PARAMS WHERE OBJECT_ID = ?";
    private static final String FIND_BY_ATTRIBUTE_ID_QUERY = "SELECT " + COLUMNS + " FROM LAB3_PARAMS WHERE ATTRIBUTE_ID = ?";
    private static final String FIND_BY_VALUE_QUERY = "SELECT " + COLUMNS + " FROM LAB3_PARAMS WHERE VALUE = ?";
    private static final String CREATE_ROW = "INSERT INTO LAB3_PARAMS VALUES (?, ?, ?)";

    public ParameterRepositoryImpl(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }


    public void insert(Parameter entity) {
        template.update(CREATE_ROW, new Object[]{entity.getObjectId(), entity.getAttributeId(), entity.getValue()});
    }

    public void update(Parameter entity) {

    }

    public void delete(Parameter entity) {

    }

    public Parameter findById(int id) {
        return null;
    }

    public int countRows() {
        return Integer.parseInt(template.queryForObject(COUNT_ROWS, String.class));
    }

    public List<Parameter> findByObjectId(int objectId) {
        return template.query(FIND_BY_OBJECT_ID_QUERY, new Object[]{objectId}, new ParameterRowMapper());
    }

    public List<Parameter> findByAttributeId(int attributeId) {
        return template.query(FIND_BY_ATTRIBUTE_ID_QUERY, new Object[]{attributeId}, new ParameterRowMapper());
    }

    public List<Parameter> findByValue(String value) {
        return template.query(FIND_BY_VALUE_QUERY, new Object[]{value}, new ParameterRowMapper());
    }

    private static class ParameterRowMapper implements RowMapper<Parameter> {
        public Parameter mapRow(ResultSet rs, int rownum) throws SQLException {
            Parameter parameter = new Parameter();
            parameter.setObjectId(rs.getInt("OBJECT_ID"));
            parameter.setAttributeId(rs.getInt("ATTRIBUTE_ID"));
            parameter.setValue(rs.getString("VALUE"));
            return parameter;
        }
    }
}
