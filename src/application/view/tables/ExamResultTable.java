package application.view.tables;

import application.model.pojo.ExamResult;
import application.view.DataColumn;
import application.view.DataTable;
import application.view.dialog.AlertDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.util.List;

public final class ExamResultTable extends DataTable<ExamResult> {

    public ExamResultTable() {

        DataColumn no = new DataColumn("sorszam");
        no.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        no.setCellValueFactory(new PropertyValueFactory<ExamResult, String>("no"));

        DataColumn neptun = new DataColumn("neptun_kod");
        neptun.setCellFactory(TextFieldTableCell.forTableColumn());
        neptun.setCellValueFactory(new PropertyValueFactory<ExamResult, String>("neptun"));

        DataColumn subcode = new DataColumn("targy_kod");
        subcode.setCellFactory(TextFieldTableCell.forTableColumn());
        subcode.setCellValueFactory(new PropertyValueFactory<ExamResult, String>("subCode"));

        DataColumn result = new DataColumn("eredmeny");
        result.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        result.setCellValueFactory(new PropertyValueFactory<ExamResult, String>("result"));

        refresh();
        getColumns().addAll(no, neptun,subcode,result);
    }

    @Override
    public boolean insert(List<String> input) {
        try {
            ExamResult examResult = new ExamResult(
                    Integer.parseInt(input.get(0)),
                    input.get(1),
                    input.get(2),
                    Integer.parseInt(input.get(3))
            );
            if (!db.insert(examResult)) return false;
            refresh();
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            new AlertDialog(Alert.AlertType.ERROR,"Hiba","helytelen eredmény!");
            return false;
        }
    }

    @Override
    public boolean update(int i, List<String> input) {
        try {
            ExamResult newExamResult = new ExamResult(
                    Integer.parseInt(input.get(0)),
                    input.get(1),
                    input.get(2),
                    Integer.parseInt(input.get(3))
            );
            if (!db.update(getItems().get(i),newExamResult)) return false;
            refresh();
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            new AlertDialog(Alert.AlertType.ERROR,"Hiba","helytelen eredmény!");
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
        setItems(db.getExamResults());
    }
}
