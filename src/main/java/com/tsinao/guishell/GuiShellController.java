package com.tsinao.guishell;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;


public class GuiShellController {

    public static final String NAOPPTX_EXE = "naopptx\\.exe";
    public static final String BEHAVIOR_EXE = "behavior\\.exe";
    public static final String PPTX = ".*\\.pptx";
    @FXML
    public Label foundNaopptx;
    @FXML
    public Label foundBehavior;
    @FXML
    public Label foundPptx;
    @FXML
    public TextField naoIp;
    @FXML
    public CheckBox noNet;

    @FXML
    public void findNaopptx() {
        FileFinding fileFinding = new FileFinding(NAOPPTX_EXE, foundNaopptx, foundNaopptx.getScene().getWindow());
        fileFinding.findFile();
    }

    @FXML
    public void findBehavior() {
        FileFinding fileFinding = new FileFinding(BEHAVIOR_EXE, foundBehavior, foundBehavior.getScene().getWindow());
        fileFinding.findFile();
    }

    @FXML
    public void findPptx() {
        FileFinding fileFinding = new FileFinding(PPTX, foundPptx, foundPptx.getScene().getWindow());
        fileFinding.findFile();
    }

    @FXML
    public void runNaopptx() {
        String command = String.format("%s --ip %s --pr %s %s", foundNaopptx.getText(), naoIp.getText(),
                foundPptx.getText(), noNet.isSelected() ? "--no-inet" : "");
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nao Gui Shell");
            alert.setHeaderText("Cannot run command");
            alert.setContentText(command);
            alert.show();
        }
    }
}