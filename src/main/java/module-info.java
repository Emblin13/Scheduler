module com.example.scheduler {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.scheduler to javafx.fxml;
    exports com.example.scheduler;
}