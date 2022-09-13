package by.krutikov.repository.media;

import by.krutikov.entity.Media;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.krutikov.repository.media.MediaTableFields.DEMO_URL;
import static by.krutikov.repository.media.MediaTableFields.ID;
import static by.krutikov.repository.media.MediaTableFields.PHOTO_URL;

@Component
public class MediaRowMapper implements RowMapper<Media> {
    static Logger log = Logger.getLogger(MediaRowMapper.class);
    @Override
    public Media mapRow(ResultSet resultSet, int i) throws SQLException {
        Media media = new Media();
        media.setId(resultSet.getLong(ID));
        media.setPhotoUrl(resultSet.getString(PHOTO_URL));
        media.setDemoUrl(resultSet.getString(DEMO_URL));

        return media;
    }

//    @Override
//    public Media mapRow(ResultSet resultSet, int i) throws SQLException {
//        return Media.builder()
//                .id(resultSet.getLong(MediaTableFields.ID))
//                .photoUrl(resultSet.getString(MediaTableFields.PHOTO_URL))
//                .demoUrl(resultSet.getString(MediaTableFields.DEMO_URL))
//                .build();
//    }
}
