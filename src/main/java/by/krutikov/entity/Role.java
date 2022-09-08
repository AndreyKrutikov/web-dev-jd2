package by.krutikov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    USER("USER", 1), ADMIN("ADMIN", 2);

    private final String name;
    private final Integer id;

    public static Role getInstance(int id) {
        for (Role role : Role.values()) {
            if (role.id == id) return role;
        }
        throw new IllegalArgumentException(String.format("no role by id %d found: ", id)); //return a default role?
    }
}