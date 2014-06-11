package chess.pieces;

import java.util.HashSet;
import java.util.Set;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * A base class for chess pieces
 */
public abstract class Piece {
    private final Player owner;

    protected Piece(Player owner) {
        this.owner = owner;
    }

    public char getIdentifier() {
        char id = getIdentifyingCharacter();
        if (owner.equals(Player.White)) {
            return Character.toLowerCase(id);
        } else {
            return Character.toUpperCase(id);
        }
    }

    public Player getOwner() {
        return owner;
    }

    protected abstract char getIdentifyingCharacter();
    
    /**
     * List all possible moves from this position
     * @param pos
     * @param game
     * @return set
     */
    public Set<Position> listAllPosssibleMoves(Position pos, GameState game){
    	Set<Position> set = getNextPositions(pos, game);
    	set.remove(null);
    	return set;
    }
    
    /**
     * Default get next positions.
     * @param pos
     * @param game
     * @return set
     */
    protected Set<Position> getNextPositions(Position pos, GameState game){
    	return new HashSet<Position>();
    }
    
    /**
     * Get one possible move
     * @param origin
     * @param rowPace
     * @param colPace
     * @param game
     * @return a position or null ( if I have a piece on this new position, or this new position is out of boundary)
     */
    protected Position getOnePosition(Position origin, int rowPace, int colPace, GameState game){
        
        int i = origin.getRow() + rowPace;
        char j = (char) (origin.getColumn() + colPace);
        
        if(i>=Position.MIN_ROW && i<=Position.MAX_ROW && j>=Position.MIN_COLUMN && j<=Position.MAX_COLUMN){
        	Position pos = new Position(j, i);
            if (canMoveTo(pos, game)) // empty or opponents
            	return pos; 
        }
        
        return null;
    }
    
    /**
     * Get one direction of possible moves
     * @param origin
     * @param rowPace
     * @param colPace
     * @param gameState
     * @return a set of position or null ( if out of boundary)
     */
    protected Set<Position> getOneDirectionalPositions(Position origin, int rowPace, int colPace, GameState game){
        
        Set<Position> options = new HashSet<Position>();
        
        int i = origin.getRow() + rowPace;
        char j = (char) (origin.getColumn() + colPace);
        
        Position pos = null;
        
        while(i>=Position.MIN_ROW && i<=Position.MAX_ROW && j>=Position.MIN_COLUMN && j<=Position.MAX_COLUMN){
        	pos = new Position((char)j, i);
        	
        	//if the position is open, keep going
        	//if the position is not open => if i have a piece on it, return set, if not, add this one and return set
        	if (!isPositionOpen(pos, game)){ // not open
        		if (canMoveTo(pos, game)) // opponent's
        			options.add(pos);
        		return options;
        	}
            options.add(pos);
            i = i+ rowPace;
            j = (char) (j+ colPace);
        }
        
        return options;
    }
    
    /**
     * Check whether the pos is open
     * @param pos
     * @param game
     * @return
     */
    protected boolean isPositionOpen(Position pos, GameState game){
    	return game.getPieceAt(pos) == null;
    }

    /**
     * Check if the pos is open or if the pos is taken by my opponent
     * @param pos
     * @param game
     * @return false only on where there's a piece of mime on it already
     */
    protected boolean canMoveTo(Position pos, GameState game){
    	if (isPositionOpen(pos, game)) return true;
    	
    	Piece piece = game.getPieceAt(pos);
    	return !piece.getOwner().equals(owner); // mime 
    			
    }
}