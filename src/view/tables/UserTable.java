package view.tables;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.pojo.User;
import sample.Database;
import view.DataColumn;
import view.DataTable;


public class UserTable extends DataTable<User> {

    public UserTable() {
        super();

        DataColumn neptun = new DataColumn("neptun_kod");
        neptun.setCellFactory(TextFieldTableCell.forTableColumn());
        neptun.setCellValueFactory(new PropertyValueFactory<User, String>("neptun"));

        DataColumn lastname = new DataColumn("vezeteknev");
        lastname.setCellFactory(TextFieldTableCell.forTableColumn());
        lastname.setCellValueFactory(new PropertyValueFactory<User, String>("lastname"));

        DataColumn firstname = new DataColumn("vezeteknev");
        firstname.setCellFactory(TextFieldTableCell.forTableColumn());
        firstname.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));

        DataColumn student = new DataColumn("hallgato");
        student.setCellFactory(tableColumn -> new CheckBoxTableCell<>());
        student.setCellValueFactory(new PropertyValueFactory<User, Boolean>("student"));

        DataColumn teacher = new DataColumn("oktato");
        teacher.setCellFactory(tableColumn -> new CheckBoxTableCell<>());
        teacher.setCellValueFactory(new PropertyValueFactory<User, Boolean>("teacher"));

        Database db = new Database();
        setItems(db.getUsers());
        db.close();
        getColumns().addAll(neptun,lastname,firstname,student,teacher);
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
        data = db.getUsers();
        setItems(data);
        db.close();
    }

    @Override
    public void refresh() {

    }
}
