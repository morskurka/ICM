package server;// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import entities.ChangeInitiator;
import entities.ChangeRequest;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

import java.io.IOException;
import java.util.List;

/**
 * This class overrides some of the methods in the abstract
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class EchoServer extends AbstractServer {
    //Class variables *************************************************

    private DBConnection dbConnection;

    //Constructors ****************************************************

    /**
     * Constructs an instance of the echo server.
     *
     * @param port The port number to connect on.
     */
    public EchoServer(int port, String url, String username, String password) {
        super(port);
        dbConnection = new DBConnection(url, username, password);
    }


    //Instance methods ************************************************

    /**
     * This method handles any messages received from the client.
     *
     * @param msg    The message received from the client.
     * @param client The connection from which the message originated.
     */
    public void handleMessageFromClient(Object msg, ConnectionToClient client) {

        System.out.println("Message received: " + msg + " from " + client);
        // extract the requested service from the server
        ServerService serverService = (ServerService) msg;

        switch (serverService.getDatabaseService()) {

            case Login:
                System.out.println("server received login request for: " + serverService.getParams());

                List<ChangeInitiator> loginRes = dbConnection.login(serverService.getParams());
                serverService.setParams(loginRes);
                try {
                    client.sendToClient(serverService);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            case Get_All_Requests:
                System.out.println("server handle Get_All_Requests_New");
                // pass the request to the database
                List<List<ChangeRequest>> allRequests;
                allRequests = dbConnection.getAllRequests(serverService.getParams());
                serverService.setParams(allRequests);
                try {
                    // pass the result back to client controller
                    client.sendToClient(serverService);
                    System.out.println("sent back to client controller");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case Get_Request_Details:
                System.out.println("server handle Get_Request_Details");
                List<Integer> crParams = serverService.getParams();

                List<ChangeRequest> crList = dbConnection.getRequestDetails(crParams);
                System.out.println("Get_Request_Details server got data");
                serverService.setParams(crList);
                try {
                    client.sendToClient(serverService);
                    System.out.println("sent request details to client");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }


    /**
     * This method overrides the one in the superclass.  Called
     * when the server starts listening for connections.
     */
    protected void serverStarted() {
        System.out.println
                ("Server listening for connections on port " + getPort());
    }

    /**
     * This method overrides the one in the superclass.  Called
     * when the server stops listening for connections.
     */
    protected void serverStopped() {
        System.out.println
                ("Server has stopped listening for connections.");
    }

}
