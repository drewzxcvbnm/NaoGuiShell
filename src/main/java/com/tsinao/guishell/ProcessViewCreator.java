package com.tsinao.guishell;

import javafx.fxml.FXMLLoader;
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
        stage.setScene(fxmlLoader.load());
        stage.setTitle("Nao Gui Shell");
        return stage;
    }

}
