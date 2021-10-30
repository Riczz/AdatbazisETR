package application.model.pojo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public final class ExamResult {

    private SimpleStringProperty neptun, subCode;
    private SimpleIntegerProperty no, result;

    public ExamResult(int no, String neptun, String subCode, int result) {
        this.no = new SimpleIntegerProperty(no);
        this.neptun = new SimpleStringProperty(neptun);
        this.subCode = new SimpleStringProperty(subCode);
        this.result = new SimpleIntegerProperty(result);
    }

    public int getNo() {
        return no.get();
    }

    public String getNeptun() {
        return neptun.get();
    }

    public String getSubCode() {
        return subCode.get();
    }

    public int getResult() {
        return result.get();
    }

}
