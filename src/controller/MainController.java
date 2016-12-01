package controller;

import model.Book;
import model.Curriculum;
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

    public MainController() {
        new MainView(this).showMenu();
    }

    public boolean authUser(String username, String hashedPassword) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        User userResponse = HTTPrequests.authorizeLogin(user);

        if (userResponse != null) {
            currentUser = userResponse;
            return true;
        } else
            return false;
    }

    public ArrayList<Book> getBooks() {
        return HTTPrequests.getBooks();
    }
//    public ArrayList<Book> getBook() {
//        return HTTPrequests.getBook();
//
//    }

    public ArrayList<Curriculum> getCurriculumList() {
        return HTTPrequests.getCurriculums();
    }

    public boolean createUser(String firstName, String lastName, String username, String password, String email) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        return HTTPrequests.createUser(user);
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    public void deleteUser (){


    }
}



