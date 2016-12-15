package view;

import controller.Controller;

import java.util.Scanner;

/**
 * Created by biancajuul-hansen on 29/11/2016.
 */

//Denne klasse er det første view brugeren får
public class MainView {
    Controller controller;
    MenuView menuView;
    private Scanner input;

    public MainView(Controller controller) {
        this.controller = controller;
        menuView = new MenuView(controller);
        input = new Scanner(System.in);
    }

    //Brugeren får nedenfor muligheden for at logge ind eller oprette brugeren ellers vil jeg ikke gå resten af denne klasse, da koden er ret basic
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
        } while (true);

    }

    private void loginMenu() {
        input.nextLine();

        String username, password;
        System.out.println("\nVelkommen til login");
        System.out.println("Indtast brugernavn her:");
        username = input.nextLine();
        System.out.println("Indtast kodeord her:");
        password = input.nextLine();


        boolean authUser = controller.authUser(username, password);

        if (authUser)
            menuView.showMenu();
        else
            System.out.println("\nDet indtastede brugernavn eller kodeord er forkert\n------------------------------------------------------------------------");

    }

    private void createUser() {
        input.nextLine();

        String firstName, lastName, username, password, email;

        System.out.println("\nOprettelse af ny bruger:");
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

        boolean created = controller.createUser(firstName, lastName, username, password, email);

        if (created)
            System.out.println("Din profil er oprettet - du kan nu logge ind med den ny oprettet bruger\n------------------------------------------------------------------------\n");
        else
            System.out.println("Opretelsen fejlede\n------------------------------------------------------------------------\n");

    }
}
