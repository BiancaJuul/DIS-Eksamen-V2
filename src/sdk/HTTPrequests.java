package sdk;

import Encrypters.Crypter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.ClientResponse;
import model.Book;
import model.Curriculum;
import model.User;

import java.util.ArrayList;

/**
 * Created by biancajuul-hansen on 22/11/2016.
 */

//I denne klasse krypterer vi, samt laver vores java om til Json og omvendt
public class HTTPrequests {

//Denne metode bruges til login, vi bruger derfor post metoden fra Connection.java og sender her til endpointet /user/login
    //Herefter laves en if else statement der giver svar på om der er forbindelse eller ikke. Ved status 200 vender den tilbage med json der laves om til java
    public static User authorizeLogin(User u) {

        String encryptedJson = Crypter.encryptDecryptXOR(new Gson().toJson(u));
        ClientResponse clientResponse = Connection.post(null, "/user/login", encryptedJson);

        User user = null;

        if (clientResponse == null) {
            System.out.println("no connection to server");
        } else {
            String response = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                user = new Gson().fromJson(response, User.class);
            }
        }
        return user;
    }

    //Forskellen på denne metode og den ovenfor er at den her henter bøger, derfor get metode og endpoint book
    public static ArrayList<Book> getBooks() {
        ClientResponse clientResponse = Connection.get("book/");
        ArrayList<Book> books = null;

        if (clientResponse == null) {
            System.out.println("no connection to server");
        } else {
            String encryptedJson = clientResponse.getEntity(String.class);

            if (clientResponse.getStatus() == 200) {
                String decryptedJson = Crypter.encryptDecryptXOR(encryptedJson);
                books = new Gson().fromJson(decryptedJson, new TypeToken<ArrayList<Book>>() {
                }.getType());
            } else {
                System.out.println("Server error");
            }
        }
        return books;
    }

    // Forskellen her er at man kun vælger en bog. Den bliver hentet ud fra et specifikt id. Get bliver derfor også brugt her, men endpointet er book/ + id
    public static Book getBook(int id) {
        ClientResponse clientResponse = Connection.get("book/" + id);
        Book book = null;

        if (clientResponse == null) {
            System.out.println("no connection to server");
        } else {
            String encryptedJson = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                String decryptedJson = Crypter.encryptDecryptXOR(encryptedJson);
                book = new Gson().fromJson(decryptedJson, Book.class);
            } else {
                System.out.println("Server error");
            }
        }
        return book;
    }

    //Denne er næsten ens med de andre, den henter blot uddannelserne
    public static ArrayList<Curriculum> getCurriculums() {
        ClientResponse clientResponse = Connection.get("/curriculum");
        ArrayList<Curriculum> curriculums = null;

        if (clientResponse == null) {
            System.out.println("no connection to server");
        } else {
            String encryptedJson = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                String decryptedJson = Crypter.encryptDecryptXOR(encryptedJson);
                curriculums = new Gson().fromJson(decryptedJson, new TypeToken<ArrayList<Curriculum>>() {
                }.getType());
            } else {
                System.out.println("Server error");
            }
        }
        return curriculums;
    }

    //Denne er også en get metode der henter bøgerne ud fra et bestemt curriculum id, som er en en bestemt uddannelse, brugeren vælger i consollen
    public static ArrayList<Book> getCurriculumBooks(int curriculumId) {
        ClientResponse clientResponse = Connection.get("/curriculum/" + curriculumId + "/books");
        ArrayList<Book> books = null;

        if (clientResponse == null) {
            System.out.println("no connection to server");
        } else {
            String encryptedJson = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                String decryptedJson = Crypter.encryptDecryptXOR(encryptedJson);
                books = new Gson().fromJson(decryptedJson, new TypeToken<ArrayList<Book>>() {
                }.getType());
            } else {
                System.out.println("Error");
            }
        }
        return books;
    }

    //Denne metode benytter post, da den opretter en ny bruger ellers er det det samme
    public static boolean createUser(User user) {
        String encryptedJson = Crypter.encryptDecryptXOR(new Gson().toJson(user));

        ClientResponse clientResponse = Connection.post(null, "/user/", encryptedJson);
        if (clientResponse == null) {
            System.out.println("no connection to server");
        } else {
            if (clientResponse.getStatus() == 200) {
                return true;
            }
        }
        return false;

    }

    //Her igen næsten det samme bortset fra at denne metode sletter brugeren der er logget ind, men sender også en token med da den skal validere brugeren ved brug af den samt brugrerens id
    public static boolean deleteUser(User currentUser) {
        ClientResponse clientResponse = Connection.delete(currentUser.getToken(), "/user/" + currentUser.getUserID());
        if (clientResponse == null) {
            System.out.println("no connection to server");
        } else {
            if (clientResponse.getStatus() == 200) {
                return true;
            }
        }
        return false;
    }

    //Denne ligner meget slet, den opdaterer bare brugeren i stedet for og benytter derfor put. Den fungerer på samme måde med token og id. Denne krypterer bare, det gør slet ikke
    public static boolean updateUser(User currentUser) {
        String encryptedJson = Crypter.encryptDecryptXOR(new Gson().toJson(currentUser));
        ClientResponse clientResponse = Connection.put(currentUser.getToken(), "/user/" + currentUser.getUserID(), encryptedJson);
        if (clientResponse == null) {
            System.out.println("no connection to server");
        } else {
            if (clientResponse.getStatus() == 200) {
                return true;
            }
        }
        return false;
    }
}
