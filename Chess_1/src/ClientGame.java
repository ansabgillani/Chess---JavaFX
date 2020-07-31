
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sheikh Hashir
 */
public class ClientGame extends Game{

    private Connection connection;
    
    private Socket socket;
    public ClientGame() throws IOException
    { 
	socket =  new Socket("localhost", Game.PORT);
	connection =  new Connection(this, socket);
    }
    @Override
    public void input(Cell press, int x, int y) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() 
    {
	try {
	    connection.close();
	    socket.close();
	} catch (IOException ex) {
	    Logger.getLogger(ClientGame.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    @Override
    public void packetRecieved(Object object) {
        
        if(object instanceof UpdatePacket)
        {
            UpdatePacket packet = (UpdatePacket) object;
            ChessBoard.squares = packet.getFields();
        }

    }
    
}

