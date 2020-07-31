
import java.util.ArrayList;
public class Rook extends Piece
{
    ArrayList<Cell> possibleMoves =  new ArrayList<Cell>();
    Rook(int color, String path, String id)
    {
        setColor(color);
        setPath(path);
        setId(id);
    }
    @Override
    public ArrayList<Cell> move(Cell[][] squares, int x, int y) 
    {
        possibleMoves.clear();
        /*bottom to top*/
        int i =x-1;
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
        int j=y-1;
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
