
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RW_Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    
    public String receiveData() throws IOException {
        String line = in.readUTF();
        return line;
    }
    public void close() throws IOException {
        System.out.println("closing connecrtion");
        in.close();
        socket.close();
    }
    public RW_Server(int port)
    {
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("server ready at "+port);
            
            socket = serverSocket.accept();
            System.out.println("connection established");
            
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
