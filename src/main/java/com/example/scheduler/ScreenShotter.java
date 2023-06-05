package com.example.scheduler;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ScreenShotter {
    private Robot robot; //responsible for handeling the screenshotting
    private Rectangle rect; //defines the resolution of the image being screenshot
    private BufferedImage screenshot; //the datatype holding the screenshot image
    private String homeDirectory; //the variable holding the users directory for the folder which holds all of the screenshots

    public ScreenShotter(){ //Constructor
        try {
            homeDirectory = System.getProperty("user.home"); //get user directory
            robot = new Robot(); //create robot for screenshotting
        }catch (Exception e){ //exception for if robot and/or home directory does not get intialized correctly
            e.printStackTrace();
        }
    }

    public void getScreen(){ //this function gets the size of the screen for screenshotting
        try {
            rect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()); //this intitalizes the rect object of this class
        }catch (Exception e){ //exception for if rect does not get intialized correctly
            e.printStackTrace();
        }
    } //end getScreen

    public void takeShot(){
        if (rect == null) throw new IllegalArgumentException("Screen was not recorded properly"); //NEED PRECONDITIONS
        else {
            try {
                screenshot = robot.createScreenCapture(rect); //robot takes screen capture of screen as defined by rect
            }catch (Exception e){ //exception for if screenshot fails
                e.printStackTrace();
            }
            rect = null;
        }
    } // end takeShot

    public void saveImage(){ //image is saved to directory
        if (screenshot == null) throw new IllegalArgumentException("Image was not created properly"); //NEED PRECONDITIONS
        // else{

        try{
            File theDir = new File(homeDirectory + "\\Pictures\\Schedules"); //creates folder in directory path
            if (!theDir.exists()){ //if the folder does not exist it will create a new folder
                theDir.mkdirs();
            }
            //the below line of code will make the file into a jpg file and save the buffered image to that file
            ImageIO.write(screenshot, "JPG", new File (homeDirectory + "\\Pictures\\Schedules\\screenshot.jpg")); 
        }catch (Exception e){ //if jpg image is not made correctly
            e.printStackTrace();
        }
        // }
    }

}
