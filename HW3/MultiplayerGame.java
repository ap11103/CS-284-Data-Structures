package hw3;

public class MultiplayerGame {
	
	//data fields
	private GameEntity turnTracker;
	private GameEntity[] index;
	
	/**
	 * Creates a new Multiplayer game with n players
	 * creates a new GamePlayer 
	 * @param n is the number of players in the new game, and has an array with size of n
	 */
	public MultiplayerGame(int n) {
		index = new GameEntity[n];
		if (n == 0) {
			throw new IllegalArgumentException("Invalid Argument");
		}
		if (n == 1) {
			index[0] = new GamePlayer(index[0], index[0], 0);
		}
		
		else {
			for (int i = 0; i < n; i++) {
				index[i] = new GamePlayer(null, null, i);
			}
			for (int i = 0; i < n; i++) {
				if (i != (n-1)) {
					index[i].next = index[i+1];
				}
				if (i != 0) {
					index[i].prev = index[i-1];
				}
				else {
					index[n-1].next = index[0];
					index[0].prev = index[n-1];
				}
			}
		}			
		
	}
	
	/**
	 * @return the amount of GamePieces in the play
	 */
	public int size() {
		int counter = 0;
		GameEntity current = index[0].next;
		while(current != index[0]) {
			if(current.isGamePlayer() == false) {
				counter ++;	
			}
			current = current.next;
		}
		
		return counter;
	}
	
	/**adds a GamePiece owned by a specified player of the given strength to game
	 * @param playerID of the GamePlayer
	 * @param name: 
	 * @param strength: strength of the game piece assigned to a player
	 */
	public void addGamePiece(int playerId, String name, int strength) {
		if (playerId > (index.length - 1) || (playerId < 0)) {
			throw new IllegalArgumentException("addGamePiece: no such player");
		}
		GameEntity current = index[playerId];
		current = current.next;
		while(current.isGamePlayer() == false) {
			if(current.getName().equals(name)) {
				throw new IllegalArgumentException("addGamePiece: duplicate identity");
			}
			current = current.next;
		}
		GameEntity piece = new GamePiece(null, null, name, strength);
		
		piece.next = current;
		piece.prev = current.prev;
		current.prev = piece;
		piece.prev.next = piece;
		
	}
	
	
	/**removes GamePiece owned by specific player by the given name
	 * @param playerId: playerId of the specific player
	 * @param name: name of the player that piece will be removed from
	 */
	public void removeGamePiece(int playerId, String name) {
		if (playerId >= (index.length) || (playerId < 0)) {
			throw new IllegalArgumentException("removeGamePiece: no such player");
		}
		int count = 0;
		GameEntity current = index[playerId];
		while (current.next.isGamePlayer() == false) {
			if (current.next.getName().equals(name)) {
				current.next.next.prev = current;
				current.next = current.next.next;
				count++;
				break;
				
			}
			current = current.next;
		}
		if (count == 0) {
			throw new IllegalArgumentException("removeGamePiece: entity does not exist");
		}
	}
	
	
	/**checks to see if any player has a GamePiece of the given name
	 * @param name of the player to check if it has the GamePiece
	 */
	public boolean hasGamePiece(String name) {
		for (int i = 0; i < index.length; i++) {
			GameEntity current = index[i].next;
			System.out.println(current);
			//GameEntity current = index[0]
			while (current.isGamePlayer()==false) {
				//counter  = index[0]
				if(current.getName().equals(name)) {
					return true;
				}
				else {
					current = current.next;
				}
			}
		}
		return false;
	}
	
	
	/**Removes all the game pieces owned by specific player
	 * @param PlayerId is the specific player whose GamePieces are removed
	 */
	public void removeAllGamePieces(int playerId) {
		if((playerId >= index.length) ||(playerId < 0) ) {
			throw new IllegalArgumentException("removeAllGamePieces: no such Player");
		}
		
		if (playerId == 0) {
			GameEntity current = index[playerId];
			current.next = current;
			current.prev = current;
		}
		
		else if((playerId + 1) == index.length) {
			GameEntity current = index[playerId];
			current.next = index[0];
			index[0].prev = current;
		}
		
		else {
			GameEntity current = index[playerId];
			current.next = index[playerId + 1];
			index[playerId + 1].prev = current;
			
		}
	}
	
	
	/**Increases strength of all GamePieces owned by a player n
	 * 
	 * @param playerId: PlayerId of the specific player
	 * @param n: GamePieces owned by a specific player n
	 */
	public void increaseStrength(int playerId, int n) {
		if (playerId > index.length - 1) {
			throw new IllegalArgumentException("removeGamePiece: no such Player");
		}
		GameEntity current = index[playerId];
		while(current.next.isGamePlayer() == false) {
			((GamePiece)(current.prev)).updateStrength(n);
			current = current.next;
		}
	}
	
	/**Produces a string representation of the MultiplayerGame
	 * @return output the string representation of first player and then their pieces
	 * and then other players followed in sequence
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < index.length; i++) {
			GameEntity current = index[i];
			str.append(index[i].getName());
			while(current.isGamePlayer() == false) {
				str.append(" ");
				str.append(current.toString());
				current = current.next;
				str.append("\n");
			}
			str.append(":");
			str.append("\n");
		}
		
		return str.toString();
	}
	
	
	/**sets the turnTracker to the first player
	 */
	public void initializeTurnTracker() {
		turnTracker = index[0];
	}
	
	/**moves the turnTracker to the next player
	 */
	public void nextPlayer() {
		if (turnTracker.isGamePlayer()) {
			GamePlayer current = (GamePlayer)turnTracker;
			int counter = current.getPlayerId();
			if(counter == index.length - 1) {
				turnTracker = index[0];
			}
			else {
				turnTracker = index[counter - 1];
			}
			
		}
		else {
			GameEntity current = turnTracker;
			while(current.isGamePlayer() == false) {
				current = current.next;
			}
			turnTracker = current;
		}
	}
	
	
	/**Moves the turnTracker to the next GameEntity
	 * could be either GamePlayer or GamePiece
	 */
	public void nextEntity() {
		GameEntity current = turnTracker;
		turnTracker = current.next;
	}
	
	
	/**Backtracks the turnTracker to the previous GamePlayer
	 */
	public void prevPlayer() {
		if(turnTracker.isGamePlayer()) {
			if (((GamePlayer)turnTracker).getPlayerId() == 0){
				turnTracker = index[index.length - 1];
			}
			turnTracker = index[((GamePlayer)turnTracker).getPlayerId() - 1];
		}
		else {
			GameEntity current = turnTracker;
			while(current.isGamePlayer() == false) {
				current = current.prev;
			}
			turnTracker = current.prev;
		}
	}
	
	/**@return the string representation of the current entity
	 * pointed by turnTracker
	 */
	public String currentEntityToString() {
		return turnTracker.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	}

}
