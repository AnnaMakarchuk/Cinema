package org.study.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.study.models.Administrator;
import org.study.models.enums.Gender;
import org.study.models.enums.UserRole;

public class AdministratorMapper implements Mapper<Administrator> {

    /**
     * this method create object from result set
     */
    @Override
    public Administrator createModel(ResultSet rs) throws SQLException {
        if (Objects.isNull(rs)) {
            return null;
        }
        return new Administrator(rs.getInt(ColumnNames.ID),
                rs.getString(ColumnNames.NAME),
                rs.getString(ColumnNames.USER_SURNAME),
                Gender.valueOf(rs.getString(ColumnNames.USER_GENDER).toUpperCase()),
                UserRole.valueOf(rs.getString(ColumnNames.USER_ROLE).toUpperCase()),
                rs.getString(ColumnNames.USER_LOGIN),
                rs.getString(ColumnNames.USER_EMAIL),
                rs.getString(ColumnNames.USER_PASSWORD),
                rs.getDouble(ColumnNames.USER_SALARY),
                rs.getInt(ColumnNames.USER_HOURS));
    }
}
