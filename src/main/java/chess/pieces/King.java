package chess.pieces;

import java.util.HashSet;
import java.util.Set;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * The King class
 */
public class King extends Piece {

	private static final int[][] movingPaces = {{-1, -1},{-1, 0},{-1, 1},{0, -1},{0, 1},{1, -1},{1, 0},{1, 1}};
	
    public King(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'k';
    }
    
    /**
     * This method should retrieve the possible positions of a piece on a chess board.
     */
    @Override
    protected Set<Position> getNextPositions(Position origin, GameState game) {
        
        Set<Position> options = new HashSet<Position>();
        
        for (int i = 0; i< movingPaces.length; i++ ){
        	options.add(getOnePosition(origin, movingPaces[i][0], movingPaces[i][1], game));
        }        
        return options;
        
    }
    
}
