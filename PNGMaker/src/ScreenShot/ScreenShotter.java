package ScreenShot;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ScreenShotter {
    private Robot robot;
    private Rectangle rect;
    private BufferedImage screenshot;
    private String homeDirectory;

    public ScreenShotter(){ //Constructor
        try {
            homeDirectory = System.getProperty("user.home");
            robot = new Robot();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getScreen(){
        try {
            rect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        }catch (Exception e){
            e.printStackTrace();
        }
    } //end getScreen

    public void takeShot(){
        if (rect == null) throw new IllegalArgumentException("Screen was not recorded properly"); //NEED PRECONDITIONS
        else {
            try {
                screenshot = robot.createScreenCapture(rect);
            }catch (Exception e){
                e.printStackTrace();
            }
            rect = null;
        }
    } // end takeShot

    public void saveImage(){
        if (screenshot == null) throw new IllegalArgumentException("Image was not created properly"); //NEED PRECONDITIONS
        // else{

        try{
            File theDir = new File(homeDirectory + "\\Pictures\\Schedules");
            if (!theDir.exists()){
                theDir.mkdirs();
            }
            ImageIO.write(screenshot, "JPG", new File (homeDirectory + "\\Pictures\\Schedules\\screenshot.jpg")); // need to change destination
        }catch (Exception e){
            e.printStackTrace();
        }
        // }
    }

}
