package by.krutikov.repository.account;

import by.krutikov.entity.Account;
import by.krutikov.entity.Role;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.krutikov.repository.account.AccountTableFields.CREATED;
import static by.krutikov.repository.account.AccountTableFields.EMAIL;
import static by.krutikov.repository.account.AccountTableFields.ID;
import static by.krutikov.repository.account.AccountTableFields.IS_LOCKED;
import static by.krutikov.repository.account.AccountTableFields.LOGIN;
import static by.krutikov.repository.account.AccountTableFields.MODIFIED;
import static by.krutikov.repository.account.AccountTableFields.PASSWORD;
import static by.krutikov.repository.account.AccountTableFields.ROLE_ID;

@Component
public class AccountRowMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getLong(ID));
        account.setLogin(resultSet.getString(LOGIN));
        account.setPassword(resultSet.getString(PASSWORD));
        account.setEmail(resultSet.getString(EMAIL));
        account.setDateCreated(resultSet.getTimestamp(CREATED));
        account.setDateModified(resultSet.getTimestamp(MODIFIED));
        account.setIsLocked(resultSet.getBoolean(IS_LOCKED));
        account.setRole(Role.getInstance(resultSet.getInt(ROLE_ID)));

        return account;
    }
}
