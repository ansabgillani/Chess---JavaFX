
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sheikh Hashir
 */
public class UpdatePacket implements Serializable{
    

    int currentPlayer;

    public UpdatePacket(Cell[][] fields, int currentPlayer) {
        ChessBoard.squares = fields;
        this.currentPlayer = currentPlayer;
    }

    public Cell[][] getFields() {
        return ChessBoard.squares;
    }

    public void setFields(Cell[][] fields) {
        ChessBoard.squares = fields;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
    
}
