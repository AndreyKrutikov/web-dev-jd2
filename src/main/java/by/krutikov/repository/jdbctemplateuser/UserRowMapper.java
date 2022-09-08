package by.krutikov.repository.jdbctemplateuser;

import by.krutikov.entity.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.krutikov.repository.user.UserTableFields.CREATED;
import static by.krutikov.repository.user.UserTableFields.DATE_OF_BIRTH;
import static by.krutikov.repository.user.UserTableFields.ID;
import static by.krutikov.repository.user.UserTableFields.IS_DELETED;
import static by.krutikov.repository.user.UserTableFields.MODIFIED;
import static by.krutikov.repository.user.UserTableFields.NAME;
import static by.krutikov.repository.user.UserTableFields.RATING;
import static by.krutikov.repository.user.UserTableFields.SURNAME;

@Component
public class UserRowMapper implements RowMapper <User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();

        user.setId(resultSet.getLong(ID));
        user.setName(resultSet.getString(NAME));
        user.setSurname(resultSet.getString(SURNAME));
        user.setDateOfBirth(resultSet.getTimestamp(DATE_OF_BIRTH));
        user.setCreated(resultSet.getTimestamp(CREATED));
        user.setModified(resultSet.getTimestamp(MODIFIED));
        user.setIsDeleted(resultSet.getBoolean(IS_DELETED));
        user.setRating(resultSet.getByte(RATING));

        return user;
    }
}
