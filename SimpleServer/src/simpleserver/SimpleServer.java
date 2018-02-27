package simpleserver;
import java.io.*;
import java.net.*;
import java.util.Scanner;
// SimpleServer.java: A simple server program
public class SimpleServer {
    public static void main(String[] args) throws IOException{
        while(true){
            //Register service on port 1254
            ServerSocket s = new ServerSocket(1254);
            Socket s1 = s.accept(); //Wait and accept a connection

            //Get a communication stream associated with the socket
            OutputStream s1out = s1.getOutputStream();
            DataOutputStream dos = new DataOutputStream(s1out);

            // Send a string!
            Scanner scanner = new Scanner( System.in );
            System.out.print( "You: " );
            String input = scanner.nextLine();
            
            dos.writeUTF("Server: " + input); 
            
            //Close the connection, but not the server socket
            dos.close();
            s1out.close();
            s1.close();
            s.close();
            
            
            //Open your connection to a server, at port 1254
            Socket s2 = new Socket("localhost", 1254);

            //Get an input file handle from the socket and read the input
            InputStream s2In = s2.getInputStream();
            DataInputStream dis = new DataInputStream(s2In);
            String st = new String(dis.readUTF());
            System.out.println(st);
            
            //When done, just close the connection and exit
            dis.close();
            s2In.close();
            s2.close();
            
            if("Client: Goodbye".equals(st)){
                break;
            }
        }
    }
    
}
