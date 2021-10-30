package application.view;


import javafx.scene.control.TableColumn;

public final class DataColumn extends TableColumn {

    public DataColumn(String text) {
        super(text);
        setMinWidth(50.0);
        setSortable(false);
        setEditable(false);
    }
}
