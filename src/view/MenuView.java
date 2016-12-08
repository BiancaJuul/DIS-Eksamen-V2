package view;

import Encrypters.Digester;
import controller.MainController;
import model.Book;
import model.Curriculum;
import model.User;
import model.UserLogin;
import sdk.Connection;
import sdk.HTTPrequests;
//import sdk.services.UserService;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by biancajuul-hansen on 24/11/2016.
 */
public class MenuView {

    private MainController mainController;
    private static Scanner input;


    public MenuView(MainController mainController) {
        this.mainController = mainController;
        input = new Scanner(System.in);
    }

    public void showMenu() {
  //  do {
        System.out.println("\nIndtast det ønskede tal, mellem 1-5");
        System.out.println("1) Print alle bøger ");
        System.out.println("2) Print priser på bestemt bog fra bestemt pensum ");
        System.out.println("3) Opdater bruger ");
        System.out.println("4) Slet bruger ");
        System.out.println("5) Log ud");

        switch (input.nextInt()) {
            case 1:
                printBooks();
                showMenu();
                break;
            case 2:
                printCurriculumList();
                showMenu();
                break;
            case 3:
                updateUser();
                break;
            case 4:
                deleteUser();
                break;
            case 5:
                mainController.setCurrentUser(null);
                System.out.println("Du er nu logget ud\n------------------------------------------------------------------------");
                break;
            default:
                System.out.println("Indtast det ønskede tal, mellem 1-5\n------------------------------------------------------------------------");
                showMenu();
                break;
        }
   // }while (true);
    }


    public void printBooks() {
        ArrayList<Book> books = mainController.getBooks();
        System.out.println("\n Information om alle de aktuelle bøger:");
        System.out.println("(Hvis du ønsker at se priserne, da vælg punkt 2 i hovedmenuen)\n");
        for (Book book : books) {
            System.out.println("Titel: \t \t \t" + book.getTitle() + "\nForfatter: \t \t" + book.getAuthor() + "\nForlag: \t \t" + book.getPublisher() + "\nUdgave: \t \t" + book.getVersion() + "\nISBN nummer: \t" + book.getISBN() + "\n------------------------------------------------------------------------");
        }
    }

    public void printBook() {
        System.out.println("\nIndtast id på den bog du ønsker at se priserne på:");
        Book book = HTTPrequests.getBook(input.nextInt());
        System.out.println("Titel: \t \t \t \t \t \t" + book.getTitle() + "\nPris hos SAXO: \t \t \t \t" + book.getPriceSAXO() + "  Kr." + "\nPris hos Academic Books: \t" + book.getPriceAB() + "  Kr." + "\nPris hos CDON: \t \t \t \t" + book.getPriceCDON() + "  Kr.\n------------------------------------------------------------------------");
    }

    public void printCurriculumList() {

        ArrayList<Curriculum> curriculums = mainController.getCurriculumList();
        System.out.println("\nAktuelle pensumlister:");
        for (Curriculum curriculum : curriculums) {
            System.out.println("Id: \t \t" + curriculum.getCurriculumID() + "\nSkole: \t \t" + curriculum.getSchool() + "\nLinje: \t \t" + curriculum.getEducation() + "\nSemester: \t" + curriculum.getSemester() + "\n------------------------------------------------------------------------");
        }

        int foundCurriculum;

        do {
            System.out.println("Indtast id på dit semester her: ");
            while (!input.hasNext()) {
                System.out.println("Fejl!");
                input.next();
            }

            foundCurriculum = input.nextInt();
        }
        while (foundCurriculum <= 0 || foundCurriculum > curriculums.size());

        ArrayList<Book> curriculumBooks = HTTPrequests.getCurriculumBooks(foundCurriculum);
        System.out.println("Bøger på dit semester:");

        for (Book book : curriculumBooks) {
            System.out.println("Id: \t \t \t" + book.getBookID() + "\nTitel: \t \t \t" + book.getTitle() + "\nForfatter: \t \t" + book.getAuthor() + "\nForlag: \t \t" + book.getPublisher() + "\nUdgave: \t \t" + book.getVersion() + "\nISBN nummer: \t" + book.getISBN() + "\n------------------------------------------------------------------------");

        }
        printBook();
    }

    public void deleteUser() {

        boolean deleted = HTTPrequests.deleteUser(mainController.getCurrentUser().getUserID());
        if (deleted) {
            mainController.setCurrentUser(null);
        //    System.out.println("Brugeren er nu slettet\n------------------------------------------------------------------------");
        } else {
            System.out.println("Fejl! Brugeren blev ikke slettet\n------------------------------------------------------------------------");
        }
    }

    public void updateUser() {
        input.nextLine();

        String firstName, lastName, username, email;

        System.out.println("Opdater bruger: ");
        System.out.println("Indtast fornavn: ");
        firstName = input.nextLine();

        System.out.println("Indtast efternavn: ");
        lastName = input.nextLine();

        System.out.println("Indtast brugernavn: ");
        username = input.nextLine();

   //     System.out.println("Indtast kodeord: ");
   //     password = input.nextLine();

        System.out.println("Indtast email adresse: ");
        email = input.nextLine();

      //  String hashedPassword = Digester.hashWithSalt(password);

        boolean updated = mainController.updateUser(firstName, lastName, username, email);


        if(updated)
            mainController.setCurrentUser(null);
            System.out.println("\nDin profil er opdateret - du kan nu logge ind\n------------------------------------------------------------------------");


//        else
//            System.out.println("Opretelsen fejlede");


    }
}

//    public void printBook(){
//        System.out.println("Indast id på den ønskede bog");
//        Book book = MainController.getBook(input.nextInt());
//        System.out.println("id: " + book.getBookID() + " title: " + book.getTitle());
//    }



