package view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import sample.Database;

public abstract class DataTable<T> extends TableView<T> {

    protected ObservableList<T> data = FXCollections.observableArrayList();
    protected Database db = new Database(false);

    public DataTable() {
        super();
        setEditable(false);
        setPlaceholder(new Label("A tábla nem tartalmaz rekordokat."));
        getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public abstract void insert();

    public abstract void modify(int i);

    public abstract void delete(int i);

    public abstract void refresh();

}
