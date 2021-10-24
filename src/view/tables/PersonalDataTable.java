package view.tables;

import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.pojo.PersonalData;
import model.pojo.User;
import sample.Database;
import view.DataColumn;
import view.DataTable;

import java.util.Date;

public class PersonalDataTable extends DataTable<PersonalData> {

    public PersonalDataTable() {
        super();

        DataColumn neptun = new DataColumn("neptun_kod");
        neptun.setCellFactory(TextFieldTableCell.forTableColumn());
        neptun.setCellValueFactory(new PropertyValueFactory<User, String>("neptun"));

        DataColumn birthPlace = new DataColumn("szul_hely");
        birthPlace.setCellFactory(TextFieldTableCell.forTableColumn());
        birthPlace.setCellValueFactory(new PropertyValueFactory<User, String>("birthPlace"));

        DataColumn birthName = new DataColumn("szul_nev");
        birthName.setCellFactory(TextFieldTableCell.forTableColumn());
        birthName.setCellValueFactory(new PropertyValueFactory<User, String>("birthName"));

        DataColumn birthDate = new DataColumn("szul_datum");
        birthDate.setCellFactory(TextFieldTableCell.forTableColumn());
        birthDate.setCellValueFactory(new PropertyValueFactory<User, Date>("birthDate"));

        DataColumn birthCounty = new DataColumn("szul_megye");
        birthCounty.setCellFactory(TextFieldTableCell.forTableColumn());
        birthCounty.setCellValueFactory(new PropertyValueFactory<User, String>("birthCounty"));

        DataColumn birthCountry = new DataColumn("szul_orszag");
        birthCountry.setCellFactory(TextFieldTableCell.forTableColumn());
        birthCountry.setCellValueFactory(new PropertyValueFactory<User, String>("birthCountry"));

        DataColumn gender = new DataColumn("nem");
        gender.setCellFactory(tableColumn -> new CheckBoxTableCell<>());
        gender.setCellValueFactory(new PropertyValueFactory<User, Boolean>("gender"));

        refresh();
        getColumns().addAll(neptun, birthPlace, birthName, birthDate, birthCounty, birthCountry, gender);
    }

    @Override
    public void insert() {
        db.connect();

        db.close();
    }

    @Override
    public void modify(int i) {
        db.connect();

        db.close();
    }

    @Override
    public void delete(int i) {
        data.remove(i);
        getItems().remove(i);

        db.connect();
        db.close();
    }

    @Override
    public void refresh() {
        data.clear();
        getItems().clear();

        db.connect();
        data = db.getPersonalDatas();
        setItems(data);
        db.close();
    }
}
