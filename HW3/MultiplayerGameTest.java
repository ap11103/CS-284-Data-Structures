package hw3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MultiplayerGameTest {

	@Test
	void testSize() {
		MultiplayerGame test = new MultiplayerGame(2);
		test.addGamePiece(0, "harry", 0);
		test.addGamePiece(1, "hehe", 4);
		assertEquals(test.size(), 2);
	}

	@Test
	void testAddGamePiece() {
		assertThrows(IllegalArgumentException.class, () -> {
			MultiplayerGame test = new MultiplayerGame(4);
			test.addGamePiece(1, "cara", 11);
			test.addGamePiece(1, "cara", 18);
		});
		
	}
	
	
	@Test
	void testHasGamePiece() {
		MultiplayerGame test = new MultiplayerGame(2);
		test.addGamePiece(0, "cara", 11);
		test.addGamePiece(1, "jon", 9);
		assertTrue(test.hasGamePiece("cara"));
	}
	
	
	@Test
	void testRemoveAllPieces() {
		MultiplayerGame test = new MultiplayerGame(2);
		test.addGamePiece(0, "cara", 11);
		test.addGamePiece(1, "jon", 9);
		test.removeAllGamePieces(1);
		assertEquals(1, test.size());
	}
	
	@Test
	void testIncreaseStrength() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			MultiplayerGame test = new MultiplayerGame(2);
			test.addGamePiece(0, "cara", 11);
			test.addGamePiece(1, "jon", 9);
			test.increaseStrength(3,2);
		});
		
	}
	
	@Test
	void testRemoveGamePiece() {
		MultiplayerGame test = new MultiplayerGame(2);
		test.addGamePiece(0, "cara", 11);
		test.addGamePiece(1, "jon", 9);
		test.removeGamePiece(1, "jon");
		assertEquals(1, test.size());
	}
	
	@Test
	void testToString() {
		MultiplayerGame test = new MultiplayerGame(1);
		String eq = "Player0:\n";
		assertEquals(eq, test.toString());
	}
	
	/**
	 * @Test
	void testInitializeTurnTracker() {
		MultiplayerGame test = new MultiplayerGame(2);
		test.addGamePiece(1, "cara", 11);
		test.addGamePiece(2, "jon", 9);
		test.initializeTurnTracker();
		
	}
	
	@Test
	void testNextPlayer() {
		MultiplayerGame test = new MultiplayerGame(2);
		test.addGamePiece(1, "cara", 11);
		test.addGamePiece(2, "jon", 9);
		test.nextPlayer();
		
	}
	
	@Test
	void testNextEntity() {
		MultiplayerGame test = new MultiplayerGame(2);
		test.addGamePiece(1, "cara", 11);
		test.addGamePiece(2, "jon", 9);
		test.nextEntity();
	}
	
	@Test
	void testPrevPlayer() {
		MultiplayerGame test = new MultiplayerGame(2);
		test.addGamePiece(1, "cara", 11);
		test.addGamePiece(2, "jon", 9);
		test.prevPlayer();
	}
	
	@Test
	void testCurrentEntityToString() {
		MultiplayerGame test = new MultiplayerGame(2);
		test.addGamePiece(1, "cara", 11);
		test.addGamePiece(2, "jon", 9);
		test.currentEntityToString();
		
	}
	 */

}
