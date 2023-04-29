package com.example.scheduler;

import javafx.stage.Stage;

public class Menu {

    Stage stage;

    public Menu(){
        System.out.println("Menu created");
    }

    public void start(Stage stage){
        System.out.println("Menu started");
        this.stage = stage;
        stage.show();
    }


}
