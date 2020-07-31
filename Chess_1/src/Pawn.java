
import java.util.ArrayList;


public class Pawn extends Piece
{
    ArrayList<Cell> possibleMoves = new ArrayList<Cell>();
    public Pawn(int color, String path, String id)
    {
        setColor(color);
        setPath(path);
        setId(id);
    }

    public ArrayList<Cell> move(Cell state[][], int x, int y) throws NullPointerException
    {
        possibleMoves.clear();
        if(getColor()==0)
        {
            if(x == 0)
            {
                return possibleMoves;
            }
            if(state[x-1][y].getPiece()==null)
            {
                System.out.println("h");
                possibleMoves.add(state[x-1][y]);
                if(x==6)
                {
                   possibleMoves.add(state[x-2][y]);                    
                }
            }
            if( (y>0) && (state[x-1][y-1].getPiece()!=null) && (state[x-1][y-1].getPiece().getColor()!=this.getColor()))
            {                
                possibleMoves.add(state[x-1][y-1]);                                    
            }
            if(y<7 && state[x-1][y+1].getPiece()!=null && state[x-1][y+1].getPiece().getColor()!=this.getColor())
            {
                possibleMoves.add(state[x-1][y+1]);
            }
        }
        else
        {
            if(x == 7)
            {
                return possibleMoves;
            }
            if(state[x+1][y].getPiece()==null)
            {
                possibleMoves.add(state[x+1][y]);
                if(x==1 && state[x+2][y].getPiece()==null)
                {
                   possibleMoves.add(state[x+2][y]);                    
                }
            }
            if(y>0 && state[x+1][y-1].getPiece()!=null && state[x+1][y-1].getPiece().getColor()!=this.getColor())
            {
                possibleMoves.add(state[x+1][y-1]);                                    
            }
            if(y<7 && state[x+1][y+1].getPiece()!=null && state[x+1][y+1].getPiece().getColor()!=this.getColor())
            {
                possibleMoves.add(state[x+1][y+1]);
            }
        }
        return possibleMoves;
    }
    
}
