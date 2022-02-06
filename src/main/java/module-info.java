module hr.java.production.paveticjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.slf4j;

    opens hr.java.production.paveticjavafx to javafx.fxml;
    exports hr.java.production.paveticjavafx;
}