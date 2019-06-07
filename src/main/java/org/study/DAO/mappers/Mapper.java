package org.study.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T> {

    T createModel(ResultSet rs) throws SQLException;
}
