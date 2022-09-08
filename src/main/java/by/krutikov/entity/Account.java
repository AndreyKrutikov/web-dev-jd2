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
public class Account {
    private Long id;
    private String login;
    private String password;
    private String email;
    private Role role;
    private Boolean isLocked;
    private Timestamp dateCreated;
    private Timestamp dateModified;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("login", login)
                .append("password", password)
                .append("email", email)
                .append("role", role)
                .append("isLocked", isLocked)
                .append("dateCreated", dateCreated)
                .append("dateModified", dateModified)
                .toString();
    }
}
