// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.







import ScreenShot.ScreenShotter;
public class Main {
    public static void main(String[] args) {
    ScreenShotter screenShotter = new ScreenShotter();
    screenShotter.getScreen();
    screenShotter.takeShot();
    screenShotter.saveImage();
    }
}