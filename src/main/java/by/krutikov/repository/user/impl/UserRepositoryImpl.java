package by.krutikov.repository.user.impl;

import by.krutikov.entity.User;
import by.krutikov.exception.UserRepositoryException;
import by.krutikov.repository.user.UserRepository;
import by.krutikov.util.DatabasePropertiesReader;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.krutikov.util.DatabasePropertiesReader.DATABASE_LOGIN;
import static by.krutikov.util.DatabasePropertiesReader.DATABASE_NAME;
import static by.krutikov.util.DatabasePropertiesReader.DATABASE_PASSWORD;
import static by.krutikov.util.DatabasePropertiesReader.DATABASE_PORT;
import static by.krutikov.util.DatabasePropertiesReader.DATABASE_URL;
import static by.krutikov.util.DatabasePropertiesReader.POSTGRES_DRIVER_NAME;

import static by.krutikov.repository.user.UserTableFields.ID;
import static by.krutikov.repository.user.UserTableFields.NAME;
import static by.krutikov.repository.user.UserTableFields.SURNAME;
import static by.krutikov.repository.user.UserTableFields.DATE_OF_BIRTH;
import static by.krutikov.repository.user.UserTableFields.CREATED;
import static by.krutikov.repository.user.UserTableFields.MODIFIED;
import static by.krutikov.repository.user.UserTableFields.IS_DELETED;
import static by.krutikov.repository.user.UserTableFields.RATING;


public class UserRepositoryImpl implements UserRepository {
    static final Logger logger = LogManager.getLogger(UserRepositoryImpl.class);
    private static final String CREATE_NEW_USER_SQL = "insert into students_schema.users " +
            "(user_name, surname, dob, date_created, date_modified, is_deleted, rating) " +
            "values (?, ?, ?, ?, ?, ?, ?);";
    private static final String GET_LAST_USER_ID_SQL = "select currval('students_schema.users_id_seq')" +
            " as last_id;";
    private static final String FIND_USER_BY_ID_SQL = "select * from students_schema.users " +
            "where id=?;";

    private static final String FIND_ALL_LIMIT_OFFSET_SQL = "select * from students_schema.users " +
            "order by id limit ? offset ?;";

    private static final String UPDATE_USER_SQL = "update students_schema.users " +
            "set user_name=?, surname=?, dob=?, date_created=?, date_modified=?, is_deleted=?, rating=? " +
            "where id=?;";

    private static final String DELETE_USER_SQL = "delete from students_schema.users " +
            "where id=?;";

    public static final String FIND_BY_NAME_SURNAME_SQL = "select * from students_schema.users " +
            "where user_name=? and surname=?;";

    @Override
    public User findById(Long id) {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(FIND_USER_BY_ID_SQL);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            boolean hasRow = resultSet.next();
            if (hasRow) {
                return userRowMapping(resultSet);
            } else {
                throw new UserRepositoryException("Entity User with id " + id + " does not exist");
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL issues in findById method", e);
        }
    }

    @Override
    public Optional<User> findOne(Long id) {
        User user = findById(id);
        return Optional.of(user);
    }

    @Override
    public List<User> findByFullName(String name, String surname) {
        List<User> userList = new ArrayList<>();

        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        try {
            connection = getConnection();
            statement=connection.prepareStatement(FIND_BY_NAME_SURNAME_SQL);

            statement.setString(1, name);
            statement.setString(2, surname);

            resultSet = statement.executeQuery();

            while (resultSet.next()){
                userList.add(userRowMapping(resultSet));
            }

            return userList;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public List<User> findAll() {
        return findAll(DEFAULT_FIND_ALL_LIMIT, DEFAULT_FIND_ALL_OFFSET);
    }

    @Override
    public List<User> findAll(int limit, int offset) {

        List<User> result = new ArrayList<>();

        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(FIND_ALL_LIMIT_OFFSET_SQL);

            statement.setInt(1, limit);
            statement.setInt(2, offset);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(userRowMapping(resultSet));
            }

            return result;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }


    @Override
    public User create(User object) {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(CREATE_NEW_USER_SQL);

            statement.setString(1, object.getName());
            statement.setString(2, object.getSurname());
            statement.setTimestamp(3, object.getDateOfBirth());
            statement.setTimestamp(4, object.getCreated());
            statement.setTimestamp(5, object.getModified());
            statement.setBoolean(6, object.isDeleted());
            statement.setByte(7, object.getRating());
            statement.executeUpdate();

            resultSet = connection.prepareStatement(GET_LAST_USER_ID_SQL).executeQuery();
            resultSet.next();

            Long lastUserId = resultSet.getLong("last_id"); //or column index = 1

            return findById(lastUserId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User update(User object) {
        Connection connection;
        PreparedStatement statement;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(UPDATE_USER_SQL);

            statement.setString(1, object.getName());
            statement.setString(2, object.getSurname());
            statement.setTimestamp(3, object.getDateOfBirth());
            statement.setTimestamp(4, object.getCreated());
            statement.setTimestamp(5, object.getModified());
            statement.setBoolean(6, object.isDeleted());
            statement.setByte(7, object.getRating());
            statement.setLong(8, object.getId());
            statement.executeUpdate();

            return findById(object.getId());
        } catch (SQLException e) {
            throw new RuntimeException("Sql exception in update method", e);
        }
    }

    @Override
    public Long delete(Long id) {
        Connection connection;
        PreparedStatement statement;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(DELETE_USER_SQL);

            statement.setLong(1, id);
            statement.executeUpdate();

            return id;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    private User userRowMapping(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getLong(ID));
        user.setName(resultSet.getString(NAME));
        user.setSurname(resultSet.getString(SURNAME));
        user.setDateOfBirth(resultSet.getTimestamp(DATE_OF_BIRTH));
        user.setCreated(resultSet.getTimestamp(CREATED));
        user.setModified(resultSet.getTimestamp(MODIFIED));
        user.setDeleted(resultSet.getBoolean(IS_DELETED));
        user.setRating(resultSet.getByte(RATING));

        return user;
    }

    private Connection getConnection() throws SQLException {
        String driver = DatabasePropertiesReader.getProperty(POSTGRES_DRIVER_NAME);

        String url = DatabasePropertiesReader.getProperty(DATABASE_URL);
        String port = DatabasePropertiesReader.getProperty(DATABASE_PORT);
        String name = DatabasePropertiesReader.getProperty(DATABASE_NAME);

        String dbURL = StringUtils.join(url, port, name);
        String login = DatabasePropertiesReader.getProperty(DATABASE_LOGIN);
        String password = DatabasePropertiesReader.getProperty(DATABASE_PASSWORD);

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.error("no driver found");
            throw new RuntimeException("no driver found", e);
        }

        return DriverManager.getConnection(dbURL, login, password);
    }
}
