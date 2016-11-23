import controller.MainController;
import sdk.Connection;
import sdk.Config;

public class Main {

    public static void main(String[] args) {
        Config.initConfig();

        new MainController().menu();

    }
}
