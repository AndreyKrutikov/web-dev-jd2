package by.krutikov.repository.account;

import by.krutikov.entity.Account;
import by.krutikov.entity.User;
import by.krutikov.repository.jdbctemplate.UserRowMapper;
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
public class AccountRepositoryImpl implements AccountRepository {
    static final Logger logger = LogManager.getLogger(AccountRepositoryImpl.class);
    private static final String CREATE_NEW_ACCOUNT_SQL = "insert into bandhub.accounts " +
            "(login, password, email, date_created, date_modified, is_locked, role_id) " +
            "values (:login, :password, :email, :dateCreated, :dateModified, :isLocked, :roleId)";

    private static final String GET_LAST_ACCOUNT_ID_SQL = "select currval('bandhub.accounts_id_seq')" +
            " as last_id;";
    private static final String FIND_ACCOUNT_BY_ID_SQL = "select * from bandhub.accounts " +
            "where id=:id;";
    private static final String FIND_ALL_LIMIT_OFFSET_SQL = "select * from bandhub.accounts " +
            "order by id limit :limit offset :offset;";
    private static final String UPDATE_ACCOUNT_SQL = "update bandhub.accounts " +
            "set login=:login, password=:password, email=:email, date_modified=:dateModified, is_locked=:isLocked, role_id=:roleId " +
            "where id=:id;"; //we do not set creation date, login, do we?

    //    public static final String DELETE_ACCOUNT_ROLE_SQL = "delete from bandhub.accounts where id=:userId;";

    private static final String DELETE_ACCOUNT_SQL = "delete from bandhub.accounts " +
            "where id=:id;";

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final AccountRowMapper accountRowMapper;

    @Override
    public Account findById(Long id) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);

        return namedParameterJdbcTemplate.queryForObject(FIND_ACCOUNT_BY_ID_SQL, map, accountRowMapper);
    }

    @Override
    public Optional<Account> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public List<Account> findAll() {
        return findAll(DEFAULT_FIND_ALL_OFFSET, DEFAULT_FIND_ALL_LIMIT);
    }

    @Override
    public List<Account> findAll(int offset, int limit) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("limit", limit);
        map.addValue("offset", offset);

        return namedParameterJdbcTemplate.query(FIND_ALL_LIMIT_OFFSET_SQL, map, accountRowMapper);
    }

    @Override
    public Account create(Account object) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("login", object.getLogin());
        map.addValue("password", object.getPassword());
        map.addValue("email", object.getEmail());
        map.addValue("dateCreated", object.getDateCreated());
        map.addValue("dateModified", object.getDateModified());
        map.addValue("isLocked", object.getIsLocked());
        map.addValue("roleId", object.getRole().getId()); //!!!!

        namedParameterJdbcTemplate.update(CREATE_NEW_ACCOUNT_SQL, map);

        long lastAccountId = namedParameterJdbcTemplate.query(GET_LAST_ACCOUNT_ID_SQL,
                resultSet -> {
                    resultSet.next();
                    return resultSet.getLong("last_id");
                });

        return findById(lastAccountId);
    }

    @Override
    public Account update(Account object) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", object.getId()); //can we update id??????
        map.addValue("login", object.getLogin()); //login should be immutable?
        map.addValue("password", object.getPassword());
        map.addValue("email", object.getEmail());
//        map.addValue("dateCreated", object.getDateCreated());
        map.addValue("dateModified", object.getDateModified());
        map.addValue("isLocked", object.getIsLocked());
        map.addValue("roleId", object.getRole().getId());

        namedParameterJdbcTemplate.update(UPDATE_ACCOUNT_SQL, map);

        return findById(object.getId());
    }

    @Override
    public Long delete(Long id) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);

        namedParameterJdbcTemplate.update(DELETE_ACCOUNT_SQL, map);

        return id;
    }
}
