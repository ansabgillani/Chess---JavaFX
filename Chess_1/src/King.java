
import java.util.ArrayList;

public class King extends Piece
{
    public int x,y;
    ArrayList<Cell> possibleMoves = new ArrayList<Cell>();
    public King(int color, String path, String id, int x, int y)
    {
        setColor(color);
        setPath(path);
        setId(id);
        setX(x);
        setY(y);
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
    @Override
    public ArrayList<Cell> move(Cell[][] squares, int x, int y) 
    {
        possibleMoves.clear();
        int i = x-1; int j=y-1;
        for(;i<x+2;i++)
        {
            for(j=y-1;j<y+2;j++)
            {
                if(i>=0 && j>=0 && i<=7 && j<=7)
                {
                    if(i==x && j==y)
                    {
                        continue;
                    }
                    else
                    {
                        if(squares[i][j].getPiece()==null)
                        {
                            possibleMoves.add(squares[i][j]); //System.out.println("i= "+i);System.out.println("j= "+j);
                        }
                        else if(squares[i][j].getPiece()!=null && squares[i][j].getPiece().getColor()!=getColor())
                        {    
                            possibleMoves.add(squares[i][j]); //System.out.println("i= "+i);System.out.println("j= "+j);
                        }
                    }
                }
                System.out.println("i= "+i);System.out.println("j= "+j);
            }
        }
        return possibleMoves;
    }
    public boolean isBownVerticalDanger(Cell [][] squares)
    {
        int i=this.x+1,j=this.y;
        while(i<=7)
        {
            if(checkDanger(squares, i, j)==true)
            {
                return true;
            }
            i++;
        }
        return false;
    }
    public boolean isUpVerticalDanger(Cell [][] squares)
    {
        int i=this.x-1,j=this.y;
        while(i>=0)
        {
            if(checkDanger(squares, i, j)==true)
            {
                return true;
            }
            i--;
        }
        return false;
    }
    public boolean isLeftHorizontalDanger(Cell [][] squares)
    {
        int i=this.x,j=this.y-1;
        while(j>=0)
        {
            if(checkDanger(squares, i, j)==true)
                return true;
            j--;
        }
        return false;
    }
    public boolean isRightHorizontalDanger(Cell [][] squares)
    {
        int i=this.x,j=this.y+1;
        while(j<=7)
        {
            if(checkDanger(squares, i, j)==true)
            {
                return true;
            }
            j++;
        }
        return false;
    }
    public boolean checkDanger(Cell [][] squares, int i, int j)
    {
        if(squares[i][j].getPiece()==null)  return false;
        else if(squares[i][j].getPiece()!=null && squares[i][j].getPiece().getColor()!=this.getColor())
        {
            if(squares[i][j].getPiece() instanceof Rook || squares[i][j].getPiece() instanceof Queen)
                return true;
            else
                return false;
        }
        else if(squares[i][j].getPiece().getColor() == getColor())
            return false;
        return false;
    }
    
    /*diagonals*/
    public boolean isPrimaryDiagonalDanger(Cell [][] squares)
    {
        int i=this.x-1,j=this.y-1;
        while(j>=0 && i>=0)
        {
            if(checkDangerDiagonal(squares, i, j)==true)
            {
                System.out.println("1");
                return true;
            }
            j--;i--;
        }
        
        i=this.x+1;j=this.y+1;
        while(j<=7 && i<=7)
        {
            if(checkDangerDiagonal(squares, i, j)==true)
            {
                return true;
            }
            j++;i++;
        }        
        return false;
    }
    public boolean isSecondaryDiagonalDanger(Cell [][] squares)
    {
        int i=this.x-1,j=this.y+1;
        while(i>=0 && j<=7)
        {
            if(checkDangerDiagonal(squares, i, j)==true)
            {
                return true;
            }
            j++;i--;
        }
        
        i=this.x+1;j=this.y-1;
        while(j>=0 && i<=7)
        {
            if(checkDangerDiagonal(squares, i, j)==true)
            {
                return true;
            }
            j--;i++;
        }        
        return false;
    }
    public boolean checkDangerDiagonal(Cell [][] squares, int i, int j)
    {
        System.out.println("i= "+i+" j= "+j);
        if(squares[i][j].getPiece()==null) { return false; }
        else if(squares[i][j].getPiece()!=null && squares[i][j].getPiece().getColor()!=this.getColor())
        {
            if(squares[i][j].getPiece() instanceof Bishop || squares[i][j].getPiece() instanceof Queen)
                return true;
            else
                return false;
        }
        else if(squares[i][j].getPiece().getColor() == getColor())
            return false;
        return false;
    }
    public boolean isKnightDanger(Cell [][] squares)
    {
        int posx[]={x+1,x+1,x+2,x+2,x-1,x-1,x-2,x-2};
        int posy[]={y-2,y+2,y-1,y+1,y-2,y+2,y-1,y+1};
        for(int i=0;i<8;i++)
        {
            if((posx[i]>=0&&posx[i]<8&&posy[i]>=0&&posy[i]<8))
            {
                if(squares[posx[i]][posy[i]].getPiece()!=null && squares[posx[i]][posy[i]].getPiece().getColor()!=this.getColor() && (squares[posx[i]][posy[i]].getPiece() instanceof Knight))
                {
                        return true;
                }
            }
        }
        return false;
    }
    public boolean isPawnDanger(Cell [][] squares)
    {
		//Checking for attack from the Pawn of opposite color
        int pox[]={x+1,x+1,x+1,x,x,x-1,x-1,x-1};
        int poy[]={y-1,y+1,y,y+1,y-1,y+1,y-1,y};
        {
            for(int i=0;i<8;i++)
            {
                if((pox[i]>=0&&pox[i]<8&&poy[i]>=0&&poy[i]<8))
                {
                    if(squares[pox[i]][poy[i]].getPiece()!=null && squares[pox[i]][poy[i]].getPiece().getColor()!=this.getColor() && (squares[pox[i]][poy[i]].getPiece() instanceof King))
                    {
                            return true;
                    }
                }
            }
        }
        if(getColor()==0)
        {
            if(x>0 && y>0 && squares[x-1][y-1].getPiece()!=null && squares[x-1][y-1].getPiece().getColor()==1 && (squares[x-1][y-1].getPiece() instanceof Pawn))
                    return true;
            if(x>0 && y<7 && squares[x-1][y+1].getPiece()!=null && squares[x-1][y+1].getPiece().getColor()==1 && (squares[x-1][y+1].getPiece() instanceof Pawn))
                    return true;
        }
        else
        {
            if(x<7 && y>0 && squares[x+1][y-1].getPiece()!=null && squares[x+1][y-1].getPiece().getColor()==0 && (squares[x+1][y-1].getPiece() instanceof Pawn))
                    return true;
            if(x<7 && y<7 && squares[x+1][y+1].getPiece()!=null && squares[x+1][y+1].getPiece().getColor()==0 && (squares[x+1][y+1].getPiece() instanceof Pawn))
                    return true;
        }
        return false;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
public boolean isindanger(Cell state[][])
    {
		
		//Checking for attack from left,right,up and down
    	for(int i=x+1;i<8;i++)
    	{
    		if(state[i][y].getPiece()==null)
    			continue;
    		else if(state[i][y].getPiece().getColor()==this.getColor())
    			break;
    		else
    		{
    			if ((state[i][y].getPiece() instanceof Rook) || (state[i][y].getPiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}
    	for(int i=x-1;i>=0;i--)
    	{
    		if(state[i][y].getPiece()==null)
    			continue;
    		else if(state[i][y].getPiece().getColor()==this.getColor())
    			break;
    		else
    		{
    			if ((state[i][y].getPiece() instanceof Rook) || (state[i][y].getPiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}
    	for(int i=y+1;i<8;i++)
    	{
    		if(state[x][i].getPiece()==null)
    			continue;
    		else if(state[x][i].getPiece().getColor()==this.getColor())
    			break;
    		else
    		{
    			if ((state[x][i].getPiece() instanceof Rook) || (state[x][i].getPiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}
    	for(int i=y-1;i>=0;i--)
    	{
    		if(state[x][i].getPiece()==null)
    			continue;
    		else if(state[x][i].getPiece().getColor()==this.getColor())
    			break;
    		else
    		{
    			if ((state[x][i].getPiece() instanceof Rook) || (state[x][i].getPiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}
    	
    	//checking for attack from diagonal direction
    	int tempx=x+1,tempy=y-1;
		while(tempx<8&&tempy>=0)
		{
			if(state[tempx][tempy].getPiece()==null)
			{
				tempx++;
				tempy--;
			}
			else if(state[tempx][tempy].getPiece().getColor()==this.getColor())
				break;
			else
			{
				if (state[tempx][tempy].getPiece() instanceof Bishop || state[tempx][tempy].getPiece() instanceof Queen)
    				return true;
    			else
    				break;
			}
		}
		tempx=x-1;tempy=y+1;
		while(tempx>=0&&tempy<8)
		{
			if(state[tempx][tempy].getPiece()==null)
			{
				tempx--;
				tempy++;
			}
			else if(state[tempx][tempy].getPiece().getColor()==this.getColor())
				break;
			else
			{
				if (state[tempx][tempy].getPiece() instanceof Bishop || state[tempx][tempy].getPiece() instanceof Queen)
    				return true;
    			else
    				break;
			}
		}
		tempx=x-1;tempy=y-1;
		while(tempx>=0&&tempy>=0)
		{
			if(state[tempx][tempy].getPiece()==null)
			{
				tempx--;
				tempy--;
			}
			else if(state[tempx][tempy].getPiece().getColor()==this.getColor())
				break;
			else
			{
				if (state[tempx][tempy].getPiece() instanceof Bishop || state[tempx][tempy].getPiece() instanceof Queen)
    				return true;
    			else
    				break;
			}
		}
		tempx=x+1;tempy=y+1;
		while(tempx<8&&tempy<8)
		{
			if(state[tempx][tempy].getPiece()==null)
			{
				tempx++;
				tempy++;
			}
			else if(state[tempx][tempy].getPiece().getColor()==this.getColor())
				break;
			else
			{
				if (state[tempx][tempy].getPiece() instanceof Bishop || state[tempx][tempy].getPiece() instanceof Queen)
    				return true;
    			else
    				break;
			}
		}
		
		//Checking for attack from the Knight of opposite color
		int posx[]={x+1,x+1,x+2,x+2,x-1,x-1,x-2,x-2};
		int posy[]={y-2,y+2,y-1,y+1,y-2,y+2,y-1,y+1};
		for(int i=0;i<8;i++)
			if((posx[i]>=0&&posx[i]<8&&posy[i]>=0&&posy[i]<8))
				if(state[posx[i]][posy[i]].getPiece()!=null && state[posx[i]][posy[i]].getPiece().getColor()!=this.getColor() && (state[posx[i]][posy[i]].getPiece() instanceof Knight))
				{
					return true;
				}
		
		
		//Checking for attack from the Pawn of opposite color
		int pox[]={x+1,x+1,x+1,x,x,x-1,x-1,x-1};
		int poy[]={y-1,y+1,y,y+1,y-1,y+1,y-1,y};
		{
			for(int i=0;i<8;i++)
				if((pox[i]>=0&&pox[i]<8&&poy[i]>=0&&poy[i]<8))
					if(state[pox[i]][poy[i]].getPiece()!=null && state[pox[i]][poy[i]].getPiece().getColor()!=this.getColor() && (state[pox[i]][poy[i]].getPiece() instanceof King))
					{
						return true;
					}
		}
		if(getColor()==0)
		{
			if(x>0&&y>0&&state[x-1][y-1].getPiece()!=null&&state[x-1][y-1].getPiece().getColor()==1&&(state[x-1][y-1].getPiece() instanceof Pawn))
				return true;
			if(x>0&&y<7&&state[x-1][y+1].getPiece()!=null&&state[x-1][y+1].getPiece().getColor()==1&&(state[x-1][y+1].getPiece() instanceof Pawn))
				return true;
		}
		else
		{
			if(x<7&&y>0&&state[x+1][y-1].getPiece()!=null&&state[x+1][y-1].getPiece().getColor()==0&&(state[x+1][y-1].getPiece() instanceof Pawn))
				return true;
			if(x<7&&y<7&&state[x+1][y+1].getPiece()!=null&&state[x+1][y+1].getPiece().getColor()==0&&(state[x+1][y+1].getPiece() instanceof Pawn))
				return true;
		}
    	return false;
    }    
}
