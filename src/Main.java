import sdk.Connection;
import sdk.Config;

public class Main {

    public static void main(String[] args) {
        Config.initConfig();

        System.out.println("Hello World!");
        Connection.getBooks();
    }
}
