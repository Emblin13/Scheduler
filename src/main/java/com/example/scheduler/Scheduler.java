package com.example.scheduler;

import javafx.stage.Stage;

public class Scheduler {
    Stage stage;

    public Scheduler(){
        System.out.println("Scheduler created");
    }

    public void start(Stage stage){
        System.out.println("Scheduler started");
        this.stage = stage;
    }

}
