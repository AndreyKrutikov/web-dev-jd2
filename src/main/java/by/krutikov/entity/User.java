package by.krutikov.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.Timestamp;

public class User {
    private Long id;
    private String name;
    private String surname;
    private Timestamp dateOfBirth;
    private Timestamp created;
    private Timestamp modified;
    private boolean isDeleted;
    private Byte rating;

    public User() {
    }

    public User(Long id, String name, String surname, Timestamp dateOfBirth, Timestamp created,
                Timestamp modified, boolean isDeleted, Byte rating) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.created = created;
        this.modified = modified;
        this.isDeleted = isDeleted;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Timestamp getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Timestamp dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Byte getRating() {
        return rating;
    }

    public void setRating(Byte rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof User)) return false;

        User user = (User) o;

        return new EqualsBuilder()
                .append(isDeleted(), user.isDeleted())
                .append(getId(), user.getId())
                .append(getName(), user.getName())
                .append(getSurname(), user.getSurname())
                .append(getDateOfBirth(), user.getDateOfBirth())
                .append(getCreated(), user.getCreated())
                .append(getModified(), user.getModified())
                .append(getRating(), user.getRating())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getName())
                .append(getSurname())
                .append(getDateOfBirth())
                .append(getCreated())
                .append(getModified())
                .append(isDeleted())
                .append(getRating())
                .toHashCode();
    }

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