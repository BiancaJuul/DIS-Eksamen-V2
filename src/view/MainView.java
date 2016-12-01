package view;

import controller.MainController;

import java.util.Scanner;

/**
 * Created by biancajuul-hansen on 29/11/2016.
 */
public class MainView {
    MainController mainController;
    MenuView menuView;
    private Scanner input;

    public MainView(MainController mainController) {
        this.mainController = mainController;
        menuView = new MenuView(mainController);
        input = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("Velkommen til Bookit\n");
        System.out.println("Indtast 1 eller 2");
        System.out.println("1) Login");
        System.out.println("2) Opret bruger");

        switch (input.nextInt()) {
            case 1:
                boolean authUser = loginMenu();

                if (authUser)
                    menuView.showMenu();
                else
                    System.out.println("Det indtastede brugernavn eller kodeord er forkert");
                break;
            case 2:
                System.out.println("Opret ny bruger: ");
                break;
            default:
                System.out.println("Venligst indtast 1 eller 2");
                break;
        }
    }

    private boolean loginMenu() {
        input.nextLine();

        String username, password;
        System.out.println("\nVelkommen til login");
        System.out.println("Indtast brugernavn her:");
        username = input.nextLine();
        System.out.println("Indtast kodeord her:");
        password = input.nextLine();

        return mainController.authUser(username, password);

    }
}
