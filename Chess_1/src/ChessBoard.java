import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ChessBoard extends JFrame implements MouseListener
{
    static Cell [][] squares;
    Cell press, now_press;
    public JPanel panel;
    JLayeredPane layeredPane;
    JLabel chessPiece;
    Piece piece;
    Container content;
    ArrayList<Cell> possibleMoves = new ArrayList<Cell>();
    JTextField textField;
    boolean Game_Win = false;
    static int turn = 0;
    Game game;
    private JPanel wdetails=new JPanel(new GridLayout(3,3));
    private JPanel bdetails=new JPanel(new GridLayout(3,3));    
    private static Rook wr01,wr02,br01,br02;
    private static Knight wk01,wk02,bk01,bk02;
    private static Bishop wb01,wb02,bb01,bb02;
    private static Pawn wp[],bp[];    
    private static Queen wq,bq;
    private static King wk,bk;
    boolean isClient;
    RW_Client client;
    RW_Server server;
    public ChessBoard(RW_Client client) throws NullPointerException, IOException 
    {
        this.client = client;
        isClient = true;
        board();
    }
    public ChessBoard(RW_Server server) throws NullPointerException, IOException 
    {
        this.server = server;
        isClient = false;
        board();
    }
    public ChessBoard() throws NullPointerException, IOException 
    {
        board();
    }
    
    
    public void board() throws NullPointerException, IOException
    {
        panel = new JPanel(new GridLayout(9,9));
        textField = new JTextField("YOUR TURN...!", 150);
        textField.setBounds(85, 590, 500, 60);
        textField.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 25));
        textField.setForeground(Color.RED);
        textField.setHorizontalAlignment(JTextField.CENTER);  // Text alignment
