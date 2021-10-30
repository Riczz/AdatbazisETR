package application.model.pojo;

import javafx.beans.property.SimpleStringProperty;

public class User {

    private SimpleStringProperty neptun, lastname, firstname;

    public User(String neptun, String lastname, String firstname) {
        this.neptun = new SimpleStringProperty(neptun);
        this.lastname = new SimpleStringProperty(lastname);
        this.firstname = new SimpleStringProperty(firstname);
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

}
