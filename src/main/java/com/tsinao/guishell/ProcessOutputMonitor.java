package com.tsinao.guishell;

import javafx.application.Platform;
import javafx.scene.control.ListView;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicBoolean;

@RequiredArgsConstructor
public class ProcessOutputMonitor {

    private final Process process;
    private final ListView<String> listView;
    private final StringBuffer builder = new StringBuffer();

    public void startMonitoring() {
        AtomicBoolean alive = new AtomicBoolean(true);
        new Thread(() -> {
            while (alive.get()) monitor();
        }).start();
        listView.getScene().getWindow().setOnCloseRequest(we -> alive.set(false));
    }

    @SneakyThrows
    private void monitor() {
        if (!process.isAlive()) {
            Thread.sleep(10);
            return;
        }
        int available = process.getInputStream().available();
        if (available != 0) {
            byte[] bytes = process.getInputStream().readNBytes(available);
            builder.append(new String(bytes));
        }
        if (builder.toString().contains("\n")) {
            Thread thread = new Thread(this::appendLineToList);
            thread.start();
            thread.join();
        }
        Thread.sleep(10);
    }

    private void appendLineToList() {
        int end = builder.indexOf("\n");
        String substring = builder.substring(0, end);
        builder.delete(0, end + 1);
        Platform.runLater(() -> listView.getItems().add(substring));
    }

}
