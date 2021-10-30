package application.view.tables;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import application.model.pojo.User;
import application.view.DataColumn;
import application.view.DataTable;

import java.util.List;


public class UserTable extends DataTable<User> {

    public UserTable() {
        super();

        DataColumn neptun = new DataColumn("neptun_kod");
        neptun.setCellFactory(TextFieldTableCell.forTableColumn());
        neptun.setCellValueFactory(new PropertyValueFactory<User, String>("neptun"));

        DataColumn lastname = new DataColumn("vezeteknev");
        lastname.setCellFactory(TextFieldTableCell.forTableColumn());
        lastname.setCellValueFactory(new PropertyValueFactory<User, String>("lastname"));

        DataColumn firstname = new DataColumn("keresztnev");
        firstname.setCellFactory(TextFieldTableCell.forTableColumn());
        firstname.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));

        refresh();
        getColumns().addAll(neptun, lastname, firstname);
    }

    @Override
    public boolean insert(List<String> input) {
        User user = new User(
                input.get(0),
                input.get(1),
                input.get(2)
        );

        if (!db.insert(user)) return false;
        refresh();
        return true;
    }

    @Override
    public boolean update(int i, List<String> input) {
        User user = new User(
                input.get(0),
                input.get(1),
                input.get(2)
        );

        if (!db.update(getItems().get(i), user)) return false;
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
        setItems(db.getUsers());
    }
}
