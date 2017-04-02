package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.ConnectionFactory;

import java.sql.*;
import java.util.List;

/**
 * Created by viktoriyasidenko on 4/2/17.
 */
public class SqlStorage implements Storage {

    public final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        try(Connection conn = connectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM resume")){
                ps.execute();
        } catch (SQLException e){
            throw new StorageException(e);
        }
    }

    @Override
    public void save(Resume r) {
        try(Connection conn = connectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")){
                ps.setString(1, r.getUuid());
                ps.setString(2, r.getFullName());
                ps.execute();
        } catch (SQLException e){
            throw new StorageException(e);
        }
    }

    @Override
    public void update(Resume r) {
        try(Connection conn = connectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid =?)")){
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.execute();
        } catch (SQLException e){
            throw new StorageException(e);
        }
    }

    @Override
    public Resume get(String uuid) {
        try(Connection conn = connectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume WHERE r.uuid =?")){
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if(!rs.next()){
                    throw new NotExistStorageException(uuid);
                }
                Resume r = new Resume(uuid, rs.getString("full name"));
                return r;
        } catch (SQLException e){
            throw new StorageException(e);
        }
    }

    @Override
    public void delete(String uuid) {
        try(Connection conn = connectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM resume WHERE r.uuid =?")){
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                throw new NotExistStorageException(uuid);
            }
            ps.execute();
        } catch (SQLException e){
            throw new StorageException(e);
        }

    }

    @Override
    public List<Resume> getAllSorted() {
        try(Connection conn = connectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume")){
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
//                throw new NotExistStorageException();
            }
            Resume r = new Resume(rs.getString("uuid"), rs.getString("full name"));
            return null;
        } catch (SQLException e){
            throw new StorageException(e);
        }    }

    @Override
    public int size() {
        return 0;
    }
}
