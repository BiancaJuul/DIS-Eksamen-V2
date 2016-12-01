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
        //  this.bookView = new BookView(this);
        //  this.userView = new UserView(this);
    }

    public BookView getBookView() {
        return bookView;
    }

    public UserView getUserView() {
        return userView;
    }


    public boolean authUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
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
}


//    public ArrayList<Book> getBook (){
//        return HTTPrequests.getBook();
//    }

//    public ArrayList<Book> getCurriculum(String school, String education, int semester) {
//        ArrayList <Curriculum>();
//        Curriculum foundCurriculum = null;
//
//
//        for (Curriculum curriculum : curriculums) {
//            if (curriculum.getSchool().equalsIgnoreCase("CBS") && curriculum.getEducation().equalsIgnoreCase(education) && curriculum.getSemester() == semester){
//                foundCurriculum = curriculum;
//            }
//        }
//
//        if(foundCurriculum != null)
//            return HTTPrequests.getCurriculumBooks(foundCurriculum.getCurriculumID())
//            else return null;
//    }


