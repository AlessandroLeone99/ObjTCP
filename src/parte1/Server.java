package parte1;
import java.io.*;
import java.net.*;
import java.util.*;


public class Server {
    
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    
    
    public Socket attendi(){
    
        try{
            System.out.println("Server in esecuzione");
            server = new ServerSocket(6789);
            client = server.accept();
            server.close();
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server !");
            System.exit(1);
        }
        
        return client;
    }
    
    public void ricevi(){
        try{
            stringaRicevuta= inDalClient.readLine();
            System.out.println("la stringa ricevuta è"+stringaRicevuta);
            
            /*
            outVersoClient.writeBytes(stringaRicevuta+"....");
            System.out.println("fine");*/
            client.close();
            System.out.println("Il client ha terminato"+client);
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione");
            System.exit(1);
        }
    }
    
    
    public static void main(String[] args){
        Server s = new Server();
        s.attendi();
        s.ricevi();
     
    }
}


