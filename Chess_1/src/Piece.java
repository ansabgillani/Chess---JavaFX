
import java.util.ArrayList;


public abstract class Piece implements Cloneable
{
    private int color;
    private String path;
    private String id = null;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Piece getcopy() throws CloneNotSupportedException
    {
            return (Piece) this.clone();
    }
    public abstract ArrayList<Cell> move(Cell squares[][],int x,int y);  //Abstract Function. Must be overridden    

}
