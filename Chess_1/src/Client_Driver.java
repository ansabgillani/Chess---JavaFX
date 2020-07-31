
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sheikh Hashir
 */
public class Client_Driver {
    public static void main(String[] args) throws IOException {
        //int choice =  Integer.parseInt(JOptionPane.showInputDialog("0 for server | 1 for client"));

        RW_Client client = new RW_Client("127.0.0.1", 4444);

        ChessBoard test = new ChessBoard(client);
        test.setSize(700,700);
        test.setResizable(false);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.setVisible(true);        
    }
}
