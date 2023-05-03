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


    private void AddEmployeeButtonClicked(){
        System.out.println("Add Employee button clicked");
        if (subMenuOpen == false){
            subMenuOpen = true;
            Stage subStage = new Stage();
            subStage.setTitle("Add Employee");

            HBox firstNameLayout = new HBox(10);
            HBox lastNameLayout = new HBox(10);

            Text firstNameText = new Text("First Name");
            TextArea firstNameTextArea = new TextArea();
            firstNameTextArea.setPrefHeight(20);
            firstNameTextArea.setPrefWidth(110);
            Text lastNameText = new Text("Last Name");
            TextArea lastNameTextArea = new TextArea();
            lastNameTextArea.setPrefHeight(20);
            lastNameTextArea.setPrefWidth(110);

            firstNameLayout.getChildren().addAll(firstNameText, firstNameTextArea);
            firstNameLayout.setAlignment(javafx.geometry.Pos.CENTER);
            lastNameLayout.getChildren().addAll(lastNameText, lastNameTextArea);
            lastNameLayout.setAlignment(javafx.geometry.Pos.CENTER);

            VBox layout = new VBox(10);
            layout.getChildren().addAll(firstNameLayout, lastNameLayout);
            layout.setAlignment(javafx.geometry.Pos.CENTER);

            Button submitButton = new Button("Submit");
            submitButton.setOnAction(e -> {
                String firstName = firstNameTextArea.getText();
                String lastName = lastNameTextArea.getText();
                if (firstName.trim().equals("") || sameNameEmployee((firstName + " " + lastName).trim())){
                    System.out.println("First name or last name is empty");
                    return;
                }
                Employee employee = new Employee((firstName + " " + lastName).trim(), 0, null);
                employees.add(employee);
                subStage.close();
                subMenuOpen = false;
            });

            layout.getChildren().add(submitButton);

            Scene scene = new Scene(layout, 500, 400);
            subStage.setScene(scene);

            subStage.addEventHandler(javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
                subMenuOpen = false;
            });

            subStage.show();
        }
    }

    private boolean sameNameRole(String name){
        for (Role role : roles){
            if (role.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public boolean sameNameEmployee(String name){
        for (Employee employee : employees){
            if (employee.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

}
