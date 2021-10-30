package application.view;

import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import application.Database;

import java.util.List;

public abstract class DataTable<T> extends TableView<T> {

    protected Database db = new Database(false);

    public DataTable() {
        super();
        setEditable(false);
        setPlaceholder(new Label("A tábla nem tartalmaz rekordokat."));
        getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public abstract boolean insert(List<String> input);

    public abstract boolean update(int i, List<String> input);

    public abstract void delete(int i);

    public abstract void refresh();

}
