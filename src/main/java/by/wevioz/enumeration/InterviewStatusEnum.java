package by.wevioz.enumeration;

public enum InterviewStatusEnum {
    RESOLVE_INTERVIEW ("review"),
    CREATE_STEP ("in progress"),
    DONE ("done");

    private final String title;

    InterviewStatusEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
