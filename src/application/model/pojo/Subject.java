package application.model.pojo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Locale;

public class Subject {

    private SimpleStringProperty sub_code, reqSub_code, neptun, name;
    private SimpleIntegerProperty credits, hours;

    public Subject(String sub_code, String reqSub_code, String neptun, String name, int credits, int hours) {
        this.sub_code = new SimpleStringProperty(sub_code);
        this.reqSub_code = reqSub_code.toLowerCase(Locale.ROOT).equals("null") ? null : new SimpleStringProperty(reqSub_code);
        this.neptun = new SimpleStringProperty(neptun);
        this.name = new SimpleStringProperty(name);
        this.credits = new SimpleIntegerProperty(credits);
        this.hours = new SimpleIntegerProperty(hours);
    }

    public String getSub_code() {
        return sub_code.get();
    }

    public String getReqSub_code() {
        return reqSub_code.get();
    }

    public String getNeptun() {
        return neptun.get();
    }

    public String getName() {
        return name.get();
    }

    public int getCredits() {
        return credits.get();
    }

    public int getHours() {
        return hours.get();
    }

    public boolean isNull() {
        return reqSub_code.getBean() == null;
    }
}
