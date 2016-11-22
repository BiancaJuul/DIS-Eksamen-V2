package sdk;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.ClientResponse;
import cryptor.Cryptor;
import model.Book;

import java.util.ArrayList;

/**
 * Created by biancajuul-hansen on 22/11/2016.
 */
public class Connection {

    /**
     * Used for getting all the users
     * Receiving a list of users
     * @return customized response
     */
    public static void getBooks() {
        ClientResponse clientResponse = HTTPrequests.get("book/1");

        if (clientResponse == null) {
            System.out.println("No sdk");
        } else {
            String json = clientResponse.getEntity(String.class);
            if (clientResponse.getStatus() != 200) {
                System.out.println("Server error");
                //JSONObject jsonObject = createJsonOpbject(json);
            } else {
                System.out.println(json);

                Book book = new Gson().fromJson(json, Book.class);
                System.out.println(book.getBookID());
//                ArrayList<Book> books = new Gson().fromJson(json, new TypeToken<ArrayList<Book>>(){}.getType());
//
//                for (Book book: books) {
//                    System.out.println(book.getTitle());
//                }
//                String decryptedJson = Cryptor.decryptResponse(json);
//                System.out.println(decryptedJson);
            }
        }
    }



    public static String encryptDecryptXOR(String input) {
        char[] key = {'D', 'E', 'F'}; //Dette kan være alle andre bogstaver end a,b og c.
        StringBuilder output = new StringBuilder();

        //For loop der scrambler den String, der bliver indtastet
        for (int i = 0; i < input.length(); i++) {
            output.append((char) (input.charAt(i) ^ key[i % key.length]));
        }

        return output.toString();
    }
}
