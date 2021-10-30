package application.controller;

import application.Database;
import application.view.DataColumn;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class QueriesController {

    @FXML
    TableView<Query1> table1;
    @FXML
    TableView<Query2> table2;

    public static class Query1 {
            private SimpleStringProperty county;
            private SimpleIntegerProperty count;

            public Query1(String county, int count) {
                this.county = new SimpleStringProperty(county);
                this.count = new SimpleIntegerProperty(count);
            }

            public String getCounty() {
                return county.get();
            }

            public int getCount() {
                return count.get();
            }
    }

    public static class Query2 {
        private SimpleStringProperty subCode;
        private SimpleDoubleProperty avg;

        public Query2(String subCode, int avg) {
            this.subCode = new SimpleStringProperty(subCode);
            this.avg = new SimpleDoubleProperty(avg);
        }

        public String getSubCode() {
            return subCode.get();
        }

        public double getAvg() {
            return avg.get();
        }
    }

    @FXML
    public void initialize() {
        Database db = new Database(false);

        //Első lekérdezés
        DataColumn county = new DataColumn("Megye");
        county.setCellFactory(TextFieldTableCell.forTableColumn());
        county.setCellValueFactory(new PropertyValueFactory<Query1, String>("county"));

        DataColumn count = new DataColumn("Létszám");
        count.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        count.setCellValueFactory(new PropertyValueFactory<Query1, Integer>("count"));

        table1.getColumns().addAll(county, count);
        table1.setItems(db.extraQuery1());

        //Második lekérdezés
        DataColumn subCode = new DataColumn("Tárgy kód");
        subCode.setCellFactory(TextFieldTableCell.forTableColumn());
        subCode.setCellValueFactory(new PropertyValueFactory<Query2, String>("subCode"));

        DataColumn avg = new DataColumn("Átlag");
        avg.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        avg.setCellValueFactory(new PropertyValueFactory<Query2, Double>("avg"));

        table2.getColumns().addAll(subCode, avg);
        table2.setItems(db.extraQuery2());


    }
}
