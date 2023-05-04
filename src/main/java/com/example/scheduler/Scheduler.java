package com.example.scheduler;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Calendar;

public class Scheduler {
    Stage stage;
    ArrayList<Role> roles = new ArrayList<Role>();
    ArrayList<Employee> employees = new ArrayList<Employee>();
    BorderPane layout;
    HBox topMenu;
    GridPane grid;
    Scene scene;
    //Boolean to keep track of if the sub menu is open or not
    Boolean subMenuOpen = false;

    //buttons
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

        //Create buttons
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

        grid = new GridPane();

        //Add employee text
        Text textEmployee = new Text(" Employees ");
        textEmployee.setStyle("-fx-font: 22 arial;");
        grid.add(textEmployee, 0, 0);

        //Set up the grid
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setGridLinesVisible(true);
        grid.setAlignment(Pos.TOP_CENTER);

        //add days of the week to the grid, with the month and day.
        for (int i = 0; i < 7; i++){
            Calendar calendar = Calendar.getInstance();
            //1 is Sunday, 2 is Monday, etc.
            int day = (calendar.get(Calendar.DAY_OF_WEEK) + i);
            //If the day is greater than 7, subtract 7 to get the correct day of the week
            if (day > 7){
                day = day - 7;
            }
            String dayOfWeek = "";
            switch (day){
                case 1:
                    dayOfWeek = " Sunday";
                    break;
                case 2:
                    dayOfWeek = " Monday";
                    break;
                case 3:
                    dayOfWeek = " Tuesday";
                    break;
                case 4:
                    dayOfWeek = " Wednesday";
                    break;
                case 5:
                    dayOfWeek = " Thursday";
                    break;
                case 6:
                    dayOfWeek = " Friday";
                    break;
                case 7:
                    dayOfWeek = " Saturday";
                    break;
            }
            //Add the text to the grid
            Text text = new Text(dayOfWeek + " " + (calendar.get(Calendar.MONTH) + 1) + "/" + (calendar.get(Calendar.DAY_OF_MONTH) + i +" "));
            text.setStyle("-fx-font: 22 arial;");
            grid.add(text, i + 1, 0);
        }

        //Set up the layout which holds the top menu and the grid
        layout = new BorderPane();
        layout.setTop(topMenu);
        layout.setCenter(grid);
        scene = new Scene(layout, 1000, 700);
        stage.setScene(scene);
    }


    private void AddEmployeeButtonClicked(){
        System.out.println("Add Employee button clicked");
        //If a sub menu is already open, don't open another one.
        if (subMenuOpen == false){
            subMenuOpen = true;
            Stage subStage = new Stage();
            subStage.setTitle("Add Employee");

            //Layout for the sub menu inputs.
            HBox firstNameLayout = new HBox(10);
            HBox lastNameLayout = new HBox(10);

            //Text and text area for the first and last name.
            Text firstNameText = new Text("First Name");
            TextArea firstNameTextArea = new TextArea();
            firstNameTextArea.setPrefHeight(20);
            firstNameTextArea.setPrefWidth(110);
            Text lastNameText = new Text("Last Name");
            TextArea lastNameTextArea = new TextArea();
            lastNameTextArea.setPrefHeight(20);
            lastNameTextArea.setPrefWidth(110);

            //Add the text and text area together and centers them.
            firstNameLayout.getChildren().addAll(firstNameText, firstNameTextArea);
            firstNameLayout.setAlignment(javafx.geometry.Pos.CENTER);
            lastNameLayout.getChildren().addAll(lastNameText, lastNameTextArea);
            lastNameLayout.setAlignment(javafx.geometry.Pos.CENTER);

            //Add the first and last name layouts to the main layout.
            VBox layout = new VBox(10);
            layout.getChildren().addAll(firstNameLayout, lastNameLayout);
            layout.setAlignment(javafx.geometry.Pos.CENTER);

            //Submit button to add the employee to the list and to close the sub menu after.
            Button submitButton = new Button("Submit");
            submitButton.setOnAction(e -> {
                String firstName = firstNameTextArea.getText();
                String lastName = lastNameTextArea.getText();
                //If the first name is empty or the name is already in the list, don't add the employee.
                if (firstName.trim().equals("") || sameNameEmployee((firstName + " " + lastName).trim())){
                    System.out.println("First name or last name is empty");
                    return;
                }
                Employee employee = new Employee((firstName + " " + lastName).trim(), 0, null);
                employees.add(employee);
                subStage.close();
                subMenuOpen = false;

                //add employee to grid
                Text text = new Text(employee.getName());
                text.setStyle("-fx-font: 22 arial;");
                grid.add(text, 0, employees.size());
            });

            //Add the submit button to the main layout.
            layout.getChildren().add(submitButton);

            //Set the scene and show the stage.
            Scene scene = new Scene(layout, 500, 400);
            subStage.setScene(scene);

            //If the sub menu is closed, set the subMenuOpen to false.
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

    //Checks if the name is already in the list.
    public boolean sameNameEmployee(String name){
        for (Employee employee : employees){
            if (employee.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

}
