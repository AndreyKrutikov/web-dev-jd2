package by.krutikov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Media {
    private Long id;
    private String photoUrl;
    private String demoUrl;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("photoUrl", photoUrl)
                .append("demoUrl", demoUrl)
                .toString();
    }
}
