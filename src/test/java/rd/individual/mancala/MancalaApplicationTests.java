package rd.individual.mancala;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rd.individual.mancala.domain.Board;
import rd.individual.mancala.service.GameExecutor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MancalaApplicationTests {

	/**
	 * Game Play
	 * The player who begins with the first move picks up all the stones in any of his
	 * own six pits, and sows the stones on to the right, one in each of the following
	 * pits, including his own big pit. No stones are put in the opponents' big pit. If the
	 * player's last stone lands in his own big pit, he gets another turn. This can be
	 * repeated several times before it's the other player's turn.
	 */
	@Test
	public void gamePlayTest(){
		//init default board
		Board board = new Board();
		int[] pits = new int[14];
		for (int i = 0; i< pits.length; i++){
			pits[i] = 6;
		}
		pits[6]=0;
		pits[13]=0;
		board.setPits(pits);

		//last stone on own house check
		board.setCurrent("Pit1_1");
		board.setPlayer("1");
		GameExecutor.getInstance().applyRules(board);

		Assert.assertEquals(0, board.getPits()[0]);
		Assert.assertEquals(7, board.getPits()[1]);
		Assert.assertEquals(7, board.getPits()[2]);
		Assert.assertEquals(7, board.getPits()[3]);
		Assert.assertEquals(7, board.getPits()[4]);
		Assert.assertEquals(7, board.getPits()[5]);
		Assert.assertEquals(1, board.getPits()[6]);

		//second turn still on same player check
		Assert.assertEquals("1", board.getNextPlayer());

		//skip opponents house check
		board.setCurrent("Pit1_5");
		board.setPlayer(board.getNextPlayer());
		GameExecutor.getInstance().applyRules(board);

		//opponent gain the turn
		Assert.assertEquals("2", board.getNextPlayer());

		board.setCurrent("Pit2_6");
		board.setPlayer(board.getNextPlayer());
		swapPits(board);
		GameExecutor.getInstance().applyRules(board);

		//own turn again, last stone skip opponent home and come back
		board.setCurrent("Pit1_6");
		board.setPlayer(board.getNextPlayer());
		swapPits(board);
		GameExecutor.getInstance().applyRules(board);

		Assert.assertEquals(1, board.getPits()[13]);
	}

	/**
	 * Capturing Stones
	 * During the game the pits are emptied on both sides. Always when the last stone
	 * lands in an own empty pit, the player captures his own stone and all stones in the
	 * opposite pit (the other player’s pit) and puts them in his own (big or little?) pit.
	 */
	@Test
	public void captureStonesTest(){
		//capture opponents stones check
		Board board = new Board();
		int[] pits = new int[14];
		for (int i = 0; i< pits.length; i++){
			pits[i] = 6;
		}
		pits[0]=1;
		pits[1]=0;
		pits[6]=0;
		pits[11]=5;
		pits[13]=0;
		board.setPits(pits);

		board.setCurrent("Pit1_1");
		board.setPlayer("1");
		GameExecutor.getInstance().applyRules(board);

		//last stone on own house check
		Assert.assertEquals(0, board.getPits()[0]);
		Assert.assertEquals(0, board.getPits()[1]);
		Assert.assertEquals(6, board.getPits()[6]);
	}

	/**
	 * The Game Ends
	 * The game is over as soon as one of the sides runs out of stones. The player who
	 * still has stones in his pits keeps them and puts them in his big pit. The winner of
	 * the game is the player who has the most stones in his big pit.
	 */
	@Test
	public void gameEndsTest(){
		Board board = new Board();
		int[] pits = new int[14];
		for (int i = 0; i< pits.length; i++){
			pits[i] = 6;
		}
		for (int i = 0; i< 6; i++){
			pits[i] = 0;
		}
		pits[5]=1;
		pits[6]=0;
		pits[13]=2;
		board.setPits(pits);

		//no stone left check
		board.setCurrent("Pit1_6");
		board.setPlayer("1");
		GameExecutor.getInstance().applyRules(board);

		Assert.assertEquals(0, board.getPits()[5]);

		//correct player wins check
		Assert.assertEquals(1, board.getPits()[6]);
		Assert.assertEquals(38, board.getPits()[13]);
		Assert.assertEquals("1", board.getPlayer());
		Assert.assertEquals("2", board.getWinner());
	}

	private void swapPits(Board board) {
		int otherplayerindex = 7;

		for (int i = 0; i< 7; i++){
			int tmp = board.getPits()[i];
			board.getPits()[i] = board.getPits()[i + otherplayerindex];
			board.getPits()[i + otherplayerindex] = tmp;
		}
	}
}
