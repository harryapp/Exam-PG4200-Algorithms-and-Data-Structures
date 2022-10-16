package ex02;

public class Student {
    private String id;
    private String firstName;
    private String lastName;

    public Student(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }
}
