package application.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import application.view.DataTable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FormController {

    @FXML
    private Label label;
    @FXML
    private GridPane grid;
    @FXML
    private Button applyBtn;
    @FXML
    private AnchorPane mainPane;

    private ArrayList<TextField> textFields;
    private List<String> input;
    private int rows = 0;

    @FXML
    public void initialize() {
        textFields = new ArrayList<>();
        input = new ArrayList<>();
    }

    @FXML
    public void sendForm() {
        this.input = textFields.stream().map(TextInputControl::getText).collect(Collectors.toList());
        getStage(mainPane).close();
    }

    public void setLabel(String text) {
        label.setText(text);
    }

    public void addAttribute(DataTable table) {
        for (Object col : table.getColumns()) {
            addAttribute(((TableColumn) col).getText());
        }
    }

    public void addAttribute(String text) {
        Label label = new Label();
        label.setFont(new Font("System", 20.0));
        label.setAlignment(Pos.CENTER);
        label.setPrefWidth(275.0);
        label.setText(text);

        TextField textField = new TextField();
        textFields.add(textField);

        grid.add(label, 0, rows);
        grid.add(textField, 1, rows++);
    }

    public void setInput(List<String> input) {
        for (int i=0; i<textFields.size(); i++) {
            System.out.println(input.size());
            textFields.get(i).setText(input.get(i));
        }
    }

    public List<String> getInput() {
        return input;
    }

    private Stage getStage(Node node) {
        return (Stage) node.getScene().getWindow();
    }
}
