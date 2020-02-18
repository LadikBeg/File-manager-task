package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;

public class MethodsClass {
    //удаление файла
    public void fileDelete(ListView directoryList, Label lblFileRemove) {
        File file1 = new File(".");
        String[] files1 = file1.list();
        ObservableList list1 = FXCollections.observableArrayList(files1);
        File file2 = new File(String.valueOf(list1.get(directoryList.getSelectionModel().getSelectedIndex())));
        if (file2.delete()) {
            lblFileRemove.setText("Файл успешно удален : " + file2.getName());
            directoryList.getItems().removeAll(directoryList.getSelectionModel().getSelectedItems());
        } else {
            lblFileRemove.setText("Не удалось удалить файл : " + file2.getName());
        }
    }

    //переименнование файла
    public void fileRename(Label lblFileName, Label lblFileRemove, TextField txtRenameFile, Button btnRenamefile, ListView directoryList,
                           TextField txtFileName, Label lblRenameFile) {
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
    }

    //создание файла
    public void createFile(Label lblRenameFile, Label lblFileRemove, CheckBox checkFile, TextField txtFileName, Label lblFileName,
                           ListView directoryList, CheckBox checkDirectory) {
        lblRenameFile.setText("");
        lblFileRemove.setText("");
        if (checkFile.isSelected()) {
            File file1 = new File(txtFileName.getText());
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
        }
        if (checkDirectory.isSelected()) {
            File dir = new File(txtFileName.getText());
            if (dir.exists()) {
                lblFileName.setText("Такая директория уже существует.Введите другое название");
            } else if (!dir.exists()) {
                dir.mkdir();
                lblFileName.setText("Директория успешно создана " + dir.getName());
                txtFileName.clear();
            }
        }
        if (checkFile.isSelected() && checkDirectory.isSelected()) {
            lblFileName.setText("Выберите что то одно!");
        }
        if (checkFile.isDisabled() && checkDirectory.isDisabled()) {
            lblFileName.setText("Выберите что нибудь");
        }
    }

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



}
