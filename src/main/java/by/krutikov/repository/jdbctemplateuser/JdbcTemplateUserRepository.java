package by.krutikov.repository.jdbctemplateuser;

import by.krutikov.entity.User;
import by.krutikov.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Primary
public class JdbcTemplateUserRepository implements UserRepository {
    static final Logger logger = LogManager.getLogger(JdbcTemplateUserRepository.class);

    private static final String CREATE_NEW_USER_SQL = "insert into students_schema.users " +
            "(user_name, surname, dob, date_created, date_modified, is_deleted, rating) " +
            "values (:username, :surname, :dob, :dateCreated, :dateModified, :isDeleted, :rating);";
    private static final String GET_LAST_USER_ID_SQL = "select currval('students_schema.users_id_seq')" +
            " as last_id;";
    private static final String FIND_USER_BY_ID_SQL = "select * from students_schema.users " +
            "where id=:id;";
    private static final String FIND_ALL_LIMIT_OFFSET_SQL = "select * from students_schema.users " +
            "order by id limit :limit offset :offset;";
    private static final String UPDATE_USER_SQL = "update students_schema.users " +
            "set user_name=:username, surname=:surname, dob=:dob, date_created=:dateCreated, date_modified=:dateModified, is_deleted=:isDeleted, rating=:rating " +
            "where id=:id;";
    public static final String DELETE_USER_ROLE_SQL = "delete from students_schema.roles " +
            "where user_id=:userId;";
    private static final String DELETE_USER_SQL = "delete from students_schema.users " +
            "where id=:id;";
    public static final String FIND_BY_NAME_SURNAME_SQL = "select * from students_schema.users " +
            "where user_name=:username and surname=:surname;";

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final UserRowMapper userRowMapper;

    @Override
    public User findById(Long id) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);

        return namedParameterJdbcTemplate.queryForObject(FIND_USER_BY_ID_SQL, map, userRowMapper);
    }

    @Override
    public Optional<User> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public List<User> findAll() {
        return findAll(DEFAULT_FIND_ALL_OFFSET, DEFAULT_FIND_ALL_LIMIT);
    }

    @Override
    public List<User> findAll(int offset, int limit) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("limit", limit);
        map.addValue("offset", offset);

        return namedParameterJdbcTemplate.query(FIND_ALL_LIMIT_OFFSET_SQL, map, userRowMapper);
    }

    @Override
    public User create(User object) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("username", object.getName());
        map.addValue("surname", object.getSurname());
        map.addValue("dob", object.getDateOfBirth());
        map.addValue("dateCreated", object.getCreated());
        map.addValue("dateModified", object.getModified());
        map.addValue("isDeleted", object.getIsDeleted());
        map.addValue("rating", object.getRating());

        namedParameterJdbcTemplate.update(CREATE_NEW_USER_SQL, map);

        long lastUserId = namedParameterJdbcTemplate.query(GET_LAST_USER_ID_SQL,
                resultSet -> {
                    resultSet.next();
                    return resultSet.getLong("last_id");
                });

        return findById(lastUserId);
    }

    @Override
    public User update(User object) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("username", object.getName());
        map.addValue("surname", object.getSurname());
        map.addValue("dob", object.getDateOfBirth());
        map.addValue("dateCreated", object.getCreated());
        map.addValue("dateModified", object.getModified());
        map.addValue("isDeleted", object.getIsDeleted());
        map.addValue("rating", object.getRating());
        map.addValue("id", object.getId());

        namedParameterJdbcTemplate.update(UPDATE_USER_SQL, map);

        return findById(object.getId());
    }

    @Override
    public Long delete(Long id) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        map.addValue("userId", id);

        namedParameterJdbcTemplate.update(DELETE_USER_ROLE_SQL, map);
        namedParameterJdbcTemplate.update(DELETE_USER_SQL, map);

        return id;
    }

    @Override
    public List<User> findByFullName(String name, String surname) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("username", name);
        map.addValue("surname", surname);

        return namedParameterJdbcTemplate.query(FIND_BY_NAME_SURNAME_SQL, map, userRowMapper);
    }
}
