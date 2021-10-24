package sample;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import view.DataTable;

public class FormController {

    @FXML
    private Label label;
    @FXML
    private GridPane grid;
    @FXML
    private Button applyBtn;

    private int rows = 0;

    @FXML
    public void initialize() {

    }

    public void setLabel(String text) {
        label.setText(text);
    }

    public void addAttribute(DataTable table) {
        for (Object col : table.getColumns()) {
            addAttribute(((TableColumn)col).getText());
        }
    }

    public void addAttribute(String text) {
        Label label = new Label();
        label.setFont(new Font("System",20.0));
        label.setAlignment(Pos.CENTER);
        label.setPrefWidth(275.0);
        label.setText(text);

        TextField textField = new TextField();

        grid.add(label,0,rows);
        grid.add(textField,1,rows++);
    }
}
