package model.pojo;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {

    private SimpleStringProperty neptun, lastname, firstname;
    private SimpleBooleanProperty student, teacher;

    public User() {
    }

    public User(String neptun, String lastname, String firstname) {
        this.neptun = new SimpleStringProperty(neptun);
        this.lastname = new SimpleStringProperty(lastname);
        this.firstname = new SimpleStringProperty(firstname);
        this.student = new SimpleBooleanProperty(false);
        this.teacher = new SimpleBooleanProperty(false);
    }

    public String getNeptun() {
        return neptun.get();
    }

    public SimpleStringProperty neptunProperty() {
        return neptun;
    }

    public void setNeptun(String neptun) {
        this.neptun.set(neptun);
    }

    public String getLastname() {
        return lastname.get();
    }

    public SimpleStringProperty lastnameProperty() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    public String getFirstname() {
        return firstname.get();
    }

    public SimpleStringProperty firstnameProperty() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    public boolean getStudent() {
        return student.get();
    }

    public SimpleBooleanProperty studentProperty() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student.set(student);
    }

    public boolean getTeacher() {
        return teacher.get();
    }

    public SimpleBooleanProperty teacherProperty() {
        return teacher;
    }

    public void setTeacher(boolean teacher) {
        this.teacher.set(teacher);
    }
}
