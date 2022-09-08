package by.krutikov.repository.profile;

import by.krutikov.entity.Profile;
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
//TODO check queries and create/update methods for latitude/longitude params order
public class ProfileRepositoryImpl implements ProfileRepository {
    static final Logger logger = LogManager.getLogger(ProfileRepositoryImpl.class);

    private static final String CREATE_NEW_PROFILE_SQL = "insert into bandhub.user_profiles " +
            "(account_id, displayed_name, location, cell_phone_number, instrument_id, experience_id, media_id, description, date_created, date_modified, is_visible ) " +
            "values (:accountId, :displayedName, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326)::geography, :phoneNumber, :instrumentId, :experienceId, :mediaId, :description, :dateCreated, :dateModified, :isVisible);";
    private static final String GET_LAST_PROFILE_ID_SQL = "select currval('bandhub.user_profiles_id_seq')" +
            " as last_id;";
    private static final String FIND_PROFILE_BY_ID_SQL = "select account_id, displayed_name, ST_X(location::geometry) AS latitude, ST_Y(location::geometry) AS longitude, cell_phone_number, instrument_id, experience_id, media_id, description, date_created, date_modified, is_visible  from bandhub.user_profiles " +
            "where id=:id;";
    private static final String FIND_ALL_LIMIT_OFFSET_SQL = "select account_id, displayed_name, ST_X(location::geometry) AS latitude, ST_Y(location::geometry) AS longitude, cell_phone_number, instrument_id, experience_id, media_id, description, date_created, date_modified, is_visible from bandhub.user_profiles " +
            "order by id limit :limit offset :offset;";
    private static final String UPDATE_PROFILE_SQL = "update bandhub.user_profiles " +
            "set account_id=:accountId, displayed_name=:displayedName, location=ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326)::geography, cell_phone_number=:phoneNumber, instrument_id=:instrumentId, experience_id=:experienceId, media_id=:mediaId, description=:description, date_modified=:dateModified, is_visible=:isVisible " +
            "where id=:id;"; //we do not set creation date?
    private static final String DELETE_PROFILE_SQL = "delete from bandhub.user_profiles " +
            "where id=:id;";

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final ProfileRowMapper profileRowMapper;

    @Override
    public Profile findById(Long id) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);

        return namedParameterJdbcTemplate.queryForObject(FIND_PROFILE_BY_ID_SQL, map, profileRowMapper);
    }

    @Override
    public Optional<Profile> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public List<Profile> findAll() {
        return findAll(DEFAULT_FIND_ALL_OFFSET, DEFAULT_FIND_ALL_LIMIT);
    }

    @Override
    public List<Profile> findAll(int offset, int limit) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("limit", limit);
        map.addValue("offset", offset);

        return namedParameterJdbcTemplate.query(FIND_ALL_LIMIT_OFFSET_SQL, map, profileRowMapper);
    }

    @Override
    public Profile create(Profile object) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("accountId", object.getAccount().getId());
        map.addValue("displayedName", object.getDisplayedName());
        map.addValue("latitude", object.getLatitude());
        map.addValue("longitude", object.getLongitude());
        map.addValue("phoneNumber", object.getPhoneNumber());
        map.addValue("instrumentId", object.getInstrument().getId());
        map.addValue("experienceId", object.getExperience().getId());
        map.addValue("mediaId", object.getMedia().getId());
        map.addValue("description", object.getDescription());
        map.addValue("dateCreated", object.getDateCreated());
        map.addValue("dateModified", object.getDateModified());
        map.addValue("isVisible", object.getIsVisible());

        namedParameterJdbcTemplate.update(CREATE_NEW_PROFILE_SQL, map);

        long lastProfileId = namedParameterJdbcTemplate.query(GET_LAST_PROFILE_ID_SQL,
                resultSet -> {
                    resultSet.next();
                    return resultSet.getLong("last_id");
                });

        return findById(lastProfileId);
    }

    @Override
    public Profile update(Profile object) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("accountId", object.getAccount().getId());
        map.addValue("displayedName", object.getDisplayedName());
        map.addValue("latitude", object.getLatitude());
        map.addValue("longitude", object.getLongitude());
        map.addValue("phoneNumber", object.getPhoneNumber());
        map.addValue("instrumentId", object.getInstrument().getId());
        map.addValue("experienceId", object.getExperience().getId());
        map.addValue("mediaId", object.getMedia().getId());
        map.addValue("description", object.getDescription());
        //map.addValue("dateCreated", object.getDateCreated());
        map.addValue("dateModified", object.getDateModified());
        map.addValue("isVisible", object.getIsVisible());
        map.addValue("id", object.getId());

        namedParameterJdbcTemplate.update(UPDATE_PROFILE_SQL, map);

        return findById(object.getId());
    }

    @Override
    public Long delete(Long id) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);

        namedParameterJdbcTemplate.update(DELETE_PROFILE_SQL, map);

        return id;
    }
}
