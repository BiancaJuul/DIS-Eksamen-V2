package sdk;

import Encrypters.Crypter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.ClientResponse;
import model.Book;
import model.UserLogin;

import java.util.ArrayList;

/**
 * Created by biancajuul-hansen on 22/11/2016.
 */
public class Connection {



    public static String authorizeLogin(String username, String password) {
        UserLogin userLogin = new UserLogin(username, password);
        ClientResponse clientResponse = HTTPrequests.post(null, "/user/login", new Gson().toJson(userLogin));
        String token = null;

        if (clientResponse == null) {
            System.out.println("no connection to server");
        } else {
            String json = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() == 200) {
                token = json;
            } else {
                System.out.println("Better luck next time");
            }
        }
        return token;
    }

    public static ArrayList<Book> getBooks() {
        ClientResponse clientResponse = HTTPrequests.get("book/");
        ArrayList<Book> books = null;

        if (clientResponse == null) {
            System.out.println("No sdk");
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

    public static Book getBook(int id) {
        ClientResponse clientResponse = HTTPrequests.get("book/" + id);
        Book book = null;

        if (clientResponse == null) {
            System.out.println("No sdk");
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


}
