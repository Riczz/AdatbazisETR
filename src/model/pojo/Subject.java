package model.pojo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Subject {

    private SimpleStringProperty sub_code, reqSub_code, neptun, name;
    private SimpleIntegerProperty credits, hours;

    public Subject(String sub_code, String reqSub_code, String neptun, String name, int credits, int hours) {
        this.sub_code = new SimpleStringProperty(sub_code);
        this.reqSub_code = new SimpleStringProperty(reqSub_code);
        this.neptun = new SimpleStringProperty(neptun);
        this.name = new SimpleStringProperty(name);
        this.credits = new SimpleIntegerProperty(credits);
        this.hours = new SimpleIntegerProperty(hours);
    }

    public String getSub_code() {
        return sub_code.get();
    }

    public SimpleStringProperty sub_codeProperty() {
        return sub_code;
    }

    public void setSub_code(String sub_code) {
        this.sub_code.set(sub_code);
    }

    public String getReqSub_code() {
        return reqSub_code.get();
    }

    public SimpleStringProperty reqSub_codeProperty() {
        return reqSub_code;
    }

    public void setReqSub_code(String reqSub_code) {
        this.reqSub_code.set(reqSub_code);
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

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getCredits() {
        return credits.get();
    }

    public SimpleIntegerProperty creditsProperty() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits.set(credits);
    }

    public int getHours() {
        return hours.get();
    }

    public SimpleIntegerProperty hoursProperty() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours.set(hours);
    }
}
