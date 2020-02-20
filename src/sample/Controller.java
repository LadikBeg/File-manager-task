package sample;

import java.io.File;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.Blend;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;


public class Controller {
    @FXML
    private ListView<?> directoryList;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnRemove;

    @FXML
    private Button bntRename;

    @FXML
    private Label lblPath;

    @FXML
    private TextField txtFileName;

    @FXML
    private Label lblFileName;

    @FXML
    private Label lblFileRemove;

    @FXML
    private TextField txtRenameFile;

    @FXML
    private Button btnRenamefile;

    @FXML
    private Label lblRenameFile;

    @FXML
    private CheckBox checkFile;

    @FXML
    private CheckBox checkDirectory;

    @FXML
    private Button btnWriteToFile;

    @FXML
    private TextField txtWriteToFile;

    @FXML
    void initialize() {

        File file = new File(".");
        String[] files = file.list();
        ObservableList list = FXCollections.observableArrayList(files);
        directoryList.setItems(list);
        lblPath.setText(file.getAbsolutePath());
        MethodsClass methods = new MethodsClass();
        ButtonClass buttonEffect = new ButtonClass();
        buttonEffect.cursorHand(btnCreate, btnRenamefile, bntRename, btnRemove, checkFile, checkDirectory);
        buttonEffect.dropShadowsButton(bntRename);
        buttonEffect.dropShadowsButton(btnCreate);
        buttonEffect.dropShadowsButton(btnRemove);
        buttonEffect.dropShadowsButton(btnRenamefile);
        buttonEffect.dropShadowsTextFild(txtFileName);
        buttonEffect.dropShadowsTextFild(txtRenameFile);
        btnCreate.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (txtFileName.getText().equals("")) {
                    lblFileName.setText("Введите название файла!");
                } else if (!txtFileName.getText().equals("")) {
                    methods.createFile(lblRenameFile, lblFileRemove, checkFile, txtFileName, lblFileName, directoryList, checkDirectory);
                }
            }
        });
        btnRemove.setOnAction(actionEvent -> {
            lblRenameFile.setText("");
            lblFileName.setText("");
            methods.fileDelete(directoryList, lblFileRemove);
        });
        checkFile.setSelected(true);
        //переименование
        bntRename.setOnAction(actionEvent -> {
            lblRenameFile.setText("");
            methods.fileRename(lblFileName, lblFileRemove, txtRenameFile, btnRenamefile, directoryList, txtFileName, lblRenameFile);
        });
        methods.writeFile(btnWriteToFile,directoryList,txtWriteToFile);
    }
}
