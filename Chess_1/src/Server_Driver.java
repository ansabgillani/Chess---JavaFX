
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
public class Server_Driver {
    public static void main(String[] args) throws IOException {
        //int choice =  Integer.parseInt(JOptionPane.showInputDialog("0 for server | 1 for client"));
        ChessBoard test = new ChessBoard();
         test.setSize(700,700);
        test.setResizable(false);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.setVisible(true);
        
        RW_Server server = new RW_Server(4444);        
        ChessBoard board = new ChessBoard(server);
    }
}
