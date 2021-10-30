package application.model.pojo;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {

    private SimpleStringProperty neptun, lastname, firstname;
    private SimpleBooleanProperty student, teacher;

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

    public String getLastname() {
        return lastname.get();
    }

    public String getFirstname() {
        return firstname.get();
    }

    public boolean getStudent() {
        return student.get();
    }

    public boolean getTeacher() {
        return teacher.get();
    }

}
