package com.tsinao.guishell;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;

public class NaoGuiApp extends Application {

    private final static Pattern IP_PATTERN = Pattern.compile("^(\\d|\\d{2,3})\\.(\\d|\\d{2,3})\\.(\\d|\\d{2,3})\\.(\\d|\\d{2,3})$");

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NaoGuiApp.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        setRunNaoPptxPredicate(scene);
        stage.setTitle("Nao Gui Shell");
        stage.setScene(scene);
        stage.show();
    }

    private void setRunNaoPptxPredicate(Scene scene) {
        Button button = (Button) scene.lookup("#runNaopptx");
        var foundNaoPptx = (Label) scene.lookup("#foundNaopptx");
        var foundPptx = (Label) scene.lookup("#foundPptx");
        var naoIP = (TextField) scene.lookup("#naoIp");
        Callable<Boolean> booleanCallable = () -> shouldDisableRunNaoPptx(foundNaoPptx, foundPptx, naoIP);
        var booleanBinding = Bindings.createBooleanBinding(booleanCallable, foundNaoPptx.textProperty(),
                foundPptx.textProperty(), naoIP.textProperty());
        button.disableProperty().bind(booleanBinding);
    }

    private boolean shouldDisableRunNaoPptx(Label foundNaoPptx, Label foundPptx, TextField naoIP) {
        if (!IP_PATTERN.matcher(naoIP.getText()).matches()) {
            return true;
        }
        return Arrays.asList(foundNaoPptx.getText(), foundPptx.getText()).contains("");
    }

    public static void main(String[] args) {
        launch();
    }
}