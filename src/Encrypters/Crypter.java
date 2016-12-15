package Encrypters;

/**
 * Created by Tastum on 17/10/2016.
 */

// Denne klasse er kopieret fra vores server og er her vores XOR krypterings metode ligger
public class Crypter {

    public static String encryptDecryptXOR(String input) {
        char[] key = {'D', 'E', 'F'}; //Dette kan være alle andre bogstaver end a,b og c.
        StringBuilder output = new StringBuilder();

        //For loop der scrambler den String, der bliver indtastet
        for (int i = 0; i < input.length(); i++) {
            output.append((char) (input.charAt(i) ^ key[i % key.length]));
        }
        //return input;
        return output.toString();
    }

}
