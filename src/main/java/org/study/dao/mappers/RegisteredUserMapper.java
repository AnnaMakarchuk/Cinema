package org.study.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.study.models.RegisteredUser;
import org.study.models.enums.Gender;
import org.study.models.enums.UserRole;;

public class RegisteredUserMapper implements Mapper<RegisteredUser> {

    /**
     * this method create object from result set
     */
    @Override
    public RegisteredUser createModel(ResultSet rs) throws SQLException {
        if (Objects.isNull(rs)) {
            return null;
        }
        return new RegisteredUser(rs.getInt(ColumnNames.ID),
                rs.getString(ColumnNames.NAME),
                rs.getString(ColumnNames.USER_SURNAME),
                Gender.valueOf(rs.getString(ColumnNames.USER_GENDER).toUpperCase()),
                UserRole.valueOf(rs.getString(ColumnNames.USER_ROLE).toUpperCase()),
                rs.getString(ColumnNames.USER_LOGIN),
                rs.getString(ColumnNames.USER_EMAIL),
                rs.getString(ColumnNames.USER_PASSWORD));
    }
}
