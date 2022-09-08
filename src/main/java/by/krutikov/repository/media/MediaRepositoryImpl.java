package by.krutikov.repository.media;

import by.krutikov.entity.Media;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MediaRepositoryImpl implements MediaRepository {
    static final Logger logger = LogManager.getLogger(MediaRepositoryImpl.class);

    private static final String CREATE_NEW_MEDIA_SQL = "insert into bandhub.media (photo_url , demo_url) " +
            "values (:photoUrl, :demoUrl);";
    private static final String GET_LAST_MEDIA_ID_SQL = "select currval('bandhub.media_id_seq')" +
            " as last_id;";
    private static final String FIND_MEDIA_BY_ID_SQL = "select * from bandhub.media " +
            "where id=?;";
    private static final String FIND_ALL_LIMIT_OFFSET_SQL = "select * from bandhub.media " +
            "order by id limit :limit offset :offset;";
    private static final String UPDATE_MEDIA_SQL = "update bandhub.media " +
            "set photo_url=:photoUrl, demo_url=:demoUrl" +
            " where id =:id;";
    private static final String DELETE_MEDIA_SQL = "delete from bandhub.media " +
            "where id=:id;";


    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final MediaRowMapper mediaRowMapper;

    @Override
    public Media findById(Long id) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);

        return namedParameterJdbcTemplate.queryForObject(FIND_MEDIA_BY_ID_SQL, map, mediaRowMapper);
    }

    @Override
    public Optional<Media> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public List<Media> findAll() {
        return findAll(DEFAULT_FIND_ALL_OFFSET, DEFAULT_FIND_ALL_LIMIT);
    }

    @Override
    public List<Media> findAll(int offset, int limit) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("limit", limit);
        map.addValue("offset", offset);

        return namedParameterJdbcTemplate.query(FIND_ALL_LIMIT_OFFSET_SQL, map, mediaRowMapper);
    }

    @Override
    public Media create(Media object) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", object.getId());
        map.addValue("photoUrl", object.getPhotoUrl());
        map.addValue("demoUrl", object.getDemoUrl());

        namedParameterJdbcTemplate.update(CREATE_NEW_MEDIA_SQL, map);

        long lastMediaId = namedParameterJdbcTemplate.query(GET_LAST_MEDIA_ID_SQL,
                resultSet -> {
                    resultSet.next();
                    return resultSet.getLong("last_id");
                });

        return findById(lastMediaId);
    }

    @Override
    public Media update(Media object) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", object.getId());
        map.addValue("photoUrl", object.getPhotoUrl());
        map.addValue("demoUrl", object.getDemoUrl());

        namedParameterJdbcTemplate.update(UPDATE_MEDIA_SQL, map);

        return findById(object.getId());
    }

    @Override
    public Long delete(Long id) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);

        namedParameterJdbcTemplate.update(DELETE_MEDIA_SQL, map);

        return id;
    }
}
