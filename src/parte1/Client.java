package parte1;
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Client {
    
    String nomeServer = "localhost";
    int portaServer = 6789;
    Socket miosocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;
    
    
    public Socket connetti(){
    System.out.println("Client in esecuzione");
        try{
        tastiera = new BufferedReader(new InputStreamReader(System.in));
        miosocket = new Socket(nomeServer,portaServer);
        outVersoServer = new DataOutputStream(miosocket.getOutputStream());
        inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
        }catch(UnknownHostException e){
            System.err.println("Host sconosciuto");
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione");
            System.exit(1);
        }
        return miosocket;
    }
    
    public void comunica(String s){
        try{
            //System.out.println("inserisci la stringa da trasmettere: ");
            //stringaUtente= tastiera.readLine();
            
            System.out.println("invio al server e attendo");
            //outVersoServer.writeBytes(stringaUtente);
            outVersoServer.writeBytes(s);
            /*stringaRicevutaDalServer= inDalServer.readLine();*/
            System.out.println("Il client termina");
            miosocket.close();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione");
            System.exit(1);
        }
    }
    
    
    
    public static void main(String[] args){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        
        Client c = new Client();
        c.connetti();
        
        c.comunica(dtf.format(now));
     
    }
}
