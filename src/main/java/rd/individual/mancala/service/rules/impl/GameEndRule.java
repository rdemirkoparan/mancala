package rd.individual.mancala.service.rules.impl;

import rd.individual.mancala.domain.Game;
import rd.individual.mancala.service.rules.RuleApplier;

public class GameEndRule implements RuleApplier {

    private static final int FIRST_START = 0;
    private static final int FIRST_HOUSE = 6;
    private static final int SECOND_START = 7;
    private static final int SECOND_HOUSE = 13;

    @Override
    public void applyRule(Game game) {
        if (sum(game.getBoard().getPits(), FIRST_START, FIRST_HOUSE) == 0) {
            sumOpponent(game, SECOND_START, SECOND_HOUSE);
            findWinner(game);
        } else if (sum(game.getBoard().getPits(), SECOND_START, SECOND_HOUSE) == 0) {
            sumOpponent(game, FIRST_START, FIRST_HOUSE);
            findWinner(game);
        }
    }

    private void findWinner(Game game) {
        if (game.getBoard().getPits()[FIRST_HOUSE] > game.getBoard().getPits()[SECOND_HOUSE]) {
            game.getBoard().setWinner(game.getBoard().getPlayer());
        } else {
            if(game.getBoard().getPlayer().equals("1")){
                game.getBoard().setWinner("2");
            } else {
                game.getBoard().setWinner("1");
            }
        }
    }

    private void sumOpponent(Game game, int start, int end) {
        for (int i = start; i < end; i++) {
            game.getBoard().getPits()[end] += game.getBoard().getPits()[i];
            game.getBoard().getPits()[i] = 0;
        }
    }

    private int sum(int[] pits, int start, int end) {
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += pits[i];
        }
        return sum;
    }
}
