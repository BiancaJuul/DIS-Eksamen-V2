import controller.Controller;
import sdk.Config;

//Her magien sker, der får programmet til at virke. Den sendes videre til Config.java
public class Main {

    public static void main(String[] args) {
        Config.initConfig();
        new Controller();

    }
}

