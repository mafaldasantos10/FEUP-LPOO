package lpoo312.Game;

import java.io.IOException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        try {
            String factory;
            do {
                System.out.println("How would you like to run FlappyBird? lpoo312.Swing (S) or lpoo312.Lanterna (L)?");
                Scanner in = new Scanner(System.in);
                factory = in.nextLine();
            } while (!factory.equals("S") && !factory.equals("s") && !factory.equals("S") && !factory.equals("l"));

            FlappyBird.setFactory(factory);
            FlappyBird.getInstance().run();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}