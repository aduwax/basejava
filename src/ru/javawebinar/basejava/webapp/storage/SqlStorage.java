package ru.javawebinar.basejava.webapp.storage;

import ru.javawebinar.basejava.sql.ConnectionFactory;
import ru.javawebinar.basejava.sql.SqlHelper;
import ru.javawebinar.basejava.webapp.exception.ExistStorageException;
import ru.javawebinar.basejava.webapp.exception.NotExistStorageException;
import ru.javawebinar.basejava.webapp.model.Resume;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SqlStorage implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());
    public final ConnectionFactory connectionFactory;
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        sqlHelper = new SqlHelper(connectionFactory);
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("SELECT * FROM resume r WHERE r.uuid = ?", preparedStatement -> {
            exceptionIfNotExist(uuid);
            preparedStatement.setString(1, uuid);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return new Resume(uuid, rs.getString("full_name"));
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.execute("UPDATE resume SET uuid = ?, full_name = ? WHERE uuid = ?", preparedStatement -> {
            exceptionIfNotExist(resume.getUuid());
            preparedStatement.setString(1, resume.getUuid());
            preparedStatement.setString(2, resume.getFullName());
            preparedStatement.setString(3, resume.getUuid());
            preparedStatement.execute();
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.execute("INSERT INTO resume (uuid, full_name) VALUES (?,?)", preparedStatement -> {
            exceptionIfExist(resume.getUuid());
            preparedStatement.setString(1, resume.getUuid());
            preparedStatement.setString(2, resume.getFullName());
            preparedStatement.execute();
            return null;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume WHERE uuid = ?", preparedStatement -> {
            exceptionIfNotExist(uuid);
            preparedStatement.setString(1, uuid);
            preparedStatement.execute();
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("SELECT * FROM resume", preparedStatement -> {
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<Resume> resumes = new ArrayList<>();
            while (rs.next()) {
                resumes.add(new Resume(rs.getString("uuid").replace(" ", ""),
                        rs.getString("full_name")));
            }
            return resumes;
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) FROM resume", (preparedStatement) -> {
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private void exceptionIfExist(String uuid) {
        sqlHelper.execute("SELECT count(*) FROM resume WHERE uuid = ?", (preparedStatement) -> {
            preparedStatement.setString(1, uuid);
            ResultSet rs = preparedStatement.executeQuery();
            int records_count = rs.next() ? rs.getInt(1) : 0;
            if (records_count != 0) {
                LOG.warning("Resume " + uuid + " already exist");
                throw new ExistStorageException(uuid);
            }
            return null;
        });
    }

    private void exceptionIfNotExist(String uuid) {
        sqlHelper.execute("SELECT count(*) FROM resume WHERE uuid = ?", (preparedStatement) -> {
            preparedStatement.setString(1, uuid);
            ResultSet rs = preparedStatement.executeQuery();
            int records_count = rs.next() ? rs.getInt(1) : 0;
            if (records_count == 0) {
                LOG.warning("Resume " + uuid + " not exist");
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }
}