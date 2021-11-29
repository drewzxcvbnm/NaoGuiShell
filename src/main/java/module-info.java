module com.tsinao.guishell {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires static lombok;

    opens com.tsinao.guishell to javafx.fxml;
    exports com.tsinao.guishell;
}