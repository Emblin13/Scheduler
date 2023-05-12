package com.example.scheduler;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    Button previousWeekButton;
    Button nextWeekButton;

    public Scheduler(){
        System.out.println("Scheduler created");
    }

    public void start(Stage stage){
        layout = new BorderPane();
        //Set background color to dark grey
        scene = new Scene(layout, 1000, 700);
        System.out.println("Scheduler started");
        this.stage = stage;
        stage.setTitle("Scheduler");

        //Create buttons and make everyother button a different color
        addRoleButton = new Button("Add Role");
        addRoleButton.setStyle("-fx-background-radius: 0; -fx-background-color: #b3b3b3;");
        addEmployeeButton = new Button("Add Employee");
        addEmployeeButton.setStyle("-fx-background-radius: 0; -fx-background-color: #cccccc;");
        addEmployeeButton.setOnAction(e -> editEmployeeStage(null));
        saveButton = new Button("Save");
        saveButton.setStyle("-fx-background-radius: 0; -fx-background-color: #b3b3b3;");
        loadButton = new Button("Load");
        loadButton.setStyle("-fx-background-radius: 0; -fx-background-color: #cccccc;");
        editRoleButton = new Button("Edit Role");
        editRoleButton.setStyle("-fx-background-radius: 0; -fx-background-color: #b3b3b3;");
        editEmployeeButton = new Button("Edit Employee");
        editEmployeeButton.setStyle("-fx-background-radius: 0; -fx-background-color: #cccccc;");
        deleteRoleButton = new Button("Delete Role");
        deleteRoleButton.setStyle("-fx-background-radius: 0; -fx-background-color: #b3b3b3;");

        //Spacer fills the rest of the top menu with a dark grey color and scales with the window
        Pane spacer = new Pane();
        spacer.setPrefHeight(saveButton.getPrefHeight());
        spacer.setStyle("-fx-background-color: #333333;");
        HBox.setHgrow(spacer, Priority.ALWAYS);

        topMenu = new HBox(0);
        topMenu.getChildren().addAll(addRoleButton, addEmployeeButton, saveButton, loadButton, editRoleButton, editEmployeeButton, deleteRoleButton, spacer);

        showWeek(0);

        layout.setTop(topMenu);
        layout.setCenter(grid);
        stage.setScene(scene);
    }

    private void addEmployeeRow(ArrayList<Employee> employees, int index){
        Employee employee = employees.get(index);
        Button button = new Button(employee.getName());
        button.setStyle("-fx-font-size: 20; -fx-background-color: transparent;");
        button.setPrefHeight(20);
        button.setMaxWidth(Double.MAX_VALUE);
        grid.add(button, 0, index + 1);
        button.setOnAction(e -> {
            editEmployeeStage(employee);
        });

        for (int i = 0; i < 7; i++){
            Button button1 = new Button();
            button1.setStyle("-fx-background-color: transparent;");
            button1.setMaxWidth(Double.MAX_VALUE);
            button1.setPrefHeight(20);
            grid.add(button1, i + 1, index + 1);
        }
    }

    private void showWeek(int mod){
        layout.getChildren().remove(grid);
        grid = new GridPane();
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setGridLinesVisible(true);
        layout.setCenter(grid);

        Text textEmployee = new Text(" Employees ");
        textEmployee.wrappingWidthProperty().bind(scene.widthProperty().divide(8));
        textEmployee.setStyle("-fx-font: 20 arial;");
        grid.add(textEmployee, 0, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, mod);

        for (int i = 0; i < 7; i++){
            //1 is Sunday, 2 is Monday, etc.
            int day = (calendar.get(Calendar.DAY_OF_WEEK));
            //If the day is greater than 7, subtract 7 to get the correct day of the week
            if (day > 7){
                day = day - 7;
            }
            String dayOfWeek = "";
            switch (day){
                case 1:
                    dayOfWeek = "Sun";
                    break;
                case 2:
                    dayOfWeek = "Mon";
                    break;
                case 3:
                    dayOfWeek = "Tue";
                    break;
                case 4:
                    dayOfWeek = "Wed";
                    break;
                case 5:
                    dayOfWeek = "Thu";
                    break;
                case 6:
                    dayOfWeek = "Fri";
                    break;
                case 7:
                    dayOfWeek = "Sat";
                    break;
            }
            //Add the text to the grid
            Text text = new Text(dayOfWeek + " " + (calendar.get(Calendar.MONTH) + 1) + "/" + (calendar.get(Calendar.DAY_OF_MONTH) +" "));
            text.setStyle("-fx-font: 20 arial;");
            //make the text scale 1/8 of the width of the screen
            text.wrappingWidthProperty().bind(scene.widthProperty().divide(8));
            grid.add(text, i + 1, 0);
            calendar.add(Calendar.DATE, 1);
        }
        //Add the employees to the grid
        for (int j = 0; j < employees.size(); j++){
            addEmployeeRow(employees, j);
        }
    }

    //Also allows you to add a new employee
    private void editEmployeeStage(Employee employee){
        if (!subMenuOpen){
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

            if (employee != null){
                firstNameTextArea.setText(employee.getFirstName());
                lastNameTextArea.setText(employee.getLastName());
            }

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
                if (employee != null){
                    employee.setName((firstName + " " + lastName).trim());
                    subStage.close();
                    subMenuOpen = false;
                    showWeek(0);
                    return;
                } else {
                    Employee newEmployee = new Employee((firstName + " " + lastName).trim(), null);
                    employees.add(newEmployee);
                    subStage.close();
                    subMenuOpen = false;
                    showWeek(0);
                }
                //add employee to grid
                addEmployeeRow(employees, employees.size() - 1);
            });

            //Add the submit button to the main layout.
            layout.getChildren().add(submitButton);

            if (employee != null){
                Button deleteButton = new Button("Delete");
                deleteButton.setOnAction(e -> {
                    employees.remove(employee);
                    subStage.close();
                    subMenuOpen = false;
                    showWeek(0);
                });
                layout.getChildren().add(deleteButton);
            }

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

    private void generateSchedule(){

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
