
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RW_Client {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    
    public void sendData(String line) throws IOException {
        out.writeUTF(line);    
    }
    public void close() throws IOException {
        out.close();
        socket.close();    
    }
    public RW_Client(String address,int port)
    {
        try{
            socket = new Socket(address, port);
            System.out.println("connected");
            //sending data out to server using socket stream
            out = new DataOutputStream(socket.getOutputStream());

            String line = "";
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
}