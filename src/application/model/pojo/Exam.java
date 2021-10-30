package application.model.pojo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Timestamp;

public final class Exam {

    private SimpleStringProperty subCode, time;
    private SimpleIntegerProperty roomNum;

    public Exam(String subCode, Timestamp time, int roomNum) {
        String formattedTime = time.toString();
        formattedTime = formattedTime.substring(0,formattedTime.lastIndexOf('.'));

        this.subCode = new SimpleStringProperty(subCode);
        this.time = new SimpleStringProperty(formattedTime);
        this.roomNum = new SimpleIntegerProperty(roomNum);
    }

    public String getSubCode() {
        return subCode.get();
    }

    public String getTime() {
        return time.get();
    }

    public int getRoomNum() {
        return roomNum.get();
    }

}
