
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
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
public class Connection implements Runnable{

    Game game;
    
    private ObjectOutputStream outPutStream;
    private ObjectInputStream inPutStream;
    private boolean running;
    public Connection(Game game, Socket socket) throws IOException
    {
	this.game =  game;
	outPutStream = new ObjectOutputStream(socket.getOutputStream());
	inPutStream = new ObjectInputStream(socket.getInputStream());
	new Thread (this).start();
    }
    public void sendPacket(Object object) throws IOException
    {
	outPutStream.writeObject(object);
	outPutStream.flush();
    }

    @Override
    public void run() {
	running= true;
	while(running)
	{
	    try {
		Object object = inPutStream.readObject();
		game.packetRecieved(object);
	    } catch (EOFException | SocketException e) {
                running = false;
            } 
            catch (IOException ex) {
		Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (ClassNotFoundException ex) {
		Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
	    } 
	}
    }

        public void close() throws IOException
    {
	inPutStream.close();
	outPutStream.close();
    }
}
