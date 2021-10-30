package application.view.tables;

import application.model.pojo.Exam;
import application.model.pojo.PersonalData;
import application.view.DataColumn;
import application.view.DataTable;
import application.view.dialog.AlertDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class ExamTable extends DataTable<Exam> {

    public ExamTable() {

        DataColumn subCode = new DataColumn("targy_kod");
        subCode.setCellFactory(TextFieldTableCell.forTableColumn());
        subCode.setCellValueFactory(new PropertyValueFactory<Exam, String>("subCode"));

        DataColumn time = new DataColumn("idopont");
        time.setCellFactory(TextFieldTableCell.forTableColumn());
        time.setCellValueFactory(new PropertyValueFactory<Exam, Date>("time"));

        DataColumn roomNum = new DataColumn("teremszam");
        roomNum.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        roomNum.setCellValueFactory(new PropertyValueFactory<Exam, Integer>("roomNum"));

        refresh();
        getColumns().addAll(subCode,time,roomNum);
    }


    @Override
    public boolean insert(List<String> input) {
        try {
            Exam exam = new Exam(
                    input.get(0),
                    Exam.DATEFORM.parse(input.get(1)),
                    Integer.parseInt(input.get(1))
            );
            if (!db.insert(exam)) return false;
            refresh();
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            new AlertDialog(Alert.AlertType.ERROR, "Hiba", "Helytelen dátum formátum!");
            return false;
        }
    }

    @Override
    public boolean update(int i, List<String> input) {
        try {
            Exam newExam = new Exam(
                    input.get(0),
                    Exam.DATEFORM.parse(input.get(1)),
                    Integer.parseInt(input.get(1))
            );
            if (!db.update(getItems().get(i),newExam)) return false;
            refresh();
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            new AlertDialog(Alert.AlertType.ERROR, "Hiba", "Helytelen dátum formátum!");
            return false;
        }
    }

    @Override
    public void delete(int i) {
        db.delete(getItems().get(i));
        refresh();
    }

    @Override
    public void refresh() {
        setItems(db.getExams());
    }
}
