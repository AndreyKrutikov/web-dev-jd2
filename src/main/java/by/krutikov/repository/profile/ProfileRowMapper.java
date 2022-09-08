package by.krutikov.repository.profile;

import by.krutikov.entity.Experience;
import by.krutikov.entity.InstrumentType;
import by.krutikov.entity.Profile;
import by.krutikov.repository.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import static by.krutikov.repository.profile.ProfileTableFields.CREATED;
import static by.krutikov.repository.profile.ProfileTableFields.DESCRIPTION;
import static by.krutikov.repository.profile.ProfileTableFields.EXPERIENCE_ID;
import static by.krutikov.repository.profile.ProfileTableFields.INSTRUMENT_ID;
import static by.krutikov.repository.profile.ProfileTableFields.IS_VISIBLE;
import static by.krutikov.repository.profile.ProfileTableFields.LATITUDE;
import static by.krutikov.repository.profile.ProfileTableFields.LONGITUDE;
import static by.krutikov.repository.profile.ProfileTableFields.MODIFIED;
import static by.krutikov.repository.profile.ProfileTableFields.ACCOUNT_ID;
import static by.krutikov.repository.profile.ProfileTableFields.DISPLAYED_NAME;
import static by.krutikov.repository.profile.ProfileTableFields.ID;
import static by.krutikov.repository.profile.ProfileTableFields.PHONE_NUMBER;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class ProfileRowMapper implements RowMapper<Profile> {
    private final AccountRepository accountRepository;

    @Override
    //TODO
    public Profile mapRow(ResultSet resultSet, int i) throws SQLException {
        Profile profile = new Profile();
        profile.setId(resultSet.getLong(ID));
        profile.setAccount(accountRepository.findById(resultSet.getLong(ACCOUNT_ID)));
        profile.setDisplayedName(resultSet.getString(DISPLAYED_NAME));
//        SELECT ST_Y(location::geometry) AS lat, ST_X(location::geometry) AS lon FROM my_table;
        profile.setLatitude(resultSet.getDouble(LATITUDE));
        profile.setLongitude(resultSet.getDouble(LONGITUDE));
        profile.setPhoneNumber(resultSet.getString(PHONE_NUMBER));
        profile.setInstrument(InstrumentType.getInstance(resultSet.getInt(INSTRUMENT_ID)));
        profile.setExperience(Experience.getInstance(resultSet.getInt(EXPERIENCE_ID)));
        profile.setMedia(null); // TODO: 7.09.22
        profile.setDescription(resultSet.getString(DESCRIPTION));
        profile.setDateCreated(resultSet.getTimestamp(CREATED));
        profile.setDateModified(resultSet.getTimestamp(MODIFIED));
        profile.setIsVisible(resultSet.getBoolean(IS_VISIBLE));

        return profile;
    }
}
