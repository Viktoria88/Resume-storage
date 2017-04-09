package com.urise.webapp.sql;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.StorageException;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;

/**
 * Created by viktoriyasidenko on 4/9/17.
 */
public class ExceptionUtil {

    private ExceptionUtil(){

    }

    public static StorageException convertException(SQLException e){
        if(e instanceof PSQLException){

            //https://www.postgresql.org/docs/8.2/static/errcodes-appendix.html
            if(e.getSQLState().equals("23505")){
                return new ExistStorageException(null);
            }
        }
        return new StorageException(e);
    }
}
