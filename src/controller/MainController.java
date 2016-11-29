package controller;

import model.Book;
import model.User;
import sdk.HTTPrequests;
import view.BookView;
import view.MainView;
import view.UserView;

import java.util.ArrayList;

/**
 * Created by biancajuul-hansen on 23/11/2016.
 */
public class MainController {
    private BookView bookView;
    private UserView userView;
    private User currentUser;

    public MainController(){
        new MainView(this).showMenu();
      //  this.bookView = new BookView(this);
      //  this.userView = new UserView(this);
    }


//    public BookView getBookView() {
//        return bookView;
//    }
//
//    public UserView getUserView() {
//        return userView;
//
    public boolean authUser (String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User userResponse = HTTPrequests.authorizeLogin(user);

        if (userResponse != null){
            currentUser = userResponse;
            return true;
        }
        else
            return false;
    }
    public ArrayList<Book> getBooks(){
        return HTTPrequests.getBooks();
    }
}

//    private static Scanner input;
//    //Scanner input;
//
//    public MainController() {
//        input = new Scanner(System.in);
//    }
//
//    public void mainMenu() {
//
//        System.out.println("Velkommen til Bookit");
//        System.out.println("");
//        System.out.println("Indtast 1 eller 2");
//        System.out.println("1) Login");
//        System.out.println("2) Opret bruger");
//        switch (input.nextInt()) {
//            case 1:
//                MainController.userMenu();
//                break;
//            case 2:
//               // MainController.userMenu();
//                break;
//            default:
//                System.out.println("Indtast enten 1 eller 2");
//                break;
//        }
//    }
//
//    public static void userMenu() {
//        String username, password;
//        System.out.println("Velkommen til login");
//        System.out.println("");
//        System.out.println("Indtast brugernavn");
//        username = input.nextLine(); //hvorfor skal den have to?!?!
//        username = input.nextLine();
//        System.out.println("Indtast kodeord");
//        password = input.nextLine();
//
//        String token = Connection.authorizeLogin(username, password);
//        if (token != null) {
//            do {
//                System.out.println("");
//                System.out.println("1) Print en bog");
//                System.out.println("2) Print alle bøger");
//                switch (input.nextInt()) {
//                    case 1:
//                        printBook();
//                        break;
//                    case 2:
//                        printBooks();
//                        break;
//                    default:
//                        System.out.println("Indtast enten 1 eller 2");
//                }
//            } while (true);//Brug noget andet en true. CurrentUser != null
//        } else {
//            System.out.println("Brugernavn og/eller kodeord er forkert");
//        }
//    }
//
//
//    public static void printBooks() {
//        ArrayList<Book> books = Connection.getBooks();
//        System.out.println("Bøger i Biancas bogklub");
//        for (Book book : books) {
//            System.out.println("ID: " + book.getBookID() + " Titel: " + book.getTitle() + " Version: " + book.getVersion() + " ISBN: "
//                    + book.getISBN() + " Forfatter: " + book.getAuthor() + " Forlag: " + book.getPublisher() + " Pris AB: "
//                    + book.getPriceAB() + " Pris CDON: " + book.getPriceCDON() + " Pris SAXO: " + book.getPriceSAXO());
//        }
//    }
//
//    public static void printBook() {
//        ArrayList<Book> books = Connection.getBooks();
//        System.out.println("Bøger i Biancas bogklub");
//        for (Book book : books) {
//            System.out.println("ID: " + book.getBookID() + " Titel: " + book.getTitle() + " Version: " + book.getVersion() + " ISBN: "
//                    + book.getISBN() + " Forfatter: " + book.getAuthor() + " Forlag: " + book.getPublisher() + " Pris AB: "
//                    + book.getPriceAB() + " Pris CDON: " + book.getPriceCDON() + " Pris SAXO: " + book.getPriceSAXO());
//      //      System.out.println("Indast id på den ønskede bog");
////            book = Connection.getBook(input.nextInt());
////            System.out.println("id: " + book.getBookID() + " title: " + book.getTitle());
//        }
//    }
//}
