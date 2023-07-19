package by.wevioz.enumeration;

public enum TaskIdEnum {
    RESOLVE_INTERVIEW ("Close_interview"),
    CREATE_STEP ("Add_step");

    private final String title;

    TaskIdEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
