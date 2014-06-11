package chess.pieces;

import java.util.HashSet;
import java.util.Set;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * The 'Bishop' class
 */
public class Bishop extends Piece {
    public Bishop(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'b';
    }
    
    /**
     * This method should retrieve the possible positions of a piece on a chess board.
     */
    @Override
    protected Set<Position> getNextPositions(Position origin, GameState gameState) {
        
        Set<Position> options = new HashSet<Position>();
        
        options.addAll(getOneDirectionalPositions(origin, -1, -1, gameState));
        options.addAll(getOneDirectionalPositions(origin, +1, -1, gameState));
        options.addAll(getOneDirectionalPositions(origin, -1, +1, gameState));
        options.addAll(getOneDirectionalPositions(origin, +1, +1, gameState));
        
        
        return options;
        
    }
    
    
    
}
