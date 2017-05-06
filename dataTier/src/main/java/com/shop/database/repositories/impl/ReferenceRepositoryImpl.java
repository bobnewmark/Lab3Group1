package com.shop.database.repositories.impl;

import com.shop.database.entities.Parameter;
import com.shop.database.entities.Reference;
import com.shop.database.repositories.ReferenceRepository;
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
public class ReferenceRepositoryImpl implements ReferenceRepository {

    private JdbcTemplate template;
    private static final String COLUMNS = "OBJECT_ID, REF_OBJECT_ID, NAME";
    private static final String COUNT_ROWS = "SELECT COUNT(*) FROM LAB3_REFERENCES";
    private static final String FIND_BY_OBJECT_ID_QUERY = "SELECT " + COLUMNS + " FROM LAB3_REFERENCES WHERE OBJECT_ID = ?";
    private static final String FIND_BY_REFERENCE_ID_QUERY = "SELECT " + COLUMNS + " FROM LAB3_REFERENCES WHERE REF_OBJECT_ID = ?";
    private static final String FIND_BY_NAME_QUERY = "SELECT " + COLUMNS + " FROM LAB3_REFERENCES WHERE NAME = ?";
    private static final String CREATE_ROW = "INSERT INTO LAB3_REFERENCES VALUES (?, ?, ?)";

    public ReferenceRepositoryImpl(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }


    public void insert(Reference entity) {
        template.update(CREATE_ROW, new Object[]{entity.getObjectId(), entity.getReferenceId(), entity.getName()});
    }

    public void update(Reference entity) {

    }

    public void delete(Reference entity) {

    }

    public int countRows() {
        return Integer.parseInt(template.queryForObject(COUNT_ROWS, String.class));
    }

    public List<Reference> findByObjectId(int objectId) {
        return template.query(FIND_BY_OBJECT_ID_QUERY, new Object[]{objectId}, new ReferenceRowMapper());
    }

    public List<Reference> findByReferenceId(int referenceId) {
        return template.query(FIND_BY_REFERENCE_ID_QUERY, new Object[]{referenceId}, new ReferenceRowMapper());
    }

    public List<Reference> findByName(int name) {
        return template.query(FIND_BY_NAME_QUERY, new Object[]{name}, new ReferenceRowMapper());
    }

    private static class ReferenceRowMapper implements RowMapper<Reference> {
        public Reference mapRow(ResultSet rs, int rownum) throws SQLException {
            Reference reference = new Reference();
            reference.setObjectId(rs.getInt("OBJECT_ID"));
            reference.setReferenceId(rs.getInt("REF_OBJECT_ID"));
            reference.setName(rs.getString("NAME"));
            return reference;
        }
    }

}
