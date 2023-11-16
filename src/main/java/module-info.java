module ru.test.docviewer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens ru.test.docviewer to javafx.fxml;
    exports ru.test.docviewer;
    exports ru.test.docviewer.model;
    opens ru.test.docviewer.model to javafx.fxml;
    exports ru.test.docviewer.exception;
    opens ru.test.docviewer.exception to javafx.fxml;
}