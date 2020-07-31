
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
public class Queen extends Piece
{
    ArrayList<Cell> possibleMoves = new ArrayList<Cell>();
    public Queen(int color, String path, String id)
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
        /*bottom to top*/
        i =x-1;
        while(i>=0 && squares[i][y].getPiece()==null)
        {
            possibleMoves.add(squares[i][y]);
            i--;
        }
        if(i>=0 && squares[i][y]!=null && getColor()!=squares[i][y].getPiece().getColor())
        {
            possibleMoves.add(squares[i][y]);                    
        }            
        
        /*right to left*/
        j=y-1;
        while(j >=0 && squares[x][j].getPiece()==null)
        {
            System.out.println("black");
            possibleMoves.add(squares[x][j]);
            j--;
        }
        if(j>=0 && squares[x][j]!=null && getColor()!=squares[x][j].getPiece().getColor())
        {
            possibleMoves.add(squares[x][j]);                    
        }

        /*top to bottom*/
        i=x+1;
        while(i <=7 && squares[i][y].getPiece()==null)
        {
            possibleMoves.add(squares[i][y]);
            i++;
        }
        if(i<=7 && squares[i][y]!=null && getColor()!=squares[i][y].getPiece().getColor())
        {
            possibleMoves.add(squares[i][y]);                
        }
        
        /*left to right*/
        j=y+1;
        while( j <=7 && squares[x][j].getPiece()==null)
        {
            possibleMoves.add(squares[x][j]);
            j++;
        }
        if(j<=7 && squares[x][j]!=null && getColor()!=squares[x][j].getPiece().getColor())
        {
            possibleMoves.add(squares[x][j]);                
        }
        
        return possibleMoves;
    }
    
}
