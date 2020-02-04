package sample;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private Button btnRefresh;

    @FXML
    private TextField txtRenameFile;

    @FXML
    private Button btnRenamefile;

    @FXML
    private Label lblRenameFile;





    @FXML
    void initialize() {
        File file = new File(".");
        String[] files = file.list();
        ObservableList list = FXCollections.observableArrayList(files);
        directoryList.setItems(list);
        lblPath.setText(file.getAbsolutePath());


        //удаление файла
        btnRemove.setOnAction(actionEvent -> {
            lblRenameFile.setText("");
            lblFileName.setText("");
            File file1 = new File(".");
            String[] files1 = file1.list();
            ObservableList list1 = FXCollections.observableArrayList(files1);
            File file2 = new File(String.valueOf(list1.get(directoryList.getSelectionModel().getSelectedIndex())));
            if (file2.delete()){
                lblFileRemove.setText("Файл успешно удален : " + file2.getName());
                directoryList.getItems().removeAll(directoryList.getSelectionModel().getSelectedItems());
            }else{
                lblFileRemove.setText("Не удалось удалить файл : " + file2.getName());
            }


        });

        //создание файла
        btnCreate.setOnAction(actionEvent -> {
            lblRenameFile.setText("");
            lblFileRemove.setText("");
            File file1;
            file1 = new File(txtFileName.getText());
            if (file1.exists()) {
                lblFileName.setText("Такой файл уже существует.Введите другое название.");
            } else if (!file1.exists()) {
                try {
                    file1.createNewFile();
                    lblFileName.setText("Файл успешно создан : " + file1.getName());
                    txtFileName.clear();
                    File file2 = new File(".");
                    String[] files3 = file2.list();
                    ObservableList list3 = FXCollections.observableArrayList(files3);
                    directoryList.setItems(list3);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        bntRename.setOnAction(actionEvent -> {
            lblFileName.setText("");
            lblFileRemove.setText("");
            txtRenameFile.setVisible(true);
            btnRenamefile.setVisible(true);
            btnRenamefile.setOnAction(actionEvent1 -> {
                File file1 = new File(".");
                String[] files1 = file1.list();
                ObservableList list1 = FXCollections.observableArrayList(files1);
                File file2 = new File(String.valueOf(list1.get(directoryList.getSelectionModel().getSelectedIndex())));
                File file3 = new File(txtRenameFile.getText());
                file2.renameTo(file3);
                File file4 = new File(".");
                String[] files3 = file4.list();
                ObservableList list3 = FXCollections.observableArrayList(files3);
                directoryList.setItems(list3);
                txtFileName.clear();
                txtRenameFile.setVisible(false);
                btnRenamefile.setVisible(false);
                lblRenameFile.setVisible(true);
                lblRenameFile.setText("Файл успешно переименнован : " + file2 + " на : " + file3);

            });

        });
        btnRefresh.setOnAction(actionEvent -> {
            File file1 = new File(".");
            String[] files1 = file1.list();
            ObservableList list1 = FXCollections.observableArrayList(files1);
            directoryList.setItems(list1);

        });
    }
}
