module com.example.gcm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gcm to javafx.fxml;
    exports com.example.gcm;
}