//        textField.setToolTipText("YOUR TURN...!");
        textField.setEditable(false);
        textField.setBorder(null);
        //panel.add(cp);
        this.add(textField);
        //cp.add(textField);
        //cp.add(textField);
        //this.add(cp);
        bp = new Pawn[8];
        wp = new Pawn[8];
        squares =  new Cell[8][8];
        panel.setBounds(100,1020,800,800);

	panel.setBorder(BorderFactory.createLoweredBevelBorder());        
        content =  getContentPane();
        content.setBackground(Color.blue);
        content.setLayout(new BorderLayout());
        
        /*Rook initialization*/
        wr01 = new Rook(0,"E:\\STUDY\\5th Semester\\Web Engineering\\Web Project\\Chess\\src\\White_Rook.png","WR01");
        wr02 = new Rook(0,"E:\\STUDY\\5th Semester\\Web Engineering\\Web Project\\Chess\\src\\White_Rook.png","WR02");
        br01 = new Rook(1,"E:\\STUDY\\5th Semester\\Web Engineering\\Web Project\\Chess\\src\\Black_Rook.png","BR01");        
        br02 = new Rook(1,"E:\\STUDY\\5th Semester\\Web Engineering\\Web Project\\Chess\\src\\Black_Rook.png","BR01");        

        /*Bishop initialization*/        
        wb01 = new Bishop(0,"E:\\STUDY\\5th Semester\\Web Engineering\\Web Project\\Chess\\src\\White_Bishop.png","WB01");
        wb02 = new Bishop(0,"E:\\STUDY\\5th Semester\\Web Engineering\\Web Project\\Chess\\src\\White_Bishop.png","WB02");
        bb01 = new Bishop(1,"E:\\STUDY\\5th Semester\\Web Engineering\\Web Project\\Chess\\src\\Black_Bishop.png","BB01");
        bb02 = new Bishop(1,"E:\\STUDY\\5th Semester\\Web Engineering\\Web Project\\Chess\\src\\Black_Bishop.png","BB02");

        /*Knight initialization*/
        wk01 = new Knight(0,"E:\\STUDY\\5th Semester\\Web Engineering\\Web Project\\Chess\\src\\White_Knight.png","WK01");
        wk02 = new Knight(0,"E:\\STUDY\\5th Semester\\Web Engineering\\Web Project\\Chess\\src\\White_Knight.png","WK02");        
        bk01 = new Knight(1,"E:\\STUDY\\5th Semester\\Web Engineering\\Web Project\\Chess\\src\\Black_Knight.png","BK01");
        bk02 = new Knight(1,"E:\\STUDY\\5th Semester\\Web Engineering\\Web Project\\Chess\\src\\Black_Knight.png","BK02");
        
        /*Queen initialization*/        
        wq = new Queen(0,"E:\\STUDY\\5th Semester\\Web Engineering\\Web Project\\Chess\\src\\White_Queen.png","WQ");
        bq = new Queen(1,"E:\\STUDY\\5th Semester\\Web Engineering\\Web Project\\Chess\\src\\Black_Queen.png","BQ");
        
        /*Pawn initialization*/        
        for(int i=0;i<8;i++)
        {
            wp[i] = new Pawn(0,"E:\\STUDY\\5th Semester\\Web Engineering\\Web Project\\Chess\\src\\White_Pawn.png","WP0"+(i+1));
            bp[i] = new Pawn(1,"E:\\STUDY\\5th Semester\\Web Engineering\\Web Project\\Chess\\src\\Black_Pawn.png","BP0"+(i+1));
        }
        
        /*King initailization*/
        wk = new King(0,"E:\\STUDY\\5th Semester\\Web Engineering\\Web Project\\Chess\\src\\White_King.png","WK",7,3);
        bk = new King(1,"E:\\STUDY\\5th Semester\\Web Engineering\\Web Project\\Chess\\src\\Black_King.png","BK",0,6);
        Cell cell;
        for(int i=0; i<8; i++)
        {
            for(int j=0; j<8; j++)
            {
                piece= null;
                /*adding rook to board*/
                //if(i==0 && j==0) piece = br01;
                //if(i==0 && j==7) piece = br02;
                if(i==3 && j==0) piece = wr01;
                //if(i==7 && j==7) piece = wr02;                
                /*adding bishop to board*/
                //if(i==0 && j==2) piece = bb01;
                //if(i==0 && j==5) piece = bb02;
                //if(i==7 && j==2) piece = wb01;
                //if(i==7 && j==5) piece = wb02;
                /*adding knight to board*/
                //if(i==0 && j==1) piece = bk01;
                //if(i==0 && j==6) piece = bk02;
                //if(i==7 && j==1) piece = wk01;
                //if(i==7 && j==6) piece = wk02;
                /*adding queen to board*/
                //if(i==0 && j==4) piece = bq;
                //if(i==7 && j==4) piece = wq;                
                /*adding King to board*/
                if(i==0 && j==6) piece = bk;
                if(i==7 && j==3) piece = wk;                
                if(i==1)
                {
                    piece = bp[i];
                }
                else if(i==6)
                {
                    piece = wp[j];
                }
                cell =  new Cell(i,j,piece);
                cell.addMouseListener(this);
                panel.add(cell);
                squares[i][j] = cell;
            }
            this.add(panel);
        }
    }
    public ArrayList<Cell> filterPossibilities(ArrayList<Cell> possibleMoves, Cell press) throws NullPointerException, IOException, CloneNotSupportedException
    {
        ArrayList<Cell> newPossibilities = new ArrayList<Cell>();
        Cell[][] virtualBoard = new Cell[8][8];
        int x ,y;
        King king;
    	ListIterator<Cell> it = possibleMoves.listIterator();
        while(it.hasNext())
        {
            for(int i=0;i<8;i++)
            {
                for(int j=0;j<8;j++)
                {
                    virtualBoard[i][j]=new Cell(squares[i][j]);
                }
            }
            Cell cell = it.next();
            if(virtualBoard[cell.x][cell.y].getPiece()!=null)
            {
                virtualBoard[cell.x][cell.y].removePiece();
            }
            virtualBoard[cell.x][cell.y].setPiece(virtualBoard[press.x][press.y].getPiece());
            king = getKing(turn);
            x = king.getX();
            y = king.getY();
            if(virtualBoard[cell.x][cell.y].getPiece() instanceof King)
            {
                ((King)(virtualBoard[cell.x][cell.y].getPiece())).setX(cell.x);
                ((King)(virtualBoard[cell.x][cell.y].getPiece())).setY(cell.y);
                x = cell.x;
                y = cell.y;
            }
            virtualBoard[press.x][press.y].removePiece();
            if(((King)(virtualBoard[x][y]).getPiece()).isindanger(virtualBoard)==false)
            {
                newPossibilities.add(cell);
            }
        }
        return newPossibilities;
    }
    public boolean willKingBeInDanger(Cell press, Cell cell) throws NullPointerException, IOException, CloneNotSupportedException
    {

        ArrayList<Cell> newPossibilities = new ArrayList<Cell>();
        Cell[][] virtualBoard = new Cell[8][8];
        int x ,y;
        King king;
        
            for(int i=0;i<8;i++)
            {
                for(int j=0;j<8;j++)
                {
                    virtualBoard[i][j]=new Cell(squares[i][j]);
                }
            }
            if(virtualBoard[cell.x][cell.y].getPiece()!=null)
            {
                virtualBoard[cell.x][cell.y].removePiece();
            }
            virtualBoard[cell.x][cell.y].setPiece(virtualBoard[press.x][press.y].getPiece());
            if(virtualBoard[cell.x][cell.y].getPiece() instanceof King)
            {
                ((King)(virtualBoard[cell.x][cell.y].getPiece())).setX(cell.x);
                ((King)(virtualBoard[cell.x][cell.y].getPiece())).setY(cell.y);
            }
            virtualBoard[press.x][press.y].removePiece();
            if (((King)(virtualBoard[getKing(turn).getX()][getKing(turn).getY()].getPiece())).isindanger(virtualBoard)==true)
            {
                return true;
            }
            else
            {
                return false;
            }
    }
    public ArrayList<Cell> confirmCheckMate(ArrayList<Cell> possibleMoves, Cell press, int color) throws NullPointerException, IOException, CloneNotSupportedException
    {
        System.out.println("confirming ");
        ArrayList<Cell> remainingPossibilities = new ArrayList<Cell>();
        Cell[][] virtualBoard = new Cell[8][8];
        int x ,y;
        King king;
    	ListIterator<Cell> it = possibleMoves.listIterator();        
        
        for(int i=0;i<possibleMoves.size();i++)
            {
                Cell temp = possibleMoves.get(i);
                System.out.println("king for mate moves before checking");
                System.out.println(temp.x);
                System.out.println(temp.y);
            }
        
        while (it.hasNext())
        {
            for(int i=0;i<8;i++)
            {
                for(int j=0;j<8;j++)
                {
                    virtualBoard[i][j]=new Cell(squares[i][j]);
                }
            }
            Cell cell = it.next();
            if(virtualBoard[cell.x][cell.y].getPiece()!=null)
            {
                virtualBoard[cell.x][cell.y].removePiece();
            }
            virtualBoard[cell.x][cell.y].setPiece(virtualBoard[press.x][press.y].getPiece());
            king = getKing(color);
            x = king.getX();
            y = king.getY();
            if(virtualBoard[cell.x][cell.y].getPiece() instanceof King)
            {
                ((King)(virtualBoard[cell.x][cell.y].getPiece())).setX(cell.x);
                ((King)(virtualBoard[cell.x][cell.y].getPiece())).setY(cell.y);
                x = cell.x;
                y = cell.y;
            }
            virtualBoard[press.x][press.y].removePiece();
            if(((King)(virtualBoard[x][y]).getPiece()).isindanger(virtualBoard)==false)
            {
                remainingPossibilities.add(cell);
            }
            for(int i=0;i<remainingPossibilities.size();i++)
            {
                Cell temp = remainingPossibilities.get(i);
                System.out.println("king for mate moves");
                System.out.println(temp.x);
                System.out.println(temp.y);
            }
        }
        return remainingPossibilities;
    }    
    public boolean isCheckMate(int color) throws NullPointerException, IOException, CloneNotSupportedException
    {
        ArrayList<Cell> remainingPossibilities = new ArrayList<Cell>();
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                
                if(squares[i][j].getPiece()!=null && squares[i][j].getPiece().getColor()==color)
                {
                    remainingPossibilities.clear();
                    remainingPossibilities = squares[i][j].getPiece().move(squares, i, j);
                    
                    remainingPossibilities = confirmCheckMate(remainingPossibilities, squares[i][j], color);
                    if(remainingPossibilities.size()!=0)
                    {
                        return false;
                    }
                }
            }
        }
        Game_Win = true;
        return true;
    }
    public void mouseClicked(MouseEvent e){ } 
    public void mouseEntered(MouseEvent e){ } 
    public void mouseExited(MouseEvent e) { } 
    public void mousePressed(MouseEvent e)
    {
        press = (Cell) e.getSource();
        if(now_press == null)
        {
            if(press.getPiece()!=null)
            {
                if(press.getPiece().getColor()!=turn)
                {
                    return;
                }
                try {
                    now_press=press;
                    press.piece_select();
                    
                    possibleMoves.clear();
                    possibleMoves = press.getPiece().move(squares,press.x,press.y);
                    if(press.getPiece() instanceof King)
                        possibleMoves = filterPossibilities(possibleMoves, press);
                    else
                    {
                        if(squares[getKing(turn).getX()][getKing(turn).getY()].isCheck()==true)
                        {
                            possibleMoves = new ArrayList<Cell>(filterPossibilities(possibleMoves, press));                                
                        }
                        else if(willKingBeInDanger(press, possibleMoves.get(0))==true && possibleMoves.isEmpty()==false)
                        {
                            possibleMoves.clear();
                        }
                    }
                    highlightSelectedArea(possibleMoves);
                    } catch (NullPointerException ex) { Logger.getLogger(ChessBoard.class.getName()).log(Level.SEVERE, null, ex);} catch (IOException ex) { Logger.getLogger(ChessBoard.class.getName()).log(Level.SEVERE, null, ex); } catch (CloneNotSupportedException ex) { Logger.getLogger(ChessBoard.class.getName()).log(Level.SEVERE, null, ex); }
            }
        }
        else
        {
            if(now_press.x == press.x && now_press.y == press.y)
            {
                press.deselect();
                unHighlightSelectedArea(possibleMoves);
                possibleMoves.clear();
                now_press = null;
            }
            else if(press.getPiece()==null || press.getPiece().getColor()!=now_press.getPiece().getColor())
            {
                if(press.isValidMove())
                {
                    if(press.getPiece()!=null)
                        press.removePiece();
                     
                    try 
                    {
                        press.setPiece(now_press.getPiece());
                    } catch (NullPointerException ex) {Logger.getLogger(ChessBoard.class.getName()).log(Level.SEVERE, null, ex);} catch (IOException ex) {Logger.getLogger(ChessBoard.class.getName()).log(Level.SEVERE, null, ex);}
                    if(now_press.isCheck()==true)
                    {
                        now_press.removeCheck();
                    }
                    now_press.removePiece();
                    
                    
                    if(getKing(turn^1).isindanger(squares)==true)
                    {
                        squares[getKing(turn^1).getX()][getKing(turn^1).getY()].setCheck();
                        try {
                            if(isCheckMate(getKing(turn^1).getColor())==true)
                            {
                                now_press.deselect();
                                if(now_press.getPiece()!=null)
                                {
                                    now_press.removePiece();
                                }
                                now_press = null;
                                resetBoard();
                            }
                        } catch (NullPointerException ex) {Logger.getLogger(ChessBoard.class.getName()).log(Level.SEVERE, null, ex);} catch (IOException ex) {Logger.getLogger(ChessBoard.class.getName()).log(Level.SEVERE, null, ex); } catch (CloneNotSupportedException ex) { Logger.getLogger(ChessBoard.class.getName()).log(Level.SEVERE, null, ex); }
                    }
                    if(getKing(turn).isindanger(squares)==false)
                    {
                        squares[getKing(turn).getX()][getKing(turn).getY()].removeCheck();                        
                    }
                    if(press.getPiece() instanceof King)
                    {
                        ((King)(press.getPiece())).setX(press.x);
                        ((King)(press.getPiece())).setY(press.y);                        
                    }
                    if(now_press!=null)
                    {
                        now_press.deselect();
                        now_press = null;
                    }
                    unHighlightSelectedArea(possibleMoves);                    
                    possibleMoves.clear();
                    repaint();
                    changeTurn();
                }
            }
            else if(now_press.getPiece().getColor() == press.getPiece().getColor())
            {
                if(press.getPiece()!=null)
                {
                    if(press.getPiece().getColor()!=turn)
                    {
                        return;
                    }
                    else
                    {
                        now_press.deselect();
                        unHighlightSelectedArea(possibleMoves);
                        possibleMoves.clear();
                        try {
                            now_press=press;
                            press.piece_select();
                            possibleMoves = press.getPiece().move(squares,press.x,press.y);
                            if(press.getPiece() instanceof King)
                                possibleMoves = filterPossibilities(possibleMoves, press);
                            else
                            {
                                if(squares[getKing(turn).getX()][getKing(turn).getY()].isCheck()==true)
                                {
                                    possibleMoves = filterPossibilities(possibleMoves, press);                                
                                }
                                else if(willKingBeInDanger(press, possibleMoves.get(0)) && possibleMoves.isEmpty()==false)
                                {
                                    possibleMoves.clear();
                                }
                            }
                            } catch (NullPointerException ex) { Logger.getLogger(ChessBoard.class.getName()).log(Level.SEVERE, null, ex);} catch (IOException ex) { Logger.getLogger(ChessBoard.class.getName()).log(Level.SEVERE, null, ex); } catch (CloneNotSupportedException ex) { Logger.getLogger(ChessBoard.class.getName()).log(Level.SEVERE, null, ex); }                        
                         highlightSelectedArea(possibleMoves);
                         if(isClient) {
                         
                         }
                    }
                }
            }
        }
    }
    public void unHighlightSelectedArea(ArrayList<Cell> possibleMoves)
    {
        for (Cell num : possibleMoves) 
        { 		      
            num.removePossibleDestinations();
        }
    }    
    public void highlightSelectedArea(ArrayList<Cell> possibleMoves)
    {
        System.out.println("in1");
        for (Cell num : possibleMoves) { 		      
            num.setPossibleDestination();
        }
    }
    public void changeTurn()
    {        
        if(Game_Win==false)
        {
            if(turn == 0)
            {
                    turn = 1;
                    textField.setText("Friends Move");

            }
            else{
                    turn = 0;
                    textField.setText("Your Turn");


            }
        }
    }
    public King getKing(int turn)
    {
        if(turn==0)
        {
            return wk;
        }
        else{
            return bk;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    public void resetBoard() throws NullPointerException, IOException, CloneNotSupportedException
    {

        squares[getKing(turn^1).getX()][getKing(turn^1).getY()].removeCheck();        
        
                       Cell cell;
        
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                if(squares[i][j].getPiece()!=null)
                {
                    squares[i][j].removePiece();                        
                    
                }
                squares[i][j].removeMouseListener(this);
            }
        }
        repaint();
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {

                if(i==0 && j==0) squares[i][j].setPiece(br01);
                if(i==0 && j==7) squares[i][j].setPiece(br02);
                if(i==7 && j==0) squares[i][j].setPiece(wr01);
                if(i==7 && j==7) squares[i][j].setPiece(wr02);                
                /*adding bishop to board*/
                if(i==0 && j==2) squares[i][j].setPiece(bb01);
                if(i==0 && j==5) squares[i][j].setPiece(bb02);
                if(i==7 && j==2) squares[i][j].setPiece(wb01);
                if(i==7 && j==5) squares[i][j].setPiece(wb02);
                /*adding knight to board*/
                if(i==0 && j==1) squares[i][j].setPiece(bk01);
                if(i==0 && j==6) squares[i][j].setPiece(bk02);
                if(i==7 && j==1) squares[i][j].setPiece(wk01);
                if(i==7 && j==6) squares[i][j].setPiece(wk02);
                /*adding queen to board*/
                if(i==0 && j==4) squares[i][j].setPiece(bq);
                if(i==7 && j==4) squares[i][j].setPiece(wq);  
                /*adding King to board*/
                if(i==0 && j==3) squares[i][j].setPiece(bk);
                if(i==7 && j==3) squares[i][j].setPiece(wk);             
                if(i==1)
                {
                    squares[i][j].setPiece(bp[i]);
                }
                else if(i==6)
                {
                    squares[i][j].setPiece(wp[i]);
                }
            }
        }
        if(turn == 0)
            textField.setText("YOU WIN");
        else
            textField.setText("FRIEND WINS");
    }
}