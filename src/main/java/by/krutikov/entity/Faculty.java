package by.krutikov.entity;

public enum Faculty {
    ABC("abc", 1), DEF("def", 2);
    private final String name;
    private final int code;

    Faculty(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }
}
