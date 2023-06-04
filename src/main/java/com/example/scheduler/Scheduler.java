package com.example.scheduler;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Scheduler {
    private ArrayList<Employee> employees = new ArrayList<Employee>();

    Stage stage;
    ArrayList<Role> roles = new ArrayList<Role>();

    int dayOffset;
    int tempId = -1;
    BorderPane mainBorderlayout;
    HBox topMenu;
    GridPane grid;
    Scene scene;
    //Boolean to keep track of if the sub menu is open or not
    Boolean subMenuOpen = false;

    //buttons
    private Button addRoleButton;
    private Button addEmployeeButton;
    private Button saveButton;
    private Button loadButton;
    private Button editRoleButton;
    private Button editEmployeeButton;
    private Button generateScheduleButton;
    private Button previousWeekButton;
    private Button nextWeekButton;

    public Scheduler(){
        System.out.println("Scheduler created");
    }

    public void start(Stage stage){
        mainBorderlayout = new BorderPane();
        //Set background color to dark grey
        scene = new Scene(mainBorderlayout, 1000, 700);
        System.out.println("Scheduler started");
        this.stage = stage;
        stage.setTitle("Scheduler");

        //Create buttons and make everyother button a different color
        addRoleButton = new Button("Add Role");
        addRoleButton.setStyle("-fx-background-radius: 0; -fx-background-color: #b3b3b3;");
        addEmployeeButton = new Button("Add Employee");
        addEmployeeButton.setStyle("-fx-background-radius: 0; -fx-background-color: #cccccc;");
        addEmployeeButton.setOnAction(e -> employeeStage(null));
        saveButton = new Button("Save");
        saveButton.setStyle("-fx-background-radius: 0; -fx-background-color: #b3b3b3;");
        loadButton = new Button("Load");
        loadButton.setStyle("-fx-background-radius: 0; -fx-background-color: #cccccc;");
        editRoleButton = new Button("Edit Role");
        editRoleButton.setStyle("-fx-background-radius: 0; -fx-background-color: #b3b3b3;");
        editRoleButton.setOnAction(e -> editRoleStage());
        previousWeekButton = new Button("Previous Week");
        previousWeekButton.setStyle("-fx-background-radius: 0; -fx-background-color: #cccccc;");
        nextWeekButton = new Button("Next Week");
        nextWeekButton.setStyle("-fx-background-radius: 0; -fx-background-color: #b3b3b3;");

        previousWeekButton.setOnAction(e -> {
            dayOffset -= 7;
            if (dayOffset < 0){
                dayOffset = 0;
            }
            showWeek(dayOffset);
        });

        nextWeekButton.setOnAction(e -> {
            dayOffset += 7;
            if(dayOffset > 28){
                dayOffset = 28;
            }
            showWeek(dayOffset);
        });

        addRoleButton.setOnAction(e -> roleStage(null));

        //Spacer fills the rest of the top menu with a dark grey color and scales with the window
        Pane spacer = new Pane();
        spacer.setPrefHeight(saveButton.getPrefHeight());
        spacer.setStyle("-fx-background-color: #333333;");
        HBox.setHgrow(spacer, Priority.ALWAYS);

        topMenu = new HBox(0);
        topMenu.getChildren().addAll(saveButton, loadButton, addRoleButton, editRoleButton, addEmployeeButton, previousWeekButton, nextWeekButton, spacer);

        showWeek(0);

        mainBorderlayout.setTop(topMenu);
        mainBorderlayout.setCenter(grid);
        stage.setScene(scene);
    }

    private void addEmployeeRow(ArrayList<Employee> employees, int index){
        Employee employee = employees.get(index);
        Button button = new Button(employee.getFirstName() + " " + employee.getLastName());
        button.setStyle("-fx-font-size: 20; -fx-background-color: transparent;");
        button.setPrefHeight(20);
        button.setMaxWidth(Double.MAX_VALUE);
        grid.add(button, 0, index + 1);
        button.setOnAction(e -> {
            employeeStage(employee);
        });

        for (int i = 0; i < 7; i++){
            Button button1 = new Button();
            button1.setStyle("-fx-background-color: transparent;");
            button1.setMaxWidth(Double.MAX_VALUE);
            button1.setPrefHeight(40);
            grid.add(button1, i + 1, index + 1);

            int finalI = i;
            button1.setOnAction(e -> {
                editSchedule(employee, finalI);
            });
        }
    }

    private void editSchedule(Employee employee, int dayOffset){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, dayOffset);
        System.out.println("Edit schedule of " + employee.getFirstName() + " " + employee.getLastName()+ " on " + calendar.getTime());

        if (!subMenuOpen){
            Stage subStage = new Stage();
            subStage.setTitle("Edit Schedule");
            //Add text to the top of the window telling the user the name of the employee and the date they are editing. Don't include the time.
            Text text = new Text("Edit schedule of " + employee.getFirstName() + " " + employee.getLastName()+ " on " + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DATE));
            text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            text.setTextAlignment(TextAlignment.CENTER);

            VBox layout = new VBox(10);
            layout.getChildren().add(text);

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

    private void showWeek(int mod){
        mainBorderlayout.getChildren().remove(grid);
        grid = new GridPane();
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setGridLinesVisible(true);
        mainBorderlayout.setCenter(grid);

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
    private void employeeStage(Employee employee){
        if (!subMenuOpen){
            tempId = -1;
            Stage subStage = new Stage();
            subStage.setTitle("Add Employee");
            ArrayList<Role> tempRoles = new ArrayList<>();

            //Layout for the sub menu inputs.
            HBox firstNameLayout = new HBox(10);
            HBox lastNameLayout = new HBox(10);
            HBox hireDateLayout = new HBox(10);
            HBox availabilityLayout = new HBox(10);
            HBox preferencesLayout = new HBox(10);
            HBox priorityLayout = new HBox(10);
            HBox maxHoursLayout = new HBox(10);
            HBox maximumHoursLayout = new HBox(10);

            //Text and text area for the first and last name.
            Text firstNameText = new Text("First Name");
            TextArea firstNameTextArea = new TextArea();
            firstNameTextArea.setPrefHeight(20);
            firstNameTextArea.setPrefWidth(110);
            Text lastNameText = new Text("Last Name");
            TextArea lastNameTextArea = new TextArea();
            lastNameTextArea.setPrefHeight(20);
            lastNameTextArea.setPrefWidth(110);

            Text hireDateText = new Text("Hire Date");
            TextArea hireDateTextArea = new TextArea();
            hireDateTextArea.setPrefHeight(20);
            hireDateTextArea.setPrefWidth(110);

            Text availabilityText = new Text("Availability");
            TextArea availabilityTextArea = new TextArea();
            availabilityTextArea.setPrefHeight(20);
            availabilityTextArea.setPrefWidth(110);

            Text preferencesText = new Text("Preferences");
            TextArea preferencesTextArea = new TextArea();
            preferencesTextArea.setPrefHeight(20);
            preferencesTextArea.setPrefWidth(110);

            Text priorityText = new Text("Priority");
            TextArea priorityTextArea = new TextArea();
            priorityTextArea.setPrefHeight(20);
            priorityTextArea.setPrefWidth(110);

            Text maxDesiredHoursText = new Text("Maximum Desired Hours");
            TextArea maxDesiredHoursTextArea = new TextArea();
            maxDesiredHoursTextArea.setPrefHeight(20);
            maxDesiredHoursTextArea.setPrefWidth(110);

            Text maxText = new Text("Maximum Hours");
            TextArea maxTextArea = new TextArea();
            maxTextArea.setPrefHeight(20);
            maxTextArea.setPrefWidth(110);

            if (employee != null){
                tempId = employee.getId();
                firstNameTextArea.setText(employee.getFirstName());
                lastNameTextArea.setText(employee.getLastName());
                hireDateTextArea.setText(employee.getHireDate().toString());
                availabilityTextArea.setText(String.valueOf(employee.getAvailability()));
                maxTextArea.setText(String.valueOf(employee.getMaximumHours()));
            }

            //Add the text and text area together and centers them.
            firstNameLayout.getChildren().addAll(firstNameText, firstNameTextArea);
            firstNameLayout.setAlignment(javafx.geometry.Pos.CENTER);
            lastNameLayout.getChildren().addAll(lastNameText, lastNameTextArea);
            lastNameLayout.setAlignment(javafx.geometry.Pos.CENTER);
            hireDateLayout.getChildren().addAll(hireDateText, hireDateTextArea);
            hireDateLayout.setAlignment(javafx.geometry.Pos.CENTER);
            availabilityLayout.getChildren().addAll(availabilityText, availabilityTextArea);
            availabilityLayout.setAlignment(javafx.geometry.Pos.CENTER);
            preferencesLayout.getChildren().addAll(preferencesText, preferencesTextArea);
            preferencesLayout.setAlignment(javafx.geometry.Pos.CENTER);
            priorityLayout.getChildren().addAll(priorityText, priorityTextArea);
            priorityLayout.setAlignment(javafx.geometry.Pos.CENTER);
            maxHoursLayout.getChildren().addAll(maxDesiredHoursText, maxDesiredHoursTextArea);
            maxHoursLayout.setAlignment(javafx.geometry.Pos.CENTER);
            maximumHoursLayout.getChildren().addAll(maxText, maxTextArea);
            maximumHoursLayout.setAlignment(javafx.geometry.Pos.CENTER);

            //Add the first and last name layouts to the main layout.
            VBox layout = new VBox(10);
            layout.getChildren().addAll(firstNameLayout, lastNameLayout, hireDateLayout, availabilityLayout, preferencesLayout, priorityLayout, maxHoursLayout, maximumHoursLayout);
            layout.setAlignment(javafx.geometry.Pos.CENTER);

            //A drop down menu to select the role of the employee.
            ArrayList<ChoiceBox> choiceBoxes = new ArrayList<>();
            if (employee != null && employee.getRoles() != null) {
                for (int i = 0; i < employee.getRoles().size(); i++){
                    ChoiceBox choiceBox = new ChoiceBox();
                    choiceBoxes.add(choiceBox);
                    choiceBoxesAddRoles(choiceBoxes.get(i));
                    choiceBoxes.get(i).setValue(employee.getRoles().get(i).getName());
                    tempRoles.add(employee.getRoles().get(i));
                    choiceBox.setOnAction(e -> choiceBoxEvent(choiceBox, choiceBoxes, tempRoles, layout));
                    choiceBox.setValue(employee.getRoles().get(i).getName());
                }
            }
            ChoiceBox choiceBox = new ChoiceBox();
            choiceBoxesAddRoles(choiceBox);
            choiceBoxes.add(choiceBox);
            choiceBox.setOnAction(e -> choiceBoxEvent(choiceBox, choiceBoxes, tempRoles, layout));

            //Add the choice box to the main layout.
            for (ChoiceBox choiceBox1 : choiceBoxes) {
                layout.getChildren().add(choiceBox1);
            }

            //Submit button to add the employee to the list and to close the sub menu after.
            Button submitButton = new Button("Submit");
            submitButton.setOnAction(e -> {
                String firstName = firstNameTextArea.getText();
                String lastName = lastNameTextArea.getText();
                String hireDateString = hireDateTextArea.getText();
                String availabilityString = availabilityTextArea.getText();
                String preferencesString = preferencesTextArea.getText();
                String priorityString = priorityTextArea.getText();
                String maxDesiredHoursString = maxDesiredHoursTextArea.getText();
                String maxString = maxTextArea.getText();

                //Convert the user inputted string into a hire date with the format MM/DD/YYYY
                LocalDate hireDate = checkString(hireDateString) ? LocalDate.parse(hireDateString, DateTimeFormatter.ofPattern("MM/dd/yyyy")) : LocalDate.of(1,1,1);
                //Convert a user inputted string into a LocalTime with the format HH:MM
                LocalTime availability = checkString(availabilityString) ? LocalTime.parse(availabilityString, DateTimeFormatter.ofPattern("HH:mm")) : LocalTime.of(0,0);
                LocalTime preferences = checkString(preferencesString) ? LocalTime.parse(preferencesString, DateTimeFormatter.ofPattern("HH:mm")) : LocalTime.of(0,0);
                int priority = checkString(priorityString) ? Integer.parseInt(priorityString) : -1;
                int maxDesiredHours = checkString(maxDesiredHoursString) ? Integer.parseInt(maxDesiredHoursString) : -1;
                int max = checkString(maxString) ? Integer.parseInt(maxString) : -1;


                //If the first or last name is blank, or the ID is already in the list, don't add the employee.
                if (firstName.equals("") || lastName.equals("")) {
                    System.out.println("First or last name is blank");
                    return;
                } else {
                    editEmployee(firstName, lastName, tempRoles, hireDate, availability, max);
                    subStage.close();
                    subMenuOpen = false;
                }
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
            Scene scene = new Scene(layout, 500, 630);
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

    private void editEmployee(String first, String last, ArrayList<Role> tempList, LocalDate hire, LocalTime availability, int maxHours){
        Employee employee = null;
        int id = tempId;
        if(sameIdEmployee(id) != -1){
            employee = employees.get(sameIdEmployee(id));
        }
        if (employee == null){
            System.out.println("Making new employee");
            employee = new Employee(first, last, assignId(), maxHours, hire, availability);
            employee.setRoles(tempList);
            employees.add(employee);
            System.out.print("Employee created: " + employee.getFirstName() + " " + employee.getLastName() + " with ID " + employee.getId() + " and roles ");
            for (Role role : employee.getRoles()){
                System.out.print(role.getName() + " ");
            }
            System.out.println();
        } else {
            employee.setFirstName(first);
            employee.setLastName(last);
            employee.setRoles(tempList);
            employee.setHireDate(hire);
            employee.setAvailability(availability);
            employee.setMaximumHours(maxHours);
        }
        showWeek(dayOffset);
    }

    //Checks if the ID is already in the list.
    public int sameIdEmployee(int id){
        for (int i = 0; i < employees.size(); i++){
            if (employees.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    private int assignId() {
        int newId = 0;
        for (Employee e : this.employees) {
            if(newId == e.getId()) {
                newId++;
            }
        }
        return newId;
    }

    private void roleStage(Role role){
        if (!subMenuOpen){
            tempId = -1;
            Stage subStage = new Stage();
            subStage.setTitle("Add Employee");

            //Layout for the sub menu inputs.
            HBox roleNameLayout = new HBox(10);

            //Text and text area for the first and last name.
            Text roleName = new Text("Role Name");
            TextArea roleNameTextArea = new TextArea();
            roleNameTextArea.setPrefHeight(20);
            roleNameTextArea.setPrefWidth(110);

            if (role != null){
                tempId = role.getId();
                roleNameTextArea.setText(role.getName());
            }

            //Add the text and text area together and centers them.
            roleNameLayout.getChildren().addAll(roleName, roleNameTextArea);
            roleNameLayout.setAlignment(javafx.geometry.Pos.CENTER);

            //Add the first and last name layouts to the main layout.
            VBox layout = new VBox(10);
            layout.getChildren().addAll(roleNameLayout);
            layout.setAlignment(javafx.geometry.Pos.CENTER);

            //Submit button to add the employee to the list and to close the sub menu after.
            Button submitButton = new Button("Submit");
            submitButton.setOnAction(e -> {
                String name = roleNameTextArea.getText();
                //If the first or last name is blank, or the ID is already in the list, don't add the employee.
                if (name.equals("")) {
                    System.out.println("First or last name is blank");
                    return;
                } else {
                    name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    roles.add(new Role(name, assignId()));
                    System.out.println("Role created: " + name);
                    subStage.close();
                    subMenuOpen = false;
                }
            });

            //add a delete button if the role is not null
            if (role != null){
                Button deleteButton = new Button("Delete");
                deleteButton.setOnAction(e -> {
                    roles.remove(role);
                    subStage.close();
                    subMenuOpen = false;
                    showWeek(0);
                });
                layout.getChildren().add(deleteButton);
            }

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

    private void choiceBoxesAddRoles(ChoiceBox choiceBox){
        for (Role role : roles){
            choiceBox.getItems().add(role.getName());
        }
        choiceBox.getItems().add("");
    }

    private boolean choiceBoxesIsDeleted(ChoiceBox choiceBox){
        //if choice equals blank then return true
        return choiceBox.getValue().equals("");
    }

    private Role getRole(String name){
        for (Role role : roles){
            if (role.getName().equals(name)){
                return role;
            }
        }
        return null;
    }

    private void choiceBoxEvent(ChoiceBox choiceBox, ArrayList<ChoiceBox> choiceBoxes, ArrayList<Role> tempRoles, VBox layout){
        if(choiceBox.getValue().equals("")){
            if(choiceBoxes.size() == 1){
                return;
            }
            tempRoles.remove(choiceBoxes.indexOf(choiceBox));
            choiceBoxes.remove(choiceBox);
            layout.getChildren().remove(choiceBox);
        } else {
            //if choice box changes, change the role in the tempRoles arraylist. if the choice box is the last one, add a new one.
            if(choiceBoxes.get(choiceBoxes.size() - 1).equals(choiceBox)){
                tempRoles.add(getRole((String) choiceBox.getValue()));
                ChoiceBox newChoiceBox = new ChoiceBox();
                choiceBoxesAddRoles(newChoiceBox);
                //get submit button and remove it from the layout and add it back to the layout so it is at the bottom.
                Button submitButton = (Button) layout.getChildren().get(layout.getChildren().size() - 1);
                layout.getChildren().remove(submitButton);
                layout.getChildren().add(newChoiceBox);
                layout.getChildren().add(submitButton);
                choiceBoxes.add(newChoiceBox);
                newChoiceBox.setOnAction(e -> choiceBoxEvent(newChoiceBox, choiceBoxes, tempRoles, layout));
            } else {
                tempRoles.set(choiceBoxes.indexOf(choiceBox), getRole((String) choiceBox.getValue()));
            }
        }
    }

    private void editRoleStage(){
        if (!subMenuOpen){
            tempId = -1;
            Stage subStage = new Stage();
            subStage.setTitle("Edit Role");
            VBox layout = new VBox(10);
            layout.setAlignment(javafx.geometry.Pos.CENTER);

            //Create Buttons for each of the roles
            for (Role role : roles){
                Button roleButton = new Button(role.getName());
                roleButton.setOnAction(e -> {
                    roleStage(role);
                    subStage.close();
                });
                layout.getChildren().add(roleButton);
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

    public boolean checkString(String string) {
        if (string == null || string.isBlank()) {
            return false;
        }
        return true;
    }
    public static Map<Shift, Employee> generateSchedule(List<Employee> employees, List<Shift> shifts) {
        Map<Shift, Employee> schedule = new HashMap<>();
        List<Employee> availableEmployees = new ArrayList<>(employees);
        Collections.sort(availableEmployees);

        for (Shift shift : shifts) {
            ArrayList<String> requiredRoles = shift.getneededRoles();
            boolean shiftFilled = false;

            for (Employee employee : availableEmployees) {
                if (employee.isAvailable(shift.getShiftDay(), shift.getStartTime(), shift.getEndTime())
                        && !(Collections.disjoint(employee.getRoles(), requiredRoles))
                        && employee.getRemainingHours() >= shift.shiftLength()) {
                    schedule.put(shift, employee);
                    employee.setRemainingHours(employee.getRemainingHours() - shift.shiftLength());
                    shiftFilled = true;
                    break;
                }
            }

            if (!shiftFilled) {
                System.out.println("Unable to fill shift: " + shift.getShiftDay() + " " + shift.getStartTime() + " " + shift.getEndTime());
            }
        }

        return schedule;
    }
    public static Availability createAvailability(DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        return new Availability(day, startTime, endTime);
    }
}

