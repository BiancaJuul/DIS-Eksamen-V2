package view;

import Encrypters.Digester;
import com.google.gson.JsonObject;
import controller.MainController;
import jdk.nashorn.internal.runtime.PropertyMap;

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
        do {
            System.out.println("Velkommen til Bookit\n");
            System.out.println("Indtast 1 eller 2");
            System.out.println("1) Login");
            System.out.println("2) Opret bruger");

            switch (input.nextInt()) {
                case 1:
                    loginMenu();

                    break;
                case 2:
                    createUser();

                    break;
                default:
                    System.out.println("Venligst indtast 1 eller 2\n------------------------------------------------------------------------");
                    break;
            }
        }while(true);

    }

    private void loginMenu() {
        input.nextLine();

        String username, password;
        System.out.println("\nVelkommen til login");
        System.out.println("Indtast brugernavn her:");
        username = input.nextLine();
        System.out.println("Indtast kodeord her:");
        password = input.nextLine();

        String hashedPassword = Digester.hashWithSalt(password);

        boolean authUser = mainController.authUser(username, hashedPassword);

        if (authUser)
            menuView.showMenu();
        else
            System.out.println("\nDet indtastede brugernavn eller kodeord er forkert\n------------------------------------------------------------------------");

    }

    private void createUser() {
        input.nextLine();

        String firstName, lastName, username, password, email;

        System.out.println("Opret ny bruger: ");
        System.out.println("Indtast fornavn: ");
        firstName = input.nextLine();

        System.out.println("Indtast efternavn: ");
        lastName = input.nextLine();

        System.out.println("Indtast brugernavn: ");
        username = input.nextLine();

        System.out.println("Indtast kodeord: ");
        password = input.nextLine();

        System.out.println("Indtast email adresse: ");
        email = input.nextLine();

//        System.out.println("Usertype");
//        usertype = input.nextLine();

        boolean created = mainController.createUser(firstName, lastName, username, Digester.hashWithSalt(password), email);

        if(created)
            System.out.println("Din profil er oprettet - du kan nu logge ind med den opdateret bruger\n------------------------------------------------------------------------");
        else
            System.out.println("Opretelsen fejlede\n------------------------------------------------------------------------");

    }
}
