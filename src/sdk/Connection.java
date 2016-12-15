package sdk;

import com.sun.jersey.api.client.*;


/**
 * Created by biancajuul-hansen on 22/11/2016.
 */

// I denne klasse laves request til serveren
public class Connection {

    static Client client = Client.create();


    //Denne metode er get, der henter både bøger og curriculums, fra vores server ved brug af server url'en
    public static ClientResponse get(String path) {

        ClientResponse clientResponse = null;
        try {
            WebResource webResource = client
                    .resource("http://localhost:8080/server2_0_war_exploded/")
                    .path(path);

            clientResponse = webResource
                    .accept("application/json")
                    .get(ClientResponse.class);
        } catch (UniformInterfaceException | ClientHandlerException e) {
            e.printStackTrace();
        }
        return clientResponse;
    }

    //Denne metode er post, der bruges til login og oprettelse af brugere og igen kalder ned til serveren
    public static ClientResponse post(String token, String path, String json) {
        ClientResponse clientResponse = null;
        try {
            WebResource webResource = client
                    .resource("http://localhost:8080/server2_0_war_exploded/")
                    .path(path);

            clientResponse = webResource
                    .accept("application/json")
                    .post(ClientResponse.class, json);

        } catch (UniformInterfaceException | ClientHandlerException e) {
            e.printStackTrace();
        }
        return clientResponse;
    }

    //Delete metoden her bruges til at slette den bruger der er logget ind, der blever derfor her sendt en token med
    public static ClientResponse delete(String token, String path) {

        ClientResponse clientResponse = null;
        try {
            WebResource webResource = client
                    .resource("http://localhost:8080/server2_0_war_exploded/")
                    .path(path);

            clientResponse = webResource
                    .accept("application/json")
                    .header("authorization", token)
                    .delete(ClientResponse.class);

        } catch (UniformInterfaceException | ClientHandlerException e) {
            e.printStackTrace();
        }
        return clientResponse;
    }

    //Put metoden bruges til opdatering af brugeren der er logget ind, som også her får sendt en token med for at validere brugeren
    public static ClientResponse put(String token, String path, String json) {

        ClientResponse clientResponse = null;
        try {
            WebResource webResource = client
                    .resource("http://localhost:8080/server2_0_war_exploded/")
                    .path(path);

            clientResponse = webResource
                    .accept("application/json")
                    .header("authorization", token)
                    .put(ClientResponse.class, json);
        } catch (UniformInterfaceException | ClientHandlerException e) {
            e.printStackTrace();
        }
        return clientResponse;
    }

}