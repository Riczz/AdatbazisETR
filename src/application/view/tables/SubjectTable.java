package application.view.tables;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import application.model.pojo.Subject;
import application.view.DataColumn;
import application.view.DataTable;
import javafx.util.converter.IntegerStringConverter;

import java.util.List;


public final class SubjectTable extends DataTable<Subject> {

    public SubjectTable() {
        super();

        DataColumn sub_code = new DataColumn("targy_kod");
        sub_code.setCellFactory(TextFieldTableCell.forTableColumn());
        sub_code.setCellValueFactory(new PropertyValueFactory<Subject, String>("sub_code"));

        DataColumn reqSub_code = new DataColumn("elokovetelmeny_targy_kod");
        reqSub_code.setCellFactory(TextFieldTableCell.forTableColumn());
        reqSub_code.setCellValueFactory(new PropertyValueFactory<Subject, String>("reqSub_code"));

        DataColumn neptun = new DataColumn("neptun_kod");
        neptun.setCellFactory(TextFieldTableCell.forTableColumn());
        neptun.setCellValueFactory(new PropertyValueFactory<Subject, String>("neptun"));

        DataColumn name = new DataColumn("nev");
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setCellValueFactory(new PropertyValueFactory<Subject, String>("name"));

        DataColumn credits = new DataColumn("kreditertek");
        credits.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        credits.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("credits"));

        DataColumn hours = new DataColumn("heti_oraszam");
        hours.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        hours.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("hours"));

        refresh();
        getColumns().addAll(sub_code,reqSub_code,neptun,name,credits,hours);
    }

    @Override
    public boolean insert(List<String> input) {
        Subject subject = new Subject(
                input.get(0),
                input.get(1),
                input.get(2),
                input.get(3),
                Integer.parseInt(input.get(4)),
                Integer.parseInt(input.get(5))
        );

        if (!db.insert(subject)) return false;
        refresh();
        return true;
    }

    @Override
    public boolean update(int i, List<String> input) {
        Subject subject = getItems().get(i);
        Subject newSubject = new Subject(
                input.get(0),
                input.get(1),
                input.get(2),
                input.get(3),
                Integer.parseInt(input.get(4)),
                Integer.parseInt(input.get(5))
        );

        if (!db.update(subject,newSubject)) return false;
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
        setItems(db.getSubjects());
    }
}
