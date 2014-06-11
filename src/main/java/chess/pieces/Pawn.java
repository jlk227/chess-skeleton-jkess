package chess.pieces;

import java.util.HashSet;
import java.util.Set;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * The Pawn
 */
public class Pawn extends Piece {
	private int movingRowPace = 0;
	
    public Pawn(Player owner) {
        super(owner);
        if (owner.equals(Player.White)) {
        	movingRowPace = 1;
        }else {
        	movingRowPace = -1;
        }
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'p';
    }
    
    
    /**
     * This method should retrieve the possible positions of a piece on a chess board.
     */    
    @Override
    protected Set<Position> getNextPositions(Position origin, GameState game) {
        
        Set<Position> options = new HashSet<Position>();
        
        if (isFirstMove(origin))
        	options.add(getOnePosition(origin, movingRowPace*2, 0, game));
        
        options.add(getOnePosition(origin, movingRowPace, 0, game));
        
        // move by capturing
        options.add(getOnePosition(origin, movingRowPace, -1, game));
        options.add(getOnePosition(origin, movingRowPace, 1, game));
        return options;
        
    }
  
    /**
     * Get one possible move
     * @param origin
     * @param rowPace
     * @param colPace
     * @param game
     * @return a position or null ( if I have a piece on this new position, or this new position is out of boundary, or it's empty postion for front left or front right)
     */
    @Override
    protected Position getOnePosition(Position origin, int rowPace, int colPace, GameState game){
        //moving straight 
    	if (colPace == 0)
    		return super.getOnePosition(origin, rowPace, colPace, game);
    	
    	// moving by capture.
        int i = origin.getRow() + rowPace;
        char j = (char) (origin.getColumn() + colPace);
        
        if(i>=Position.MIN_ROW && i<=Position.MAX_ROW && j>=Position.MIN_COLUMN && j<=Position.MAX_COLUMN){
        	Position pos = new Position(j, i);
            // is not open and I can't move To ==>it's my opponent's
            if (!isPositionOpen(pos, game) && canMoveTo(pos, game))
            	return null;
        }
        
        return null;
    }
    
    /**
     * if this this my first move
     * @param origin
     * @return
     */
    private boolean isFirstMove(Position origin){
    	return (origin.getRow() == 2 || origin.getColumn() ==7);
    }

}
