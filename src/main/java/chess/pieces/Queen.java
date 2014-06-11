package chess.pieces;

import java.util.HashSet;
import java.util.Set;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * The Queen
 */
public class Queen extends Piece{
    public Queen(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'q';
    }
    
    
    /**
     * This method should retrieve the possible positions of a piece on a chess board.
     */
    @Override
    protected Set<Position> getNextPositions(Position origin, GameState game) {
        
        Set<Position> options = new HashSet<Position>();
        
        options.addAll(getOneDirectionalPositions(origin, -1, -1, game));
        options.addAll(getOneDirectionalPositions(origin, +1, -1, game));
        options.addAll(getOneDirectionalPositions(origin, -1, +1, game));
        options.addAll(getOneDirectionalPositions(origin, +1, +1, game));
        
        options.addAll(getOneDirectionalPositions(origin, 0, -1, game));
        options.addAll(getOneDirectionalPositions(origin, 0, 1, game));
        options.addAll(getOneDirectionalPositions(origin, -1, 0, game));
        options.addAll(getOneDirectionalPositions(origin, 1, 0, game));
        
        
        return options;
        
    }
    
}
