package application.view.dialog;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

public final class AlertDialog extends Alert {

    public AlertDialog(AlertType type, String title, String text) {
        super(type, text, ButtonType.OK);

        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setTitle(title);
        setHeaderText(null);
        showAndWait();
    }
}
