package com.tsinao.guishell;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class ProcessViewCreator {

    private final Process process;

    @SneakyThrows
    public Stage getProcessView() {
        FXMLLoader fxmlLoader = new FXMLLoader(NaoGuiApp.class.getResource("process-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Nao Gui Shell");
        ListView<String> lview = (ListView<String>) scene.lookup("#processOutput");
        new ProcessOutputMonitor(process, lview).startMonitoring();
        ((Button) scene.lookup("#killProcess")).setOnAction(actionEvent -> {
            process.destroyForcibly();
            waitForProcess();
            lview.getItems().add("Process Killed: " + process);
        });
        return stage;
    }

    @SneakyThrows
    private void waitForProcess() {
        process.waitFor();
    }

}
