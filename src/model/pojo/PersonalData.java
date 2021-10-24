package model.pojo;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class PersonalData {

    private SimpleStringProperty neptun, birthPlace, birthName, birthDate, birthCounty, birthCountry;
    private SimpleBooleanProperty gender;

    public PersonalData(String neptun, String bPlace, String bName, Date bDate, String bCounty, String bCountry, boolean gender) {
        this.neptun = new SimpleStringProperty(neptun);
        this.birthPlace = new SimpleStringProperty(bPlace);
        this.birthName = new SimpleStringProperty(bName);
        this.birthDate = new SimpleStringProperty(bDate.toString());
        this.birthCounty = new SimpleStringProperty(bCounty);
        this.birthCountry = new SimpleStringProperty(bCountry);
        this.gender = new SimpleBooleanProperty(gender);
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

    public String getBirthPlace() {
        return birthPlace.get();
    }

    public SimpleStringProperty birthPlaceProperty() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace.set(birthPlace);
    }

    public String getBirthName() {
        return birthName.get();
    }

    public SimpleStringProperty birthNameProperty() {
        return birthName;
    }

    public void setBirthName(String birthName) {
        this.birthName.set(birthName);
    }

    public String getBirthDate() {
        return birthDate.get();
    }

    public SimpleStringProperty birthDateProperty() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate.set(birthDate);
    }

    public String getBirthCounty() {
        return birthCounty.get();
    }

    public SimpleStringProperty birthCountyProperty() {
        return birthCounty;
    }

    public void setBirthCounty(String birthCounty) {
        this.birthCounty.set(birthCounty);
    }

    public String getBirthCountry() {
        return birthCountry.get();
    }

    public SimpleStringProperty birthCountryProperty() {
        return birthCountry;
    }

    public void setBirthCountry(String birthCountry) {
        this.birthCountry.set(birthCountry);
    }

    public boolean isGender() {
        return gender.get();
    }

    public SimpleBooleanProperty genderProperty() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender.set(gender);
    }
}
