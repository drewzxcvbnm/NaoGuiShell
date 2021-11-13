package com.tsinao.guishell;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class HelloController {

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

}