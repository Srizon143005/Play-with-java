package simpleclient;
//SimpleClient.java: A simple client program
import java.net.*;
import java.io.*;
import java.util.Scanner;
public class SimpleClient {
    public static void main(String[] args) throws IOException {
        while(true){
            //Open your connection to a server, at port 1254
            Socket s1 = new Socket("localhost", 1254);

            //Get an input file handle from the socket and read the input
            InputStream s1In = s1.getInputStream();
            DataInputStream dis = new DataInputStream(s1In);
            String st = new String(dis.readUTF());
            System.out.println(st);
            
            //When done, just close the connection and exit
            dis.close();
            s1In.close();
            s1.close();
            
            
            //Register service on port 1254
            ServerSocket s = new ServerSocket(1254);
            Socket s2 = s.accept(); //Wait and accept a connection

            //Get a communication stream associated with the socket
            OutputStream s2out = s2.getOutputStream();
            DataOutputStream dos = new DataOutputStream(s2out);

            // Send a string!
            Scanner scanner = new Scanner( System.in );
            System.out.print( "You: " );
            String input = scanner.nextLine();
            
            dos.writeUTF("Client: " + input); 
            
            //Close the connection, but not the server socket
            dos.close();
            s2out.close();
            s2.close();
            s.close();
            
            if("Goodbye".equals(input)){
               break;
            }
        }
    }
    
}
