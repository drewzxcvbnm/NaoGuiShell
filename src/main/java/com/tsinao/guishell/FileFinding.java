package com.tsinao.guishell;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.util.Optional;
import java.util.regex.Pattern;

public class FileFinding {

    private final String wantedFile;
    private final Label pathLabel;
    private final Window scene;
    private final Pattern filePattern;

    public FileFinding(String wantedFileRegex, Label pathLabel, Window window) {
        this.wantedFile = wantedFileRegex;
        this.pathLabel = pathLabel;
        this.scene = window;
        this.filePattern = Pattern.compile(wantedFileRegex);
    }

    public void findFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(String.format("Find %s", wantedFile));
        File file = fileChooser.showOpenDialog(scene);
        if (!filePattern.matcher(file.getName()).matches()) {
            confirmFileChoice(file, wantedFile);
        }
        pathLabel.setText(file.getAbsolutePath());
    }

    private void confirmFileChoice(File file, String wantedFile) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(String.format("Name of file is not '%s'", wantedFile));
        alert.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            pathLabel.setText(file.getAbsolutePath());
        }
    }
}
