package view.tables;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.pojo.Subject;
import sample.Database;
import view.DataColumn;
import view.DataTable;


public class SubjectTable extends DataTable<Subject> {

    public SubjectTable() {
        super();

        DataColumn sub_code = new DataColumn("targy_kod");
        sub_code.setCellFactory(TextFieldTableCell.forTableColumn());
        sub_code.setCellValueFactory(new PropertyValueFactory<Subject, String>("sub_code"));

        DataColumn reqSub_code = new DataColumn("elokovetelmeny_targy_kod");
        sub_code.setCellFactory(TextFieldTableCell.forTableColumn());
        sub_code.setCellValueFactory(new PropertyValueFactory<Subject, String>("reqSub_code"));

        DataColumn neptun = new DataColumn("neptun_kod");
        sub_code.setCellFactory(TextFieldTableCell.forTableColumn());
        sub_code.setCellValueFactory(new PropertyValueFactory<Subject, String>("neptun"));

        DataColumn name = new DataColumn("nev");
        sub_code.setCellFactory(TextFieldTableCell.forTableColumn());
        sub_code.setCellValueFactory(new PropertyValueFactory<Subject, String>("name"));

        DataColumn credits = new DataColumn("kreditertek");
        sub_code.setCellFactory(TextFieldTableCell.forTableColumn());
        sub_code.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("credits"));

        DataColumn hours = new DataColumn("heti_oraszam");
        sub_code.setCellFactory(TextFieldTableCell.forTableColumn());
        sub_code.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("hours"));

        Database db = new Database();
        setItems(db.getSubjects());
        db.close();
        getColumns().addAll(sub_code,reqSub_code,neptun,name,credits,hours);
    }

    @Override
    public void insert() {

    }

    @Override
    public void modify(int i) {

    }

    @Override
    public void delete(int i) {
        data.clear();
        getItems().clear();

        db.connect();
        data = db.getSubjects();
        setItems(data);
        db.close();
    }

    @Override
    public void refresh() {

    }
}
