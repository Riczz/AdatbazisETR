package application.controller;

import application.Database;
import application.Main;
import application.model.DataInfo;
import application.view.DataTable;
import application.view.dialog.AlertDialog;
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

    private FormController controller;
    private Stage formStage;

    private DataTable currentTable;
    private Tab currentTab;

    private Stage stage;
    private double xOffset;
    private double yOffset;

    @FXML
    public void initialize() {
        Database database = new Database();
        database.fillWithTables(tabPane);
        tabPane.getTabs().forEach(this::createTable);
        refreshBtn.setOnAction(actionEvent -> refresh(true));
        tabPane.getSelectionModel().selectLast();
        tabPane.getSelectionModel().selectFirst();
        database.close();
    }

    @FXML
    private void showQueries() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("queries.fxml"));
            Parent root = loader.load();
            stage = new Stage(StageStyle.DECORATED);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showForm() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("insert.fxml"));
            Parent root = loader.load();
            controller = loader.getController();
            controller.addAttribute(getCurrentTable());
            controller.setLabel(getCurrentTab().getText());
            formStage = new Stage(StageStyle.UTILITY);
            Scene formScene = new Scene(root);
            formStage.setScene(formScene);
            formStage.setResizable(false);
            formStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void insert() {
        showForm();
        if (!getCurrentTable().insert(controller.getInput())) insert();
    }

    @FXML
    public void modify() {
        if (!isRowSelected()) return;
        showForm();
        if (!getCurrentTable().update(getSelectedIndex(), controller.getInput())) modify();
    }

    @FXML
    public void delete() {
        if (!isRowSelected()) return;
        getCurrentTable().delete(getSelectedIndex());
    }

    @FXML
    public void refresh(boolean verbose) {
        getCurrentTable().refresh();
        if (verbose) new AlertDialog(Alert.AlertType.INFORMATION, "Frissítés", "Adatok lekérése sikeres!");
    }

    public Tab getCurrentTab() {
        return currentTab;
    }

    public DataTable getCurrentTable() {
        return currentTable;
    }

    private boolean isRowSelected() {
        if (getSelectedIndex() == -1) {
            new AlertDialog(Alert.AlertType.ERROR, "Nincs kiválasztva cella!", "Válassz ki egy cellát.");
            return false;
        }
        return true;
    }

    public int getSelectedIndex() {
        return currentTable.getSelectionModel().getSelectedIndex();
    }

    private void createTable(Tab tab) {
        DataInfo info = DataInfo.getInstance();

        tab.setOnSelectionChanged(event -> {
            currentTab = (Tab) event.getTarget();
            currentTable = (DataTable) currentTab.getContent();
            refresh(false);
        });

        DataTable t = info.getTable(tab.getId());
        tab.setContent(t);
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
