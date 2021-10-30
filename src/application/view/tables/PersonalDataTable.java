package application.view.tables;

import application.model.pojo.PersonalData;
import application.model.pojo.User;
import application.view.DataColumn;
import application.view.DataTable;
import application.view.dialog.AlertDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

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
    public boolean insert(List<String> input) {
        try {
            PersonalData personalData = new PersonalData(
                    input.get(0), input.get(1), input.get(2),
                    PersonalData.DATEFORM.parse(input.get(3)),
                    input.get(4), input.get(5), input.get(6)
            );
            if (!db.insert(personalData)) return false;
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
            PersonalData personalData = new PersonalData(
                    input.get(0), input.get(1), input.get(2),
                    PersonalData.DATEFORM.parse(input.get(3)),
                    input.get(4), input.get(5), input.get(6)
            );
            if (!db.update(getItems().get(i),personalData)) return false;
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
        setItems(db.getPersonalDatas());
    }
}
