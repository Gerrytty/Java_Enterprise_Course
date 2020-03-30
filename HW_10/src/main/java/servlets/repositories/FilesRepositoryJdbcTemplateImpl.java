package servlets.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import servlets.models.FileInfo;
import servlets.repositories.interfaces.FilesRepository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Component
public class FilesRepositoryJdbcTemplateImpl implements FilesRepository {

    //language=`SQL`
    private static final String SQL_SELECT_BY_ID = "select * from FileInfo where id = ?";

    private static final String SQL_SELECT_BY_STORAGE_NAME = "select * from FileInfo where storageFileName = ? limit 1";
    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from FileInfo";
    //language=SQL
    private static final String SQL_INSERT = "insert into FileInfo(storageFileName, originalFileName, size, type, url)" +
            " values (?, ?, ?, ?, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<FileInfo> fileRowMapper = (row, rowNumber) ->
            FileInfo.builder()
                    .id(row.getLong("id"))
                    .originalFileName(row.getString("originalFileName"))
                    .storageFileName(row.getString("storageFileName"))
                    .size(row.getLong("size"))
                    .url(row.getString("url"))
                    .type(row.getString("type"))
                    .build();

    @Override
    public Optional<FileInfo> find(Long id) {
        try {
            FileInfo fileInfo = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, fileRowMapper);
            return Optional.ofNullable(fileInfo);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<FileInfo> findByName(String name) {
        try {
            FileInfo fileInfo = jdbcTemplate.queryForObject(SQL_SELECT_BY_STORAGE_NAME, new Object[]{name}, fileRowMapper);
            return Optional.ofNullable(fileInfo);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<FileInfo> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, fileRowMapper);
    }

    @Override
    public void save(FileInfo entity) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getStorageFileName());
            statement.setString(2, entity.getOriginalFileName());
            statement.setLong(3, entity.getSize());
            statement.setString(4, entity.getType());
            statement.setString(5, entity.getUrl());

            return statement;

        }, keyHolder);

        entity.setId(keyHolder.getKey().longValue());

    }

    @Override
    public void delete(Long aLong) {

    }
}
