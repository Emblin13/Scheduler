module com.example.scheduler {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.scheduler to javafx.fxml;
    exports com.example.scheduler;
}