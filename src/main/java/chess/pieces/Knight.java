package chess.pieces;

import java.util.HashSet;
import java.util.Set;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * The Knight class
 */
public class Knight extends Piece {
	
	private static final int[][] movingPaces = {{-1, -2},{-1, 2},{-2, -1},{-2, 1},{1, -2},{1, 2},{2, -1},{2, 1}};
//	private static final boolean canLeap = true;
	
    public Knight(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'n';
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
