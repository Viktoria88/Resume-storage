package com.urise.webapp.sql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by viktoriyasidenko on 4/2/17.
 */
public interface ConnectionFactory {

    Connection getConnection() throws SQLException;
}
