module hellofxml {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.gluonhq.attach.vibration;
    requires charm.down.core;

    opens hellofx to javafx.fxml;

    exports hellofx;
}