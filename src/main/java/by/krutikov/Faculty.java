package by.krutikov;

public enum Faculty {
    ABC("abc"), DEF("def");
    private String name;

    Faculty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
