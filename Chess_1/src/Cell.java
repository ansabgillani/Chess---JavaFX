
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Cell extends JPanel implements Cloneable
{
    int x;
    int y;
    Piece piece;
    private boolean isValidMove;
    private boolean isCheck = false;   
    JLabel content;
    private boolean isSelected;
    public Cell(int x, int y, Piece p) throws NullPointerException, IOException
    {
        this.x = x;
        this.y = y;
        setLayout(new BorderLayout());
        if((this.x+this.y)%2==0)
        {
            setBackground(Color.white);
        }
        else if( (this.x + this.y) % 2 !=0)
        {
            setBackground(new Color(113,198,113));
        }
        if(p!=null)
        {
            setPiece(p);
        }
    }
    public Cell(Cell cell) throws CloneNotSupportedException, NullPointerException, IOException
    {
            this.x=cell.x;
            this.y=cell.y;
            setLayout(new BorderLayout());
            if((x+y)%2==0)
                    setBackground(new Color(113,198,113));
            else
                    setBackground(Color.white);
            if(cell.getPiece()!=null)
            {
                    setPiece(cell.getPiece().getcopy());
            }
            else
                    piece=null;
    }    
    public Piece getPiece()    //Function to access piece of a particular cell
    {
            return this.piece;
    }
    void setPiece(Piece p) throws NullPointerException, IOException
    {
    /*    piece = p;
        BufferedImage image = null;
        File sourceimage = new File(p.getPath());
        image = ImageIO.read(sourceimage);        
        content = new JLabel(new ImageIcon(image));
        this.add(content);
    */
    		piece=p;
		ImageIcon img=new javax.swing.ImageIcon(p.getPath());
		content=new JLabel(img);
		this.add(content);
    }

    public void piece_select()       //Function to mark a cell indicating it's selection
    {
            this.setBorder(BorderFactory.createLineBorder(Color.red,6));
            this.isSelected=true;
    }
    public void setPossibleDestination()     //Function to highlight a cell to indicate that it is a possible valid move
    {
            this.setBorder(BorderFactory.createLineBorder(Color.blue,4));
            this.isValidMove=true;
    }
    public void setPossibleKillDestination()
    {
        this.setBorder(BorderFactory.createLineBorder(Color.red,4));        
    }
    public void deselect()      //Function to delselect the cell
    {
            this.setBorder(null);
            this.isSelected=false;
    }    
    public void removePossibleDestinations()
    {
        this.setBorder(null);
        this.isValidMove = false;
    }
    public boolean isValidMove()
    {
        return this.isValidMove;
    }
    public void removePiece()
    {
		if (piece instanceof King)
		{
			piece=null;
			this.remove(content);
		}
		else
		{

                    piece=null;
			this.remove(content);
		}
                this.repaint();
    }

    public void setCheck() 
    {
        this.setBorder(BorderFactory.createLineBorder(Color.PINK,4));
        isCheck = true;
    }

    public boolean isCheck()
    {
        return this.isCheck;
    }
    void removeCheck() 
    {
        this.setBorder(null);
        isCheck=false;
    }
}
