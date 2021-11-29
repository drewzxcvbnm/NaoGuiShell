package com.tsinao.guishell;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;


public class GuiShellController {

    private static final Pattern IP_PATTERN = Pattern.compile("^(\\d|\\d{2,3})\\.(\\d|\\d{2,3})\\.(\\d|\\d{2,3})\\.(\\d|\\d{2,3})$");
    private static final String NAOPPTX_EXE = "naopptx\\.exe";
    private static final String BEHAVIOR_EXE = "behavior\\.exe";
    private static final String PPTX = ".*\\.pptx";
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
    public Button runNaopptx;

    @FXML
    public void initialize() {
        setRunNaoPptxPredicate();
    }

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
    public void runNaoPptxCommand() {
        String command = String.format("%s --ip %s --pr %s %s", foundNaopptx.getText(), naoIp.getText(),
                foundPptx.getText(), noNet.isSelected() ? " --no-inet" : "");
        try {
            Process process = Runtime.getRuntime().exec(command.split(" "));
            Stage processView = new ProcessViewCreator(process).getProcessView();
            processView.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nao Gui Shell");
            alert.setHeaderText("Cannot run command");
            alert.setContentText(command);
            alert.show();
        }
    }

    private void setRunNaoPptxPredicate() {
        Callable<Boolean> booleanCallable = () -> shouldDisableRunNaoPptx(foundPptx, foundPptx, naoIp);
        var booleanBinding = Bindings.createBooleanBinding(booleanCallable, foundPptx.textProperty(),
                foundPptx.textProperty(), naoIp.textProperty());
        runNaopptx.disableProperty().bind(booleanBinding);
    }

    private boolean shouldDisableRunNaoPptx(Label foundNaoPptx, Label foundPptx, TextField naoIP) {
        if (!IP_PATTERN.matcher(naoIP.getText()).matches()) {
            return true;
        }
        return Arrays.asList(foundNaoPptx.getText(), foundPptx.getText()).contains("");
    }

}