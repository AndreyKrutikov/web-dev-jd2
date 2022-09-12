package by.krutikov.repository.profile;

import by.krutikov.entity.Experience;
import by.krutikov.entity.InstrumentType;
import by.krutikov.entity.Profile;
import by.krutikov.repository.account.AccountRepository;
import by.krutikov.repository.media.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.krutikov.repository.profile.ProfileTableFields.ACCOUNT_ID;
import static by.krutikov.repository.profile.ProfileTableFields.CREATED;
import static by.krutikov.repository.profile.ProfileTableFields.DESCRIPTION;
import static by.krutikov.repository.profile.ProfileTableFields.DISPLAYED_NAME;
import static by.krutikov.repository.profile.ProfileTableFields.EXPERIENCE_ID;
import static by.krutikov.repository.profile.ProfileTableFields.ID;
import static by.krutikov.repository.profile.ProfileTableFields.INSTRUMENT_ID;
import static by.krutikov.repository.profile.ProfileTableFields.IS_VISIBLE;
import static by.krutikov.repository.profile.ProfileTableFields.LATITUDE;
import static by.krutikov.repository.profile.ProfileTableFields.LONGITUDE;
import static by.krutikov.repository.profile.ProfileTableFields.MEDIA_ID;
import static by.krutikov.repository.profile.ProfileTableFields.MODIFIED;
import static by.krutikov.repository.profile.ProfileTableFields.PHONE_NUMBER;

@Component
@RequiredArgsConstructor
public class ProfileRowMapper implements RowMapper<Profile> {
    static Logger log = Logger.getLogger(ProfileRowMapper.class);
    private final AccountRepository accountRepository;
    private final MediaRepository mediaRepository;

    @Override
    public Profile mapRow(ResultSet resultSet, int i) throws SQLException {
        Profile profile = new Profile();
        profile.setId(resultSet.getLong(ID));
        profile.setAccount(accountRepository.findById(resultSet.getLong(ACCOUNT_ID)));
        profile.setDisplayedName(resultSet.getString(DISPLAYED_NAME));
        profile.setLatitude(resultSet.getDouble(LATITUDE));
        profile.setLongitude(resultSet.getDouble(LONGITUDE));
        profile.setPhoneNumber(resultSet.getString(PHONE_NUMBER));
        profile.setInstrument(InstrumentType.valueOf(resultSet.getInt(INSTRUMENT_ID)));
        profile.setExperience(Experience.valueOf(resultSet.getInt(EXPERIENCE_ID)));
        profile.setMedia(mediaRepository.findById(resultSet.getLong(MEDIA_ID)));
        profile.setDescription(resultSet.getString(DESCRIPTION));
        profile.setDateCreated(resultSet.getTimestamp(CREATED));
        profile.setDateModified(resultSet.getTimestamp(MODIFIED));
        profile.setIsVisible(resultSet.getBoolean(IS_VISIBLE));

        return profile;
    }
}
