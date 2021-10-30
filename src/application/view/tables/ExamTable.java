package application.view.tables;

import application.model.pojo.Exam;
import application.view.DataColumn;
import application.view.DataTable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.sql.Timestamp;
import java.util.List;

public final class ExamTable extends DataTable<Exam> {

    public ExamTable() {

        DataColumn subCode = new DataColumn("targy_kod");
        subCode.setCellFactory(TextFieldTableCell.forTableColumn());
        subCode.setCellValueFactory(new PropertyValueFactory<Exam, String>("subCode"));

        DataColumn time = new DataColumn("idopont");
        time.setCellFactory(TextFieldTableCell.forTableColumn());
        time.setCellValueFactory(new PropertyValueFactory<Exam, String>("time"));

        DataColumn roomNum = new DataColumn("teremszam");
        roomNum.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        roomNum.setCellValueFactory(new PropertyValueFactory<Exam, Integer>("roomNum"));

        refresh();
        getColumns().addAll(subCode, time, roomNum);
    }


    @Override
    public boolean insert(List<String> input) {
        Exam exam = new Exam(
                input.get(0),
                Timestamp.valueOf((input.get(1))),
                Integer.parseInt(input.get(2))
        );
        if (!db.insert(exam)) return false;
        refresh();
        return true;
    }

    @Override
    public boolean update(int i, List<String> input) {
        Exam newExam = new Exam(
                input.get(0),
                Timestamp.valueOf((input.get(1))),
                Integer.parseInt(input.get(2))
        );
        if (!db.update(getItems().get(i), newExam)) return false;
        refresh();
        return true;
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
