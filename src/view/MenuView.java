package view;

import controller.MainController;
import model.Book;
import sdk.Connection;
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
        System.out.println("1) Print alle bøger ");
        System.out.println("2) Print pensumliste ");

        switch (input.nextInt()){
            case 1:
                printBooks();
                break;

            case 2:
                break;

        }
    }

    public void printBooks(){
        ArrayList<Book> books = mainController.getBooks();
        System.out.println("Print alle bøger:");
        for (Book book : books) {
            System.out.println("id: " + book.getBookID() + " title: " + book.getTitle());
        }
    }

//    public void printBook(){
//        System.out.println("Indast id på den ønskede bog");
//        Book book = MainController.getBook(input.nextInt());
//        System.out.println("id: " + book.getBookID() + " title: " + book.getTitle());
//    }




//        System.out.println("Venligst login:");
//        System.out.println("Indtast brugernavn");
//     //   username = input.nextLine(); //hvorfor skal den have to?!?!
//        String username = new Scanner(System.in).nextLine();
//        System.out.println("Indtast kodeord");
//        String password = new Scanner(System.in).nextLine();
//
//        this.userService.login()


    }


