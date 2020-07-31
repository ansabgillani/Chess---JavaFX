
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sheikh Hashir
 */
public abstract class Game 
{
    ChessBoard chess; 
    public static final int PORT = 55555;
    public boolean Game_Win = false;
    protected int thisPlayer;
    int turn;
    public Game() throws NullPointerException, IOException
    {
	System.out.println("initializing");
	chess =  new ChessBoard();
        chess.setSize(700, 700);
	chess.setVisible(true);
	chess.setResizable(false);	
        turn = thisPlayer;
    }
    public void changeTurn()
    {
        if(Game_Win==false)
        {
            if(turn == 0)
            {
                    turn = 1;
//                    textField.setText("Friends Move");
            }
            else{
                    turn = 0;
  //                  textField.setText("Your Turn");
            }
        }        
    }
    public abstract void input(Cell press,int x, int y);
    public abstract void packetRecieved(Object object);
    public abstract void close();
}
