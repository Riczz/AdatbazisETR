package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.dialog.AlertDialog;
import view.DataTable;

import java.io.IOException;

public class MainController {

    @FXML
    private TabPane tabPane;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Rectangle minBtn;
    @FXML
    private Button insertBtn;
    @FXML
    private Button modifyBtn;
    @FXML
    private Button delBtn;
    @FXML
    private Button refreshBtn;

    private DataTable currentTable;
    private Tab currentTab;

    private Stage stage;
    private double xOffset;
    private double yOffset;

    @FXML
    public void initialize() {
        Database database = new Database();
        tabPane.getSelectionModel().select(0);
        database.fillWithTables(tabPane);
        tabPane.getTabs().forEach(database::createTable);
        database.close();
    }

    @FXML
    public void insert() {

        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("insert.fxml"));
            Parent root = loader.load();

            FormController controller = loader.getController();

            controller.addAttribute(getCurrentTable());
            controller.setLabel(getCurrentTab().getText());


            Stage insertStage = new Stage(StageStyle.UTILITY);
            Scene insertScene = new Scene(root);
            insertStage.setScene(insertScene);
            insertStage.setResizable(false);
            insertStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentTable.insert();
    }

    @FXML
    public void modify() {
        if (getSelectedIndex() == -1) {
            new AlertDialog(Alert.AlertType.ERROR,"Nincs kiválasztva cella!","Válassz ki egy cellát.");
            return;
        }
        getCurrentTable().modify(getSelectedIndex());
    }

    @FXML
    public void delete() {
        if (getSelectedIndex() == -1) {
            new AlertDialog(Alert.AlertType.ERROR,"Nincs kiválasztva cella!","Válassz ki egy cellát.");
            return;
        }
        getCurrentTable().delete(getSelectedIndex());
    }

    @FXML
    public void refresh() {
        getCurrentTable().refresh();
        new AlertDialog(Alert.AlertType.INFORMATION,"Frissítés","Adatok lekérése sikeres!");
    }

    public Tab getCurrentTab() {
        currentTab = tabPane.getSelectionModel().getSelectedItem();
        return currentTab;
    }

    public DataTable getCurrentTable() {
        currentTable = (DataTable)tabPane.getSelectionModel().getSelectedItem().getContent();
        return currentTable;
    }

    public int getSelectedIndex() {
        if (currentTable != null) {
            return currentTable.getSelectionModel().getSelectedIndex();
        }
        return -1;
    }

    // ------------------------
    // Ablakot kezelő metódusok
    // ------------------------

    @FXML
    private void handlePressEvent(MouseEvent event) {
        stage = getStage(mainPane);
        xOffset = stage.getX() - event.getScreenX();
        yOffset = stage.getY() - event.getScreenY();
    }

    @FXML
    private void handleDragEvent(MouseEvent event) {
        stage = getStage(mainPane);
        if (event.isPrimaryButtonDown()) {
            stage.setX(event.getScreenX() + xOffset);
            stage.setY(event.getScreenY() + yOffset);
        }
    }

    @FXML
    private void minimize() {
        stage = getStage(minBtn);
        stage.setIconified(true);
    }

    @FXML
    private void closeApp() {
        Platform.exit();
    }

    private Stage getStage(Node node) {
        return stage = (Stage) node.getScene().getWindow();
    }
}
