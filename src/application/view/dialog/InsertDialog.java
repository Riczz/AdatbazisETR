package application.view.dialog;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import application.view.DataTable;

public class InsertDialog extends DialogPane {

    public InsertDialog() {
        super();
    }

    public InsertDialog(String text, DataTable table) {
        this();
        GridPane grid = new GridPane();

        int rowIndex = 0;
        for (Object col : table.getColumns()) {
            System.out.println(((TableColumn)col).getId());
            grid.add(new Label(((TableColumn)col).getId()),0,rowIndex);
            grid.add(new TextField(),1,rowIndex++);
        }

        setHeight(30.0 * table.getColumns().size());
        setWidth(250.0);
        getChildren().add(grid);
        setHeaderText(text);
        createButton(ButtonType.CLOSE);
        createButton(ButtonType.APPLY);

        Stage stage = new Stage(StageStyle.UTILITY);
        Scene scene = new Scene(this);
        stage.setOnCloseRequest(windowEvent -> stage.close());
        stage.setScene(scene);
        stage.showAndWait();
    }
}
