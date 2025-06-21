//import py4j.GatewayServer;
import Table.QueryService; 

public class Main {
    public static void main(String[] args) {
     
        QueryService queryService = new QueryService();

   
        GatewayServer gatewayServer = new GatewayServer(queryService);
        gatewayServer.start();
        System.out.println("Gateway Server Started at port 25333");
        System.out.println("Java Query Service ready for Python connection.");
    }
}