package sdk;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;

/**
 * Created by biancajuul-hansen on 17/11/2016.
 */

//Når programmet køres i Main.java, bliver programmet sendt hertil og starter op, ved JsonParser, der sender videre til Config.json, hvor serverurl'en ligger
public class Config {
    private static String serverUrl;

    public static JsonObject initConfig() {


        JsonObject json = new JsonObject();

        try {
            JsonParser parserJ = new JsonParser();
            json = (JsonObject) parserJ.parse(new FileReader("src/sdk/config.json"));


        } catch (Exception e) {
            e.printStackTrace();

        }

        return json;
    }


    public static String getServerUrl() {
        return serverUrl;
    }

    public static void setServerUrl(String serverUrl) {
        Config.serverUrl = serverUrl;
    }

}
