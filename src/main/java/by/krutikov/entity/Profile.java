package by.krutikov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.Timestamp;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Profile {
    private Long id;
    private String displayedName;
    private Account account;
    private Double longitude;
    private Double latitude;
    private String phoneNumber;
    private InstrumentType instrument;
    private Experience experience;
    private Media media;
    private String description;
    private Boolean isVisible;
    private Timestamp dateCreated;
    private Timestamp dateModified;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("displayedName", displayedName)
                .append("account", account)
                .append("longitude", longitude)
                .append("latitude", latitude)
                .append("phoneNumber", phoneNumber)
                .append("instrumentType", instrument)
                .append("experience", experience)
                .append("media", media)
                .append("description", description)
                .append("isVisible", isVisible)
                .append("dateCreated", dateCreated)
                .append("dateModified", dateModified)
                .toString();
    }
}
