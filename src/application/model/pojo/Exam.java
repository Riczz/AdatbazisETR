package application.model.pojo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Exam {

    public static final SimpleDateFormat DATEFORM = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleStringProperty subCode, time;
    private SimpleIntegerProperty roomNum;

    public Exam(String subCode, Date time, int roomNum) {
        this.subCode = new SimpleStringProperty(subCode);
        this.time = new SimpleStringProperty(DATEFORM.format(time));
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
