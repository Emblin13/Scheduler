package com.example.scheduler;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    public Stage stage;// = new Stage();
    public static File toRead = new File("temp.txt");

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Hello world");

        this.stage = primaryStage;
        Menu menu = new Menu();
        menu.start(stage);
    }

}