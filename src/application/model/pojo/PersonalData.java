package application.model.pojo;

import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class PersonalData {

    public static final SimpleDateFormat DATEFORM = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleStringProperty neptun, birthPlace, birthName, birthDate, birthCounty, birthCountry, gender;

    public PersonalData(String neptun, String bPlace, String bName, Date bDate, String bCounty, String bCountry, String gender) {
        this.neptun = new SimpleStringProperty(neptun);
        this.birthPlace = new SimpleStringProperty(bPlace);
        this.birthName = new SimpleStringProperty(bName);
        this.birthDate = new SimpleStringProperty(DATEFORM.format(bDate));
        this.birthCounty = new SimpleStringProperty(bCounty);
        this.birthCountry = new SimpleStringProperty(bCountry);
        this.gender = new SimpleStringProperty(gender);
    }

    public String getNeptun() {
        return neptun.get();
    }

    public String getBirthPlace() {
        return birthPlace.get();
    }

    public String getBirthName() {
        return birthName.get();
    }

    public String getBirthDate() {
        return birthDate.get();
    }

    public String getBirthCounty() {
        return birthCounty.get();
    }

    public String getBirthCountry() {
        return birthCountry.get();
    }

    public String getGender() {
        return gender.get();
    }

}
