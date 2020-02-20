package sample;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.Blend;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

public class ButtonClass {
    //меняем курсор когда наводится на значимые объекты!
    public void cursorHand(Button btnCreate, Button btnRenamefile, Button bntRename, Button btnRemove, CheckBox checkFile,
                           CheckBox checkDirectory) {
        btnCreate.setCursor(Cursor.HAND);
        btnRenamefile.setCursor(Cursor.HAND);
        bntRename.setCursor(Cursor.HAND);
        btnRemove.setCursor(Cursor.HAND);
        checkFile.setCursor(Cursor.HAND);
        checkDirectory.setCursor(Cursor.HAND);
    }

    DropShadow shadow = new DropShadow();
    Blend blend = new Blend();

    public void dropShadowsButton(Button button) {
        button.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setEffect(shadow);
            }
        });
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button.setEffect(blend);
            }
        });
    }
    public void dropShadowsTextFild(TextField textField) {
        textField.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                textField.setEffect(shadow);
            }
        });
        textField.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                textField.setEffect(blend);
            }
        });
    }
}
