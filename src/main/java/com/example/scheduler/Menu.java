package com.example.scheduler;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu {

    Stage stage;
    Button managerButton;
    Button employeeButton;
    VBox layout;
    Scene scene;

    public Menu(){
        System.out.println("Menu created");
    }

    public void start(Stage stage){
        System.out.println("Menu started");
        this.stage = stage;

        managerButton = new Button("Manager");
        employeeButton = new Button("Employee");
        managerButton.setOnAction(e -> managerButtonClicked());

        layout = new VBox(20);
        layout.getChildren().addAll(managerButton, employeeButton);
        layout.setAlignment(javafx.geometry.Pos.CENTER);
        scene = new Scene(layout, 300, 250);
        stage.setScene(scene);

        stage.show();
    }

    public void managerButtonClicked(){
        System.out.println("Manager button clicked");
        Scheduler scheduler = new Scheduler();
        scheduler.start(stage);
    }

}