package sdk;

import com.sun.jersey.api.client.*;

/**
 * Created by biancajuul-hansen on 22/11/2016.
 */
public class HTTPrequests {

    static Client client = Client.create();

    /**
     * Used to get requests to the server
     * @param path the specific path
     * @return
     */
    public static ClientResponse get(String path) {

        ClientResponse clientResponse = null;
        try {
            WebResource webResource = client
                    .resource("http://localhost:8080/server2_0_war_exploded/")
//                    .resource(Config.getServerUrl())//det kommer fra config
                    .path(path); //book

            clientResponse = webResource.accept("application/json").get(ClientResponse.class);
        } catch (UniformInterfaceException | ClientHandlerException e) {
            e.printStackTrace();
        }
        return clientResponse;
    }
}
