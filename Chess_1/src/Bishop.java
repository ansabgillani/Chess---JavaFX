
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sheikh Hashir
 */
public class Bishop extends Piece
{
    ArrayList<Cell> possibleMoves = new ArrayList<Cell>();
    public Bishop(int color, String path, String id)
    {
        setColor(color);
        setPath(path);
        setId(id);
    }
    @Override
    public ArrayList<Cell> move(Cell[][] squares, int x, int y) 
    {
        possibleMoves.clear();
        /*button to up and right to left*/
        int i = x-1; int j = y-1;
        while((i>=0 && j>=0) && squares[i][j].getPiece()==null)
        {
            possibleMoves.add(squares[i][j]);
            i--;j--;
        }
        if(i>=0 && j>=0 && squares[i][j].getPiece()!=null && getColor()!=squares[i][j].getPiece().getColor())
        {
            possibleMoves.add(squares[i][j]);
        }

        /*button to up and left to right*/
        i = x-1; j = y+1;
        while((i>=0 && j<=7) && squares[i][j].getPiece()==null)
        {
            possibleMoves.add(squares[i][j]);
            i--;j++;
        }
        if((i>=0 && j<=7) && squares[i][j].getPiece()!=null && getColor()!=squares[i][j].getPiece().getColor())
        {
            possibleMoves.add(squares[i][j]);
        }
        
        /*up to botton and left to right*/
        i = x+1; j = y+1;
        while((i<=7 && j<=7) && squares[i][j].getPiece()==null)
        {
            possibleMoves.add(squares[i][j]);
            i++;j++;
        }
        if(i<=7 && j<=7 && squares[i][j].getPiece()!=null && getColor()!=squares[i][j].getPiece().getColor())
        {
            possibleMoves.add(squares[i][j]);
        }
        
        /*button to up and left to right*/
        i = x+1; j = y-1;
        while((i<=7 && j>=0) && squares[i][j].getPiece()==null)
        {
            possibleMoves.add(squares[i][j]);
            j--;i++;
        }
        System.out.println("i = "+ i);
        System.out.println("j = "+ j);        
        if((i<=7 && j>=0) && squares[i][j].getPiece()!=null && getColor()!=squares[i][j].getPiece().getColor())
        {
            possibleMoves.add(squares[i][j]);
        }
        
        return possibleMoves;
    }
    
}
