package fact.it.cyclistservice.model;

public enum Gender {
    MALE,
    FEMALE,
    X11;

    public String toString() {
        return switch (this) {
            case MALE -> "Male";
            case FEMALE -> "Female";
            case X11 -> "X11";
        };
    }
}
