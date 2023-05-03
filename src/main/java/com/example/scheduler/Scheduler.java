package com.example.scheduler;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Scheduler {
    Stage stage;
    ArrayList<Role> roles = new ArrayList<Role>();
    ArrayList<Employee> employees = new ArrayList<Employee>();
    BorderPane layout;
    HBox topMenu;
    Scene scene;
    Boolean subMenuOpen = false;

    Button addRoleButton;
    Button addEmployeeButton;
    Button saveButton;
    Button loadButton;
    Button editRoleButton;
    Button editEmployeeButton;
    Button deleteRoleButton;
    Button generateScheduleButton;

    public Scheduler(){
        System.out.println("Scheduler created");
    }

    public void start(Stage stage){
        System.out.println("Scheduler started");
        this.stage = stage;
        stage.setTitle("Scheduler");

        addRoleButton = new Button("Add Role");
        addEmployeeButton = new Button("Add Employee");
        addEmployeeButton.setOnAction(e -> AddEmployeeButtonClicked());
        saveButton = new Button("Save");
        loadButton = new Button("Load");
        editRoleButton = new Button("Edit Role");
        editEmployeeButton = new Button("Edit Employee");
        deleteRoleButton = new Button("Delete Role");

        topMenu = new HBox(20);
        topMenu.getChildren().addAll(addRoleButton, addEmployeeButton, saveButton, loadButton, editRoleButton, editEmployeeButton, deleteRoleButton);
        topMenu.setAlignment(javafx.geometry.Pos.CENTER);

        layout = new BorderPane();
        layout.setTop(topMenu);
        scene = new Scene(layout, 1000, 700);
        stage.setScene(scene);

    }


    public void AddEmployeeButtonClicked(){
        System.out.println("Add Employee button clicked");
        if (subMenuOpen == false){
            subMenuOpen = true;
            Stage subStage = new Stage();
            subStage.setTitle("Add Employee");
            HBox holder = new HBox(20);
            VBox names = new VBox(20);
            VBox fillInBoxes = new VBox(20);
            VBox buttons = new VBox(20);
            Button submitButton = new Button("Submit");
            Text firstNameText = new Text("First Name");
            Text lastNameText = new Text("Last Name");

            TextArea firstNameTextArea = new TextArea();
            TextArea lastNameTextArea = new TextArea();

            names.getChildren().addAll(firstNameText, lastNameText);
            fillInBoxes.getChildren().addAll(firstNameTextArea, lastNameTextArea);
            buttons.getChildren().addAll(submitButton);
            holder.getChildren().addAll(names, fillInBoxes, buttons);

            Scene subScene = new Scene(holder, 500, 500);
            subStage.setScene(subScene);

            subStage.addEventHandler(javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
                subMenuOpen = false;
            });

            subStage.show();
        }
    }

}
