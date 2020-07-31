
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
public class Knight extends Piece
{
    ArrayList<Cell> possibleMoves = new ArrayList<Cell>();
    public Knight(int color, String path, String id)
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
        if(x-2>=0 && y-1>=0) 
        {
            if(squares[x-2][y-1].getPiece()!=null && getColor()!=squares[x-2][y-1].getPiece().getColor())
                possibleMoves.add(squares[x-2][y-1]);
            else if(squares[x-2][y-1].getPiece()==null)
                possibleMoves.add(squares[x-2][y-1]);
        }
        if(x-1>=0 && y-2>=0) 
        {
            if(squares[x-1][y-2].getPiece()!=null && getColor()!=squares[x-1][y-2].getPiece().getColor())
                possibleMoves.add(squares[x-1][y-2]);
            else if(squares[x-1][y-2].getPiece()==null)
                possibleMoves.add(squares[x-1][y-2]);
        }
        /*button to up and left to right*/
        if(x-2>=0 && y+1<=7)
        {
            if(squares[x-2][y+1].getPiece()!=null && getColor()!=squares[x-2][y+1].getPiece().getColor())
                possibleMoves.add(squares[x-2][y+1]);
            else if(squares[x-2][y+1].getPiece()==null)
                possibleMoves.add(squares[x-2][y+1]);
        }
        if(x-1>=0 && y+2<=7) 
        {
            if(squares[x-1][y+2].getPiece()!=null && getColor()!=squares[x-1][y+2].getPiece().getColor())
                possibleMoves.add(squares[x-1][y+2]);
            else if(squares[x-1][y+2].getPiece()==null)
                possibleMoves.add(squares[x-1][y+2]);
        }        

        /*up to botton and left to right*/
        if(x+1<=7 && y+2<=7)
        {
            if(squares[x+1][y+2].getPiece()!=null && getColor()!=squares[x+1][y+2].getPiece().getColor())
                possibleMoves.add(squares[x+1][y+2]);
            else if(squares[x+1][y+2].getPiece()==null)
                possibleMoves.add(squares[x+1][y+2]);
        }
        if(x+2<=7 && y+1<=7) 
        {
            if(squares[x+2][y+1].getPiece()!=null && getColor()!=squares[x+2][y+1].getPiece().getColor())
                possibleMoves.add(squares[x+2][y+1]);
            else if(squares[x+2][y+1].getPiece()==null)
                possibleMoves.add(squares[x+2][y+1]);
        }                
        /*up to botton right to left*/
        if(x+2<=7 && y-1>=0)
        {
            if(squares[x+2][y-1].getPiece()!=null && getColor()!=squares[x+2][y-1].getPiece().getColor())
                possibleMoves.add(squares[x+2][y-1]);
            else if(squares[x+2][y-1].getPiece()==null)
                possibleMoves.add(squares[x+2][y-1]);
        }
        if(x+1<=7 && y-2>=0) 
        {
            if(squares[x+1][y-2].getPiece()!=null && getColor()!=squares[x+1][y-2].getPiece().getColor())
                possibleMoves.add(squares[x+1][y-2]);
            else if(squares[x+1][y-2].getPiece()==null)
                possibleMoves.add(squares[x+1][y-2]);
        }                
        return possibleMoves;
    }
    
}
