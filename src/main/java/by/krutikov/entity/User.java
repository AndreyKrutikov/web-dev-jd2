package by.krutikov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.Timestamp;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    private Long id;
    private String name;
    private String surname;
    private Timestamp dateOfBirth;
    private Timestamp created;
    private Timestamp modified;
    private boolean isDeleted;
    private Byte rating;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("surname", surname)
                .append("dateOfBirth", dateOfBirth)
                .append("created", created)
                .append("modified", modified)
                .append("isDeleted", isDeleted)
                .append("rating", rating)
                .toString();
    }
}