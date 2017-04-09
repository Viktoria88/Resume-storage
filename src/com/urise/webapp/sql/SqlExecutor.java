package com.urise.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by viktoriyasidenko on 4/9/17.
 */
public interface SqlExecutor<T> {

    T execute(PreparedStatement st) throws SQLException;
}
