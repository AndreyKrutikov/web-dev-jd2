package by.krutikov.repository.media;

import by.krutikov.entity.Media;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MediaRowMapper implements RowMapper<Media> {
    @Override
    public Media mapRow(ResultSet resultSet, int i) throws SQLException {
        Media media = new Media();

        media.setId(resultSet.getLong(MediaTableFields.ID));
        media.setPhotoUrl(resultSet.getString(MediaTableFields.PHOTO_URL));
        media.setDemoUrl(resultSet.getString(MediaTableFields.DEMO_URL));

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
