package controller;

import model.Book;
import model.Curriculum;
import model.User;
import sdk.HTTPrequests;
import view.MainView;

import java.util.ArrayList;

/**
 * Created by biancajuul-hansen on 23/11/2016.
 */

//Denne klasse er her klientens "logik" er, som brugeren indtaster i menuerne går her igennem og sendes så videre til HTTPRequest.java og efterfølgende til Connection.java, der så laver kaldet til serveren
public class Controller {
    private User currentUser;

    public Controller() {
        new MainView(this).showMenu();
    }

    public boolean authUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user = HTTPrequests.authorizeLogin(user);

        if (user != null) {
            currentUser = user;
            return true;
        } else
            return false;
    }

    public ArrayList<Book> getBooks() {
        return HTTPrequests.getBooks();
    }

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

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean updateUser(String firstName, String lastName, String username, String email) {

        currentUser.setFirstName(firstName);
        currentUser.setLastName(lastName);
        currentUser.setUsername(username);
        currentUser.setEmail(email);
        boolean updated = HTTPrequests.updateUser(currentUser);

        if (updated)
            currentUser = null;

        return updated;

    }

    public Book getBook(int bookId) {
        return HTTPrequests.getBook(bookId);
    }

    public boolean deleteUser() {
        boolean deleted = HTTPrequests.deleteUser(currentUser);
        if (deleted)
            currentUser = null;
        return deleted;
    }
}
